package objects;

import java.awt.Color;
import java.util.ArrayList;

import mapItems.Design;
import mapItems.MapItems;
import mapItems.PlotInterface;

public class ObstaclePlot extends MapItems implements PlotInterface{

	private static ArrayList<ObstaclePlot> obstacles = new ArrayList<>();
	
	public ObstaclePlot(String name, Color color, Design type, int startX, int startY) {
		super(name, color, type, startX, startY);
		obstacles.add(this);
	}

	@Override
	public void update() {
		indicator.setIndPosX(posX);
		indicator.setIndPosY(posY);
	}

	public static ArrayList<ObstaclePlot> getObstacles() {
		return obstacles;
	}
}
