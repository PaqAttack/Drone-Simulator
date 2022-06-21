package main;

public enum State {
	PLOT_A_TARGETS,
	PLOT_B_TARGETS,
	PLOT_OBSTACLES,
	WAITING,
	PLAYING,
	PAUSED;
	
	public static State myState = PLOT_A_TARGETS;
	
	public static void setType(State state) {
		myState = state;
	}
}
