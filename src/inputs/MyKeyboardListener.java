package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyboardListener implements KeyListener{
	
	private InputManager inputManager;
	
	// With minor refactoring this could be removed. TODO
	public MyKeyboardListener(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		inputManager.keyPress(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
