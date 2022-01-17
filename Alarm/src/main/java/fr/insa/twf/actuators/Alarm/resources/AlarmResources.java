package fr.insa.twf.actuators.Alarm.resources;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.twf.actuators.Alarm.model.AlarmInfos;

@RestController
@RequestMapping("/alarm")
public class AlarmResources {
	
	private ArrayList<AlarmInfos> AlarmList = null;
		
	private AlarmInfos getAlarm(int id) {
		AlarmInfos item = null;
		for (AlarmInfos alarm: AlarmList) {
			int alarmId = alarm.getId();
			if (alarmId == id) {
				item = alarm;
			}
		}
		return item;
	}
	
	@PostMapping("new/{id}/{batiment}/{room}/{position}")
	public boolean add1larm(@PathVariable("id") int id, @PathVariable("batiment") String batiment, @PathVariable("room") String room, @PathVariable("position") int position) {
		AlarmInfos alarm = new AlarmInfos(id, batiment, room, position);
		AlarmList.add(alarm);
		return true;
	}
	
	@DeleteMapping("delete/{id}")
	public boolean deleteAlarm(@PathVariable("id") int id) {
		boolean status = false;
		for (int i = 0; i < AlarmList.size(); i++) {
			AlarmInfos item = AlarmList.get(i);
			int alarmId = item.getId();
			if (alarmId == id) {
				AlarmList.remove(i);
				status = true;
				break;
			}
		}
		
		return status;
	}
	
	@GetMapping("{id}")
	public boolean statusAlarm(@PathVariable("id") int id) {
		boolean status = false;
		AlarmInfos alarm = getAlarm(id);
		if (alarm != null) {
			status = alarm.isAlarmOn();
		}
		return status;
	}
	
	@PostMapping("{id}/{alarmOn}")
	public boolean switchAlarm(@PathVariable("id") int id, @PathVariable("alarmOn") boolean alarmOn) {
		boolean status = false;
		AlarmInfos alarm = getAlarm(id);
		if (alarm != null) {
			alarm.setAlarmOn(alarmOn);
			status = true;
		}
		return status;
	}

}
