package studentDrones;

import java.awt.Color;

import main.Point;
import mapItems.CommInterface;
import mapItems.Drone;
import mapItems.Message;
import mapItems.Plot;
import objects.TargetPlotB;


public class ChrisDrone extends Drone implements CommInterface {
	
	public ChrisDrone(Point location, String name, String studentName, Color color, int speedMPH) {
		super(location, name, studentName, color, speedMPH);

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
		if (location.getX() != 4) {
			System.out.println("Camper at point " + location.getX() + ", " + location.getY() + " evacuated.");
		}
	}

}
