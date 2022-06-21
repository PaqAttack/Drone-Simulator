package objects;

import java.awt.Color;
import java.util.ArrayList;

import mapItems.MapIcon;
import mapItems.MapItems;
import mapItems.PlotInterface;

public class ObstaclePlot extends MapItems implements PlotInterface{

	private static ArrayList<ObstaclePlot> obstacles = new ArrayList<>();
	
	
	public ObstaclePlot(String name, Color color, MapIcon type, int startX, int startY) {
		super("Obstacle", color, type, startX, startY);
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
