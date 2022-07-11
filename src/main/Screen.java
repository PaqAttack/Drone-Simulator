package main;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.InputManager;

public class Screen extends JPanel{
	
	private Dimension size;
	
	private Simulator simulator;
		
	public Screen(Simulator simulator) {
		this.simulator = simulator;
		setPanelSize();
	}
	
	public void initScreen(InputManager inputManager) {
		addMouseListener(inputManager);
		addMouseMotionListener(inputManager);
		addKeyListener(inputManager);
		
		requestFocus();
	}
	
	private void setPanelSize() {
		size = new Dimension(800, 600);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		simulator.render(g);
	}
	
}
