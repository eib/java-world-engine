package com.eblackwelder.world.ui.layers;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;

import com.eblackwelder.world.ui.IGraphicsContext;

/**
 * A multi-layered IGraphicsContext implementation that can be dynamically resized
 * and automatically combines layers when there aren't enough.
 * 
 * If the actual graphics layers aren't deep enough to support a particular layer,
 * the "bottom-most" layer will be used instead.
 * 
 * @author Ethan
 */
public class GraphicsContext extends GraphicsAdapter implements IGraphicsContext {

	protected static final int DRAG_LAYER = 0;
	protected static final int OVERLAY_LAYER = 1;
	protected static final int MAIN_LAYER = 2;
	protected static final int SHADOW_LAYER = 3;
	protected static final int BACKGROUND_LAYER = 4;

	private Dimension size;
	
	public GraphicsContext(List<Graphics2D> graphics) {
		super(graphics);
		this.size = new Dimension();
	}
	
	public GraphicsContext(Graphics2D... graphics) {
		super(graphics);
		this.size = new Dimension();
	}
	
	public Dimension getCanvasSize() {
		return size;
	}

	public IGraphicsContext setCanvasSize(Dimension size) {
		this.size = size;
		return this;
	}

	/* Getters */

	@Override
	public Graphics2D getDragLayer() {
		int layerNum = getDragLayerNum();
		return super.graphics[layerNum];
	}

	@Override
	public Graphics2D getOverlayLayer() {
		int layerNum = getOverlayLayerNum();
		return super.graphics[layerNum];
	}

	@Override
	public Graphics2D getMainLayer() {
		int layerNum = getMainLayerNum();
		return super.graphics[layerNum];
	}

	@Override
	public Graphics2D getShadowLayer() {
		int layerNum = getShadowLayerNum();
		return super.graphics[layerNum];
	}

	@Override
	public Graphics2D getBackgroundLayer() {
		int layerNum = getBackgroundLayerNum();
		return super.graphics[layerNum];
	}
	
	/* Setters */

	@Override
	public void setDragLayer(Graphics2D graphics) {
		int layerNum = getDragLayerNum();
		super.graphics[layerNum] = graphics;
	}

	@Override
	public void setOverlayLayer(Graphics2D graphics) {
		int layerNum = getOverlayLayerNum();
		super.graphics[layerNum] = graphics;
	}

	@Override
	public void setMainLayer(Graphics2D graphics) {
		int layerNum = getMainLayerNum();
		super.graphics[layerNum] = graphics;
	}

	@Override
	public void setShadowLayer(Graphics2D graphics) {
		int layerNum = getShadowLayerNum();
		super.graphics[layerNum] = graphics;
	}

	@Override
	public void setBackgroundLayer(Graphics2D graphics) {
		int layerNum = getBackgroundLayerNum();
		super.graphics[layerNum] = graphics;
	}
	
	/* Layer numbers */

	private int getDragLayerNum() {
		return Math.min(DRAG_LAYER, graphics.length - 1);
	}

	private int getOverlayLayerNum() {
		return Math.min(OVERLAY_LAYER, graphics.length - 1);
	}

	private int getMainLayerNum() {
		return Math.min(MAIN_LAYER, graphics.length - 1);
	}

	private int getShadowLayerNum() {
		return Math.min(SHADOW_LAYER, graphics.length - 1);
	}

	private int getBackgroundLayerNum() {
		return Math.min(BACKGROUND_LAYER, graphics.length - 1);
	}

}
