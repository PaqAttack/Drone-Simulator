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
		
		for (int a = x - GlobalVars.getMapObsDim(); a <= x + GlobalVars.getMapObsDim(); a++) {
			for (int b = y - GlobalVars.getMapObsDim(); b <= y + GlobalVars.getMapObsDim(); b++) {
				if (Graph.doesExist(a, b)) {
					if (Graph.getGraphValue(a, b) == 0) {
						obstacles.add(new ObstaclePlot(GlobalVars.getObsColor(), a, b));
						Graph.changeGraphIDbyCoord(1, a, b);
					}
				}
			}
		}
		
	}
}
