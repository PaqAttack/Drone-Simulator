package mapItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.GlobalVars;
import main.Simulator;

public class Indicator {
	private Color color;
	private int posX;
	private int posY;
	private String name;
	
	private int textX;
	private int textY;
	private int renderX;
	private int renderY;
	
	private int height, width;
	
	public Indicator(Color color, String name) {

		this.color = color;
		this.name = name;
		height = GlobalVars.getMapItemDim();
		width = GlobalVars.getMapItemDim();

	}
	
	public void render(Graphics g) {

		renderX = posX - (width / 2) + GlobalVars.getGraphX();
		renderY = GlobalVars.getGraphY() - posY - (height / 2);

		drawIndicator(g, renderX, renderY);
		
		drawText(g, renderX, renderY);
	}
	
	private void drawText(Graphics g, int renderX, int renderY) {
		if (name != null) {
			g.setColor(GlobalVars.getTextColor());
			g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			int textHeight = g.getFontMetrics().getHeight();
			int textWidth = g.getFontMetrics().stringWidth(name);
			
			textX = renderX + (width / 2) - (textWidth / 2);
			textY = renderY + (height / 2) + textHeight + 10;

		
			g.drawString(name, textX, textY);
		}
	}
	
	public void drawIndicator(Graphics g, int renderX, int renderY) {
		g.setColor(color);
		
		g.fillRect(renderX, renderY, width, height);
		g.drawRect(renderX, renderY, width, height);

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

