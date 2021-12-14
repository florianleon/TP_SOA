package fr.insa.twf.sensors.CO2HumiditySensor.model;

public class CO2HumiditySensorInfos {

	private int id;
	private String batiment;
	private String room;
	private int position;
	private double CO2Value;
	private double humidityValue;
	private int state;
	private int battery;
	
	public CO2HumiditySensorInfos(int id, String batiment, String room, int position) {
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

	public double getHumidityValue() {
		return humidityValue;
	}

	public void setHumidityValue(double value) {
		this.humidityValue = value;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getBatterie() {
		return battery;
	}

	public void setBatterie(int battery) {
		this.battery = battery;
	}

	public double getCO2Value() {
		return CO2Value;
	}

	public void setCO2Value(double value) {
		CO2Value = value;
	}
	
	
}
