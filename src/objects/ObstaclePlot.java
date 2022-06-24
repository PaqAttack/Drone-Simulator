package objects;

import java.awt.Color;
import java.util.ArrayList;

import main.GlobalVars;
import main.Graph;
import mapItems.MapIcon;
import mapItems.MapItems;

public class ObstaclePlot extends MapItems{

	private static ArrayList<ObstaclePlot> obstacles = new ArrayList<>();
	
	public ObstaclePlot(Color color, int startX, int startY) {
		super("Obstacle", color, startX, startY);
		
	}

	@Override
	public void update() {

	}

	
	public static ArrayList<ObstaclePlot> getObstacles() {
		return obstacles;
	}
	
	public static void createObstacle(int x, int y) {
		obstacles.add(new ObstaclePlot(GlobalVars.getObsColor(), x, y));
		Graph.changeGraphID(1, x, y);
		for (int a = x - GlobalVars.getMapObsDim(); a <= x + GlobalVars.getMapObsDim(); a++) {
			for (int b = y - GlobalVars.getMapObsDim(); b <= y + GlobalVars.getMapObsDim(); b++) {
				if (Graph.doesExist(x, y)) {
					Graph.changeGraphIDbyCoord(1, x, y);
				}
			}
		}
		
	}
}
