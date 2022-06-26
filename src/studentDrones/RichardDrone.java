package studentDrones;

import java.awt.Color;

import main.Point;
import mapItems.CommInterface;
import mapItems.Drone;
import mapItems.Message;

public class RichardDrone extends Drone implements CommInterface{

	public RichardDrone(Point location, String name, String studentName, Color color, int speedMPH) {
		super(location, name, studentName, color, speedMPH);

	}

	@Override
	public void transmit(CommInterface reciever, Message msg) {
		
	}

	@Override
	public void recieve(CommInterface transmitter, Message msg) {
		
	}

	@Override
	public void loop() {
		
	}

	@Override
	public void activate() {
		
	}




}
