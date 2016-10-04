import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;

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
					Motor.A.setAcceleration(100);
					Motor.B.setAcceleration(100);
					Motor.A.setSpeed(100);
					Motor.B.setSpeed(100);
					//robot.lowerSpeed();
				} else if (roadIsBumpy.compareTo(Boolean.TRUE) != 0) {
					Motor.A.setAcceleration(300);
					Motor.B.setAcceleration(300);
					Motor.A.setSpeed(300);
					Motor.B.setSpeed(300);
					//robot.normalSpeed();
				}
			}
		}
	}
	
}
