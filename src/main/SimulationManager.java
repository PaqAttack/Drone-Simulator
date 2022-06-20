package main;

public class SimulationManager {

	private static State myState = State.PLOT_TARGETS;
	private String message1;
	private String message2;
	
	public SimulationManager() {

	}
	
	public void update() {
		updateMsg();
	}
	
	private void updateMsg() {
		switch (myState) {
			case PLOT_TARGETS: {
				message1 = "Click the field to plot targets.";
				message2 = "Press Enter when done.";
				break;
			}
			case PLOT_OBSTACLES: {
				message1 = "Click the field to plot obstacles.";
				message2 = "Press Enter when done.";
				break;
			}
			case WAITING: {
				message1 = "Press Enter to start.";
				message2 = "";
				break;
			}
			case STARTED: {
				message1 = "Simulation in progress";
				message2 = "";
				break;
			}
		}
	}
	
	public String getMessage1() {
		return message1;
	}

	public String getMessage2() {
		return message2;
	}
	
	public static State getState() {
		return myState;
	}
	
	public static void setState(State state) {
		myState = state;
	}

}
