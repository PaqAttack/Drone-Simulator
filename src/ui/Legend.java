package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import main.Simulator;
import mapItems.DroneInterface;
import mapItems.MapItems;
import mapItems.PlotInterface;

public class Legend {
	
	private static ArrayList<MapItems> drones = new ArrayList<>();
	private static ArrayList<MapItems> plots = new ArrayList<>();
	
	private static final int MAX_DRONES = 5;
	private static final int MAX_PLOTS = 3;
	
	private static final int START_X = 25;
	private static final int START_Y = 75;
	
	private static final int PLOT_TITLE_HEIGHT = 24;
	private static final int PLOT_ENTRY_HEIGHT = 16;
	private static final int PLOT_TITLE_TOP = 500;
	private static final int PLOT_LEGEND_TOP = PLOT_TITLE_TOP + 10;
	
	private static final int LEGEND_WIDTH = 225;
	private static final int LEGEND_HEIGHT = 60;
	private static final int LEGEND_GAP = 20;
	
	public Legend() {
		// *pin drops*
	}
	
	
	public static void update() {
		drones.clear();
		plots.clear();
		
		for (MapItems mapItem : MapItems.getList()) {
			if (mapItem instanceof DroneInterface) {
				if (drones.size() < MAX_DRONES) {
					drones.add(mapItem);
				}
			} else {
				if (plots.size() < MAX_PLOTS) {
					plots.add(mapItem);
				}
			}
		}
		System.out.println("Drones: " + drones.size() + ", Plots: " + plots.size());
	}
	
	public static void render(Graphics g) {
		drawDrones(g, START_Y);
		drawPlots(g);
	}


	private static void drawDrones(Graphics g, int top) {
		g.setColor(Simulator.getGraphColor());
		
		if (!drones.isEmpty()) {
			
			for (int i = 0; i < drones.size(); i++) {
				g.drawRect(START_X, top + ((LEGEND_HEIGHT + LEGEND_GAP) * i), LEGEND_WIDTH, LEGEND_HEIGHT);
			}

		}
	}


	private static void drawPlots(Graphics g) {
		g.setColor(Simulator.getGraphColor());
		
		g.setFont(new Font("Times New Roman", Font.PLAIN, PLOT_TITLE_HEIGHT));
		g.drawString("PLOTS:", START_X, PLOT_TITLE_TOP);
		

		
		if (!plots.isEmpty()) {
			for (int i = 0; i < plots.size(); i++) {
				
				int x = START_X + (Simulator.getMapItemWidth() / 2);
				int y = PLOT_LEGEND_TOP + (i * g.getFontMetrics().getHeight() + 5);
				
				plots.get(i).getIndicator().drawIndicator(g, x, y);	
				
				g.setColor(Simulator.getGraphColor());
				g.setFont(new Font("Times New Roman", Font.PLAIN, PLOT_ENTRY_HEIGHT));
				g.drawString(":  " + plots.get(i).getName(), START_X + (Simulator.getMapItemWidth() * 2) + 5, y + (g.getFontMetrics().getHeight() / 2) - 2);
				
			}
		}

	}
}