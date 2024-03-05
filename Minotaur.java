package minotaur;
import minotaur.*;
import robocode.*;
public class Minotaur extends Robot{
	
	public void run() {
		
		// setBodyColor(Color.black);
		// setGunColor(Color.green);
		// setRadarColor(Color.silver);
		// setBulletColor(Color.pink);
		// setScanColor(Color.pink);

		while(true) {
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		fire(1);
	}

	public void onHitByBullet(HitByBulletEvent e) {
		back(10);
	}
	
	public void onHitWall(HitWallEvent e) {
		back(20);
	}	
}
