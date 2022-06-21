package objects;

import java.awt.Color;
import java.util.ArrayList;

import mapItems.MapIcon;
import mapItems.MapItems;
import mapItems.PlotInterface;

public class TargetPlotA extends MapItems implements PlotInterface{

	private static ArrayList<TargetPlotA> targets = new ArrayList<>();
	
	public TargetPlotA(String name, Color color, MapIcon type, int startX, int startY) {
		super(name, color, type, startX, startY);
		targets.add(this);
	}

	
	@Override
	public void update() {
		indicator.setIndPosX(posX);
		indicator.setIndPosY(posY);
	}

	public static ArrayList<TargetPlotA> getTargets() {
		return targets;
	}
}
