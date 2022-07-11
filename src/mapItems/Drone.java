package mapItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

import main.Graph;
import main.Node;
import main.Simulator;
import objects.CentralHub;

public abstract class Drone {
	
	// This stores a reference to all drones all the time.
	private static ArrayList<Drone> drones = new ArrayList<>();

	// Current location of the drone
	private Point location;
	
	// Given name of the drone
	private String name;
	
	// Student's name who designed the drone.
	private String studentName;
	
	// Color to display the drone with
	private Color color;
	
	// Can be used to block the drone from moving
	protected boolean moving;
	
	// Speed of drone in MPH. Variable speed not supported
	private int speedMPH;
	
	// Movement variables
	private double lastCount = 0;
	private double secPerSpot;
	private int pathLoc = 0;
	
	// Sets if the drone should always return home after travel.
	private boolean returnHome;

	// Current Destination Node
	protected Node destination = null;

	// List of nodes from current location to next destination point.
	private ArrayList<Node> path = new ArrayList<>();

	// Nodes to Travel to.
	private Stack<Node> destinationPoints = new Stack<>();

	protected Drone(Point location, String name, String studentName, Color color, boolean returnHome, int speedMPH) {
		this.location = location;
		this.name = name;
		this.studentName = studentName;
		this.color = color;
		this.returnHome = returnHome;
		this.speedMPH = speedMPH;

		moving = false;
		drones.add(this);

		if (returnHome) {
			destinationPoints.push(CentralHub.getHome());
		}
		getMoveSpeed();
	}

	public abstract void loop();

	public abstract void arrived();

	public void update(int elapsedSec) {

		// If there is no destination set but destination points exist then move a
		// destination from list to set destination
		if (destination == null && destinationPoints.size() > 0) {
			destination = destinationPoints.pop();
		}

		// If there is a destination but no path then make a path
		if (destination != null && (path == null || path.isEmpty())) {

			path = Node.FindPath(getNodeFromPoint(location), destination);

			lastCount = elapsedSec;
			pathLoc = 0;
		}

		// if a path exists and you are not at the last step then attempt to progress
		if (path != null && path.size() > 0 && pathLoc < path.size() - 1) {

			// if its been more than secPerSpot
			if (elapsedSec - lastCount > Math.round(secPerSpot)) {

				// If its been longer than secPerSpot move to next pathLoc.
				location.move(Graph.graphXtoScreenX(path.get(pathLoc).getX()),
						Graph.graphYtoScreenY(path.get(pathLoc).getY()));

				// if pathLoc is at the last index of path...
				if (pathLoc + 1 < path.size()) {
					pathLoc++;
					lastCount += secPerSpot;
				}
				
			} 
		}

		if (getNodeFromPoint(location).equals(destination)) {
			if (path != null) {
				path.clear();
			}
			pathLoc = 0;
			destination = null;
			arrived();
		}

	}

	private Node getNodeFromPoint(Point point) {
		return new Node(Graph.screenXtoGraphX((int) point.getX()), Graph.screenYtoGraphY((int) point.getY()), null);
	}

	public abstract void activate();

	// methods for student use
	public void addDestinationPoint(Node destination) {
		if (destinationPoints.size() == 0 && returnHome) {
			destinationPoints.push(CentralHub.getHome());
		}
		destinationPoints.push(destination);
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.drawOval((int) location.getX() - (Graph.getMapDroneDiameter() / 2),
				(int) location.getY() - (Graph.getMapDroneDiameter() / 2), Graph.getMapDroneDiameter(),
				Graph.getMapDroneDiameter());
		g.fillOval((int) location.getX() - (Graph.getMapDroneDiameter() / 2),
				(int) location.getY() - (Graph.getMapDroneDiameter() / 2), Graph.getMapDroneDiameter(),
				Graph.getMapDroneDiameter());

		g.setColor(Simulator.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		g.drawString(getStudentName(), (int) location.getX() - (g.getFontMetrics().stringWidth(getStudentName()) / 2),
				(int) location.getY() + Graph.getMapDroneDiameter() + 15);

		if (destination != null && Graph.showLine) {
			g.setColor(Color.GREEN);
			g.drawLine((int) location.getX(), (int) location.getY(), Graph.graphXtoScreenX(destination.getX()),
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
		return (int) Math.round(location.getX());
	}

	public int getPosY() {
		return (int) Math.round(location.getY());
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
