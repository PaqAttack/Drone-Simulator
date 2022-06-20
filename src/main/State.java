package main;

import mapItems.Design;

public enum State {
	PLOT_TARGETS,
	PLOT_OBSTACLES,
	WAITING,
	STARTED;

	
	public static State myState = PLOT_TARGETS;
	
	public static void setType(State state) {
		myState = state;
	}
}
