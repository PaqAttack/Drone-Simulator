package main;

public class StateManager {

	private static State myState = State.PLOT_A_TARGETS;
	private String message1;
	private String message2;
	
	public StateManager() {

	}
	
	public void update() {
		updateMsg();
	}
	
	private void updateMsg() {
		switch (myState) {
			case PLOT_A_TARGETS: {
				message1 = "Click the field to plot targets of type A.";
				message2 = "Press Enter when done.";
				break;
			}
			case PLOT_B_TARGETS: {
				message1 = "Click the field to plot targets of type B.";
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
			case PLAYING: {
				message1 = "Simulation in progress";
				message2 = "Press Enter to pause.";
				break;
			}
			case PAUSED: {
				message1 = "Simulation paused";
				message2 = "Press Enter to resume.";
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
