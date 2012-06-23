package com.eblackwelder.graphics.cursor;

import com.eblackwelder.math.Velocity;
import com.eblackwelder.world.Type;
import com.eblackwelder.world.World;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.model.Named;
import com.eblackwelder.world.model.impl.MovingObject;


public class Cursor extends MovingObject implements WorldObject, Named {

	public static final Type cursorType = new Type("Cursor");
	
	private String name;
	public final Movement movement = new Movement();
	
	public Cursor(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return cursorType;
	}
	
	@Override
	public void update(long millisElapsed, World world) {
		double scale = 9.0 * millisElapsed;
		Velocity v = new Velocity(movement.asVector(scale));
		setVelocity(v);
		super.update(millisElapsed, world);
	}
}
