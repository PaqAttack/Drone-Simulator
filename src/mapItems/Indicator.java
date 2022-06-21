package mapItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Simulator;

public class Indicator {
	private MapIcon type;
	private Color color;
	private int posX;
	private int posY;
	private String name;
	
	private int textX;
	private int textY;
	private int renderX;
	private int renderY;
	
	private int height, width;
	
	public Indicator(Color color, MapIcon type, String name) {
		this.type = type;
		this.color = color;
		this.name = name;
		if (this.type == MapIcon.RECTANGLE) {
			height = Simulator.getMapObsHeight();
			width = Simulator.getMapObsWidth();
		} else {
			height = Simulator.getMapItemHeight();
			width = Simulator.getMapItemWidth();
		}
	}
	
	public void render(Graphics g) {
		if (this.type == MapIcon.RECTANGLE) {
			renderX = posX - (width / 2) + Simulator.getFieldXOffset();
			renderY = Simulator.getFieldYOffset() - posY - (height / 2);
		} else {
			renderX = posX - (width / 2) + Simulator.getFieldXOffset();
			renderY = Simulator.getFieldYOffset() - posY - (height / 2);
		}
		
		drawIndicator(g, renderX, renderY);
		
		drawText(g, renderX, renderY);
	}
	
	private void drawText(Graphics g, int renderX, int renderY) {
		if (name != null) {
			g.setColor(Simulator.getTextColor());
			g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			int textHeight = g.getFontMetrics().getHeight();
			int textWidth = g.getFontMetrics().stringWidth(name);
			
			if (this.type == MapIcon.RECTANGLE) {
				textX = renderX + (width / 2) - (textWidth / 2);
				textY = renderY + (height / 2) + textHeight + 10;
			} else {
				textX = renderX + (width / 2) - (textWidth / 2);
				textY = renderY + (height / 2) + textHeight + 10;
			}
		
			g.drawString(name, textX, textY);
		}
	}
	
	public void drawIndicator(Graphics g, int renderX, int renderY) {
		g.setColor(color);
		
		switch (type) {
			case RECTANGLE: {
				g.fillRect(renderX, renderY, width, height);
				g.drawRect(renderX, renderY, width, height);
			}
			case CIRCLE: {
				g.fillOval(renderX, renderY, width, height);
				g.drawOval(renderX, renderY, width, height);
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

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	
}

