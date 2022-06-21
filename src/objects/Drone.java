package objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import main.GlobalVars;
import main.Graph;

public abstract class Drone {

	private static ArrayList<Drone> drones = new ArrayList<>();
	
	private int posX, posY;
	private String name;
	private String studentName;
	private Color color;
	
	public Drone(int startPosX, int startPosY, String name, String studentName, Color color) {
		super();
		this.posX = startPosX;
		this.posY = startPosY;
		this.name = name;
		this.studentName = studentName;
		this.color = color;
		drones.add(this);
	}

	public void move (int destX, int destY) {
		if (Graph.getGraphValue(destX, destY) != 1) {
			posX = destX;
			posY = destY;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.drawOval(Graph.graphXtoScreenX(posX) - (GlobalVars.getMapItemDim() / 2), Graph.graphYtoScreenY(posY) - (GlobalVars.getMapItemDim() / 2), GlobalVars.getMapItemDim(), GlobalVars.getMapItemDim());
		g.fillOval(Graph.graphXtoScreenX(posX) - (GlobalVars.getMapItemDim() / 2), Graph.graphYtoScreenY(posY) - (GlobalVars.getMapItemDim() / 2), GlobalVars.getMapItemDim(), GlobalVars.getMapItemDim());
		
		g.setColor(GlobalVars.getGraphColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		g.drawString(getStudentName(), Graph.graphXtoScreenX(posX) - (g.getFontMetrics().stringWidth(getStudentName()) / 2), Graph.graphYtoScreenY(posY) + GlobalVars.getMapItemDim() + 15);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
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
	
	
	
}
