package objects;

import java.awt.Color;
import java.util.ArrayList;
import main.GlobalVars;
import main.Graph;
import main.Point;
import mapItems.Plot;

public class TargetPlotB extends Plot{

	private static ArrayList<TargetPlotB> targets = new ArrayList<>();
	
	public TargetPlotB(Point location, Color color, String name) {
		super(location, color, name);
	}



	@Override
	public void update() {

	}

	@Override
	public void activate() {
		
	}

	public static void createPlotB(int x, int y) {
		if (Graph.getGraphValue(x, y) == 0) {
			Graph.changeGraphIDbyCoord(3, x, y);
			targets.add(new TargetPlotB(new Point(x, y, null), GlobalVars.getPlotBColor(), GlobalVars.getPlotBName()));
			for (int a = x - GlobalVars.getMapPlotSpace(); a <= x + GlobalVars.getMapPlotSpace(); a++) {
				for (int b = y - GlobalVars.getMapPlotSpace(); b <= y + GlobalVars.getMapPlotSpace(); b++) {
					if (Graph.doesExist(a, b)) {
						if (Graph.getGraphValue(a, b) == 0) {
							Graph.changeGraphIDbyCoord(3, a, b);
						} 
					}
				}
			}
		} else {
			System.out.println("Invalid plot location");
		}
		

	}

	public void remove() {
		System.out.println("Camper at point " + getLocation().getX() + ", " + getLocation().getY() + " evacuated.");
		targets.remove(this);
	}

	public static Plot getPlotByLocation(Point p) {
		for (Plot plot : targets) {
			if (plot.getLocation().equals(p)) {
				System.out.println("found plot");
				return plot;
			}
		}
		return null;
	}
}
