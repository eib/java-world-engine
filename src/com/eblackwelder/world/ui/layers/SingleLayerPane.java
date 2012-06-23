package com.eblackwelder.world.ui.layers;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.eblackwelder.world.ui.IGraphicsContext;

public class SingleLayerPane implements ILayeredPane {

	private GraphicsContext context;
	
	public SingleLayerPane() {
		this.context = new GraphicsContext((Graphics2D)null);
	}

	@Override
	public IGraphicsContext getGraphicsContext(Graphics2D graphics) {
		context.setMainLayer(graphics);
		return context;
	}

	@Override
	public void resizeLayers(Dimension newSize) {
		context.setCanvasSize(newSize);
	}

	@Override
	public void paintOnto(Graphics2D graphics) { }

	@Override
	public void clearLayers() { }
}
