package studentDrones;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.Node;
import mapItems.CommInterface;
import mapItems.Drone;
import mapItems.Message;

public class RichardDrone extends Drone implements CommInterface {

	ArrayList<Node> visited;
	ArrayList<Node> unVisited;

	public RichardDrone(Node location, String name, String studentName, Color color, int speedMPH) {
		super(location, name, studentName, color, speedMPH);

	}

	@Override
	public void transmit(CommInterface reciever, Message msg) {
		reciever.recieve(this, msg);
	}

	@Override
	public void recieve(CommInterface transmitter, Message msg) {
		if (msg.getMsg().equalsIgnoreCase("START")) {
			List<Node> destPoints = new ArrayList<>();
			for (Node p : (List<Node>) msg.getO()) {
				destPoints.add(p);
			}
			setDestinationPoints(destPoints);
			moving = true;
			System.out.println("Camper Check-in Drone Deployed to " + destPoints.size() + " people.");
		}

		if (msg.getMsg().equalsIgnoreCase("ADD")) {
			Node p = (Node) msg.getO();
			getDestinationPoints().add(p);

			moving = true;
			System.out.println("Camper Check-in Drone added 1 person to destination list.");
		}
	}

	@Override
	public void loop() {
		if (moving && getDestination() == null && node != new Node(4, 4, null)) {
			List<Node> destPoints = new ArrayList<>();
			destPoints.add(new Node(4, 4, null));
			setDestinationPoints(destPoints);
		}

		if (getDestination() == null) {
			getDestinationPoints().add(new Node(4, 4, null));
			moving = true;
		}
	}

	@Override
	public void activate() {

	}

	@Override
	public void arrived() {

	}

}
