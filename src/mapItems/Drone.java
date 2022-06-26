package mapItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import main.GlobalVars;
import main.Graph;
import main.Point;
import main.Simulator;

public abstract class Drone {

	private static ArrayList<Drone> drones = new ArrayList<>();
	
	protected Point location;
	private double realX, realY;
	private String name;
	private String studentName;
	private Color color;
	protected boolean moving;
	protected Point destination;
	private Point nextStep;
	private int speedMPH;
	private int lastCount = 0;
	private double secPerSpot;
	private int pathLoc = 0;
	private List<Point> path;
	private List<Point> destinationPoints;
	
	public Drone(Point location, String name, String studentName, Color color, int speedMPH) {
		super();
		this.location = location;
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
	
	public void update(int elapsedSec) {
		if (destination == null && destinationPoints.size() > 0) {
			destination = destinationPoints.get(0);
			destinationPoints.remove(0);
		}
		
		if (path == null && destination != null) {
			if (!destination.equals(location)) {
				path = Point.FindPath(Graph.getGraph(), location, destination);
				pathLoc = 0;
				lastCount = elapsedSec;
			} else {
				destination = null;
				moving = false;
				path = null;
			}
		}
		
		if (path != null) {
			if (!location.equals(destination)) {
				if ((elapsedSec - lastCount) > secPerSpot) {
					location.x = path.get(pathLoc).getX();
					location.y = path.get(pathLoc).getY();
					if (location != destination) {
						pathLoc++;
					} 
					lastCount = elapsedSec;
				}
			} else {
				destination = null;
				moving = false;
				path = null;
				System.out.println("arrived");
			}
		}
		
		
	}
	
	public abstract void activate();
	
	
	// methods for student use
	public void setDestination (Point destination) {
		this.destination = destination;
	}
	
	public void moveOther(Plot itemToBeMoved, Point destination) {
		
	}
	
	public void deleteItem(Plot item) {
		
	}
	
	public void deleteItemOnPoint(Point location) {
		
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.drawOval(Graph.graphXtoScreenX(location.x) - (GlobalVars.getMapDroneDim() / 2), Graph.graphYtoScreenY(location.y) - (GlobalVars.getMapDroneDim() / 2), GlobalVars.getMapDroneDim(), GlobalVars.getMapDroneDim());
		g.fillOval(Graph.graphXtoScreenX(location.x) - (GlobalVars.getMapDroneDim() / 2), Graph.graphYtoScreenY(location.y) - (GlobalVars.getMapDroneDim() / 2), GlobalVars.getMapDroneDim(), GlobalVars.getMapDroneDim());
		
		g.setColor(GlobalVars.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		g.drawString(getStudentName(), Graph.graphXtoScreenX(location.x) - (g.getFontMetrics().stringWidth(getStudentName()) / 2), Graph.graphYtoScreenY(location.y) + GlobalVars.getMapDroneDim() + 15);
		
		if (destination != null && GlobalVars.isShowLine()) {
			g.setColor(Color.GREEN);
			g.drawLine(Graph.graphXtoScreenX(location.x), 
					Graph.graphYtoScreenY(location.y), 
					Graph.graphXtoScreenX(destination.getX()), 
					Graph.graphYtoScreenY(destination.getY()));
		}
	}


	private void getMoveSpeed() {
		double feetPerMile = 5280;
		double inchesPerMile = feetPerMile * 12;
		
		double inchesTotal = inchesPerMile * GlobalVars.getGraphLengthInMiles();
		double inchesPerBlock = inchesTotal / Graph.getGraphWidth();
		
		double feetPerHour = speedMPH * feetPerMile;
		double inchesPerHour = feetPerHour * 12;
		double inchesPerMin = inchesPerHour / 60;
		double inchesPerSec = inchesPerMin / 60;
		
		secPerSpot = inchesPerBlock / inchesPerSec;
		System.out.println("Seconds Per Spot: " + secPerSpot);
		
	}
	
	public int getPosX() {
		return location.x;
	}

	public int getPosY() {
		return location.y;
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

	public List<Point> getDestinationPoints() {
		return destinationPoints;
	}

	public void setDestinationPoints(List<Point> destinationPoints) {
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

	public Point getDestination() {
		return destination;
	}
	
}
