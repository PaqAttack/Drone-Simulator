package mapItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import main.Graph;
import main.Node;
import main.Simulator;

public abstract class Plot {
	private Node node;
	protected boolean moving;
	private Color color;
	private Node destination;
	private Node nextStep;
	private int speedMPH;
	private int lastCount = 0;
	private String name;

	private static ArrayList<Plot> plots = new ArrayList<>();

	public Plot(Node node, Color color, String name) {
		super();
		this.node = node;
		this.color = color;
		this.name = name;
		plots.add(this);
	}

	public abstract void activate();

	public abstract void update();

	public void render(Graphics g) {
		g.setColor(color);
		g.drawOval(Graph.graphXtoScreenX(node.getX()) - (Graph.getMapItemDiameter() / 2),
				Graph.graphYtoScreenY(node.getY()) - (Graph.getMapItemDiameter() / 2), Graph.getMapItemDiameter(),
				Graph.getMapItemDiameter());
		g.fillOval(Graph.graphXtoScreenX(node.getX()) - (Graph.getMapItemDiameter() / 2),
				Graph.graphYtoScreenY(node.getY()) - (Graph.getMapItemDiameter() / 2), Graph.getMapItemDiameter(),
				Graph.getMapItemDiameter());

		g.setColor(Simulator.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		g.drawString(name, Graph.graphXtoScreenX(node.getX()) - (g.getFontMetrics().stringWidth(name) / 2),
				Graph.graphYtoScreenY(node.getY()) + Graph.getMapItemDiameter() + 15);
	}

	public void remove() {
		System.out.println("Camper at point " + getNode().getX() + ", " + getNode().getY() + " evacuated.");
		plots.remove(this);
	}

	public static Plot getPlotByLocation(Node p) {
		for (Plot plot : plots) {
			if (plot.getNode().equals(p)) {
				return plot;
			}
		}
		return null;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node destination) {
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

	public Node getNextStep() {
		return nextStep;
	}

	public static ArrayList<Plot> getPlots() {
		return plots;
	}

}
