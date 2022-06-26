package studentDrones;

import java.awt.Color;

import main.Point;
import mapItems.CommInterface;
import mapItems.Drone;
import mapItems.Message;


public class ChrisDrone extends Drone implements CommInterface {
	
	public ChrisDrone(Point location, String name, String studentName, Color color, int speedMPH) {
		super(location, name, studentName, color, speedMPH);

	}

	@Override
	public void transmit(CommInterface reciever, Message msg) {
		
	}

	@Override
	public void recieve(CommInterface transmitter, Message msg) {
		
	}

	@Override
	public void activate() {
		
	}

	@Override
	public void loop() {
		
	}

}
