import lejos.hardware.lcd.LCD;

public class SpeedRegulator extends Thread {

	private Boolean roadIsBumpy;
	private Robot robot;
	
	public SpeedRegulator(Boolean roadIsBumpy, Robot r) {
		
		this.roadIsBumpy = roadIsBumpy;
		robot = r;
		
	}
	
	public void run() {
		
		synchronized(roadIsBumpy) {
			
			robot.lowerSpeed();
			LCD.drawString("SLOW DOWN!! you ass", 0, 4);
			try{Thread.sleep(3000);}catch(Exception e){e.printStackTrace();}
			robot.normalSpeed();
			roadIsBumpy = false;
			
		}
		
	}
	
}
