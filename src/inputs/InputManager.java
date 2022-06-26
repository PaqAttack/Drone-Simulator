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
	
	public InputManager(CentralHub centralHub) {
		myMouseListener = new MyMouseListener(this);
		myKeyboardListener = new MyKeyboardListener(this);
		this.centralHub = centralHub;
	}

	public void mouseClick(int x, int y) {
		if (Graph.getBounds().contains(x, y)) {
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
	
	public void keyPress(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			switch (StateManager.getState()) {
				case PLOT_A_TARGETS: {
					System.out.println("State A detected");
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
		
		if (e.getKeyCode() == KeyEvent.VK_L) {
			GlobalVars.SHOW_LINE = !GlobalVars.SHOW_LINE;
		}
		
	}
	
	
	public MyMouseListener getMyMouseListener() {
		return myMouseListener;
	}

	public MyKeyboardListener getMyKeyboardListener() {
		return myKeyboardListener;
	}
	
	
}
