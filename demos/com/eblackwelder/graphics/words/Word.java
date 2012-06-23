package com.eblackwelder.graphics.words;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import com.eblackwelder.world.Type;
import com.eblackwelder.world.World;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.model.Updatable;
import com.eblackwelder.world.model.impl.PositionedObject;

public class Word extends PositionedObject implements WorldObject, Updatable {

	public static final Type wordType = new Type("Word");

	public static final double MIN_RADIUS = 20.0;
	
	public final String string;
	public final char[] characters;
	public double startingRadius;
	public double radius;
	public double fontSizeScale; //how to scale the font size
	public double startDegrees;
	public Font font;
	
	public Word(String string) {
		this(string, 50, 2.0, 0.0);
	}
	
	public Word(String string, double size, double fontScaleSize, double startDegrees) {
		this.string = string + " " + string + " " + string + " " + string + " " + string + " " + string +
				 " " + string + " " + string + " " + string + " " + string + " " + string;
		this.characters = this.string.toCharArray();
		this.radius = this.startingRadius = size;
		this.fontSizeScale = 2.0;
		this.startDegrees = startDegrees;
		this.font = new Font("Arial", Font.BOLD, (int) (12 * fontScaleSize));
	}
	
	public Font getFont() {
		return font;
	}
	
	public List<Letter> getLetters() {
		List<Letter> letters = new ArrayList<Letter>();
		double size = this.radius;
		double fontSizeScale = this.fontSizeScale;
		double startDegrees = this.startDegrees;
		for (char c : characters) {
			letters.add(new Letter(c, size, fontSizeScale, startDegrees));
			
			size *= 0.97;
			fontSizeScale *= 0.97;
			startDegrees += 15;
		}
		return letters;
	}

	double degreesPerMilli = 2.0/16.0;
	double scalePerUpdate = 0.996;
	
	@Override
	public void update(long millisElapsed, World world) {
		if (millisElapsed > 0.0) {
			this.startDegrees += degreesPerMilli * millisElapsed;
			this.startDegrees %= 360.0;
			
			this.radius *= scalePerUpdate;
			this.fontSizeScale *= scalePerUpdate;
		}
	}

	@Override
	public Type getType() {
		return wordType;
	}

}
