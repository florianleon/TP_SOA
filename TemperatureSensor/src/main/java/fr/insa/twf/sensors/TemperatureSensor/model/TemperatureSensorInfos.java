package fr.insa.twf.sensors.TemperatureSensor.model;

public class TemperatureSensorInfos {
	
	private int id;
	private String batiment;
	private String room;
	private int position;
	private double value;
	private int state;
	private int battery;
	
	public TemperatureSensorInfos(int id, String batiment, String room, int position) {
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
	public void setRoom(String salle) {
		this.room = salle;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
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
