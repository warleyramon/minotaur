package minotaur;
import minotaur.*;
import robocode.*;
import java.awt.Color;


public class Minotaur extends AdvancedRobot
{
	public void run() {
		
        setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.green);
		setBulletColor(Color.red);
		setScanColor(Color.green);

		while(true) {
			setAhead(100);
			setTurnRight(10);
			setTurnGunLeft(80);
			execute();
		}
	}
	

	public void onScannedRobot(ScannedRobotEvent e) {
		fire(7);
	}

	public void onHitByBullet(HitByBulletEvent e) {
		back(55);
	}
	
	public void onHitWall(HitWallEvent e) {
		back(55);
	}	
}