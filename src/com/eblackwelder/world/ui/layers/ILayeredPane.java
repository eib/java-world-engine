package com.eblackwelder.world.ui.layers;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.eblackwelder.world.ui.IGraphicsContext;


public interface ILayeredPane {

	public IGraphicsContext getGraphicsContext(Graphics2D graphics);
	
	public void resizeLayers(Dimension newSize);

	public abstract void clearLayers();

	public abstract void paintOnto(Graphics2D graphics);

}
