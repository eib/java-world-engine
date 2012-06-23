package com.eblackwelder.graphics.alpha;

import java.awt.Font;

public class FontCreator {

	private final int fontStyle;
	
	public FontCreator(int fontStyle) {
		this.fontStyle = fontStyle;
	}

	public Font getFontBySize(int size, String fontName) {
		return new Font(fontName, fontStyle, size);
	}	
}
