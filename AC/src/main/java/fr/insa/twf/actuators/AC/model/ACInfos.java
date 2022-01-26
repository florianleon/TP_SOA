package fr.insa.twf.actuators.AC.model;

public class ACInfos {

	private int id;
	private String batiment;
	private String room;
	private int position;
	private int targetedTemperature;
	private boolean climOn; 
	private boolean heatOn; 
	private int state;
	
	public ACInfos(int id, String batiment, String room, int position) {
		this.id = id;
		this.batiment = batiment;
		this.room = room;
		this.position = position;
		this.climOn = false;
		this.heatOn = false;
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

	public double getTargetedTemperature() {
		return targetedTemperature;
	}

	public void setTargetedTemperature(int targetedTemperature) {
		this.targetedTemperature = targetedTemperature;
	}

	public boolean isClimOn() {
		return climOn;
	}

	public void setClimOn(boolean climOn) {
		this.climOn = climOn;
	}

	public boolean isHeatOn() {
		return heatOn;
	}

	public void setHeatOn(boolean heatOn) {
		this.heatOn = heatOn;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	

	
}
