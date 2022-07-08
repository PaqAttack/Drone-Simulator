package inputs;

import java.awt.event.KeyEvent;

import main.GlobalVars;
import main.Graph;
import main.State;
import main.StateManager;
import objects.CentralHub;
import objects.ObstaclePlot;
import objects.TargetPlotA;
import objects.TargetPlotB;

public class InputManager {
	
	private MyMouseListener myMouseListener;
	private MyKeyboardListener myKeyboardListener;
	private CentralHub centralHub;
	
	
	/**
	 * Functions as a HUB for all player input.
	 * Used due to a low amount of inputs monitored. If too many get added then this would need to split up.
	 * @param centralHub to give a reference for the central HUB. Used to check for active status
	 */
	public InputManager(CentralHub centralHub) {
		myMouseListener = new MyMouseListener(this);
		myKeyboardListener = new MyKeyboardListener(this);
		this.centralHub = centralHub;
	}

	/**
	 * Mouse click event is redirected here for processing.
	 * @param x is X position of mouse click.
	 * @param y is Y position of mouse click.
	 */
	public void mouseClick(int x, int y) {
		if (Graph.getBounds().contains(x, y)) {
			
			// Based on the simulation state different plots will be created.
			switch (StateManager.getState()) {
				case PLOT_A_TARGETS: {
					TargetPlotA.createPlotA(Graph.screenXtoGraphX(x), Graph.screenYtoGraphY(y));
					break;
				}
				case PLOT_B_TARGETS: {
					TargetPlotB.createPlotB(Graph.screenXtoGraphX(x), Graph.screenYtoGraphY(y));
					break;
				}
				case PLOT_OBSTACLES: {
					ObstaclePlot.createObstacle(Graph.screenXtoGraphX(x), Graph.screenYtoGraphY(y));
					break;
				}
				case WAITING: {
					
					break;
				}
				case PLAYING: {

					break;
				}
				case PAUSED: {

					break;
				}
			}
		}
	}
	
	/**
	 * Key Press event is redirected here for processing.
	 * @param e is the key event.
	 */
	public void keyPress(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			// This will swap the state of the game based on the current state.
			switch (StateManager.getState()) {
				case PLOT_A_TARGETS: {
					StateManager.setState(State.PLOT_B_TARGETS);
					break;
				}
				case PLOT_B_TARGETS: {
					StateManager.setState(State.PLOT_OBSTACLES);
					break;
				}
				case PLOT_OBSTACLES: {
					StateManager.setState(State.WAITING);
					break;
				}
				case WAITING: {
					StateManager.setState(State.PLAYING);
					centralHub.InitCentralHUB();
					break;
				}
				case PLAYING: {
					StateManager.setState(State.PAUSED);
					centralHub.setActive(false);
					break;
				}
				case PAUSED: {
					StateManager.setState(State.PLAYING);
					centralHub.setActive(true);
					break;
				}
			}
		} 
		
		// The L key toggles a green line that shows the destination of each drone when it has a destination.
		if (e.getKeyCode() == KeyEvent.VK_L) {
			GlobalVars.showLine = !GlobalVars.showLine;
		}
		
	}
	
	
	public MyMouseListener getMyMouseListener() {
		return myMouseListener;
	}

	public MyKeyboardListener getMyKeyboardListener() {
		return myKeyboardListener;
	}

	
}
