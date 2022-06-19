package main;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Simulator extends JFrame implements Runnable{

	private Thread simThread;
		
	private static final double FPS_SET = 80.0;
	private static final double UPS_SET = 60.0;
	
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
				updateGame();
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

	
	private void updateGame() {
		
	}
	
	public void render(Graphics g) {
		
	}
}
