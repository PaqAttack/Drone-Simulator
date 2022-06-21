package ui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

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
	
	private static Indicator targetAInd;
	private static Indicator targetBInd;
	private static Indicator obstacleInd;
	
	private static Point indPoint1 = new Point(START_X + (Simulator.getMapItemWidth() / 2), PLOT_LEGEND_TOP);
	private static Point txtPoint1 = new Point(START_X + (Simulator.getMapObsWidth() * 2) - 5, PLOT_LEGEND_TOP + 10);
		
	private static Point indPoint2 = new Point(START_X + (Simulator.getMapItemWidth() / 2), PLOT_LEGEND_TOP + 20);
	private static Point txtPoint2 = new Point(START_X + (Simulator.getMapObsWidth() * 2) - 5, PLOT_LEGEND_TOP + 30);
	
	private static Point indPoint3 = new Point(START_X + (Simulator.getMapItemWidth() / 2) - 5, PLOT_LEGEND_TOP + 40);
	private static Point txtPoint3 = new Point(START_X + (Simulator.getMapObsWidth() * 2) - 5, PLOT_LEGEND_TOP + 55);

	public Legend() {
		// *pin drops*
	}
	
	
	public static void update() {

	}
	
	public static void render(Graphics g) {
		drawDrones(g, START_Y);
		drawPlots(g);
	}


	private static void drawDrones(Graphics g, int top) {
		g.setColor(Simulator.getGraphColor());
		
		if (!MapItems.getDrones().isEmpty()) {
			
			for (int i = 0; i < MapItems.getDrones().size(); i++) {
				g.drawRect(START_X, top + ((LEGEND_HEIGHT + LEGEND_GAP) * i), LEGEND_WIDTH, LEGEND_HEIGHT);
			}

		}
	}


	private static void drawPlots(Graphics g) {
		g.setColor(Simulator.getGraphColor());
		
		g.setFont(new Font("Times New Roman", Font.PLAIN, PLOT_TITLE_HEIGHT));
		g.drawString("PLOTS:", START_X, PLOT_TITLE_TOP);
		
		if (targetAInd != null) {
			targetAInd.drawIndicator(g, indPoint1.x, indPoint1.y);
			
			g.setColor(Simulator.getGraphColor());
			g.setFont(new Font("Times New Roman", Font.PLAIN, PLOT_ENTRY_HEIGHT));
			g.drawString(":  Target A", txtPoint1.x, txtPoint1.y);
		}
		
		if (targetBInd != null) {
			targetBInd.drawIndicator(g, indPoint2.x, indPoint2.y);
			
			g.setColor(Simulator.getGraphColor());
			g.setFont(new Font("Times New Roman", Font.PLAIN, PLOT_ENTRY_HEIGHT));
			g.drawString(":  Target B", txtPoint2.x, txtPoint2.y);
		}
		
		if (obstacleInd != null) {
			obstacleInd.drawIndicator(g, indPoint3.x, indPoint3.y);
			
			g.setColor(Simulator.getGraphColor());
			g.setFont(new Font("Times New Roman", Font.PLAIN, PLOT_ENTRY_HEIGHT));
			g.drawString(":  Obstacle", txtPoint3.x, txtPoint3.y);
		}
	}


	public static void setTargetAInd(Indicator targetAInd) {
		Legend.targetAInd = targetAInd;
	}


	public static void setTargetBInd(Indicator targetBInd) {
		Legend.targetBInd = targetBInd;
	}


	public static void setObstacleInd(Indicator obstacleInd) {
		Legend.obstacleInd = obstacleInd;
	}
	
	
}
