package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import main.GlobalVars;
import main.Simulator;
import mapItems.Indicator;
import mapItems.MapItems;

public class Legend {
	
	private static final int START_X = 25;
	private static final int START_Y = 75;
	
	private static final int PLOT_TITLE_HEIGHT = 24;
	private static final int PLOT_ENTRY_HEIGHT = 16;
	private static final int PLOT_TITLE_TOP = 500;
	private static final int PLOT_LEGEND_TOP = PLOT_TITLE_TOP + 10;
	
	private static final int LEGEND_WIDTH = 225;
	private static final int LEGEND_HEIGHT = 60;
	private static final int LEGEND_GAP = 20;
	
	private static Point indPoint1 = new Point(START_X + 5, PLOT_LEGEND_TOP);
	private static Point txtPoint1 = new Point(START_X + 25, PLOT_LEGEND_TOP + 10);
		
	private static Point indPoint2 = new Point(START_X + 5, PLOT_LEGEND_TOP + 20);
	private static Point txtPoint2 = new Point(START_X + 25, PLOT_LEGEND_TOP + 30);
	
	private static Point indPoint3 = new Point(START_X + 5, PLOT_LEGEND_TOP + 40);
	private static Point txtPoint3 = new Point(START_X + 25, PLOT_LEGEND_TOP + 50);

	public Legend() {
		// *pin drops*
	}
	
	
	public static void update() {
		// *pin drops*
	}
	
	public static void render(Graphics g) {
		drawDrones(g, START_Y);
		drawPlots(g);
	}


	private static void drawDrones(Graphics g, int top) {
		g.setColor(GlobalVars.getGraphColor());
		
		if (!MapItems.getDrones().isEmpty()) {
			
			for (int i = 0; i < MapItems.getDrones().size(); i++) {
				g.drawRect(START_X, top + ((LEGEND_HEIGHT + LEGEND_GAP) * i), LEGEND_WIDTH, LEGEND_HEIGHT);
			}

		}
	}


	private static void drawPlots(Graphics g) {
		// PLOT HEADER
		g.setColor(GlobalVars.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, PLOT_TITLE_HEIGHT));
		g.drawString("PLOTS:", START_X, PLOT_TITLE_TOP);
		
		// PLOT A LEGEND
		g.setColor(GlobalVars.getPlotAColor());
		g.drawRect(indPoint1.x, indPoint1.y, 10, 10);
		g.fillRect(indPoint1.x, indPoint1.y, 10, 10);
			
		g.setColor(GlobalVars.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, PLOT_ENTRY_HEIGHT));
		g.drawString(":  " + GlobalVars.getPlotAName(), txtPoint1.x, txtPoint1.y);
		
		// PLOT A LEGEND
		g.setColor(GlobalVars.getPlotBColor());
		g.drawRect(indPoint2.x, indPoint2.y, 10, 10);
		g.fillRect(indPoint2.x, indPoint2.y, 10, 10);
			
		g.setColor(GlobalVars.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, PLOT_ENTRY_HEIGHT));
		g.drawString(":  " + GlobalVars.getPlotBName(), txtPoint2.x, txtPoint2.y);

		// OBSTACLE LEGEND
		g.setColor(GlobalVars.getObsColor());
		g.drawRect(indPoint3.x, indPoint3.y, 10, 10);
		g.fillRect(indPoint3.x, indPoint3.y, 10, 10);
		
		g.setColor(GlobalVars.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, PLOT_ENTRY_HEIGHT));
		g.drawString(":  Obstacle", txtPoint3.x, txtPoint3.y);

	}

	
	
}
