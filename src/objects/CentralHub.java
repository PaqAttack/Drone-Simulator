package objects;

import java.util.ArrayList;

import mapItems.CommInterface;
import mapItems.DroneInterface;
import mapItems.PlotInterface;

public class CentralHub implements CommInterface{
	private static boolean active = false;
	private static ArrayList<DroneInterface> drones;
	private static ArrayList<PlotInterface> plots;
		
	public static void InitCentralHUB() {
		// Execute this on startup after plots & Drones created
		drones = new ArrayList<>();
		plots = new ArrayList<>();
		active = true;
	}
	
	
	@Override
	public void transmit(CommInterface reciever, String data) {
		
	}
	
	@Override
	public void recieve(CommInterface transmitter, String data) {
		
	}


	public static boolean isActive() {
		return active;
	}
	
	
	
	
}
