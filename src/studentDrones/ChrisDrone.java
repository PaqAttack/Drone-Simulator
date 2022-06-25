package studentDrones;

import java.awt.Color;

import main.Point;
import mapItems.CommInterface;
import mapItems.Drone;


public class ChrisDrone extends Drone implements CommInterface {

	public ChrisDrone(Point location, String name, String studentName, Color color, int speedMPH) {
		super(location, name, studentName, color, speedMPH);

	}

	@Override
	public void transmit(CommInterface reciever, String data) {
		
	}

	@Override
	public void recieve(CommInterface transmitter, String data) {
		
	}

	@Override
	public void activate() {
		setDestination(new Point(99, 99, null));
		moving = true;
	}

	@Override
	public void loop() {
		
	}


}
