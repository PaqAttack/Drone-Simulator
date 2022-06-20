package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.time.LocalTime;

import javax.swing.JFrame;

import mapItems.Design;
import mapItems.MapItems;
import objects.CentralHub;
import objects.ChrisDrone;
import objects.TargetPlot;
import ui.Legend;

@SuppressWarnings("serial")
public class Simulator extends JFrame implements Runnable{

	// GLOBAL VARIABLES - SET THESE
	public static final int MAP_ITEM_WIDTH = 8;
	public static final int MAP_ITEM_HEIGHT = 8;
	
	public static final int FIELD_HEIGHT_IN_MILES = 100;
	public static final int FIELD_WIDTH_IN_MILES = 100;
	
	// Program Operation Variables.
	private static final double FPS_SET = 80.0;
	private static final double UPS_SET = 60.0;
	
	// Program Interface Variables
	private static final int LABEL_HEIGHT = 75;
	
	private static final int FIELD_WIDTH = 500;
	private static final int FIELD_HEIGHT = 500;
	
	private static final int FIELD_X_OFFSET = 275;
	private static final int FIELD_Y_OFFSET = LABEL_HEIGHT + FIELD_HEIGHT;
	
	private static final Color BACKGROUND_COLOR = Color.BLACK;
	private static final Color GRAPH_COLOR = Color.WHITE;
	private static final Color TEXT_COLOR = Color.WHITE;
	
	private Screen screen;
	private Thread simThread;
	private LocalTime startTime;
	private LocalTime curTime;
	
	private long elapsedSeconds = 0;
	private int counter = 0;
	
	private static final int CLOCK_X = FIELD_X_OFFSET + (FIELD_WIDTH / 2);
	private static final int CLOCK_Y = 25;
	
	private void initDrones() {
		// Create all drones here
		ChrisDrone chrisDrone = new ChrisDrone("Chris", Color.YELLOW, Design.RECTANGLE, 30, 200);
		ChrisDrone chrisDrone1 = new ChrisDrone("Todd", Color.PINK, Design.CIRCLE, 50, 100);
		ChrisDrone chrisDrone2 = new ChrisDrone("John", Color.RED, Design.RECTANGLE, 300, 98);
		ChrisDrone chrisDrone3 = new ChrisDrone("Bill", Color.BLUE, Design.CIRCLE, 480, 420);
		ChrisDrone chrisDrone4 = new ChrisDrone("Max", Color.GREEN, Design.RECTANGLE, 222, 300);
	}
	
	private void initPlots() {
		// Create plots here to be plot later
		TargetPlot target1 = new TargetPlot("Fire", Color.RED, Design.CIRCLE, 250, 250);
		TargetPlot target2 = new TargetPlot("Water", Color.BLUE, Design.CIRCLE, 275, 275);
	}
	
	public Simulator() {
		screen = new Screen(this);
		
		CentralHub centralHub = new CentralHub();
		initDrones();
		initPlots();
		//CentralHub.InitCentralHUB();
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
		g.setColor(Simulator.getBackgroundColor());
		g.fillRect(0, 0, 800, 600);
		
		// draw graph
		g.setColor(Simulator.getGraphColor());
		g.drawRect(FIELD_X_OFFSET, LABEL_HEIGHT, FIELD_WIDTH, FIELD_HEIGHT);
		
		
		if (MapItems.getList() != null) {
			for (MapItems mapItems : MapItems.getList()) {
				mapItems.getIndicator().render(g);
			}
		}
		renderClock(g);
		Legend.render(g);
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
				updateTime();
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

	private void updateTime() {
		counter++;
		if (counter >= UPS_SET && CentralHub.isActive()) {
			counter = 0;
			elapsedSeconds++;
			curTime = startTime.plusSeconds( (int) elapsedSeconds);
		}
	}
	
	private void renderClock(Graphics g) {
		g.setColor(Simulator.getTextColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		int w = g.getFontMetrics().stringWidth(curTime.toString());
		int h = g.getFontMetrics().getHeight();
		g.drawString(curTime.toString(), CLOCK_X - (w / 2), LABEL_HEIGHT - 5);
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
	
	public static int getMapItemWidth() {
		return MAP_ITEM_WIDTH;
	}
	
	public static int getMapItemHeight() {
		return MAP_ITEM_HEIGHT;
	}
	
	public static int getFieldHeightInMiles() {
		return FIELD_HEIGHT_IN_MILES;
	}
	
	public static int getFieldWidthInMiles() {
		return FIELD_WIDTH_IN_MILES;
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

	
}
