package com.eblackwelder.world.model.impl;

import com.eblackwelder.math.Position;
import com.eblackwelder.world.model.Positionable;

public class PositionedObject implements Positionable {

	private Position location;
	
	public PositionedObject() {
		this(new Position());
	}
	
	public PositionedObject(Position location) {
		this.location = location;
	}
	
	@Override
	public Position getPosition() {
		return location;
	}

	@Override
	public void setPosition(Position location) {
		this.location = location;
	}
	
	public void setLocation(double x, double y) {
		setPosition(new Position(x, y));
	}
	
	public void translate(double deltaX, double deltaY) {
		setLocation(location.x + deltaX, location.y + deltaY);
	}
}
