package fr.insa.twf.sensors.LightSensor.resources;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.twf.sensors.LightSensor.model.LightSensorInfos;

@RestController
@RequestMapping("/light")
public class LightSensorResources {
	
	private ArrayList<LightSensorInfos> sensorsList = null;
	
	private LightSensorInfos getSensor(int id) {
		LightSensorInfos item = null;
		for (LightSensorInfos sensor: sensorsList) {
			int sensorId = sensor.getId();
			if (sensorId == id) {
				item = sensor;
			}
		}
		return item;
	}
	
	@PostMapping("new/{id}/{batiment}/{room}/{position}")
	public boolean addSensor(@PathVariable("id") int id, @PathVariable("batiment") String batiment, @PathVariable("room") String room, @PathVariable("position") int position) {
		LightSensorInfos sensor = new LightSensorInfos(id, batiment, room, position);
		sensorsList.add(sensor);
		return true;
	}
	
	@DeleteMapping("delete/{id}")
	public boolean deleteSensor(@PathVariable("id") int id) {
		boolean status = false;
		for (int i = 0; i < sensorsList.size(); i++) {
			LightSensorInfos item = sensorsList.get(i);
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
		LightSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			level = sensor.getBattery();
		}
		return level;
	}
	
	@PostMapping("battery/{id}/{level}")
	public int setBatteryLevel(@PathVariable("id") int id, @PathVariable("level") int level) {
		LightSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			sensor.setBattery(level);
		}
		return level;
	}
	
	@GetMapping("/{id}")
	public double getValue(@PathVariable("id") int id) {
		double level = 0.0;
		LightSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			level = sensor.getValue();
		}
		return level;
	}
	
	@PostMapping("/{id}/{level}")
	public int setValue(@PathVariable("id") int id, @PathVariable("level") int level) {
		LightSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			sensor.setValue(level);
		}
		return level;
	}

}
