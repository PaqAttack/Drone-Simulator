package mapItems;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Simulator;

public class Drone extends MapItems{
	
	String name;
	Rectangle indicator;
	Color droneColor;
	
	public Drone(Color color, String name, int startX, int startY) {
		super();
		posX = startX;
		posY = startY;
		this.name = name;
		this.droneColor = color;
		indicator = new Rectangle();
		indicator.height = 5;
		indicator.width = 5;
		
	}

	@Override
	public void update() {
		indicator.x = posX - (indicator.height / 2) + Simulator.getFieldXOffset();
		indicator.y = Simulator.getFieldYOffset() - posY - (indicator.width / 2);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(droneColor);
		g.drawRect(indicator.x, indicator.y, indicator.width, indicator.height);
	}

}
