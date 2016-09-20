import java.util.Date;
import java.util.LinkedList;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class SensorReader extends Thread {

	private Boolean roadIsBumpy;
	private EV3TouchSensor sensor;
	private SampleProvider provider;
	private LinkedList<Long> touchInfos = new LinkedList<Long>();
	private Date date = new Date();
	
	
	public SensorReader(Boolean theRoadIsBumpyRightNow) {
		
		roadIsBumpy = theRoadIsBumpyRightNow;
		sensor = new EV3TouchSensor(SensorPort.S1);
		provider = sensor.getTouchMode();
		this.setDaemon(true);
		
	}
	
	public void run() {
		
		while (true) {
			
			float[] sample = new float[provider.sampleSize()];
			provider.fetchSample(sample, 0);
			if (sample[0] > 0.5) {
				touchInfos.add(date.getTime());
			}
			if (touchInfos.size() >= 5) {
				for (; touchInfos.size() > 5; ) {
					touchInfos.removeFirst();
				}
				if (date.getTime() - touchInfos.get(4) < 3000
						&& touchInfos.get(4) - touchInfos.get(0) < 2000) {
					synchronized(roadIsBumpy) {
						roadIsBumpy = true;
					};
					
				}
			}
		}
		
	}
	
}
