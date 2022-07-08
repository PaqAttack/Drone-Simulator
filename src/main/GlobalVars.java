package main;

import java.awt.Color;

/**
 * Repository of all Global Variables
 * @author Christopher Paquin
 *
 */
public class GlobalVars {
	// GLOBAL VARIABLES - SET THESE
	public static final int TIME_SCALE = 15;			// number of seconds in simulation that pass every real second
	public static final int GRAPH_LENGTH_IN_MILES = 5;	// height and width represented by graph
	
	public static final double MILES_REPRESENTED_BY_EACH_GRAPH_BLOCK = 0.05;
	
	public static final int MAP_ITEM_DIM = 7;	// Plot oval diameter
	public static final int MAP_OBS_DIM = 3;	// obstacle range from center (2 equals a 5x5 square)
	public static final int MAP_DRONE_DIM = 10; // Drone diameter
	
	public static final int MAP_PLOT_SPACE = 4; // Spaces around plots that are protected
	
	public static boolean showLine = false;
	
	public static final String PLOT_A_NAME = "Fire";
	public static final String PLOT_B_NAME = "Camper";
	
	private static final Color PLOT_A_COLOR = Color.RED;
	private static final Color PLOT_B_COLOR = Color.BLUE;
	private static final Color OBS_COLOR = Color.GREEN;
	
	private static final int GRAPH_X = 275;
	private static final int GRAPH_Y = 75;
	private static final int GRAPH_WIDTH = 500;
	
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color GRAPH_COLOR = Color.WHITE;
	private static final Color TEXT_COLOR = Color.WHITE;
	
	
	public static double getMilesRepresentedByEachGraphBlock() {
		return MILES_REPRESENTED_BY_EACH_GRAPH_BLOCK;
	}
	public static boolean isShowLine() {
		return showLine;
	}
	public static int getGraphLengthInMiles() {
		return GRAPH_LENGTH_IN_MILES;
	}
	public static int getTimeScale() {
		return TIME_SCALE;
	}
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
	public static int getMapPlotSpace() {
		return MAP_PLOT_SPACE;
	}
	public static double getGraphBlockMiles() {
		return MILES_REPRESENTED_BY_EACH_GRAPH_BLOCK;
	}
	
	
}
