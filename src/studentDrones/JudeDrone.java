package studentDrones;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.Point;
import mapItems.CommInterface;
import mapItems.DataType;
import mapItems.Drone;
import mapItems.DroneScannerB;
import mapItems.Message;
import mapItems.Plot;
import objects.CentralHub;

public class JudeDrone extends Drone implements CommInterface{

	DroneScannerB scanner;
	Point camper;
	ArrayList<Plot> ignoreList;
	
	public JudeDrone(Point location, String name, String studentName, Color color, int speedMPH) {
		super(location, name, studentName, color, speedMPH);
		ignoreList = new ArrayList<>();
		scanner = new DroneScannerB(this, 10);
	}

	@Override
	public void transmit(CommInterface reciever, Message msg) {
		reciever.recieve(this, msg);
	}

	@Override
	public void recieve(CommInterface transmitter, Message msg) {
		if (msg.getMsg().equalsIgnoreCase("START")) {
			List<Point> destPoints = new ArrayList<>();
			destPoints.add(new Point(80, 20, null));
			destPoints.add(new Point(80, 80, null));
			destPoints.add(new Point(60, 80, null));
			destPoints.add(new Point(60, 20, null));
			destPoints.add(new Point(40, 20, null));
			destPoints.add(new Point(40, 80, null));
			destPoints.add(new Point(20, 80, null));
			destPoints.add(new Point(4, 4, null));
			setDestinationPoints(destPoints);
			moving = true;
			System.out.println("Human Finder Drone Deployed.");
		}
	}

	@Override
	public void loop() {
		if (scanner.scan() > 0) {
			//found Human
			for (Plot p : scanner.getScannedItemList()) {
				if (!ignoreList.contains(p)) {
					transmit(CentralHub.getHUBs().get(0), new Message("Human", DataType._POINT, p.getLocation()));
					ignoreList.add(p);
					System.out.println("Human finder drone sends detected human location to central HUB.");
				}
			}
			
		}
		
	}

	@Override
	public void activate() {

	}

	

}
