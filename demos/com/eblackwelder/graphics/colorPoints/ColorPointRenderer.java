package com.eblackwelder.graphics.colorPoints;

import java.awt.Color;
import java.awt.Graphics2D;

import com.eblackwelder.math.Position;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.ui.IGraphicsContext;
import com.eblackwelder.world.ui.Renderer;


public class ColorPointRenderer implements Renderer {
	
	@Override
	public void render(WorldObject object, IGraphicsContext context) {
		ColorPoint point = (ColorPoint)object;
		Graphics2D g2d = context.getMainLayer();
		
		Position location = point.getPosition();
		g2d.translate(location.x, location.y);

		if (point.heatLevel > 0.1) {
			Color c = point.getColor();
			int radius = point.getRadius();
			g2d.setPaint(c);
			g2d.fillOval(-radius, -radius, 2*radius, 2*radius);
		}
		
		g2d.translate(-location.x, -location.y);
	}
	
}
