package com.eblackwelder.world.ui.layers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.eblackwelder.world.ui.IGraphicsContext;


public class LayeredPane implements ILayeredPane {

	private final int numLayers;
	private final List<ILayer> layers = new ArrayList<ILayer>();
	private IGraphicsContext context;
	
	public LayeredPane(int numLayers) {
		this.numLayers = numLayers;
	}

	@Override
	public void resizeLayers(Dimension newSize) {
		layers.clear();
		for (int ii = 0; ii < numLayers; ii++) {
			ILayer layer = new BufferedImageLayer(newSize);
			layers.add(layer);
		}
		this.context = createGraphicsContext(newSize);
	}
	
	@Override
	public void paintOnto(Graphics2D graphics) {
		for (int ii = 0; ii < numLayers; ii++) {
			ILayer layer = layers.get(numLayers - 1 - ii);
			layer.drawOnto(graphics);
		}
	}
	
	@Override
	public void clearLayers() {
		for (ILayer layer : layers) {
			layer.clear();
		}
	}
	
	protected ILayer getLayer(int layerNum) {
		return layers.get(layerNum);
	}

	@Override
	public IGraphicsContext getGraphicsContext(Graphics2D g2d) {
		context.setBackgroundLayer(g2d);
		return context;
	}
	
	protected IGraphicsContext createGraphicsContext(Dimension size) {
		List<Graphics2D> graphics = new ArrayList<Graphics2D>(layers.size() + 1);
		for (ILayer layer : layers) {
			graphics.add(layer.getGraphics());
		}
		graphics.add(null); //place-holder for background
		return new GraphicsContext(graphics).setCanvasSize(size);
	}
}
