package com.eblackwelder.graphics.alpha;

import com.eblackwelder.math.Function1;
import com.eblackwelder.world.Type;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.model.impl.PositionedObject;


public class Letter extends PositionedObject implements WorldObject {
	
	public static final Type letterType = new Type("Letter");
	
	public char character;
	public String upperCase;
	public double size;
	public String fontName;
	public Function1<Double> resizeFunction;
	
	public Letter(char letter, double size, String fontName, Function1<Double> resizeFunction) {
		this.character = letter;
		this.upperCase = String.valueOf(this.character);
		this.size = size;
		this.fontName = fontName;
		this.resizeFunction = resizeFunction;
	}
	
	@Override
	public Type getType() {
		return letterType;
	}
	
	public void resize(double period) {
		this.size = resizeFunction.Evaluate(size, period);
	}
}
