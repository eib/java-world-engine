package com.eblackwelder.world.model.impl;

import com.eblackwelder.world.Type;
import com.eblackwelder.world.WorldObject;

public class AbstractWorldObject implements WorldObject {

	private final Type type;

	public AbstractWorldObject(String type) {
		this.type = new Type(type);
	}

	@Override
	public Type getType() {
		return type;
	}
	
}
