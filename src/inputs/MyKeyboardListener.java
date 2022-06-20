package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Simulator;

public class MyKeyboardListener implements KeyListener{
	
	private Simulator simulator;
	private boolean rdy = true;
	
	public MyKeyboardListener(Simulator simulator) {
		this.simulator = simulator;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (rdy) {
			rdy = false;
			simulator.getScreen().keyPress(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		rdy = true;
		
	}

}
