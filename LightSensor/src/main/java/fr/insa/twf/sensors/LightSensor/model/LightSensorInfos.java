package fr.insa.twf.sensors.LightSensor.model;

public class LightSensorInfos {

	private int id;
	private String batiment;
	private String room;
	private int position;
	private int value; //de 0 √† 100% de luminosit√©
	private int state;
	private int battery;
	
	public LightSensorInfos(int id, String batiment, String room, int position) {
		this.id = id;
		this.batiment = batiment;
		this.room = room;
		this.position = position;
		this.battery = 100;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBatiment() {
		return batiment;
	}

	public void setBatiment(String batiment) {
		this.batiment = batiment;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}
	
	
}
