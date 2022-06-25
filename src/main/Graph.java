package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Graph {

	private static final int GRAPH_WIDTH = 100;
	private static final int GRAPH_HEIGHT = 100;
	
	private static final int GRAPH_PLOT_WIDTH = 5;
	private static final int GRAPH_PLOT_HEIGHT = 5;
	
	private static int[][] graph = new int[GRAPH_WIDTH][GRAPH_HEIGHT];
	private static Rectangle bounds = new Rectangle(GlobalVars.getGraphX(), GlobalVars.getGraphY(), GRAPH_PLOT_WIDTH * GRAPH_WIDTH, GRAPH_HEIGHT * GRAPH_PLOT_HEIGHT);
	
	public static void changeGraphID(int newID, int mouseX, int mouseY) {
		if (bounds.contains(mouseX, mouseY)) {
			int gPosX = screenXtoGraphX(mouseX);
			int gPosY = screenYtoGraphY(mouseY);;
			graph[gPosX][gPosY] = newID;
		} 
	}
	
	public static void changeGraphIDbyCoord(int newID, int x, int y) {
		if (x >= 0 && x < GRAPH_WIDTH) {
			if (y >= 0 && y < GRAPH_HEIGHT) {
				graph[x][y] = newID;
			}
		}
	}
	
	public static int getGraphValue(int x, int y) {
		return graph[x][y];
	}
	
	public static int graphXtoScreenX(int graphCoord) {
		return GlobalVars.getGraphX() + (graphCoord * GRAPH_PLOT_WIDTH) + (GRAPH_PLOT_WIDTH / 2);
	}
	
	public static int graphYtoScreenY(int graphCoord) {
		return GlobalVars.getGraphY() + (GRAPH_PLOT_HEIGHT * GRAPH_HEIGHT) - (graphCoord * GRAPH_PLOT_HEIGHT);
	}
	
	public static int screenXtoGraphX(int screenCoord) {
		return (screenCoord - GlobalVars.getGraphX()) / GRAPH_PLOT_WIDTH;
	}
	
	public static int screenYtoGraphY(int screenCoord) {
		return GRAPH_HEIGHT - (screenCoord - GlobalVars.getGraphY()) / GRAPH_PLOT_HEIGHT;
	}
	
	public static void initGraph() {
		for (int x = 0; x < GRAPH_WIDTH; x++) {
			for (int y = 0; y < GRAPH_HEIGHT; y++) {
				graph[x][y] = 0;
			}
		}
	}

	public static Point getNextPoint(Point start, Point end) {
        List<Point> path = Point.FindPath(graph, start, end);
        for (Point p : path) {
        	System.out.println("point X: " + p.getX() + ", Y: " + p.getY());
        }
        
        if (path != null) {
        	return path.get(1);
        }
        return start;
	}

	
	public static void renderGraph(Graphics g) {
		// draw border
		renderBorder(g);
		
		// draw pixels
		renderInterior(g);
	}
	
	public static boolean doesExist(int x, int y) {
		if (x >= 0 && x < GRAPH_WIDTH) {
			if (y >= 0 && y < GRAPH_HEIGHT) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean doesExist(Point point) {
		return doesExist(point.getX(), point.getY());
	}
	
	private static void renderBorder(Graphics g) {
		g.setColor(GlobalVars.getGraphColor());
		g.drawRect(GlobalVars.getGraphX() - 1, GlobalVars.getGraphY() - 1, (GRAPH_WIDTH * GRAPH_PLOT_WIDTH) + 2, (GRAPH_HEIGHT * GRAPH_PLOT_HEIGHT) + 2);
		g.drawRect(GlobalVars.getGraphX() - 2, GlobalVars.getGraphY() - 2, (GRAPH_WIDTH * GRAPH_PLOT_WIDTH) + 4, (GRAPH_HEIGHT * GRAPH_PLOT_HEIGHT) + 4);
	}
	
	private static void renderInterior(Graphics g) {
		for (int x = 0; x < GRAPH_WIDTH; x++) {
			for (int y = 0; y < GRAPH_HEIGHT; y++) {
				switch (graph[x][y]) {
					case 0: {		// Blank
						g.setColor(Color.BLACK);
						break;
					}
					case 1: {		// Obstacle
						g.setColor(GlobalVars.getObsColor());
						break;
					}
					case 2: {		// PLOT Target A
						g.setColor(Color.BLACK);
						break;
					}
					case 3: {		// PLOT Target B
						g.setColor(Color.BLACK);
						break;
					}

				}
				g.drawRect(
						GlobalVars.getGraphX() + (x * GRAPH_PLOT_WIDTH), 
						GlobalVars.getGraphY() + (GRAPH_HEIGHT * GRAPH_PLOT_HEIGHT) - (y * GRAPH_PLOT_HEIGHT) - 5, 
						GRAPH_PLOT_WIDTH, 
						GRAPH_PLOT_HEIGHT);
				
				g.fillRect(
						GlobalVars.getGraphX() + (x * GRAPH_PLOT_WIDTH), 
						GlobalVars.getGraphY() + (GRAPH_HEIGHT * GRAPH_PLOT_HEIGHT) - (y * GRAPH_PLOT_HEIGHT) - 5, 
						GRAPH_PLOT_WIDTH, 
						GRAPH_PLOT_HEIGHT);
				
			}
		}
	}

	public static Rectangle getBounds() {
		return bounds;
	}

	public static int getGraphWidth() {
		return GRAPH_WIDTH;
	}

	public static int getGraphHeight() {
		return GRAPH_HEIGHT;
	}

	public static int getGraphPlotWidth() {
		return GRAPH_PLOT_WIDTH;
	}

	public static int getGraphPlotHeight() {
		return GRAPH_PLOT_HEIGHT;
	}

	public static int[][] getGraph() {
		return graph;
	}
	
	
	
}
