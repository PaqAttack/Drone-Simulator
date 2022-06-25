package mapItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import main.GlobalVars;
import main.Graph;
import main.Point;

public abstract class Plot {
	private Point location;
	protected boolean moving;
	private Color color;
	private Point destination;
	private Point nextStep;
	private int speedMPH;
	private int lastCount = 0;
	private double secPerSpot;
	private String name;
	
	private static ArrayList<Plot> plots = new ArrayList<>();
	
	public Plot(Point location, Color color, String name) {
		super();
		this.location = location;
		this.color = color;
		this.name = name;
		plots.add(this);
	}

	public abstract void activate();
	
	public abstract void update();
	
	public void render(Graphics g) {
		g.setColor(color);
		g.drawOval(Graph.graphXtoScreenX(location.x) - (GlobalVars.getMapItemDim() / 2), Graph.graphYtoScreenY(location.y) - (GlobalVars.getMapItemDim() / 2), GlobalVars.getMapItemDim(), GlobalVars.getMapItemDim());
		g.fillOval(Graph.graphXtoScreenX(location.x) - (GlobalVars.getMapItemDim() / 2), Graph.graphYtoScreenY(location.y) - (GlobalVars.getMapItemDim() / 2), GlobalVars.getMapItemDim(), GlobalVars.getMapItemDim());
		
		g.setColor(GlobalVars.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		g.drawString(name, Graph.graphXtoScreenX(location.x) - (g.getFontMetrics().stringWidth(name) / 2), Graph.graphYtoScreenY(location.y) + GlobalVars.getMapItemDim() + 15);
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Point getDestination() {
		return destination;
	}

	public void setDestination(Point destination) {
		this.destination = destination;
	}

	public int getSpeedMPH() {
		return speedMPH;
	}

	public void setSpeedMPH(int speedMPH) {
		this.speedMPH = speedMPH;
	}

	public int getLastCount() {
		return lastCount;
	}

	public void setLastCount(int lastCount) {
		this.lastCount = lastCount;
	}

	public Point getNextStep() {
		return nextStep;
	}

	public static ArrayList<Plot> getPlots() {
		return plots;
	}

}
