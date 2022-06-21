package studentDrones;

import java.awt.Color;

import mapItems.CommInterface;
import objects.Drone;

public class FosterDrone  extends Drone implements CommInterface{

	public FosterDrone(int startPosX, int startPosY, String name, String studentName, Color color) {
		super(startPosX, startPosY, name, studentName, color);

	}

	@Override
	public void transmit(CommInterface reciever, String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recieve(CommInterface transmitter, String data) {
		// TODO Auto-generated method stub
		
	}


}
