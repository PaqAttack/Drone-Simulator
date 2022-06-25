package mapItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import main.GlobalVars;
import main.Graph;
import main.Point;
import main.Simulator;

public abstract class Drone {

	private static ArrayList<Drone> drones = new ArrayList<>();
	
	private Point location;
	private String name;
	private String studentName;
	private Color color;
	protected boolean moving;
	private Point destination;
	private Point nextStep;
	private int speedMPH;
	private int lastCount = 0;
	double secPerSpot;
	
	public Drone(Point location, String name, String studentName, Color color, int speedMPH) {
		super();
		this.location = location;
		this.name = name;
		this.studentName = studentName;
		this.color = color;
		this.speedMPH = speedMPH;
		moving = false;
		drones.add(this);
		
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

	public abstract void loop();
	
	public void update(int counter) {
		if (!destination.equals(location) && moving) {
			if (counter - lastCount >= secPerSpot) {
				// move
				lastCount = counter;
				nextStep = Graph.getNextPoint(location, destination);
				location.x = nextStep.x;
				location.y = nextStep.y;
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
