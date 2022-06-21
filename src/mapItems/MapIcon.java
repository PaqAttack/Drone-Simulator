package mapItems;

public enum MapIcon {
	RECTANGLE,
	CIRCLE;

	public static MapIcon myDesign = RECTANGLE;
	
	public static void setType(MapIcon design) {
		myDesign = design;
	}
}
