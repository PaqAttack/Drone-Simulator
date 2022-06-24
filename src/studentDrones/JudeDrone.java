package studentDrones;

import java.awt.Color;

import mapItems.CommInterface;
import objects.Drone;

public class JudeDrone extends Drone implements CommInterface{

	public JudeDrone(int startPosX, int startPosY, String name, String studentName, Color color) {
		super(startPosX, startPosY, name, studentName, color);

	}

	@Override
	public void transmit(CommInterface reciever, String data) {
		
	}

	@Override
	public void recieve(CommInterface transmitter, String data) {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}



}
