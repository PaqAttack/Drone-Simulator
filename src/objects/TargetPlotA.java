package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import main.GlobalVars;
import main.Graph;
import mapItems.MapItems;

public class TargetPlotA extends MapItems{

	private static ArrayList<TargetPlotA> targets = new ArrayList<>();
	
	public TargetPlotA(String name, Color color, int x, int y) {
		super(name, color, x, y);
	}

	public void render(Graphics g) {
		for (TargetPlotA a : targets) {
			
			g.setColor(a.getColor());
			g.drawOval(Graph.graphXtoScreenX(a.getPosX()) - (GlobalVars.getMapItemDim() / 2), Graph.graphYtoScreenY(a.getPosY()) - (GlobalVars.getMapItemDim() / 2), GlobalVars.getMapItemDim(), GlobalVars.getMapItemDim());
			g.fillOval(Graph.graphXtoScreenX(a.getPosX()) - (GlobalVars.getMapItemDim() / 2), Graph.graphYtoScreenY(a.getPosY()) - (GlobalVars.getMapItemDim() / 2), GlobalVars.getMapItemDim(), GlobalVars.getMapItemDim());
			
			g.setColor(GlobalVars.getGraphColor());
			g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			g.drawString(GlobalVars.getPlotAName(), Graph.graphXtoScreenX(a.getPosX()) - (g.getFontMetrics().stringWidth(GlobalVars.getPlotAName()) / 2), Graph.graphYtoScreenY(a.getPosY()) + GlobalVars.getMapItemDim() + 15);
		}
	}
	
	@Override
	public void update() {

	}

	public static ArrayList<TargetPlotA> getTargets() {
		return targets;
	}
	
	public static void createPlotA(int x, int y) {
		
		if (Graph.getGraphValue(x, y) == 0) {
			Graph.changeGraphIDbyCoord(2, x, y);
			targets.add(new TargetPlotA(GlobalVars.getPlotAName(), GlobalVars.getPlotAColor(), x, y));
			System.out.println("Plot Created");
		}
		
		for (int a = x - GlobalVars.getMapPlotSpace(); a <= x + GlobalVars.getMapPlotSpace(); a++) {
			for (int b = y - GlobalVars.getMapPlotSpace(); b <= y + GlobalVars.getMapPlotSpace(); b++) {
				if (Graph.doesExist(a, b)) {
					if (Graph.getGraphValue(a, b) == 0) {
						Graph.changeGraphIDbyCoord(2, a, b);
					}
				}
			}
		}
		
	}
}