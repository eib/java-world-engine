package com.eblackwelder.world.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.eblackwelder.world.Type;
import com.eblackwelder.world.World;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.ui.layers.ILayeredPane;
import com.eblackwelder.world.ui.layers.LayeredPane;
import com.eblackwelder.world.ui.layers.SingleLayerPane;

public class GridPanel extends JPanel {
	private static final long serialVersionUID = -1803100576961200270L;
	
	private World world;
	private Map<Type, Renderer> renderers = new HashMap<Type, Renderer>();
	private List<Renderer> globalRenderers = new ArrayList<Renderer>();
	private ILayeredPane layeredPane;

	private Renderer defaultRenderer = new DefaultRenderer();
	private boolean clearAfterPaint = true;
	
	protected GridPanel(World world, ILayeredPane layeredPane) {
		this.world = world;
		this.layeredPane = layeredPane;
		super.setDoubleBuffered(false);
		super.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				GridPanel.this.layeredPane.resizeLayers(getSize());
			}
		});
	}

	public GridPanel(World world) {
		this(world, new SingleLayerPane());
	}
	
	public GridPanel(World world, int numLayers) {
		this(world, new LayeredPane(numLayers));
	}
	
	public GridPanel setClearAfterPaint(boolean clearAfterPaint) {
		this.clearAfterPaint = clearAfterPaint;
		return this;
	}

	public GridPanel setRendererForType(Renderer renderer, Type type) {
		this.renderers.put(type, renderer);
		return this;
	}
	
	public GridPanel addRenderer(Renderer renderer) {
		this.globalRenderers.add(renderer);
		return this;
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		AffineTransform transform = g2d.getTransform();
		
		IGraphicsContext context = layeredPane.getGraphicsContext(g2d);
		for (WorldObject object : world.getObjects()) {
			drawObject(object, context);
		}
		
		g2d.setTransform(transform);
		layeredPane.paintOnto(g2d);
		if (clearAfterPaint) {
			layeredPane.clearLayers();
		}
	}
	
	protected void drawObject(WorldObject object, IGraphicsContext context) {
		Type type = object.getType();
		Renderer renderer = getRendererForType(type);
		renderer.render(object, context);
		for (Renderer r : globalRenderers) {
			r.render(object, context);
		}
	}
	
	protected Renderer getRendererForType(Type type) {
		Renderer renderer;
		if (renderers.containsKey(type)) {
			renderer = renderers.get(type);
		} else {
			renderer = defaultRenderer;
		}
		return renderer;
	}
}