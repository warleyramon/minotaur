package minotaur;
import minotaur.*;
import robocode.*;
import java.awt.Color;


public class Minotaur extends AdvancedRobot{
	boolean movingForward;
	int dist = 50;
	double turnGunAmt, angle;

	public void run() {
		
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.green);
		setBulletColor(Color.red);
		setScanColor(Color.green);

		while(true) {
			setAhead(4000);
			movingForward = true;
			setTurnRight(10);
			setTurnGunLeft(80);
			execute();
			
		}
	}
	

	public void onHitWall(HitWallEvent e) {
		reverseDirection();
	}

	public void reverseDirection() {
		if (movingForward) {
			setBack(4000);
			setTurnRight(9);
			setTurnGunLeft(80);
			movingForward = false;
			execute();
		} else {
			setAhead(4000);
			setTurnRight(9);
			setTurnGunLeft(80);
			movingForward = true;
			execute();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		if (e.getDistance() < 50 && getEnergy() > 50) {
			fire(3);
		} 
		else {
			fire(7);
		}
		scan();
	}


	public void onHitByBullet(HitByBulletEvent e) {
		turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

		ahead(dist);
		dist *= -1;
		scan();
	}

	public static double normalRelativeAngleDegrees(double angle) {
		return (angle %= 360) >= 0 ? (angle < 180) ? angle : angle - 360 : (angle >= -180) ? angle : angle + 360;
	}

	public void onHitRobot(HitRobotEvent e) {
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

		turnGunRight(turnGunAmt);
		fire(3);
	}	
	
}