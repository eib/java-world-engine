package com.eblackwelder.graphics.colorPoints;

import java.awt.Color;

import com.eblackwelder.math.Function1;
import com.eblackwelder.world.Type;
import com.eblackwelder.world.World;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.model.Updatable;
import com.eblackwelder.world.model.impl.PositionedObject;

public class ColorPoint extends PositionedObject implements WorldObject, Updatable {

	public static final Type pointType = new Type("ColorPoint");
	
	public static final double MAX_HEAT = 1.0;
	private static final Function1<Double> identityFunction = new Function1<Double>() {
		@Override public Double Evaluate(Double... args) {
			return args[0];
		}
	};
	private double componentStrength = 0;
	public double heatLevel;
	public int hue = 0; //an int value from 0 to 360 (doesn't change once the point is created)
	public float brightness = 1.0f; //between 0 and 1f (will fade out over time)
	public float saturation = 0.8f; //between 0 and 1f (stays constant)
	public Function1<Double> dissipationFunction;
	
	public ColorPoint(int hue) {
		this(hue, MAX_HEAT, identityFunction);
	}
	
	public ColorPoint(int hue, double heatLevel, Function1<Double> dissipationFunction) {
		this.hue = hue;
		this.heatLevel = heatLevel;
		this.dissipationFunction = dissipationFunction;
		this.componentStrength = calculateStrength();
	}
	
	public Color getColor() {
		float hueFloat = ((float) this.hue) / 360.0f;
		return Color.getHSBColor(hueFloat, saturation, brightness);
	}

	private final double heatScale = 30.0;
	public int getRadius() {
		int radius = (int) (heatScale * componentStrength * 0.8);
		return radius;
	}
	
	protected double calculateStrength() {
		double componentStrength = 1.0 - Math.log(heatLevel) / Math.log(0.1);
		return componentStrength;
	}
	
	@Override
	public void update(long millisElapsed, World world) {
		dissipate((double)millisElapsed);
	}
	
	public void dissipate(double period) {
		this.heatLevel = dissipationFunction.Evaluate(heatLevel, period);
		this.componentStrength = calculateStrength();
		this.brightness = (float) componentStrength;
//		this.saturation = (float) componentStrength; //this greys out the color. I'd rather have fade-to-black.
	}
	
	@Override
	public Type getType() {
		return pointType;
	}
	
}
