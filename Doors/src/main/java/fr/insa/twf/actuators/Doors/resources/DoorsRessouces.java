package fr.insa.twf.actuators.Doors.resources;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.twf.actuators.Doors.model.DoorsInfos;


@RestController
@RequestMapping("/doors")
public class DoorsRessouces {
	private ArrayList<DoorsInfos> doorsList = new ArrayList<DoorsInfos>();
	
	private DoorsInfos getDoor(int id) {
		DoorsInfos item = null;
		for (DoorsInfos doors: doorsList) {
			int doorsId = doors.getId();
			if (doorsId == id) {
				item = doors;
			}
		}
		return item;
	}
	
	@GetMapping("list")
	public ArrayList<Integer> getDoorsList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (DoorsInfos item: doorsList) {
			list.add(item.getId());
		}
		return list;
	}
	
	@PostMapping("new/{id}/{batiment}/{room}/{position}")
	public boolean addAlarm(@PathVariable("id") int id, @PathVariable("batiment") String batiment, @PathVariable("room") String room, @PathVariable("position") int position) {
		DoorsInfos alarm = new DoorsInfos(id, batiment, room, position);
		doorsList.add(alarm);
		return true;
	}
	
	@DeleteMapping("delete/{id}")
	public boolean deleteAlarm(@PathVariable("id") int id) {
		boolean status = false;
		for (int i = 0; i < doorsList.size(); i++) {
			DoorsInfos item = doorsList.get(i);
			int ACid = item.getId();
			if (ACid == id) {
				doorsList.remove(i);
				status = true;
				break;
			}
		}
		
		return status;
	}
	
	@GetMapping("{id}")
	public boolean statusAlarm(@PathVariable("id") int id) {
		boolean status = false;
		DoorsInfos door = getDoor(id);
		if (door != null) {
			status = door.isDoorsOpen();
		}
		return status;
	}
	
	@PostMapping("{id}/{doorsOpen}")
	public boolean switchAlarm(@PathVariable("id") int id, @PathVariable("doorsOpen") boolean doorsOpen) {
		boolean status = false;
		DoorsInfos doors = getDoor(id);
		if (doors != null) {
			doors.setDoorsOpen(doorsOpen);
			status = true;
		}
		return status;
	}
}
