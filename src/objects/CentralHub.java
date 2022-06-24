package objects;

import mapItems.CommInterface;

public class CentralHub implements CommInterface{
	private static boolean active = false;

		
	public static void InitCentralHUB() {
		// Execute this on startup after plots & Drones created
		active = true;
		for (Drone drone : Drone.getDrones()) {
			drone.activate();
		}
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


	public static void setActive(boolean active) {
		CentralHub.active = active;
	}
	
	
	
	
}
