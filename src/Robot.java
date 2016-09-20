import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Robot {
	
	private Boolean roadIsBumpy = new Boolean(false);
	private MovePilot pilot;
	
	
	public Robot() {
		
		SensorReader reader = new SensorReader(roadIsBumpy);
		SpeedRegulator regulator = new SpeedRegulator(roadIsBumpy, this);
		reader.start();
		regulator.start();
		movePilotMethod();

	}

	
	
	public void movePilotMethod() {
		
		Wheel wheelL = WheeledChassis.modelWheel(Motor.A, 43).offset(-60);
		Wheel wheelR = WheeledChassis.modelWheel(Motor.B, 43).offset(60);
	
		Chassis chassis = new WheeledChassis(new Wheel[] { wheelL, wheelR }, WheeledChassis.TYPE_DIFFERENTIAL);
		
		pilot = new MovePilot(chassis);
		  
		pilot.setAngularSpeed(50); 	// degrees per sec
		pilot.setLinearSpeed(80); 	// cm per sec
		pilot.travel(60000);         	// cm
		/*pilot.rotate(80);        	// degree 
		pilot.travel(350);
		pilot.arc(200, 90); 			// radius, degree
		pilot.arc(-100, 360);
		pilot.travel(400);
		pilot.rotate(80);
		pilot.travel(400);
		pilot.arc(200, 90);*/
		
		
		pilot.travel(-80);  			// move backward 
		pilot.rotate(-13);        	// degree 
		pilot.stop();  
		  	
}



	public void lowerSpeed() {
		if (pilot != null) {
			pilot.setAngularSpeed(25);
			pilot.setLinearSpeed(40);
		}
	}
	
	public void normalSpeed() {
		if (pilot != null) {
			pilot.setAngularSpeed(50);
			pilot.setLinearSpeed(80);
		}
	}
}