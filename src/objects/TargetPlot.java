package objects;

import java.awt.Color;

import mapItems.Design;
import mapItems.MapItems;
import mapItems.PlotInterface;

public class TargetPlot extends MapItems implements PlotInterface{

	public TargetPlot(String name, Color color, Design type, int startX, int startY) {
		super(name, color, type, startX, startY);

	}

	@Override
	public void update() {
		indicator.setIndPosX(posX);
		indicator.setIndPosY(posY);
	}

}
