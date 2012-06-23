package com.eblackwelder.world.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import com.eblackwelder.math.Position;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.model.Positionable;

public class DefaultRenderer implements Renderer {

	@Override
	public void render(WorldObject object, IGraphicsContext context) {
		if (object instanceof Positionable) {
			drawPositionableObject((Positionable)object, context.getMainLayer());
		}
	}

	private void drawPositionableObject(Positionable object, Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.setStroke(new BasicStroke(2));
		Position location = object.getPosition();
		g2d.drawOval((int) location.x, (int) location.y, 10, 10);
	}
}