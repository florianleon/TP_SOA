package fr.insa.twf.sensors.PresenceSensor.resources;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.twf.sensors.PresenceSensor.model.PresenceSensorInfos;

@RestController
@RequestMapping("/presence")
public class PresenceSensorResources {

	private ArrayList<PresenceSensorInfos> sensorsList = new ArrayList<PresenceSensorInfos>();
	
	private PresenceSensorInfos getSensor(int id) {
		PresenceSensorInfos item = null;
		for (PresenceSensorInfos sensor: sensorsList) {
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
		for (PresenceSensorInfos item: sensorsList) {
			list.add(item.getId());
		}
		return list;
	}
	
	@PostMapping("new/{id}/{batiment}/{room}/{position}")
	public boolean addSensor(@PathVariable("id") int id, @PathVariable("batiment") String batiment, @PathVariable("room") String room, @PathVariable("position") int position) {
		PresenceSensorInfos sensor = new PresenceSensorInfos(id, batiment, room, position);
		sensorsList.add(sensor);
		return true;
	}
	
	@DeleteMapping("delete/{id}")
	public boolean deleteSensor(@PathVariable("id") int id) {
		boolean status = false;
		for (int i = 0; i < sensorsList.size(); i++) {
			PresenceSensorInfos item = sensorsList.get(i);
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
		PresenceSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			level = sensor.getBattery();
		}
		return level;
	}
	
	@PostMapping("battery/{id}/{level}")
	public int setBatteryLevel(@PathVariable("id") int id, @PathVariable("level") int level) {
		PresenceSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			sensor.setBattery(level);
		}
		return level;
	}
	
	@GetMapping("/{id}")
	public boolean getValue(@PathVariable("id") int id) {
		boolean level = false;
		PresenceSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			level = sensor.getValue();
		}
		return level;
	}
	
	@PostMapping("/{id}/{level}")
	public boolean setValue(@PathVariable("id") int id, @PathVariable("level") boolean level) {
		PresenceSensorInfos sensor = getSensor(id);
		if (sensor != null) {
			sensor.setValue(level);
		}
		return true;
	}
}
