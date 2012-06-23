package com.eblackwelder.graphics.words;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import com.eblackwelder.math.Position;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.ui.IGraphicsContext;
import com.eblackwelder.world.ui.Renderer;

public class WordRenderer implements Renderer {

	@Override
	public void render(WorldObject object, IGraphicsContext context) {
		Word word = (Word) object;
		Graphics2D g2d = context.getMainLayer();
		
		Position location = word.getPosition();
		g2d.translate(location.x, location.y);
		
		g2d.setFont(word.getFont());
		AffineTransform recenterTransform = g2d.getTransform();
		
		Color color = Color.BLACK;
		for (Letter letter : word.getLetters()) {
			if (letter.radius < Word.MIN_RADIUS) {
				break;
			}
			String string = Character.toString(letter.character);
			
			//Letter position/orientation
			g2d.scale(letter.fontSizeScale, letter.fontSizeScale);
			g2d.rotate(Math.toRadians(letter.rotationDegrees));
			
			//Color
			float cc = (float) (1.0f - letter.radius / word.startingRadius) / 2.0f + 0.5f;
			color = new Color(cc, cc, cc);
			g2d.setColor(color);
			
			//Offset the letter half-way, "horizontally"
			AffineTransform transform = AffineTransform.getScaleInstance(letter.fontSizeScale, letter.fontSizeScale);
			Rectangle2D fontBounds = word.font.getStringBounds(string, new FontRenderContext(transform, false, true));
			float fontWidth = (float) fontBounds.getWidth();
			
			//Draw another letter
			g2d.drawString(string, -fontWidth / 2.0f, -(float)letter.radius);

			//Reset transform (for next letter)
			g2d.setTransform(recenterTransform);
		}
		
		g2d.translate(-location.x, -location.y);
	}
}
