package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import inputs.MyKeyboardListener;
import inputs.MyMouseListener;
import mapItems.Design;
import mapItems.MapItems;
import objects.CentralHub;
import objects.ObstaclePlot;
import objects.TargetPlot;

public class Screen extends JPanel{
	
	private MyMouseListener myMouseListener;
	private MyKeyboardListener myKeyboardListener;
	private Dimension size;

	private ArrayList<MapItems> makeStuff;
	
	private Simulator simulator;
		
	public Screen(Simulator simulator) {
		this.simulator = simulator;
		makeStuff = new ArrayList<>();
		setPanelSize();
	}
	
	public void initInputs() {
		myMouseListener = new MyMouseListener(simulator);
		myKeyboardListener = new MyKeyboardListener(simulator);
		
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(myKeyboardListener);
		
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
	
	public void mouseClick(int x, int y) {

		Rectangle bounds = new Rectangle(simulator.getFieldXOffset(), simulator.getLabelHeight(), simulator.getFieldWidth(), simulator.getFieldHeight());
		
		switch (SimulationManager.getState()) {
			case PLOT_TARGETS: {
				if (bounds.contains(x, y)) {
					makeStuff.add(new TargetPlot("Target", Color.GREEN, Design.CIRCLE, getCoordX(x), getCoordY(y)));
					System.out.println("Target created");
				}
				break;
			}
			case PLOT_OBSTACLES: {
				if (bounds.contains(x, y)) {
					makeStuff.add(new ObstaclePlot("Obstacle", Color.RED, Design.RECTANGLE, getCoordX(x), getCoordY(y)));
					System.out.println("Obstacle created");
				}
				break;
			}
		}
	}
	
	public void keyPress(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			switch (SimulationManager.getState()) {
				case PLOT_TARGETS: {
					SimulationManager.setState(State.PLOT_OBSTACLES);
					break;
				}
				case PLOT_OBSTACLES: {
					SimulationManager.setState(State.WAITING);
					break;
				}
				case WAITING: {
					SimulationManager.setState(State.STARTED);
					CentralHub.InitCentralHUB();
					break;
				}
			}
		} 
	}
	
	
	private int getCoordX(int screenX) {
		return screenX - simulator.getFieldXOffset();
	}
	
	private int getCoordY(int screenY) {
		return Simulator.getFieldYOffset() - screenY;
	}
}
