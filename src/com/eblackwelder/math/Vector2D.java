package com.eblackwelder.math;

import java.awt.geom.Point2D;


public class Vector2D {

	public final double x;
	public final double y;

	public Vector2D() {
		this(0, 0);
	}
	
	public Vector2D(Vector2D other) {
		this(other.x, other.y);
	}
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param direction The direction of the new Vector (does not have to be a unit Vector).
	 * @param magnitude The magnitude of the new Vector.
	 */
	public Vector2D(Vector2D direction, double magnitude) {
		double oldMagnitude = direction.getMagnitude();
		double scale = magnitude / oldMagnitude;
		this.x = direction.x * scale;
		this.y = direction.y * scale;
	}

	public Vector2D minus(Vector2D other) {
		return new Vector2D(this.x - other.x, this.y - other.y);
	}
	
	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}
	
	public boolean isAlmostZero(double epsilon) {
		return MathUtils.almostZero(x, epsilon) && MathUtils.almostZero(y, epsilon);
	}
	
	public double getTheta() {
		return Math.atan2(y, x);
	}
	
	protected int getQuadrant() {
		int quadrant;
		if (x >= 0) {
			if (y >= 0) {
				quadrant = 1;
			} else {
				quadrant = 2;
			}
		} else if (y >= 0) {
			quadrant = 4;
		} else {
			quadrant = 3;
		}
		return quadrant;
	}
	
	public Point2D asPoint() {
		return new Point2D.Double(x, y);
	}

	@Override
	public String toString() {
		return "{ " + x + ", " + y + " }";
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof Vector2D) {
			Vector2D other = (Vector2D) obj;
			equals = this.getClass().isAssignableFrom(obj.getClass()) &&
					MathUtils.areEquivalent(this.x, other.x, 0.0001) &&
					MathUtils.areEquivalent(this.y, other.y, 0.0001);
		}
		return equals;
	}

	@Override
	public int hashCode() {
		int hashCode = 51 * getClass().hashCode();
		hashCode += x * 31;
		hashCode += y * 29;
		return hashCode;
	}
}
