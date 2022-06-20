package objects;

import java.awt.Color;
import mapItems.CommInterface;
import mapItems.DroneInterface;
import mapItems.Indicator;
import mapItems.MapItems;
import mapItems.Design;

public class ChrisDrone extends MapItems implements CommInterface, DroneInterface{

	public ChrisDrone(String name, Color color, Design type, int startX, int startY) {
		super(name, color, type, startX, startY);

	}

	@Override
	public void transmit(CommInterface reciever, String data) {
		
	}

	@Override
	public void recieve(CommInterface transmitter, String data) {
		
	}

	@Override
	public void update() {
		indicator.setIndPosX(posX);
		indicator.setIndPosY(posY);
	}

}
