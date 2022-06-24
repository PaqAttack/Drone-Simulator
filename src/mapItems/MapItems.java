//package mapItems;
//
//import java.awt.Color;
//import java.awt.Rectangle;
//import java.util.ArrayList;
//
//public abstract class MapItems {
//	
//	public String name;
//	public Color color;
//	
//	public int posX;
//	public int posY;
//	
//	public int width, height;
//	
//	public Indicator indicator;
//	private Rectangle bounds;
//	
//	public MapItems(String name, Color color, int x, int y) {
//		this.name = name;
//		this.color = color;
//		indicator = new Indicator(color, name);
//		posX = x;
//		posY = y;
//		
//		bounds = new Rectangle(x, y, indicator.getWidth(), indicator.getHeight());
//		
//		updateLists();
//	}
//	
//	public abstract void update();
//	
//	public void updatePos() {
//		indicator.setIndPosX(posX);
//		indicator.setIndPosY(posY);
//		bounds.x = posX;
//		bounds.y = posY;
//	}
//	
//	private void updateLists() {
//
//	}
//
//
//	public Indicator getIndicator() {
//		return indicator;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public int getPosX() {
//		return posX;
//	}
//
//	public void setPosX(int posX) {
//		this.posX = posX;
//	}
//
//	public int getPosY() {
//		return posY;
//	}
//
//	public void setPosY(int posY) {
//		this.posY = posY;
//	}
//
//	public Color getColor() {
//		return color;
//	}
//
//	public Rectangle getBounds() {
//		return bounds;
//	}
//
//	public void setBounds(Rectangle bounds) {
//		this.bounds = bounds;
//	}
//
//	
//}
