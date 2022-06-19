package main;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

import mapItems.Drone;
import mapItems.MapItems;

@SuppressWarnings("serial")
public class Simulator extends JFrame implements Runnable{

	private Screen screen;
	private Thread simThread;
		
	private static final double FPS_SET = 80.0;
	private static final double UPS_SET = 60.0;
	
	private static final int LABEL_HEIGHT = 75;
	
	private static final int FIELD_WIDTH = 500;
	private static final int FIELD_HEIGHT = 500;
	
	private static final int FIELD_X_OFFSET = 275;
	private static final int FIELD_Y_OFFSET = LABEL_HEIGHT + FIELD_HEIGHT;
	
	private void initDrones() {
		Drone drone1 = new Drone(Color.BLUE, "Chris", 50, 50);
		
		
	}
	
	private void initPlots() {
		
		
		
	}
	
	public Simulator() {
		screen = new Screen(this);
		
		initDrones();
		initPlots();
		
		// Set Frame (Stage) size and data.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// Add Game Screen to JFrame
		add(screen);
		
		// ensure window matches panel
		pack();
		
		// Make visible
		setVisible(true);
	}

	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		// Start thread that will handle game loop
		simulator.start();
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
			}
		}
	}
	
	public void render(Graphics g) {
		// Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		
		// draw graph
		g.setColor(Color.WHITE);
		g.drawRect(FIELD_X_OFFSET, LABEL_HEIGHT, FIELD_WIDTH, FIELD_HEIGHT);
		
		
		if (MapItems.getList() != null) {
			for (MapItems mapItems : MapItems.getList()) {
				mapItems.render(g);
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

	public static int getLabelHeight() {
		return LABEL_HEIGHT;
	}

	public static int getFieldWidth() {
		return FIELD_WIDTH;
	}

	public static int getFieldHeight() {
		return FIELD_HEIGHT;
	}

	public static int getFieldXOffset() {
		return FIELD_X_OFFSET;
	}

	public static int getFieldYOffset() {
		return FIELD_Y_OFFSET;
	}

	

}
