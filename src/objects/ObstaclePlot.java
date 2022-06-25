package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.GlobalVars;
import main.Graph;
import main.Point;
import mapItems.Plot;

public class ObstaclePlot extends Plot{

	private static ArrayList<ObstaclePlot> obstacles = new ArrayList<>();
	
	public ObstaclePlot(Point location, Color color, String name) {
		super(location, color, name);
		
	}

	@Override
	public void update() {

	}

	
	public static ArrayList<ObstaclePlot> getObstacles() {
		return obstacles;
	}
	
	public static void createObstacle(int x, int y) {
		if (Graph.getGraphValue(x, y) == 0 || Graph.getGraphValue(x, y) == 1) {
			for (int a = x - GlobalVars.getMapObsDim(); a <= x + GlobalVars.getMapObsDim(); a++) {
				for (int b = y - GlobalVars.getMapObsDim(); b <= y + GlobalVars.getMapObsDim(); b++) {
					if (Graph.doesExist(a, b)) {
						if (Graph.getGraphValue(a, b) == 0) {
							obstacles.add(new ObstaclePlot(new Point(x, y, null), GlobalVars.getObsColor(), "Obstacle"));
							Graph.changeGraphIDbyCoord(1, a, b);
						} 
					}
				}
			}
		}else {
			System.out.println("Invalid Plot Location.");
		}
		
	}



	@Override
	public void render(Graphics g) {
		// Don't render obstacles
	}



	@Override
	public void activate() {
		
	}
}
