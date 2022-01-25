package fr.insa.twf.actuators.Windows.resources;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.twf.actuators.Windows.model.WindowsInfos;



@RestController
@RequestMapping("/windows")
public class WindowsRessources {
	
	private ArrayList<WindowsInfos> windowsList = new ArrayList<WindowsInfos>();
	
	private WindowsInfos getWindow(int id) {
		WindowsInfos item = null;
		for (WindowsInfos alarm: windowsList) {
			int windowId = alarm.getId();
			if (windowId == id) {
				item = alarm;
			}
		}
		return item;
	}
	
	@GetMapping("list")
	public ArrayList<Integer> getDoorsList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (WindowsInfos item: windowsList) {
			list.add(item.getId());
		}
		return list;
	}
	
	@PostMapping("new/{id}/{batiment}/{room}/{position}")
	public boolean addWindows(@PathVariable("id") int id, @PathVariable("batiment") String batiment, @PathVariable("room") String room, @PathVariable("position") int position) {
		WindowsInfos alarm = new WindowsInfos(id, batiment, room, position);
		windowsList.add(alarm);
		return true;
	}
	
	@DeleteMapping("delete/{id}")
	public boolean deleteWindows(@PathVariable("id") int id) {
		boolean status = false;
		for (int i = 0; i < windowsList.size(); i++) {
			WindowsInfos item = windowsList.get(i);
			int windowId = item.getId();
			if (windowId == id) {
				windowsList.remove(i);
				status = true;
				break;
			}
		}
		return status;
	}
	
	@GetMapping("{id}")
	public boolean statusWindows(@PathVariable("id") int id) {
		boolean status = false;
		WindowsInfos window = getWindow(id);
		if (window != null) {
			status = window.isWindowsOpen();
		}
		return status;
	}
	
	@PostMapping("{id}/{windowsOpen}")
	public boolean switchWindows(@PathVariable("id") int id, @PathVariable("windowsOpen") boolean windowsOpen) {
		boolean status = false;
		WindowsInfos window = getWindow(id);
		if (window != null) {
			window.setWindowsOpen(windowsOpen);
			status = true;
		}
		return status;
	}
	

}
