package mapItems;

import java.util.ArrayList;

import main.GlobalVars;
import objects.TargetPlotA;

public class DroneScannerA {

	private ArrayList<Plot> scannedItemList;
	private double range;
	private Drone drone;
	
	public DroneScannerA(Drone drone, double rangeInMiles) {
		this.range = rangeInMiles;
		this.drone = drone;
		scannedItemList = new ArrayList<>();
	}
	
	public int scan() {
		for (Plot p : Plot.getPlots()) {
			if (p instanceof TargetPlotA) {
				if (getDistance(p) < range / GlobalVars.getGraphBlockMiles()) {
					if (!scannedItemList.contains(p)) {
						scannedItemList.add(p);
					}
				}
			}
		}
		return scannedItemList.size();
	}

	private double getDistance(Plot p) {
		double distX = Math.abs(p.getLocation().getX() - drone.getPosX());
		double distY = Math.abs(p.getLocation().getY() - drone.getPosY());
		return Math.sqrt(((distX * distX) + (distY * distY)));
	}
	
	public ArrayList<Plot> getScannedItemList() {
		return scannedItemList;
	}
}
