package inputs;

import java.awt.event.KeyEvent;

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
	
	public InputManager() {
		myMouseListener = new MyMouseListener(this);
		myKeyboardListener = new MyKeyboardListener(this);
	}

	public void mouseClick(int x, int y) {
//		System.out.println("x: " + x + ", Y: " + y);
		if (Graph.getBounds().contains(x, y)) {
			switch (StateManager.getState()) {
				case PLOT_A_TARGETS: {
					TargetPlotA.createPlotA(Graph.ScreenXtoGraphX(x), Graph.ScreenYtoGraphY(y));
					break;
				}
				case PLOT_B_TARGETS: {
					TargetPlotB.createPlotB(Graph.ScreenXtoGraphX(x), Graph.ScreenYtoGraphY(y));
					break;
				}
				case PLOT_OBSTACLES: {
					ObstaclePlot.createObstacle(Graph.ScreenXtoGraphX(x), Graph.ScreenYtoGraphY(y));
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
		//debug
		System.out.println("Key pressed. State: " + StateManager.getState().toString());
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
					CentralHub.InitCentralHUB();
					break;
				}
				case PLAYING: {
					StateManager.setState(State.PAUSED);
					CentralHub.setActive(false);
					break;
				}
				case PAUSED: {
					StateManager.setState(State.PLAYING);
					CentralHub.setActive(true);
					break;
				}
			}
		} 
	}
	
	
	public MyMouseListener getMyMouseListener() {
		return myMouseListener;
	}

	public MyKeyboardListener getMyKeyboardListener() {
		return myKeyboardListener;
	}
	
	
}
