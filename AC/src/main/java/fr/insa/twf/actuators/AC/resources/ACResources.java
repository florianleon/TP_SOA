package fr.insa.twf.actuators.AC.resources;


import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.twf.actuators.AC.model.ACInfos;

@RestController
@RequestMapping("/AC")
public class ACResources {

	private ArrayList<ACInfos> ACList = null;
	
	private ACInfos getAC(int id) {
		ACInfos item = null;
		for (ACInfos ac: ACList) {
			int ACid = ac.getId();
			if (ACid == id) {
				item = ac;
			}
		}
		return item;
	}
	
	@PostMapping("clim/ON/{id}/{temp}")
	public boolean switchClimOn(@PathVariable("temp") Double temp, @PathVariable("id") int id) {
		boolean status = false;
		ACInfos clim = getAC(id);
		if (clim != null) {
			clim.setHeatOn(false);
			clim.setClimOn(true);
			clim.setTargetedTemperature(temp);
			status = true;
		}
		return status;
	}
	
	@PostMapping("clim/OFF/{id}")
	public boolean switchClimOff(@PathVariable("id") int id) {
		boolean status = false;
		ACInfos clim = getAC(id);
		if (clim != null) {
			clim.setClimOn(false);
			status = true;
		}
		return status;
	}
	
	@PostMapping("heat/ON/{id}/{temp}")
	public boolean switchRadiateurOn(@PathVariable("temp") Double temp, @PathVariable("id") int id) {
		boolean status = false;
		ACInfos heat = getAC(id);
		if (heat != null) {
			heat.setHeatOn(false);
			heat.setClimOn(true);
			heat.setTargetedTemperature(temp);
			status = true;
		}
		return status;
	}
	
	@PostMapping("heat/{id}")
	public boolean switchRadiateurOff(@PathVariable("id") int id) {
		boolean status = false;
		ACInfos heat = getAC(id);
		if (heat != null) {
			heat.setClimOn(false);
			status = true;
		}
		return status;
	}
	
	@GetMapping("clim/{id}")
	public boolean statusClim(@PathVariable("id") int id) {
		boolean status = false;
		ACInfos clim = getAC(id);
		if (clim != null) {
			status = clim.isClimOn();
		}
		return status;
	}

	
	@GetMapping("heat/{id}")
	public boolean statusRadiateur(@PathVariable("id") int id) {
		boolean status = false;
		ACInfos heat = getAC(id);
		if (heat != null) {
			status = heat.isClimOn();
		}
		return status;
	}
	
	@PostMapping("AC/new/{id}/{batiment}/{room}/{position}")
	public boolean addAC(@PathVariable("id") int id, @PathVariable("batiment") String batiment, @PathVariable("room") String room, @PathVariable("position") int position) {
		ACInfos AC = new ACInfos(id, batiment, room, position);
		ACList.add(AC);
		return true;
	}
	
	@DeleteMapping("AC/delete/{id}")
	public boolean addHeat(@PathVariable("id") int id) {
		boolean status = false;
		for (int i = 0; i < ACList.size(); i++) {
			ACInfos item = ACList.get(i);
			int ACid = item.getId();
			if (ACid == id) {
				ACList.remove(i);
				status = true;
				break;
			}
		}
		
		return status;
	}
}
