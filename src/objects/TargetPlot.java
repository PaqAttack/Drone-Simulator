package objects;

import java.awt.Color;
import java.util.ArrayList;

import mapItems.Design;
import mapItems.MapItems;
import mapItems.PlotInterface;

public class TargetPlot extends MapItems implements PlotInterface{

	private static ArrayList<TargetPlot> targets = new ArrayList<>();
	
	public TargetPlot(String name, Color color, Design type, int startX, int startY) {
		super(name, color, type, startX, startY);
		targets.add(this);
	}

	
	
	@Override
	public void update() {
		indicator.setIndPosX(posX);
		indicator.setIndPosY(posY);
	}

	public static ArrayList<TargetPlot> getTargets() {
		return targets;
	}
}
