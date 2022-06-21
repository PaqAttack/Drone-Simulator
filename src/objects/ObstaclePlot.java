package objects;

import java.awt.Color;
import java.util.ArrayList;

import mapItems.MapIcon;
import mapItems.MapItems;

public class ObstaclePlot extends MapItems{

	private static ArrayList<ObstaclePlot> obstacles = new ArrayList<>();
	
	public ObstaclePlot(String name, Color color, MapIcon type, int startX, int startY) {
		super("Obstacle", color, type, startX, startY);
		obstacles.add(this);
		
	}

	@Override
	public void update() {

	}

	public static ArrayList<ObstaclePlot> getObstacles() {
		return obstacles;
	}
	
	
}
