package com.eblackwelder.graphics.alpha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import com.eblackwelder.math.Position;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.ui.IGraphicsContext;
import com.eblackwelder.world.ui.Renderer;


public class LetterRenderer implements Renderer {

	private final String fontName;
	private final int fontStyle;
	private Map<Integer, Font> fontsBySize = new HashMap<Integer, Font>();
	
	public LetterRenderer() {
		this("Arial", Font.BOLD|Font.ITALIC);
	}
	
	public LetterRenderer(String fontName, int fontStyle) {
		this.fontName = fontName;
		this.fontStyle = fontStyle;
	}
	
	public Font getFontBySize(int size) {
		Font font;
		if (fontsBySize.containsKey(size)) {
			font = fontsBySize.get(size);
		} else {
			font = new Font(fontName, fontStyle, size);
			fontsBySize.put(size, font);
		}
		return font;
	}
	
	@Override
	public void render(WorldObject object, IGraphicsContext context) {
		Letter letter = (Letter)object;
		Graphics2D g2d = context.getMainLayer();
		
		int fontSize = (int)Math.round(letter.size);
		if (fontSize > 1) {
			Position location = letter.getPosition();
			g2d.translate(location.x, location.y);
	
			g2d.setColor(Color.BLACK);
			Font font = getFontBySize(fontSize);
			g2d.setFont(font);
			g2d.drawString(letter.upperCase, 0, 0);
			
			g2d.translate(-location.x, -location.y);
		}
	}
	
}
