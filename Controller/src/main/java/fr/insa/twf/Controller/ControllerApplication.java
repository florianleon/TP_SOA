package fr.insa.twf.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping("/controller")
@SuppressWarnings("unused")
public class ControllerApplication {
	
	private final String clockURL = "http://localhost:8090/";
	private final String databaseURL = "http://localhost:8091/";
	
	private final String acURL = "http://localhost:8101/";
	private final String alarmURL = "http://localhost:8102/";
	private final String doorsURL = "http://localhost:8103/";
	private final String windowsURL = "http://localhost:8104/";
	
	private final String co2HumURL = "http://localhost:8201/";
	private final String lightURL = "http://localhost:8202/";
	private final String presenceURL = "http://localhost:8203/";
	private final String tempURL = "http://localhost:8204/";
	

	public static void main(String[] args) {
		SpringApplication.run(ControllerApplication.class, args);
	}

}
