package com.eblackwelder.graphics.words;

import com.eblackwelder.world.model.impl.PositionedObject;

public class Letter extends PositionedObject {

	public final char character;
	public double radius;
	public double fontSizeScale;
	public double rotationDegrees;
	
	public Letter(char character, double size, double fontSizeScale, double rotationDegrees) {
		this.character = character;
		this.radius = size;
		this.fontSizeScale = fontSizeScale;
		this.rotationDegrees = rotationDegrees;
	}
	
}
