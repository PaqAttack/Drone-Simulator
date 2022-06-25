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
		setDestination(450, 450);
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		
	}


}
