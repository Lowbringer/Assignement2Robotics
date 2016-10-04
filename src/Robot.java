import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Robot {
	
	private Boolean roadIsBumpy = new Boolean(false);
	private MovePilot pilot;
	
	private boolean lowerSpeed;
	public boolean isWorking;
	
	
	public Robot() {
		
		isWorking = true;
		lowerSpeed = false;
		SensorReader reader = new SensorReader(roadIsBumpy, this);
		SpeedRegulator regulator = new SpeedRegulator(roadIsBumpy, this);
		reader.start();
		regulator.start();
		movePilotMethod();

	}

	
	
	public void movePilotMethod() {
		
		Motor.A.setAcceleration(300);
		Motor.B.setAcceleration(300);
		Motor.A.setSpeed(300);
		Motor.B.setSpeed(300);
		
		Motor.A.forward();
		Motor.B.forward();
		/*Wheel wheelL = WheeledChassis.modelWheel(Motor.A, 43).offset(-60);
		Wheel wheelR = WheeledChassis.modelWheel(Motor.B, 43).offset(60);
	
		Chassis chassis = new WheeledChassis(new Wheel[] { wheelL, wheelR }, WheeledChassis.TYPE_DIFFERENTIAL);
		
		pilot = new MovePilot(chassis);
		  
		pilot.setAngularSpeed(500); 	// degrees per sec
		pilot.setLinearSpeed(160); 	// cm per sec
		//for (int j = 0; j < 10; j++) {
		pilot.travel(1000);
		//}
		         	// cm
		/*pilot.rotate(80);        	// degree 
		pilot.travel(350);
		pilot.arc(200, 90); 			// radius, degree
		pilot.arc(-100, 360);
		pilot.travel(400);
		pilot.rotate(80);
		pilot.travel(400);
		pilot.arc(200, 90);*/
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*
		
		
		pilot.travel(1000);
		lowerSpeed();
		
		//pilot.travel(-80);  			// move backward 
		//pilot.rotate(-13);        	// degree 
		pilot.stop();  */
		isWorking = false;
		  	
}



	public void lowerSpeed() {
		if (pilot != null) {
			lowerSpeed = true;
			Motor.A.setAcceleration(100);
			Motor.B.setAcceleration(100);
			Motor.A.setSpeed(100);
			Motor.B.setSpeed(100);
			//pilot.setAngularSpeed(25);
			//pilot.setLinearSpeed(40);
		}
	}
	
	public void normalSpeed() {
		if (pilot != null) {
			lowerSpeed = false;
			Motor.A.setAcceleration(300);
			Motor.B.setAcceleration(300);
			Motor.A.setSpeed(300);
			Motor.B.setSpeed(300);
			//pilot.setAngularSpeed(100);
			//pilot.setLinearSpeed(160);
		}
	}
	
	public boolean isLowerSpeed() {
		return lowerSpeed;
	}
}
