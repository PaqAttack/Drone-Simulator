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
import mapItems.MapIcon;
import mapItems.MapItems;
import objects.CentralHub;
import objects.ObstaclePlot;
import objects.TargetPlotA;
import objects.TargetPlotB;

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

		Rectangle bounds = new Rectangle(Simulator.getFieldXOffset(), Simulator.getLabelHeight(), Simulator.getFieldWidth(), Simulator.getFieldHeight());
		
		switch (SimulationManager.getState()) {
			case PLOT_A_TARGETS: {
				if (bounds.contains(x, y)) {
					
					boolean collide = false;
					Rectangle rect = new Rectangle(getCoordX(x) - (Simulator.getMapItemWidth() / 2), getCoordY(y) - (Simulator.getMapItemHeight() / 2), Simulator.getMapItemWidth(), Simulator.getMapItemHeight());
					for (MapItems i : MapItems.getList()) {
						if (i.getBounds().contains(rect)) {
							collide = true;
						}
					}
					if (!collide) {
						makeStuff.add(new TargetPlotA("Target A", Color.GREEN, MapIcon.CIRCLE, getCoordX(x), getCoordY(y)));
						System.out.println("Target A created");
					} else {
						System.out.println("Unable to plot target here.");
					}
					rect = null;
				}
				break;
			}
			case PLOT_B_TARGETS: {
				if (bounds.contains(x, y)) {
					boolean collide = false;
					Rectangle rect = new Rectangle(getCoordX(x) - (Simulator.getMapItemWidth() / 2), getCoordY(y) - (Simulator.getMapItemHeight() / 2), Simulator.getMapItemWidth(), Simulator.getMapItemHeight());
					for (MapItems i : MapItems.getList()) {
						if (i.getBounds().contains(rect)) {
							collide = true;
						}
					}
					if (!collide) {
						makeStuff.add(new TargetPlotB("Target B", Color.BLUE, MapIcon.CIRCLE, getCoordX(x), getCoordY(y)));
						System.out.println("Target B created");
					} else {
						System.out.println("Unable to plot target here.");
					}
					rect = null;
				}
				break;
			}
			case PLOT_OBSTACLES: {
				if (bounds.contains(x, y)) {
					boolean collide = false;
					Rectangle rect = new Rectangle(getCoordX(x) - (Simulator.getMapObsWidth() / 2), getCoordY(y) - (Simulator.getMapObsHeight() / 2), Simulator.getMapObsWidth(), Simulator.getMapObsHeight());
					for (MapItems i : MapItems.getList()) {
						if (i.getBounds().contains(rect)) {
							collide = true;
						}
					}
					if (!collide) {
						makeStuff.add(new ObstaclePlot("Obstacle", Color.RED, MapIcon.RECTANGLE, getCoordX(x), getCoordY(y)));
						System.out.println("Obstacle created");
					} else {
						System.out.println("Unable to plot obstacle here.");
					}
					rect = null;
				}
				break;
			}
		}
	}
	
	public void keyPress(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			switch (SimulationManager.getState()) {
				case PLOT_A_TARGETS: {
					SimulationManager.setState(State.PLOT_B_TARGETS);
					break;
				}
				case PLOT_B_TARGETS: {
					SimulationManager.setState(State.PLOT_OBSTACLES);
					break;
				}
				case PLOT_OBSTACLES: {
					SimulationManager.setState(State.WAITING);
					break;
				}
				case WAITING: {
					SimulationManager.setState(State.PLAYING);
					CentralHub.InitCentralHUB();
					break;
				}
				case PLAYING: {
					SimulationManager.setState(State.PAUSED);
					CentralHub.setActive(false);
					break;
				}
				case PAUSED: {
					SimulationManager.setState(State.PLAYING);
					CentralHub.setActive(true);
					break;
				}
			}
		} 
	}
	
	
	private int getCoordX(int screenX) {
		return screenX - Simulator.getFieldXOffset();
	}
	
	private int getCoordY(int screenY) {
		return Simulator.getFieldYOffset() - screenY;
	}
}
