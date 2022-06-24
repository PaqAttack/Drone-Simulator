package studentDrones;

import java.awt.Color;

import main.Point;
import mapItems.CommInterface;
import objects.Drone;

public class FosterDrone  extends Drone implements CommInterface{

	public FosterDrone(Point location, String name, String studentName, Color color, int speedMPH) {
		super(location, name, studentName, color, speedMPH);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void transmit(CommInterface reciever, String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recieve(CommInterface transmitter, String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		
	}




}
