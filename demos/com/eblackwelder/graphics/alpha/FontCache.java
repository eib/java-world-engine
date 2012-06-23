package com.eblackwelder.graphics.alpha;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

public class FontCache {
	private final int fontStyle;
	private final Map<String, Map<Integer, Font>> fontsByNameThenSize = new HashMap<String, Map<Integer, Font>>();

	public FontCache(int fontStyle) {
		this.fontStyle = fontStyle;
	}
	
	int tick = 0;
	int numMisses = 0;
	int numHits = 0;
	public Font getFontBySize(int size, String fontName) {
//		Map<Integer, Font> fontsBySize;
//		if (fontsByNameThenSize.containsKey(fontName)) {
//			fontsBySize = fontsByNameThenSize.get(fontName);
//		} else {
//			fontsBySize = new HashMap<Integer, Font>();
//			fontsByNameThenSize.put(fontName, fontsBySize);
//		}
//
//		tick++;
		Font font;
//		if (fontsBySize.containsKey(size)) {
//			font = fontsBySize.get(size);
//			numHits++;
//		} else {
			font = new Font(fontName, fontStyle, size);
//			fontsBySize.put(size, font);
//			numMisses++;
//		}
//		if (tick > 100) {
//			double ratio = ((double)numHits / (double)numMisses);
//			System.out.println("Hits=" + numHits + ", misses=" + numMisses + ", ratio=" + ratio);
//			tick %= 100;
//		}
		return font;
	}
	
	public int getSize() {
		int totalSize = 0;
		for (Map<Integer, Font> temp : fontsByNameThenSize.values()) {
			totalSize += temp.size();
		}
		return totalSize;
	}
	
	public void clear() {
		numHits = numMisses = 0;
		this.fontsByNameThenSize.clear();
		System.out.println("Clear!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
}