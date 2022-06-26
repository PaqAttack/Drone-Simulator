package objects;

import java.awt.Color;
import java.util.ArrayList;

import main.Point;
import mapItems.CommInterface;
import mapItems.Drone;
import mapItems.Message;
import studentDrones.ChrisDrone;
import studentDrones.FosterDrone;
import studentDrones.JudeDrone;
import studentDrones.RichardDrone;

public class CentralHub implements CommInterface{
	
//	ChrisDrone chrisDrone = new ChrisDrone(new Point(2, 99, null), "HID Drone", "Chris", Color.CYAN, 40);
//	FosterDrone fosterDrone = new FosterDrone(new Point(2, 80, null), "Fire Finder", "Foster", Color.PINK, 30);
//	RichardDrone richardDrone = new RichardDrone(new Point(2, 70, null), "Camper Check-in", "Richard", Color.yellow, 40);
//	JudeDrone judeDrone = new JudeDrone(new Point(5, 50, null), "Human Finder", "Jude", Color.MAGENTA, 60);
	
	
	
	private boolean active = false;
	private ArrayList<Point> fires = new ArrayList<>();
	
	private static ArrayList<CentralHub> HUBs =new ArrayList<>();
		
	public CentralHub() {
		HUBs.add(this);
	}


	public void InitCentralHUB() {
		// Execute this on startup after plots & Drones created
		active = true;
		for (Drone drone : Drone.getDrones()) {
			drone.activate();
		}
		
		transmit((CommInterface) Drone.getDroneByStudentName("FOSTER"), new Message("START", null, null));
		System.out.println("Central HUB directs Fire finder drone to deploy");
	}
	
	
	public void transmit(CommInterface reciever, Message msg) {
		reciever.recieve(this, msg);
	}
	
	public void recieve(CommInterface transmitter, Message msg) {
		// Add newly detected fires to list
		if (msg.getMsg().equalsIgnoreCase("FIRE")) {
			if (!fires.contains((Point) msg.getO())) {
				fires.add((Point) msg.getO());
			}

		}
		
		

	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public static ArrayList<CentralHub> getHUBs() {
		return HUBs;
	}
	
	
	
	
}
