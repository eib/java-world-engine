package com.eblackwelder.graphics.alpha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.eblackwelder.math.Position;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.ui.IGraphicsContext;
import com.eblackwelder.world.ui.Renderer;


public class MultiFontLetterRenderer implements Renderer {

	private final FontCache cache;
	
	public MultiFontLetterRenderer() {
		this(Font.BOLD|Font.ITALIC);
	}
	
	public MultiFontLetterRenderer(int fontStyle) {
		this.cache = new FontCache(fontStyle);
	}
	
	private int tick = 0;
	
	@Override
	public void render(WorldObject object, IGraphicsContext context) {
		Letter letter = (Letter)object;
		Graphics2D g2d = context.getMainLayer();
		
		int fontSize = (int)Math.round(letter.size);
		if (fontSize > 1) {
			Position location = letter.getPosition();
			g2d.translate(location.x, location.y + fontSize/2);
	
			g2d.setColor(Color.BLACK);
			Font font = cache.getFontBySize(fontSize, letter.fontName);
			g2d.setFont(font);
			g2d.drawString(letter.upperCase, 0, 0);
			
			g2d.translate(-location.x, -location.y - fontSize/2);
		}
		if (++tick > 150) {
//			System.out.println("Tick");
			if (cache.getSize() > 5000) {
				cache.clear();
			}
			tick %= 150;
		}
	}
}
