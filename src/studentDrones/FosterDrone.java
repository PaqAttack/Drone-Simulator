package studentDrones;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.Point;
import mapItems.CommInterface;
import mapItems.DataType;
import mapItems.Drone;
import mapItems.DroneScannerA;
import mapItems.Message;
import mapItems.Plot;
import objects.CentralHub;

public class FosterDrone  extends Drone implements CommInterface{

	DroneScannerA scanner;
	Point fire;
	ArrayList<Plot> ignoreList;
	
	public FosterDrone(Point location, String name, String studentName, Color color, int speedMPH) {
		super(location, name, studentName, color, speedMPH);
		ignoreList = new ArrayList<>();
		scanner = new DroneScannerA(this, 1);
	}

	@Override
	public void transmit(CommInterface reciever, Message msg) {
		reciever.recieve(this, msg);
	}

	@Override
	public void recieve(CommInterface transmitter, Message msg) {
		if (msg.getMsg().equalsIgnoreCase("START")) {
			List<Point> destPoints = new ArrayList<>();
			destPoints.add(new Point(20, 80, null));
			destPoints.add(new Point(40, 80, null));
			destPoints.add(new Point(40, 20, null));
			destPoints.add(new Point(60, 20, null));
			destPoints.add(new Point(60, 80, null));
			destPoints.add(new Point(80, 80, null));
			destPoints.add(new Point(80, 20, null));
			setDestinationPoints(destPoints);
			moving = true;
			System.out.println("Finder Finder Drone Deployed.");
		}
	}

	@Override
	public void loop() {
		if (scanner.scan() > 0) {
			//found fire
			for (Plot p : scanner.getScannedItemList()) {
				if (!ignoreList.contains(p)) {
					transmit(CentralHub.getHUBs().get(0), new Message("Fire", DataType._POINT, p.getLocation()));
					ignoreList.add(p);
					System.out.println("Fire finder drone sends detected fire location to central HUB.");
				}
			}
			
		}
	}

	@Override
	public void activate() {
		
	}




}
