package mapItems;

import java.util.ArrayList;

import main.Graph;
import main.Simulator;
import objects.TargetPlotB;

public class DroneScannerB {

	private ArrayList<Plot> scannedItemList;
	private double range;
	private Drone drone;

	public DroneScannerB(Drone drone, double rangeInMiles) {
		this.range = rangeInMiles;
		this.drone = drone;
		scannedItemList = new ArrayList<>();
	}

	public int scan() {
		for (Plot p : Plot.getPlots()) {
			if (p instanceof TargetPlotB) {
				if (getDistance(p) < range / Simulator.getMilesRepresentedByEachGraphBlock()) {
					if (!scannedItemList.contains(p)) {
						scannedItemList.add(p);
					}
				}
			}
		}
		return scannedItemList.size();
	}

	private double getDistance(Plot p) {
		double distX = Math.abs(p.getNode().getX() - Graph.screenXtoGraphX(drone.getPosX()));
		double distY = Math.abs(p.getNode().getY() - Graph.screenYtoGraphY(drone.getPosY()));
		return Math.sqrt(((distX * distX) + (distY * distY)));
	}

	public ArrayList<Plot> getScannedItemList() {
		return scannedItemList;
	}

}
