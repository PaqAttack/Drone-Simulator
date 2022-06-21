package mapItems;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class MapItems {

	private static final int MAX_DRONES = 5;
	
	public String name;
	public Color color;
	public MapIcon type;
	
	public int posX;
	public int posY;
	
	public int width, height;
	
	private static ArrayList<MapItems> mapItemList = new ArrayList<>();
	private static ArrayList<MapItems> drones = new ArrayList<>();
	private static ArrayList<MapItems> plots = new ArrayList<>();
	
	public Indicator indicator;
	private Rectangle bounds;
	
	public MapItems(String name, Color color, MapIcon type, int startX, int startY) {
		this.name = name;
		this.color = color;
		indicator = new Indicator(color, type, name);
		posX = startX;
		posY = startY;
		
		bounds = new Rectangle(startX, startY, indicator.getWidth(), indicator.getHeight());
		
		updateLists();
	}
	
	public abstract void update();
	
	public void updatePos() {
		indicator.setIndPosX(posX);
		indicator.setIndPosY(posY);
		bounds.x = posX;
		bounds.y = posY;
	}
	
	public static ArrayList<MapItems> getList() {
		return mapItemList;
	}

	private void updateLists() {
//		if (this instanceof DroneInterface) {
//			if (drones.size() + 1 > MAX_DRONES) {
//				System.out.println("Drone count exceeds max supported drones. Deleteing Extras.");
//			} else {
//				MapItems.mapItemList.add(this);
//				drones.add(this);
//			}
//		} else {
//			MapItems.mapItemList.add(this);
//			plots.add(this);
//		}
	}
	
	public static ArrayList<MapItems> getDrones() {
		return drones;
	}

	public static ArrayList<MapItems> getPlots() {
		return plots;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Color getColor() {
		return color;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	
}
