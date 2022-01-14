package fr.insa.twf.clock.Clock.resources;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clock")
public class ClockResources {
	
	@GetMapping("time")
	public String getTime() {
		String timestamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
		return timestamp;
	}
	
	@GetMapping("hours")
	public String getHours() {
		String timestamp = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
		return timestamp;
	}
	
	@GetMapping("minutes")
	public String getMinutes() {
		String timestamp = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
		return timestamp;
	}
	
	@GetMapping("date")
	public String getDate() {
		String timestamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		return timestamp;
	}
	
	@GetMapping("day")
	public String getDay() {
		String timestamp = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		return timestamp;
	}
	
	@GetMapping("month")
	public String getMonth() {
		String timestamp = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
		return timestamp;
	}
	
	@GetMapping("year")
	public String getYear() {
		String timestamp = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		return timestamp;
	}
	
	@GetMapping("datenoyear")
	public String getDateNoYear() {
		String timestamp = new SimpleDateFormat("dd/MM").format(Calendar.getInstance().getTime());
		return timestamp;
	}
}
