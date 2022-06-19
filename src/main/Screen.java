package main;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.MyKeyboardListener;
import inputs.MyMouseListener;

public class Screen extends JPanel{
	
	private MyMouseListener myMouseListener;
	private MyKeyboardListener myKeyboardListener;
	private Dimension size;

	private Simulator simulator;
		
	public Screen(Simulator simulator) {
		this.simulator = simulator;
	
		setPanelSize();
	}
	
	public void initInputs() {
		myMouseListener = new MyMouseListener(simulator);
		myKeyboardListener = new MyKeyboardListener();
		
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(myKeyboardListener);
		
		requestFocus();
	}
	
	private void setPanelSize() {
		size = new Dimension(800, 800);
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
