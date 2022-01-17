package fr.insa.twf.sensors.CO2HumiditySensor.resources;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.twf.sensors.CO2HumiditySensor.model.CO2HumiditySensorInfos;


@RestController
@RequestMapping("/co2Humidity")
public class CO2HumiditySensorResources {

	
	private ArrayList<CO2HumiditySensorInfos> sensorsList = null;
	
	private CO2HumiditySensorInfos getSensor(int id) {
		CO2HumiditySensorInfos item = null;
		for (CO2HumiditySensorInfos sensor: sensorsList) {
			int sensorId = sensor.getId();
			if (sensorId == id) {
				item = sensor;
			}
		}
		return item;
	}
	
	@PostMapping("new/{id}/{batiment}/{room}/{position}")
	public boolean addWindows(@PathVariable("id") int id, @PathVariable("batiment") String batiment, @PathVariable("room") String room, @PathVariable("position") int position) {
		CO2HumiditySensorInfos sensor = new CO2HumiditySensorInfos(id, batiment, room, position);
		sensorsList.add(sensor);
		return true;
	}
	
	@DeleteMapping("delete/{id}")
	public boolean deleteWindows(@PathVariable("id") int id) {
		boolean status = false;
		for (int i = 0; i < sensorsList.size(); i++) {
			CO2HumiditySensorInfos item = sensorsList.get(i);
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
		CO2HumiditySensorInfos sensor = getSensor(id);
		if (sensor != null) {
			level = sensor.getBatterie();
		}
		return level;
	}
	
	@PostMapping("battery/{id}/{level}")
	public int setBatteryLevel(@PathVariable("id") int id, @PathVariable("level") int level) {
		CO2HumiditySensorInfos sensor = getSensor(id);
		if (sensor != null) {
			sensor.setBatterie(level);
		}
		return level;
	}
	
	@GetMapping("co2/{id}")
	public double getCO2(@PathVariable("id") int id) {
		double level = 0.0;
		CO2HumiditySensorInfos sensor = getSensor(id);
		if (sensor != null) {
			level = sensor.getCO2Value();
		}
		return level;
	}
	
	@GetMapping("humidity/{id}")
	public double getHumidity(@PathVariable("id") int id) {
		double level = 0.0;
		CO2HumiditySensorInfos sensor = getSensor(id);
		if (sensor != null) {
			level = sensor.getHumidityValue();
		}
		return level;
	}
	
	@PostMapping("co2/{id}/{level}")
	public int setCO2Level(@PathVariable("id") int id, @PathVariable("level") int level) {
		CO2HumiditySensorInfos sensor = getSensor(id);
		if (sensor != null) {
			sensor.setCO2Value(level);
		}
		return level;
	}
	
	@PostMapping("humidity/{id}/{level}")
	public int setHumidityLevel(@PathVariable("id") int id, @PathVariable("level") int level) {
		CO2HumiditySensorInfos sensor = getSensor(id);
		if (sensor != null) {
			sensor.setHumidityValue(level);
		}
		return level;
	}
	
}
