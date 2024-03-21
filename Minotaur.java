package minotaur;
import robocode.*;
import robocode.util.*;
import java.awt.Color;
import java.util.HashMap;
import java.awt.geom.Point2D;

public class Minotaur extends AdvancedRobot {
    
    private static final double MAX_DISTANCE = 1000;
    private static final double BULLET_POWER = 2;
	private double absoluteBearing;
	private double enemyX;
    private double enemyY;
    private double enemyHeading;
	private double enemyVelocity;
	private double bulletPower;


    public void run() {
        setColors(
				Color.black, 
				Color.black,
				Color.green);
				
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        while (true) {
            turnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
        double enemyX = getX() + e.getDistance() * Math.sin(absoluteBearing);
        double enemyY = getY() + e.getDistance() * Math.cos(absoluteBearing);
        double enemyHeading = e.getHeadingRadians();
        double enemyVelocity = e.getVelocity();
        double bulletPower = Math.min(BULLET_POWER, getEnergy() - 0.1);

        setTurnGunRightRadians(Utils.normalRelativeAngle(absoluteBearing - getGunHeadingRadians()));
        
		setFire(bulletPower);

        double deltaTime = 0;
        double predictedX = enemyX, predictedY = enemyY;
        while ((++deltaTime) * (20.0 - 3.0 * bulletPower) < Point2D.distance(getX(), getY(), predictedX, predictedY)){
            predictedX += Math.sin(enemyHeading) * enemyVelocity;
            predictedY += Math.cos(enemyHeading) * enemyVelocity;
        }

        double theta = Utils.normalAbsoluteAngle(Math.atan2(predictedX - getX(), predictedY - getY()));
        
		setTurnGunRightRadians(Utils.normalRelativeAngle(theta - getGunHeadingRadians()));
        
		setFire(bulletPower);

        if (Math.random() < 0.5) {
            setAhead(MAX_DISTANCE * Math.random());
        } else {
            setBack(MAX_DISTANCE * Math.random());
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
        setBack(100);
    }

    public void onHitWall(HitWallEvent e) {
        setBack(100);
    }
}