package com.eblackwelder.graphics.cursor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import com.eblackwelder.math.Position;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.model.Named;
import com.eblackwelder.world.model.Positionable;
import com.eblackwelder.world.ui.IGraphicsContext;
import com.eblackwelder.world.ui.Renderer;

public class CursorRenderer implements Renderer {

	@Override
	public void render(WorldObject object, IGraphicsContext context) {
		drawCursor((Positionable)object, context);
	}
	
	public void drawCursor(Positionable cursor, IGraphicsContext context) {
		Position location = cursor.getPosition();

		context.setStroke(new BasicStroke(1));

		Graphics2D mainLayer = context.getMainLayer();
		Graphics2D overlayLayer = context.getOverlayLayer();
		Graphics2D shadowLayer = context.getShadowLayer();

		int width = 5;
		int height = 12;
		int[] xPoints = { 0, width, -width };
		int[] yPoints = { -height, width, width };
		
		context.translate(location.x, location.y);

		//indicator's drop-shadow
		int offsetX = 2;
		int offsetY = 2;
		shadowLayer.translate(offsetX, offsetY);
		shadowLayer.setColor(Color.GRAY);
		shadowLayer.fillPolygon(xPoints, yPoints, xPoints.length);
		shadowLayer.translate(-offsetX, -offsetY);

		//main cursor indicator (triangle)
		mainLayer.setColor(Color.GREEN);
		mainLayer.fillPolygon(xPoints, yPoints, xPoints.length);

		//name
		if (cursor instanceof Named) {
			offsetX = 7;
			offsetY = 1;
			Named named = (Named) cursor;
			overlayLayer.setColor(Color.BLACK);
			overlayLayer.drawString(named.getName(), offsetX, offsetY);	
		}
		
		context.translate(-location.x, -location.y);
	}

}
