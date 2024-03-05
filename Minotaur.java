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
			ahead(110);
			turnRight(90);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		fire(2);
	}

	public void onHitByBullet(HitByBulletEvent e) {
		back(50);
	}
	
	public void onHitWall(HitWallEvent e) {
		back(50);
	}	
}