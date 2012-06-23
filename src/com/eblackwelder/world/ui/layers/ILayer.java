package com.eblackwelder.world.ui.layers;

import java.awt.Graphics2D;

public interface ILayer {

	public Graphics2D getGraphics();
	
	public void drawOnto(Graphics2D g2d);
	
	public void clear();
}
