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
	
	private Point location;
	private double realX, realY;
	private String name;
	private String studentName;
	private Color color;
	protected boolean moving;
	private Point destination;
	private Point nextStep;
	private int speedMPH;
	private int lastCount = 0;
	double secPerSpot;
	private int pathLoc = 0;
	private List<Point> path;
	
	public Drone(Point location, String name, String studentName, Color color, int speedMPH) {
		super();
		this.location = location;
		this.name = name;
		this.studentName = studentName;
		this.color = color;
		this.speedMPH = speedMPH;
		moving = false;
		drones.add(this);
		
		getMoveSpeed();
	
	}


	public abstract void loop();
	
	public void update(int elapsedSec) {
		if (path == null && destination != null) {
			if (!destination.equals(location)) {
				path = Point.FindPath(Graph.getGraph(), location, destination);
				pathLoc = 0;
				System.out.println("Path found");
				lastCount = elapsedSec;
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
	
	
	
}
