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

	private ArrayList<ACInfos> ACList = new ArrayList<ACInfos>();
	
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
	public String switchClimOn(@PathVariable("id") int id, @PathVariable("temp") int temp) {
		String res = "Error";
		ACInfos clim = getAC(id);
		if (clim != null) {
			clim.setClimOn(true);
			clim.setHeatOn(false);
			clim.setTargetedTemperature(temp);
			res = "Clim " + id + " set to " + temp;
		}
		return res;
	}
	
	@PostMapping("clim/OFF/{id}")
	public String switchClimOff(@PathVariable("id") int id) {
		String res = "Error";
		ACInfos clim = getAC(id);
		if (clim != null) {
			clim.setClimOn(false);
			res = "Clim " + id + " OFF.";
		}
		return res;
	}
	
	@PostMapping("heat/ON/{id}/{temp}")
	public String switchRadiateurOn(@PathVariable("id") int id, @PathVariable("temp") int temp) {
		String res = "Error";
		ACInfos heat = getAC(id);
		if (heat != null) {
			heat.setHeatOn(true);
			heat.setClimOn(false);
			heat.setTargetedTemperature(temp);
			res = "Heat " + id + " set to " + temp;
		}
		return res;
	}
	
	@PostMapping("heat/OFF/{id}")
	public String switchRadiateurOff(@PathVariable("id") int id) {
		String res = "Error";
		ACInfos heat = getAC(id);
		if (heat != null) {
			heat.setHeatOn(false);
			res = "Heat " + id + " OFF.";
		}
		return res;
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
			status = heat.isHeatOn();
		}
		return status;
	}
	
	@PostMapping("new/{id}/{batiment}/{room}/{position}")
	public boolean addAC(@PathVariable("id") int id, @PathVariable("batiment") String batiment, @PathVariable("room") String room, @PathVariable("position") int position) {
		ACInfos AC = new ACInfos(id, batiment, room, position);
		ACList.add(AC);
		return true;
	}
	
	@GetMapping("list")
	public ArrayList<Integer> getACList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (ACInfos ac: ACList) {
			list.add(ac.getId());
		}
		return list;
	}
	
	@DeleteMapping("delete/{id}")
	public boolean deleteAC(@PathVariable("id") int id) {
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
