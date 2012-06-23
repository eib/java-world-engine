package com.eblackwelder.world.model.impl;

import com.eblackwelder.math.Position;
import com.eblackwelder.math.Velocity;
import com.eblackwelder.world.World;
import com.eblackwelder.world.model.Movable;
import com.eblackwelder.world.model.Updatable;

public class MovingObject extends PositionedObject implements Movable, Updatable {

	private Velocity velocity;
	private double maxVelocity = Double.POSITIVE_INFINITY;

	public MovingObject() {
		this(new Position());
	}
	public MovingObject(Position position) {
		this(position, new Velocity());
	}
	public MovingObject(Position position, Velocity velocity) {
		super(position);
		this.velocity = velocity;
	}
	
	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		double maxV = getMaxVelocity();
		if (velocity.getMagnitude() > maxV) {
			velocity = new Velocity(velocity, maxV);
		}
		this.velocity = velocity;
	}
	
	public void setMaxVelocity(double magnitude) {
		this.maxVelocity = magnitude;
	}

	public double getMaxVelocity() {
		return maxVelocity;
	}
	
	@Override
	public void update(long millisElapsed, World world) {
		double deltaSeconds = millisElapsed / 1000.0;
		Position deltaPosition = getVelocity().getDeltaPosition(deltaSeconds);
		setPosition(getPosition().add(deltaPosition));
	}
	
}