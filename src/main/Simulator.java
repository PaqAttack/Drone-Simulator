package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.time.LocalTime;

import javax.swing.JFrame;

import inputs.InputManager;
import mapItems.MapItems;
import objects.CentralHub;
import objects.Drone;
import studentDrones.ChrisDrone;
import studentDrones.FosterDrone;
import studentDrones.JudeDrone;
import studentDrones.RichardDrone;
import ui.Legend;

@SuppressWarnings("serial")
public class Simulator extends JFrame implements Runnable{

	// Program Operation Variables.
	private static final double FPS_SET = 80.0;
	private static final double UPS_SET = 60.0;
	
	private Screen screen;
	private Thread simThread;
	private LocalTime startTime;
	private LocalTime curTime;
	private StateManager simManager;
	
	private long elapsedSeconds = 0;
	private int counter = 0;
	
	private static final int CLOCK_X = GlobalVars.getGraphX() + (GlobalVars.getGraphWidth() / 2);
	private static final int CLOCK_Y = GlobalVars.getGraphY() - 5;
	
	private void initDrones() {
		// Create all drones here
		// MAX is 5
		ChrisDrone chrisDrone = new ChrisDrone(5, 2, "HID Drone", "Chris", Color.WHITE);
		FosterDrone fosterDrone = new FosterDrone(25, 2, "HID Drone", "Foster", Color.WHITE);
		RichardDrone richardDrone = new RichardDrone(45, 2, "HID Drone", "Richard", Color.WHITE);
		JudeDrone judeDrone = new JudeDrone(65, 2, "HID Drone", "Jude", Color.WHITE);
	}
	
	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		InputManager inputManager = new InputManager();
		simulator.screen.initScreen(inputManager);
		// Start thread that will handle game loop
		simulator.start();
	}
	
	public Simulator() {
		screen = new Screen(this);
		simManager = new StateManager();
		CentralHub centralHub = new CentralHub();
		Graph.initGraph();
		initDrones();
		
		startTime = LocalTime.of(0, 0, 0);
		curTime = startTime;
		
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
		
		if (MapItems.getList() != null) {
			for (MapItems mapItems : MapItems.getList()) {
				mapItems.update();
				mapItems.updatePos();
			}
		}
		
		if (simManager != null) {
			simManager.update();
		}
		
		updateTime();
		
		
	}
	
	private void updateTime() {
		counter++;
		if (counter >= UPS_SET && CentralHub.isActive()) {
			counter = 0;
			elapsedSeconds++;
			curTime = startTime.plusSeconds( (int) elapsedSeconds);
		}
	}
	
	
	public void render(Graphics g) {
		// Background
		g.setColor(GlobalVars.getBackgroundColor());
		g.fillRect(0, 0, 800, 600);
		
		// UI
		// Draw Clock
		renderClock(g);
		// Draw legend
		Legend.render(g);
		// Draw Message
		if (simManager != null) {
			renderScreenMessage(g);
		}
		
		// draw graph
		Graph.renderGraph(g);

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
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}
		
	}
	
	private void renderClock(Graphics g) {
		g.setColor(GlobalVars.getTextColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		int w = g.getFontMetrics().stringWidth(curTime.toString());
		int h = g.getFontMetrics().getHeight();
		g.drawString(curTime.toString(), CLOCK_X - (w / 2), CLOCK_Y);
	}
	
	private void renderScreenMessage(Graphics g) {
		if (simManager.getMessage2() != null) {
			g.setColor(GlobalVars.getTextColor());
			g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
			g.drawString(simManager.getMessage1(), 25, 25);
			g.drawString(simManager.getMessage2(), 25, 50);
		}
	}

	public Screen getScreen() {
		return screen;
	}

}
