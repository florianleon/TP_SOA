package fr.insa.twf.sensors.TemperatureSensor.resources;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TemperatureSensorResources {

	
	public Double randomValue(int a, int b, double pas) {
		Double randomDouble = a + (Math.random() * (b-a));
		BigDecimal bd = new BigDecimal(randomDouble).setScale(1, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	
	
}
