package mapItems;

import java.awt.Color;
import java.util.ArrayList;

import ui.Legend;

public abstract class MapItems {

	public String name;
	public Color color;
	public Design type;
	
	public int posX;
	public int posY;
	
	public static ArrayList<MapItems> mapItemList = new ArrayList<>();
	
	public Indicator indicator;
	
	public MapItems(String name, Color color, Design type, int startX, int startY) {
		this.name = name;
		this.color = color;
		indicator = new Indicator(color, type, name);
		posX = startX;
		posY = startY;
		MapItems.mapItemList.add(this);
		Legend.update();
	}
	
	public abstract void update();
	
	public static ArrayList<MapItems> getList() {
		return mapItemList;
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

	
}
