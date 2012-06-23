package com.eblackwelder.world.ui.layers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BufferedImageLayer implements ILayer {

	private Dimension size;
	private BufferedImage image;
	
	public BufferedImageLayer(Dimension size) {
		this.size = size;
		this.image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
	public Graphics2D getGraphics() {
		return (Graphics2D) image.getGraphics();
	}

	@Override
	public void drawOnto(Graphics2D g2d) {
		g2d.drawImage(image, 0, 0, null);
	}

	@Override
	public void clear() {
		Graphics2D g = getGraphics();
		g.setBackground(new Color(255, 255, 255, 0));
		g.clearRect(0, 0, size.width, size.height);
	}
	
}
