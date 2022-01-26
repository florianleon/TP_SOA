package fr.insa.twf.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@SpringBootApplication
@RequestMapping("/controller")
@SuppressWarnings("unused")
public class ControllerApplication {
	
	private final String clockURL = "http://localhost:8090/";
	private final String databaseURL = "http://localhost:8091/";
	
	private final String acURL = "http://localhost:8101/AC/"; 
	private final String alarmURL = "http://localhost:8102/alarm/"; 
	private final String doorsURL = "http://localhost:8103/doors/"; 
	private final String windowsURL = "http://localhost:8104/windows/"; 
	private final String lightURL = "http://localhost:8105/lights/"; 
	
	private final String co2HumURL = "http://localhost:8201/co2Humidity/"; 
	private final String lightSensorURL = "http://localhost:8202/lightSensor/"; 
	private final String presenceURL = "http://localhost:8203/presence/";
	private final String tempURL = "http://localhost:8204/temperature/"; 

	
	private Map<List<String>, List<Integer>> acMap = new HashMap<List<String>, List<Integer>>();
	private Map<List<String>, List<Integer>> alarmMap = new HashMap<List<String>, List<Integer>>();
	private Map<List<String>, List<Integer>> doorsMap = new HashMap<List<String>, List<Integer>>();
	private Map<List<String>, List<Integer>> windowsMap = new HashMap<List<String>, List<Integer>>();
	private Map<List<String>, List<Integer>> lightMap = new HashMap<List<String>, List<Integer>>();
	
	private Map<List<String>, List<Integer>> co2HumMap = new HashMap<List<String>, List<Integer>>();
	private Map<List<String>, List<Integer>> lightSensorMap = new HashMap<List<String>, List<Integer>>();
	private Map<List<String>, List<Integer>> presenceMap = new HashMap<List<String>, List<Integer>>();
	private Map<List<String>, List<Integer>> tempMap = new HashMap<List<String>, List<Integer>>();

	private Map<String, String> rooms = new HashMap<String, String>();
	
	private RestTemplate rest() {
		return new RestTemplate();
	}
	
/////////////////////////////////////////////////////////////////////////////
	/**
	 * Map functions
	 */
	
	private void addItem2Map(Map<List<String>, List<Integer>> map, String batiment, String room, int id) {
		List<String> key = Arrays.asList(batiment, room);
		List<Integer> value = Arrays.asList(id);
		if (map.isEmpty() == false) {
			if (map.containsKey(key)) {
				List<Integer> newValue = map.get(key);
				map.put(key, newValue);
			} else {
				map.put(key, value);
			}	
		} else {
			map.put(key, value);
		}
	}
	
	public Map<List<String>, List<Integer>> getAcMap() {
		return acMap;
	}

	public Map<List<String>, List<Integer>> getAlarmMap() {
		return alarmMap;
	}

	public Map<List<String>, List<Integer>> getDoorsMap() {
		return doorsMap;
	}

	public Map<List<String>, List<Integer>> getWindowsMap() {
		return windowsMap;
	}

	public Map<List<String>, List<Integer>> getLightMap() {
		return lightMap;
	}

	public Map<List<String>, List<Integer>> getCo2HumMap() {
		return co2HumMap;
	}

	public Map<List<String>, List<Integer>> getLightSensorMap() {
		return lightSensorMap;
	}

	public Map<List<String>, List<Integer>> getPresenceMap() {
		return presenceMap;
	}

	public Map<List<String>, List<Integer>> getTempMap() {
		return tempMap;
	}

	public Map<String, String> getRooms() {
		return rooms;
	}

	/////////////////////////////////////////////////////////////////////////////
	/**
	 * AC part
	 */
	
	private void createAC(int id, String batiment, String room, int position) {
		String URL = acURL + "new/" + id + "/" + batiment + "/" + room + "/" + position;
		this.rest().postForObject(URL, null, boolean.class);
		this.addItem2Map(acMap, batiment, room, id);
	}
	
	private void setAC(String device, int id, int temp) {
		String URL = null;
		
		if (device.equals("heat")) {
			URL = acURL + "heat/ON/" + id + "/" + temp;
		} else if (device.equals("clim")) {
			URL = acURL + "clim/ON/" + id + "/" + temp;
		} else {
			System.out.println("Order not executed wrong device");
		}
		this.rest().postForObject(URL, null, String.class);
	}

	private void setACOFF(String device, int id) {
		String URL = null;
		if (device.equals("heat")) {
			URL = acURL + "heat/OFF/" + id;
		} else if (device.equals("clim")) {
			URL = acURL + "clim/OFF/" + id;
		} else {
			System.out.println("Order not executed wrong device");
		}
		this.rest().postForObject(URL, null, String.class);
	}
	
	private void turnACOff(String device, int id) {
		String URL = null;
		if (device.equals("heat")) {
			URL = acURL + "heat/OFF/" + id;
		} else if (device.equals("clim")) {
			URL = acURL + "clim/OFF/" + id;
		} else {
			System.out.println("Order not executed wrong device");
		}
		this.rest().postForObject(URL, null, String.class);
	}
	
	private List<Boolean> getACStatus(int id) {
		String climURL = acURL + "clim/" + id;
		String heatURL = acURL + "heat/" + id;
		boolean climStatus = this.rest().getForObject(climURL, boolean.class);
		boolean heatStatus = this.rest().getForObject(heatURL, boolean.class);
		
		return Arrays.asList(climStatus, heatStatus);
	}
	
	private void fetchACList() {
		String URL = acURL + "list";
		Integer[] list = this.rest().getForObject(URL, Integer[].class);
		System.out.println("We have created " + list.length + " AC(s):");
		for (int i = 0; i < list.length; i++) {
			System.out.print("\t" + list[i]);
		}
	}
	
/////////////////////////////////////////////////////////////////////////////
	/**
	 * Alarm
	 */
	
	private void createAlarm(int id, String batiment, String room, int position) {
		String URL = alarmURL + "new/" + id + "/" + batiment + "/" + room + "/" + position;
		this.rest().postForObject(URL, null, boolean.class);
		this.addItem2Map(alarmMap, batiment, room, id);
	}
	
	private void switchAlarmState(int id, boolean state) {
		String URL = alarmURL + id + "/" + state;
		this.rest().postForObject(URL, null, boolean.class);
	}
	
	private void fetchAlarmList() {
		String URL = alarmURL + "list";
		Integer[] list = this.rest().getForObject(URL, Integer[].class);
		System.out.println("We have created " + list.length + " Alarm(s):");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + "\t");
		}
	}
	
	
/////////////////////////////////////////////////////////////////////////////
/**
* Doors
*/
	
	private void createDoor(int id, String batiment, String room, int position) {
		String URL = doorsURL + "new/" + id + "/" + batiment + "/" + room + "/" + position;
		this.rest().postForObject(URL, null, boolean.class);
		this.addItem2Map(doorsMap, batiment, room, id);
	}
	
	private void switchDoorState(int id, boolean state) {
		String URL = doorsURL + id + "/" + state;
		this.rest().postForObject(URL, null, boolean.class);
	}
	
	private void fetchDoorsList() {
		String URL = doorsURL + "list";
		Integer[] list = this.rest().getForObject(URL, Integer[].class);
		System.out.println("We have created " + list.length + " Doors:");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + "\t");
		}
	}
	
/////////////////////////////////////////////////////////////////////////////
/**
* Windows
*/

	private void createWindows(int id, String batiment, String room, int position) {
		String URL = windowsURL + "new/" + id + "/" + batiment + "/" + room + "/" + position;
		this.rest().postForObject(URL, null, boolean.class);
		this.addItem2Map(windowsMap, batiment, room, id);
	}
	
	private void switchWindowsState(int id, boolean state) {
		String URL = windowsURL + id + "/" + state;
		this.rest().postForObject(URL, null, boolean.class);
	}
	
	private void fetchWindowsList() {
		String URL = windowsURL + "list";
		Integer[] list = this.rest().getForObject(URL, Integer[].class);
		System.out.println("We have created " + list.length + " Windows:");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + "\t");
		}
	}
	
/////////////////////////////////////////////////////////////////////////////
/**
* Lights
*/
	
	private void createLights(int id, String batiment, String room, int position) {
		String URL = lightURL + "new/" + id + "/" + batiment + "/" + room + "/" + position;
		this.rest().postForObject(URL, null, boolean.class);
		this.addItem2Map(lightMap, batiment, room, id);
	}
	
	private void switchLightsState(int id, boolean state) {
		String URL = lightURL + id + "/" + state;
		this.rest().postForObject(URL, null, boolean.class);
	}
	
	private void fetchLightsList() {
		String URL = lightURL + "list";
		Integer[] list = this.rest().getForObject(URL, Integer[].class);
		System.out.println("We have created " + list.length + " lights:");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + "\t");
		}
	}
	
/////////////////////////////////////////////////////////////////////////////
/**
* CO2 & humidity sensor
*/
	
	private void createCo2HumSensor(int id, String batiment, String room, int position) {
		String URL = co2HumURL + "new/" + id + "/" + batiment + "/" + room + "/" + position;
		this.rest().postForObject(URL, null, boolean.class);
		this.addItem2Map(co2HumMap, batiment, room, id);
	}
	
	private void fetchCo2HumList() {
		String URL = co2HumURL + "list";
		Integer[] list = this.rest().getForObject(URL, Integer[].class);
		System.out.println("We have created " + list.length + " CO2 & Humidity sensors:");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + "\t");
		}
	}
	
	private Double getCO2Value(int id) {
		String URL = co2HumURL + "co2/" + id;
		Double value = this.rest().getForObject(URL, Double.class);
		return value;
	}
	
	private Double getHumValue(int id) {
		String URL = co2HumURL + "humidity/" + id;
		Double value = this.rest().getForObject(URL, Double.class);
		return value;
	}
	
	private int getCo2HumBatteryLevel(int id) {
		String URL = co2HumURL + "battery/" + id;
		int value = this.rest().getForObject(URL, int.class);
		return value;
	}
	
	
/////////////////////////////////////////////////////////////////////////////
/**
* Light sensor
*/

	private void createLightSensor(int id, String batiment, String room, int position) {
		String URL = lightSensorURL + "new/" + id + "/" + batiment + "/" + room + "/" + position;
		this.rest().postForObject(URL, null, boolean.class);
		this.addItem2Map(lightSensorMap, batiment, room, id);
	}
	
	private void fetchLightSensorList() {
		String URL = lightSensorURL + "list";
		Integer[] list = this.rest().getForObject(URL, Integer[].class);
		System.out.println("We have created " + list.length + " Light sensors:");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + "\t");
		}
	}
	
	private int getLightSensorValue(int id) {
		String URL = lightSensorURL + id;
		int value = this.rest().getForObject(URL, int.class);
		return value;
	}
	
	private int getLightBatteryLevel(int id) {
		String URL = lightSensorURL + "battery/" + id;
		int value = this.rest().getForObject(URL, int.class);
		return value;
	}
	
/////////////////////////////////////////////////////////////////////////////
/**
* Presence sensor
*/
	
	private void createPresenceSensor(int id, String batiment, String room, int position) {
		String URL = presenceURL + "new/" + id + "/" + batiment + "/" + room + "/" + position;
		this.rest().postForObject(URL, null, boolean.class);
		this.addItem2Map(presenceMap, batiment, room, id);
	}
	
	private void fetchPresenceList() {
		String URL = presenceURL + "list";
		Integer[] list = this.rest().getForObject(URL, Integer[].class);
		System.out.println("We have created " + list.length + " Presence sensors:");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + "\t");
		}
	}
	
	private boolean getPresenceValue(int id) {
		String URL = presenceURL + id;
		boolean value = this.rest().getForObject(URL, boolean.class);
		return value;
	}
	
	private int getPresenceBatteryLevel(int id) {
		String URL = presenceURL + "battery/" + id;
		int value = this.rest().getForObject(URL, int.class);
		return value;
	}
	
	
/////////////////////////////////////////////////////////////////////////////
/**
* Temperature sensor
*/
	
	private void createTempSensor(int id, String batiment, String room, int position) {
		String URL = tempURL + "new/" + id + "/" + batiment + "/" + room + "/" + position;
		this.rest().postForObject(URL, null, boolean.class);
		this.addItem2Map(tempMap, batiment, room, id);
	}
	
	private void fetchTempList() {
		String URL = tempURL + "list";
		Integer[] list = this.rest().getForObject(URL, Integer[].class);
		System.out.println("We have created " + list.length + " Temperature sensors:");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + "\t");
		}
	}
	
	private Double getTempValue(int id) {
		String URL = tempURL + id;
		Double value = this.rest().getForObject(URL, Double.class);
		return value;
	}
	
	private int getTempBatteryLevel(int id) {
		String URL = tempURL + "battery/" + id;
		int value = this.rest().getForObject(URL, int.class);
		return value;
	}
	
/////////////////////////////////////////////////////////////////////////////
/**
* Init functions 
*/
	//Définis les salles et batiments dans lesquels on a des capteurs 
	//Pour simplifier les choses on ne va garder que la GEI 213
	//A titre d'exemple mais on peu imaginer un autre système plus tard
	private void initRooms() {
		rooms.put("GEI", "213");
	}
	
	private void initActuators() {
		this.createAC(0, "GEI", "213", 0);
		this.createAC(1, "GEI", "213", 1);
		this.createAlarm(0, "GEI", "213", 0);
		this.createDoor(0, "GEI", "213", 0);
		this.createDoor(1, "GEI", "213", 1);
		this.createWindows(0, "GEI", "213", 0);
		this.createWindows(1, "GEI", "213", 1);
		this.createWindows(2, "GEI", "213", 2);
		this.createWindows(3, "GEI", "213", 3);
		this.createLights(0, "GEI", "213", 0);
		this.createLights(1, "GEI", "213", 1);
		this.createLights(2, "GEI", "213", 2);
		this.createLights(3, "GEI", "213", 3);
		System.out.println("\n");
		this.fetchACList();
		System.out.println("\n");
		this.fetchAlarmList();
		System.out.println("\n");
		this.fetchDoorsList();
		System.out.println("\n");
		this.fetchWindowsList();
		System.out.println("\n");
		this.fetchLightsList();
		System.out.println("\n");
	}
	
	private void initSensors() {
		this.createCo2HumSensor(0, "GEI", "213", 0);
		this.createLightSensor(0, "GEI", "213", 0);
		this.createPresenceSensor(0, "GEI", "213", 0);
		this.createPresenceSensor(1, "GEI", "213", 1);
		this.createPresenceSensor(2, "GEI", "213", 2);
		this.createPresenceSensor(3, "GEI", "213", 3);
		this.createTempSensor(0, "GEI", "213", 0);
		System.out.println("\n");
		this.fetchCo2HumList();
		System.out.println("\n");
		this.fetchLightSensorList();
		System.out.println("\n");
		this.fetchPresenceList();
		System.out.println("\n");
		this.fetchTempList();
		System.out.println("\n");

	}

	public static void main(String[] args) {
		SpringApplication.run(ControllerApplication.class, args);
		ControllerApplication cont = new ControllerApplication();
		cont.initRooms();
		cont.initActuators();
		cont.initSensors();
		boolean open = false;
		System.out.println("Started...");
		try {
			Thread.sleep(15000); //A modifier plus tard
			System.out.println("Ready...");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (true) {
			for(Map.Entry<String, String> entry : cont.getRooms().entrySet()) {
				//////////////////////////////////////////////////////
				List<String> key = Arrays.asList(entry.getKey(), entry.getValue());
				
				//On commence par vérifier s'il y a quelqu'un dans la salle
				Map<List<String>, List<Integer>> presenceMap = cont.getPresenceMap();
				boolean presence = false;
				if (presenceMap.containsKey(key)) {
					List<Integer> presenceSensors = presenceMap.get(key);
					for (Integer id : presenceSensors) {
						//Au moins un des capteurs detecte une présence
						presence = presence || cont.getPresenceValue(id);
					}
				}
				//////////////////////////////////////////////////////
				//On s'occupe ensuite des lumières 
				Map<List<String>, List<Integer>> lightSensorMap = cont.getLightSensorMap();
				Map<List<String>, List<Integer>> lightMap = cont.getLightMap();
				int lightLevel = 0; //de 0 à 100% de luminosité
				if (lightSensorMap.containsKey(key)) {
					List<Integer> lightSensors = lightSensorMap.get(key);
					//On fait la moyenne des capteurs de luminosité
					for (Integer id : lightSensors) {
						lightLevel += cont.getLightSensorValue(id);
					}
					lightLevel /= lightSensors.size();
					if (lightMap.containsKey(key)) {
						boolean lightOn = false;
						if (presence) {
							if (lightLevel < 50) {
								lightOn = true;
							} else if (lightLevel >= 50 && lightLevel <= 80) {
								break;
							} else {
								lightOn = false;
							}
						} else {
							lightOn = false;
						}
						for (Integer id : lightSensors) {
							cont.switchLightsState(id, lightOn);
						}
					}
					
				}
				
				
				//////////////////////////////////////////////////////
				//On s'occupe ensuite de l'AC 
				Map<List<String>, List<Integer>> acMap = cont.getAcMap();
				Map<List<String>, List<Integer>> tempMap = cont.getTempMap();
				double temp = 0.0;
				boolean climOn = false;
				boolean heatOn = false;
				if (acMap.containsKey(key)) {
					List<Integer> tempSensors = tempMap.get(key);
					//On fait la moyenne des capteurs de temperature
					//for (Integer id : tempSensors) {
					//	temp += cont.getTempValue(id);
					//}
					//temp /= tempSensors.size();
					temp = cont.getTempValue(0);
					
					if (tempMap.containsKey(key)) {
						List<Integer> acs = acMap.get(key);
						if (presence) {
							for (Integer id : acs) {
								List<Boolean> acStatus = cont.getACStatus(id);
								climOn = climOn || acStatus.get(0);
								heatOn = heatOn || acStatus.get(0);
							}
							if (temp < 18.0) {
								for(Integer id : acs) {
									cont.setAC("heat", id, 20);
								}
							} else if (temp >= 18.0 && temp <= 20.0) {
								for(Integer id : acs) {
									cont.setACOFF("clim", id);
								}
							} else if (temp > 20.0 && temp <= 26.0) {
								for(Integer id : acs) {
									cont.setACOFF("heat", id);
								}
							} else if (temp > 26.0) {
								for(Integer id : acs) {
									cont.setAC("clim", id, 20);
								}
							}
						} else {
							for(Integer id : acs) {
								cont.setACOFF("heat", id);
								cont.setACOFF("clim", id);
							}
						}
					}
				}
				
				//////////////////////////////////////////////////////
				//On s'occupe ensuite du reste
				Map<List<String>, List<Integer>> windowsMap = cont.getWindowsMap();
				Map<List<String>, List<Integer>> alarmMap = cont.getAlarmMap();
				Map<List<String>, List<Integer>> doorsMap = cont.getDoorsMap();
				Map<List<String>, List<Integer>> co2HumMap = cont.getCo2HumMap();
				double co2Value = 0.0; //ppm
				boolean isKeyInAllMap = windowsMap.containsKey(key) && alarmMap.containsKey(key) && doorsMap.containsKey(key) && co2HumMap.containsKey(key);
				if(isKeyInAllMap) {
					if (presence) {
						List<Integer> co2Sensors = co2HumMap.get(key);
						//On fait la moyenne des capteurs de luminosité
						for (Integer id : co2Sensors) {
							co2Value += cont.getCO2Value(id);
						}
						co2Value /= co2Sensors.size();
						
						List<Integer> windows = windowsMap.get(key);
						List<Integer> doors = doorsMap.get(key);
						List<Integer> alarms = alarmMap.get(key);
						//On verifie si ou doit aerer ou pas
						//boolean open = false;
						if (co2Value < 2000.0) {
							for (Integer id : windows) {
								cont.switchWindowsState(id, false);
							}
							for (Integer id : doors) {
								cont.switchDoorState(id, false);
							}
							for (Integer id : alarms) {
								cont.switchAlarmState(id, false);
							}
						} else if (co2Value >= 2000.0 && co2Value <= 2500.0) {
							;
						} else if (co2Value > 2500.0) {
							for (Integer id : windows) {
								cont.switchWindowsState(id, true);
							}
							for (Integer id : doors) {
								cont.switchDoorState(id, true);
							}
							for (Integer id : alarms) {
								cont.switchAlarmState(id, false);
							}
						}

					} else {
						//on ferme la porte et les fenetres
						List<Integer> windows = windowsMap.get(key);
						List<Integer> doors = doorsMap.get(key);
						List<Integer> alarms = alarmMap.get(key);
						for (Integer id : windows) {
							cont.switchWindowsState(id, false);
						}
						for (Integer id : doors) {
							cont.switchDoorState(id, false);
						}
						for (Integer id : alarms) {
							cont.switchAlarmState(id, false);
						}
						List<Integer> co2Sensors = co2HumMap.get(key);
						//On fait la moyenne des capteurs de luminosité
						for (Integer id : co2Sensors) {
							co2Value += cont.getCO2Value(id);
						}
						co2Value /= co2Sensors.size();
						if (co2Value < 2000.0) {
							for (Integer id : alarms) {
								cont.switchAlarmState(id, false);
							}
						} else if (co2Value >= 2000.0 && co2Value <= 2500.0) {
							;
						} else if (co2Value > 2500.0) {
							for (Integer id : alarms) {
								cont.switchAlarmState(id, true);
							}
						}

					}
				}
			}
			try {
				System.out.println("Sleep...");
				Thread.sleep(5000); //A modifier plus tard
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
