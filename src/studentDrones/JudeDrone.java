package studentDrones;

import java.awt.Color;

import mapItems.CommInterface;
import mapItems.MapIcon;
import mapItems.DroneInterface;
import mapItems.MapItems;

public class JudeDrone extends MapItems implements CommInterface, DroneInterface{

	public JudeDrone(String name, Color color, MapIcon type, int startX, int startY) {
		super(name, color, type, startX, startY);
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
	public void update() {
		indicator.setIndPosX(posX);
		indicator.setIndPosY(posY);
	}

}
