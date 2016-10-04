import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class SpeedRegulator extends Thread {

	private Boolean roadIsBumpy;
	private Robot robot;
	
	public SpeedRegulator(Boolean roadIsBumpy, Robot r) {
		
		this.roadIsBumpy = roadIsBumpy;
		robot = r;
		
	}
	
	public void run() {
		
		while (robot.isWorking) {
			synchronized(roadIsBumpy) {	
				/*System.out.println(roadIsBumpy.compareTo(Boolean.TRUE));
				if (roadIsBumpy.compareTo(Boolean.TRUE) == 0) {
					Sound.twoBeeps();
					robot.lowerSpeed();
					LCD.drawString("SLOW DOWN!! you ass", 0, 4);
					try{Thread.sleep(3000);}catch(Exception e){e.printStackTrace();}
					robot.normalSpeed();
					roadIsBumpy = false;
				}*/
				if (roadIsBumpy.compareTo(Boolean.TRUE) == 0 && !robot.isLowerSpeed()) {
					robot.lowerSpeed();
				} else if (roadIsBumpy.compareTo(Boolean.TRUE) != 0) {
					robot.normalSpeed();
				}
			}
		}
	}
	
}
