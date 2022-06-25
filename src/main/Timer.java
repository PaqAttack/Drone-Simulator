package main;

import java.awt.Font;
import java.awt.Graphics;
import java.time.LocalTime;

import mapItems.Drone;
import objects.CentralHub;

public class Timer {
	
	private static final int CLOCK_X = GlobalVars.getGraphX() + (GlobalVars.getGraphWidth() / 2);
	private static final int CLOCK_Y = GlobalVars.getGraphY() - 15;
	
	private static LocalTime startTime = LocalTime.of(0, 0, 0);
	private static LocalTime curTime = startTime;
	
	private static long elapsedSeconds = 0;
	private static int counter = 0;
	
	public static void update() {
		if(CentralHub.isActive() ) {
			counter++;
			for (Drone drone : Drone.getDrones()) {
				drone.update((int) elapsedSeconds);
			}
		}
		
		if (counter >= Simulator.getUpsSet()) {
			counter = 0;
			elapsedSeconds += GlobalVars.getTimeScale();
			curTime = startTime.plusSeconds( (int) elapsedSeconds);
		}
	}
	
	public static void render(Graphics g) {
		g.setColor(GlobalVars.getTextColor());
		g.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		int w = g.getFontMetrics().stringWidth(curTime.toString());
		int h = g.getFontMetrics().getHeight();
		g.drawString(curTime.toString(), CLOCK_X - (w / 2), CLOCK_Y);
	}
}
