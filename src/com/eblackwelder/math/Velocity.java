package com.eblackwelder.math;

import com.eblackwelder.math.Vector2D;

public class Velocity extends Vector2D {
	
	public Velocity() {
		super();
	}

	public Velocity(double x, double y) {
		super(x, y);
	}

	public Velocity(Vector2D other) {
		super(other);
	}
	
	public Velocity(Vector2D direction, double magnitude) {
		super(direction, magnitude);
	}

	public Velocity add(Velocity other) {
		return new Velocity(this.x + other.x, this.y + other.y);
	}
	
	public Position getDeltaPosition(double deltaSeconds) {
		double dx = this.x * deltaSeconds;
		double dy = this.y * deltaSeconds;
		return new Position(dx, dy);
	}
}
