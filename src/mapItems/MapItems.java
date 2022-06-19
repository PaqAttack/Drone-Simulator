package mapItems;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class MapItems {

	public int posX;
	public int posY;
	
	public float speed;
	
	public static ArrayList<MapItems> mapItemList = new ArrayList<>();
	
	public MapItems() {
		MapItems.mapItemList.add(this);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public static ArrayList<MapItems> getList() {
		return mapItemList;
	}

}
