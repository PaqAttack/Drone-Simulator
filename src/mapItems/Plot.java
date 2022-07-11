package mapItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import main.Graph;
import main.Node;
import main.Simulator;

public abstract class Plot {
	private Node location;
	private Color color;
	private String name;

	private static ArrayList<Plot> plots = new ArrayList<>();

	public Plot(Node node, Color color, String name) {
		super();
		this.location = node;
		this.color = color;
		this.name = name;
		plots.add(this);
	}

	public abstract void activate();

	public abstract void update();

	public void render(Graphics g) {
		g.setColor(color);
		g.drawOval(Graph.graphXtoScreenX(location.getX()) - (Graph.getMapItemDiameter() / 2),
				Graph.graphYtoScreenY(location.getY()) - (Graph.getMapItemDiameter() / 2), Graph.getMapItemDiameter(),
				Graph.getMapItemDiameter());
		g.fillOval(Graph.graphXtoScreenX(location.getX()) - (Graph.getMapItemDiameter() / 2),
				Graph.graphYtoScreenY(location.getY()) - (Graph.getMapItemDiameter() / 2), Graph.getMapItemDiameter(),
				Graph.getMapItemDiameter());

		g.setColor(Simulator.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		g.drawString(name, Graph.graphXtoScreenX(location.getX()) - (g.getFontMetrics().stringWidth(name) / 2),
				Graph.graphYtoScreenY(location.getY()) + Graph.getMapItemDiameter() + 15);
	}

	public void remove() {
		plots.remove(this);
	}

	public static Plot getPlotByLocation(Node p) {
		for (Plot plot : plots) {
			if (plot.getLocation().equals(p)) {
				return plot;
			}
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		Plot plot = (Plot) obj;
		if (this.getLocation().equals(plot.getLocation())) {
			return true;
		}
		return false;
	}

	public Node getLocation() {
		return location;
	}

	public void setNode(Node node) {
		this.location = node;
	}

	public static ArrayList<Plot> getPlots() {
		return plots;
	}

}
