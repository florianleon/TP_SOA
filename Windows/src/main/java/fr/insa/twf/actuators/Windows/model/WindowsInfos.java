package fr.insa.twf.actuators.Windows.model;

public class WindowsInfos {

	private int id;
	private String batiment;
	private String room;
	private int position;
	private boolean windowsOpen; 
	private int state;
	
	public WindowsInfos(int id, String batiment, String room, int position) {
		this.id = id;
		this.batiment = batiment;
		this.room = room;
		this.position = position;
		this.windowsOpen = false;
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

	public boolean isWindowsOpen() {
		return windowsOpen;
	}

	public void setWindowsOpen(boolean windowsOpen) {
		this.windowsOpen = windowsOpen;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
