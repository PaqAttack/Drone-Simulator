package objects;

import java.awt.Color;
import java.util.ArrayList;

import mapItems.MapIcon;
import mapItems.MapItems;

public class TargetPlotB extends MapItems{

	private static ArrayList<TargetPlotB> targets = new ArrayList<>();
	
	public TargetPlotB(String name, Color color, MapIcon type, int startX, int startY) {
		super(name, color, type, startX, startY);
		targets.add(this);
	}

	@Override
	public void update() {
		indicator.setIndPosX(posX);
		indicator.setIndPosY(posY);
	}

	public static ArrayList<TargetPlotB> getTargets() {
		return targets;
	}

}
