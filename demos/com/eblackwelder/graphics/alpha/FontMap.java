package com.eblackwelder.graphics.alpha;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FontMap {
	private final String defaultFontName;
	private final List<String> fontNames;
	private final Map<String, Font> fonts;
	
	public FontMap(String preferredFont) {
		List<String> allFontNames = new ArrayList<String>();
		Map<String, Font> allFonts = new HashMap<String, Font>();
		Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		for (Font font : fonts) {
			allFontNames.add(font.getName());
			allFonts.put(font.getName(), font);
//			System.out.println("Font: " + font.getName());
		}
		String[] types = { "Bold", "Italic", "Oblique" };
		System.out.println("# total fonts: " + allFontNames.size());
		for (String name : allFontNames) {
			for (String type : types) {
				if (name.endsWith(type)) {
					allFonts.remove(name);
					break;
				}
			}
		}
		System.out.println("# available fonts: " + allFonts.size());
		this.fontNames = new ArrayList<String>(allFonts.keySet());
		this.fonts = new HashMap<String, Font>(allFonts);
		this.defaultFontName = preferredFont;
	}

	private int fontIndex = 0;
	protected String getRandomFontName() {
		fontIndex %= fontNames.size();
		return fontNames.get(fontIndex++);
	}
	
	private int numRetries = 10;
	protected String getRandomFontNameThatSupportsCharacter(char character) {
		for (int ii = 0; ii < numRetries; ii++) {
			String randomFontName = getRandomFontName();

			Font randomFont = fonts.get(randomFontName);
			if (randomFont.canDisplay(character)) {
				return randomFontName;
			}
		}
		return defaultFontName;
	}
}