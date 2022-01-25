package fr.insa.twf.actuators.Lights.model;

public class LightsInfos {
	
	private int id;
	private String batiment;
	private String room;
	private int position;
	private boolean lightOn; 
	private int state;
	
	public LightsInfos(int id, String batiment, String room, int position) {
		this.id = id;
		this.batiment = batiment;
		this.room = room;
		this.position = position;
		this.lightOn = false;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isLightOn() {
		return lightOn;
	}

	public void setLightOn(boolean lightOn) {
		this.lightOn = lightOn;
	}

	
	

}
