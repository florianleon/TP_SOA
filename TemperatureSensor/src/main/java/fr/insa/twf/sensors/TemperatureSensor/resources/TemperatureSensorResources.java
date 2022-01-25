package fr.insa.twf.sensors.TemperatureSensor.resources;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.twf.sensors.TemperatureSensor.model.TemperatureSensorInfos;


@RestController
@RequestMapping("/temperature")
public class TemperatureSensorResources {

	//TODO: A déplacer au bon endroit
	public Double randomValue(int a, int b, double pas) {
		Double randomDouble = a + (Math.random() * (b-a));
		BigDecimal bd = new BigDecimal(randomDouble).setScale(1, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
private ArrayList<TemperatureSensorInfos> sensorsList = new ArrayList<TemperatureSensorInfos>();
	
	private TemperatureSensorInfos getSensor(int id) {
		TemperatureSensorInfos item = null;
		for (TemperatureSensorInfos sensor: sensorsList) {
			int sensorId = sensor.getId();
			if (sensorId == id) {
				item = sensor;
			}
		}
		return item;
	}
	
	@GetMapping("list")
	public ArrayList<Integer> getList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (TemperatureSensorInfos item: sensorsList) {
			list.add(item.getId());
		}
		return list;
	}
	
	@PostMapping("new/{id}/{batiment}/{room}/{position}")
	public boolean addSensor(@PathVariable("id") int id, @PathVariable("batiment") String batiment, @PathVariable("room") String room, @PathVariable("position") int position) {
		TemperatureSensorInfos sensor = new TemperatureSensorInfos(id, batiment, room, position);
		sensorsList.add(sensor);
		return true;
	}
	
	@DeleteMapping("delete/{id}")
	public boolean deleteSensor(@PathVariable("id") int id) {
		boolean status = false;
		for (int i = 0; i < sensorsList.size(); i++) {
			TemperatureSensorInfos item = sensorsList.get(i);
			int sensorId = item.getId();
			if (sensorId == id) {
				sensorsList.remove(i);
				status = true;
				break;
			}
		}
		return status;
	}
	
	@GetMapping("battery/{id}")
	public int getBatteryLevel(@PathVariable("id") int id) {
		int level = 0;
		TemperatureSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			level = sensor.getBattery();
		}
		return level;
	}
	
	@PostMapping("battery/{id}/{level}")
	public int setBatteryLevel(@PathVariable("id") int id, @PathVariable("level") int level) {
		TemperatureSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			sensor.setBattery(level);
		}
		return level;
	}
	
	@GetMapping("/{id}")
	public double getValue(@PathVariable("id") int id) {
		double level = 0.0;
		TemperatureSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			level = sensor.getValue();
		}
		return level;
	}
	
	@PostMapping("/{id}/{level}")
	public int setValue(@PathVariable("id") int id, @PathVariable("level") int level) {
		TemperatureSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			sensor.setValue(level);
		}
		return level;
	}
	
	
	
}
