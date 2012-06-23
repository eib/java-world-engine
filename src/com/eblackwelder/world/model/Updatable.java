package com.eblackwelder.world.model;

import com.eblackwelder.world.World;

public interface Updatable {

	public void update(long millisElapsed, World world);
	
}
