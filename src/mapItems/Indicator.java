package mapItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Simulator;

public class Indicator {
	private Design type;
	private Color color;
	private int posX;
	private int posY;
	private String name;
	
	public Indicator(Color color, Design type, String name) {
		this.type = type;
		this.color = color;
		this.name = name;
	}
	
	public void render(Graphics g) {
		int renderX = posX - (Simulator.getMapItemWidth() / 2) + Simulator.getFieldXOffset();
		int renderY = Simulator.getFieldYOffset() - posY - (Simulator.getMapItemHeight() / 2);
		
		drawIndicator(g, renderX, renderY);
		
		drawText(g, renderX, renderY);
	}
	
	private void drawText(Graphics g, int renderX, int renderY) {
		if (name != null) {
			g.setColor(Simulator.getTextColor());
			g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			int textHeight = g.getFontMetrics().getHeight();
			int textWidth = g.getFontMetrics().stringWidth(name);
		
			int textX = renderX + (Simulator.getMapItemWidth() / 2) - (textWidth / 2);
			int textY = renderY + (Simulator.getMapItemHeight() / 2) + textHeight + 10;
		
			g.drawString(name, textX, textY);
		}
	}
	
	public void drawIndicator(Graphics g, int renderX, int renderY) {
		g.setColor(color);
		
		switch (type) {
			case RECTANGLE: {
				g.fillRect(renderX, renderY, Simulator.getMapItemWidth(), Simulator.getMapItemHeight());
				g.drawRect(renderX, renderY, Simulator.getMapItemWidth(), Simulator.getMapItemHeight());
			}
			case CIRCLE: {
				g.fillOval(renderX, renderY, Simulator.getMapItemWidth(), Simulator.getMapItemHeight());
				g.drawOval(renderX, renderY, Simulator.getMapItemWidth(), Simulator.getMapItemHeight());
			}
		}
	}
	
	public int getIndPosX() {
		return posX;
	}

	public void setIndPosX(int posX) {
		this.posX = posX;
	}

	public int getIndPosY() {
		return posY;
	}

	public void setIndPosY(int posY) {
		this.posY = posY;
	}
	
	
}

