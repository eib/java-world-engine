package com.eblackwelder.math;

import com.eblackwelder.math.Vector2D;

public class Position extends Vector2D {
	
	public Position() {
		super();
	}

	public Position(double x, double y) {
		super(x, y);
	}

	public Position(Position other) {
		super(other);
	}

	public Position add(Position other) {
		return new Position(this.x + other.x, this.y + other.y);
	}
}
