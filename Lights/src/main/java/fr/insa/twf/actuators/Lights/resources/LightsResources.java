package fr.insa.twf.actuators.Lights.resources;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.twf.actuators.Lights.model.LightsInfos;

@RestController
@RequestMapping("/lights")
public class LightsResources {

	private ArrayList<LightsInfos> lightsList = new ArrayList<LightsInfos>();
	
	private LightsInfos getLights(int id) {
		LightsInfos item = null;
		for (LightsInfos light: lightsList) {
			int lightId = light.getId();
			if (lightId == id) {
				item = light;
			}
		}
		return item;
	}
	
	@GetMapping("list")
	public ArrayList<Integer> getLightList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (LightsInfos item: lightsList) {
			list.add(item.getId());
		}
		return list;
	}
	
	@PostMapping("new/{id}/{batiment}/{room}/{position}")
	public boolean addLight(@PathVariable("id") int id, @PathVariable("batiment") String batiment, @PathVariable("room") String room, @PathVariable("position") int position) {
		LightsInfos light = new LightsInfos(id, batiment, room, position);
		lightsList.add(light);
		return true;
	}
	
	@DeleteMapping("delete/{id}")
	public boolean deleteLight(@PathVariable("id") int id) {
		boolean status = false;
		for (int i = 0; i < lightsList.size(); i++) {
			LightsInfos item = lightsList.get(i);
			int lightId = item.getId();
			if (lightId == id) {
				lightsList.remove(i);
				status = true;
				break;
			}
		}
		
		return status;
	}
	
	@GetMapping("{id}")
	public boolean statusLight(@PathVariable("id") int id) {
		boolean status = false;
		LightsInfos light = getLights(id);
		if (light != null) {
			status = light.isLightOn();
		}
		return status;
	}
	
	@PostMapping("{id}/{lightOn}")
	public boolean switchLight(@PathVariable("id") int id, @PathVariable("lightOn") boolean lightOn) {
		boolean status = false;
		LightsInfos light = getLights(id);
		if (light != null) {
			light.setLightOn(lightOn);
			status = true;
		}
		return status;
	}
}
