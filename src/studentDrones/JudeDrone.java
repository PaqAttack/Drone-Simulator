package studentDrones;

import java.awt.Color;

import main.Point;
import mapItems.CommInterface;
import mapItems.Drone;
import mapItems.Message;

public class JudeDrone extends Drone implements CommInterface{

	public JudeDrone(Point location, String name, String studentName, Color color, int speedMPH) {
		super(location, name, studentName, color, speedMPH);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void transmit(CommInterface reciever, Message msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recieve(CommInterface transmitter, Message msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activate() {

	}



}
