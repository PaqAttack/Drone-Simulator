package studentDrones;

import java.awt.Color;
import java.awt.Point;

import mapItems.CommInterface;
import mapItems.Drone;
import mapItems.Message;


public class ChrisDrone extends Drone implements CommInterface {
	
	public ChrisDrone(Point location, String name, String studentName, Color color, boolean returnHome, int speedMPH) {
		super(location, name, studentName, color, returnHome, speedMPH);

	}

	@Override
	public void transmit(CommInterface reciever, Message msg) {
		reciever.recieve(this, msg);
	}

	@Override
	public void recieve(CommInterface transmitter, Message msg) {
		
	}

	@Override
	public void activate() {
		moving = true;
	}

	@Override
	public void loop() {

	}

	@Override
	public void arrived() {

	}

}
