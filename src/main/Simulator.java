package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;
import inputs.InputManager;
import mapItems.Drone;
import mapItems.Plot;
import objects.CentralHub;
import studentDrones.FosterDrone;
import studentDrones.JudeDrone;
import studentDrones.RichardDrone;
import ui.Legend;

@SuppressWarnings("serial")
public class Simulator extends JFrame implements Runnable{

	// PROGRAM OPERATION VARIABLES
	
	private static final double FPS_SET = 80.0;		// Frames per second goal.
	private static final double UPS_SET = 60.0;		// Updates per second goal.
	
	public static final int TIME_SCALE = 15;			// number of seconds in simulation that pass every real second
	public static final int GRID_LENGTH_IN_MILES = 5;	// height and width represented by graph. This Grid will be a Square.
	public static final double MILES_REPRESENTED_BY_EACH_GRAPH_BLOCK = (double) GRID_LENGTH_IN_MILES / Graph.getGraphLengthInPlots();
	
	public static final String PLOT_A_NAME = "Fire";
	public static final String PLOT_B_NAME = "Camper";
	
	private static final Color PLOT_A_COLOR = Color.RED;
	private static final Color PLOT_B_COLOR = Color.BLUE;
	private static final Color OBS_COLOR = Color.GREEN;
	
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color GRAPH_COLOR = Color.WHITE;
	private static final Color TEXT_COLOR = Color.WHITE;
	
	private Screen screen;
	private Thread simThread;
	
	private void initDrones() {
		// Create all drones here
		// Only the first 4 will be on the legend
		FosterDrone fosterDrone = new FosterDrone(new Point(4, 4, null), "Fire Finder", "Foster", Color.PINK, 30);
		RichardDrone richardDrone = new RichardDrone(new Point(4, 4, null), "Camper Check-in", "Richard", Color.yellow, 40);
		JudeDrone judeDrone = new JudeDrone(new Point(4, 4, null), "Human Finder", "Jude", Color.MAGENTA, 40);
	}
	
	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		CentralHub centralHub = new CentralHub();
		InputManager inputManager = new InputManager(centralHub);
		simulator.screen.initScreen(inputManager);
		// Start thread that will handle game loop
		simulator.start();
		
		System.out.println(MILES_REPRESENTED_BY_EACH_GRAPH_BLOCK);
	}
	
	public Simulator() {
		screen = new Screen(this);
		Graph.initGraph();
		initDrones();
	
		// Set Frame (Stage) size and data.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("ASU / FSE 100 Drone Simulation");
		// Add Game Screen to JFrame
		add(screen);
		
		// ensure window matches panel
		pack();
		
		// Make visible
		setVisible(true);
	}

	private void start() {
		// Create and start thread
		simThread = new Thread(this) {};
		simThread.start();
	}
	
	
	private void update() {
		StateManager.update();
		Timer.update();
		
		if (CentralHub.getHUBs().get(0).isActive()) {
			
			try {
				if (Drone.getDrones() != null) {
					for (Drone drone : Drone.getDrones()) {
						drone.loop();
					}
				}
			} catch (ConcurrentModificationException e) {
				
			}
			
			CentralHub.getHUBs().get(0).update();
			

		}
		
	}

	
	public void render(Graphics g) {
		// Background
		g.setColor(getBackgroundColor());
		g.fillRect(0, 0, 800, 600);
		
		// UI
		// Draw Clock
		Timer.render(g);
		// Draw legend
		Legend.render(g);
		// Draw Message
		renderScreenMessage(g);

		
		// draw graph
		Graph.renderGraph(g);

		// Draw Plots
		for (Plot p : Plot.getPlots()) {
			p.render(g);
		}
		
		// Obstacles handled by graph
		
		// Draw Drone Items
		if (Drone.getDrones() != null) {
			for (Drone drone : Drone.getDrones()) {
				drone.render(g);
			}
		}
	}
	
	@Override
	public void run() {
		
		// Declared Frames per second
		double timePerFrame = 1000000000.0 / FPS_SET;
		
		// Declared Updates per Second
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();
		
		int frames = 0;
		int updates = 0;
		
		long now;
		
		while (true) {
			
			now = System.nanoTime();
			
			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = now;
				frames++;
			} 

			// Update
			if (now - lastUpdate >= timePerUpdate) {
				update();
				
				lastUpdate = now;
				updates++;
			}
			
			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
//				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
		
	}
	
	private void renderScreenMessage(Graphics g) {
			g.setColor(getTextColor());
			g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
			g.drawString(StateManager.getMessage1(), 25, 25);
			g.drawString(StateManager.getMessage2(), 25, 50);
	}

	public Screen getScreen() {
		return screen;
	}

	public static double getUpsSet() {
		return UPS_SET;
	}

	public static Color getBackgroundColor() {
		return BACKGROUND_COLOR;
	}

	public static Color getGraphColor() {
		return GRAPH_COLOR;
	}

	public static Color getTextColor() {
		return TEXT_COLOR;
	}

	public static Color getObsColor() {
		return OBS_COLOR;
	}

	public static int getTimeScale() {
		return TIME_SCALE;
	}

	public static int getGridLengthInMiles() {
		return GRID_LENGTH_IN_MILES;
	}

	public static double getMilesRepresentedByEachGraphBlock() {
		return MILES_REPRESENTED_BY_EACH_GRAPH_BLOCK;
	}

	public static String getPlotAName() {
		return PLOT_A_NAME;
	}

	public static String getPlotBName() {
		return PLOT_B_NAME;
	}

	public static Color getPlotAColor() {
		return PLOT_A_COLOR;
	}

	public static Color getPlotBColor() {
		return PLOT_B_COLOR;
	}

	
}
