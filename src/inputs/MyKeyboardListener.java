package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyboardListener implements KeyListener{
	
	private InputManager inputManager;
	
	public MyKeyboardListener(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		inputManager.keyPress(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
