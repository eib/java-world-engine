package com.eblackwelder.world.ui;

import java.awt.Dimension;
import java.awt.Graphics2D;

public interface IGraphicsContext extends IGraphicsAdapter {

	public Dimension getCanvasSize();
	
	/* Getters */
	
	public Graphics2D getDragLayer();

	public Graphics2D getOverlayLayer();

	public Graphics2D getMainLayer();

	public Graphics2D getShadowLayer();

	public Graphics2D getBackgroundLayer();

	/* Setters */
	
	public void setDragLayer(Graphics2D graphics);

	public void setOverlayLayer(Graphics2D graphics);

	public void setMainLayer(Graphics2D graphics);

	public void setShadowLayer(Graphics2D graphics);
	
	public void setBackgroundLayer(Graphics2D graphics);

}