package mapItems;

public enum Design {
	RECTANGLE,
	CIRCLE;

	
	public static Design myDesign = RECTANGLE;
	
	public static void setType(Design design) {
		myDesign = design;
	}
}
