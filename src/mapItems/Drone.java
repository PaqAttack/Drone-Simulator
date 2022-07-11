package mapItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import main.Graph;
import main.Node;
import main.Simulator;

public abstract class Drone {

	private static ArrayList<Drone> drones = new ArrayList<>();
	
	protected Node node;
	private String name;
	private String studentName;
	private Color color;
	protected boolean moving;
	protected Node destination;
	private int speedMPH;
	private int lastCount = 0;
	private double secPerSpot;
	private int pathLoc = 0;
	private List<Node> path;
	private List<Node> destinationPoints;
	
	public Drone(Node node, String name, String studentName, Color color, int speedMPH) {
		super();
		this.node = node;
		this.name = name;
		this.studentName = studentName;
		this.color = color;
		this.speedMPH = speedMPH;
		moving = false;
		drones.add(this);
		destinationPoints = new ArrayList<>();
		getMoveSpeed();
	}

	public abstract void loop();
	
	public abstract void arrived();
	
	public void update(int elapsedSec) {
		if (destination == null && destinationPoints.size() > 0) {
			destination = destinationPoints.get(0);
			destinationPoints.remove(0);
			
		}
		
		if (path == null && destination != null) {
			if (!destination.equals(node)) {
				path = Node.FindPath(node, destination);
				pathLoc = 0;
				lastCount = elapsedSec;
			} else {
				destination = null;
				moving = false;
				path = null;
				arrived();
			}
		}
		
		if (path != null) {
			if (!node.equals(destination)) {
				if ((elapsedSec - lastCount) > secPerSpot) {
					node.setX(path.get(pathLoc).getX());
					node.setY(path.get(pathLoc).getY());
					if (node != destination) {
						pathLoc++;
					} 
					lastCount = elapsedSec;
				}
			} else {
				destination = null;
				moving = false;
				path = null;
				arrived();
			}
		}
		
		
	}
	
	public abstract void activate();
	
	
	// methods for student use
	public void setDestination (Node destination) {
		this.destination = destination;
	}
	
	public void moveOther(Plot itemToBeMoved, Node destination) {
		
	}
	
	public void deleteItem(Plot item) {
		
	}
	
	public void deleteItemOnPoint(Node location) {
		
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.drawOval(Graph.graphXtoScreenX(node.getX()) - (Graph.getMapDroneDiameter() / 2), Graph.graphYtoScreenY(node.getY()) - (Graph.getMapDroneDiameter() / 2), Graph.getMapDroneDiameter(), Graph.getMapDroneDiameter());
		g.fillOval(Graph.graphXtoScreenX(node.getX()) - (Graph.getMapDroneDiameter() / 2), Graph.graphYtoScreenY(node.getY()) - (Graph.getMapDroneDiameter() / 2), Graph.getMapDroneDiameter(), Graph.getMapDroneDiameter());
		
		g.setColor(Simulator.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		g.drawString(getStudentName(), Graph.graphXtoScreenX(node.getX()) - (g.getFontMetrics().stringWidth(getStudentName()) / 2), Graph.graphYtoScreenY(node.getY()) + Graph.getMapDroneDiameter() + 15);
		
		if (destination != null && Graph.showLine) {
			g.setColor(Color.GREEN);
			g.drawLine(Graph.graphXtoScreenX(node.getX()), 
					Graph.graphYtoScreenY(node.getY()), 
					Graph.graphXtoScreenX(destination.getX()), 
					Graph.graphYtoScreenY(destination.getY()));
		}
	}


	private void getMoveSpeed() {
		double feetPerMile = 5280;
		double inchesPerMile = feetPerMile * 12;
		
		double inchesTotal = inchesPerMile * Simulator.getGridLengthInMiles();
		double inchesPerBlock = inchesTotal / Graph.getGraphLengthInPlots();
		
		double feetPerHour = speedMPH * feetPerMile;
		double inchesPerHour = feetPerHour * 12;
		double inchesPerMin = inchesPerHour / 60;
		double inchesPerSec = inchesPerMin / 60;
		
		secPerSpot = inchesPerBlock / inchesPerSec;
	}
	
	public int getPosX() {
		return node.getX();
	}

	public int getPosY() {
		return node.getY();
	}

	public String getName() {
		return name;
	}

	public String getStudentName() {
		return studentName;
	}

	public Color getColor() {
		return color;
	}

	public static ArrayList<Drone> getDrones() {
		return drones;
	}

	public double getSecPerSpot() {
		return secPerSpot;
	}

	public List<Node> getDestinationPoints() {
		return destinationPoints;
	}

	public void setDestinationPoints(List<Node> destinationPoints) {
		this.destinationPoints = destinationPoints;
	}
	
	public static Drone getDroneByStudentName(String name) {
		for (Drone d : drones) {
			if (d.getStudentName().equalsIgnoreCase(name)) {
				return d;
			}
		}
		return null;
	}

	public Node getDestination() {
		return destination;
	}
	
}
