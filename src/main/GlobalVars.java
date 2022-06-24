package main;

import java.awt.Color;

public class GlobalVars {
	// GLOBAL VARIABLES - SET THESE
	public static final int MAP_ITEM_DIM = 7;	// Plot oval diameter
	public static final int MAP_OBS_DIM = 3;	// obstacle range from center (2 equals a 5x5 square)
	public static final int MAP_DRONE_DIM = 10; // Drone diameter
	
	public static final String PLOT_A_NAME = "Fire";
	public static final String PLOT_B_NAME = "Water";
	
	private static final Color PLOT_A_COLOR = Color.BLUE;
	private static final Color PLOT_B_COLOR = Color.GREEN;
	private static final Color OBS_COLOR = Color.RED;
	
	private static final int GRAPH_X = 275;
	private static final int GRAPH_Y = 75;
	private static final int GRAPH_WIDTH = 500;
	
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color GRAPH_COLOR = Color.WHITE;
	private static final Color TEXT_COLOR = Color.WHITE;
	
	
	public static Color getObsColor() {
		return OBS_COLOR;
	}
	public static Color getPlotAColor() {
		return PLOT_A_COLOR;
	}
	public static Color getPlotBColor() {
		return PLOT_B_COLOR;
	}
	public static int getMapItemDim() {
		return MAP_ITEM_DIM;
	}
	public static int getMapObsDim() {
		return MAP_OBS_DIM;
	}
	public static int getGraphWidth() {
		return GRAPH_WIDTH;
	}
	public static String getPlotAName() {
		return PLOT_A_NAME;
	}
	public static String getPlotBName() {
		return PLOT_B_NAME;
	}
	public static int getGraphX() {
		return GRAPH_X;
	}
	public static int getGraphY() {
		return GRAPH_Y;
	}
	public static Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}
	public static Color getGraphColor() {
		return GRAPH_COLOR;
	}
	public static Color getTextColor() {
		return TEXT_COLOR;
	}
	public static int getMapDroneDim() {
		return MAP_DRONE_DIM;
	}
	
	
}
