package com.eblackwelder.world.ui;

import com.eblackwelder.world.WorldObject;

public interface Renderer {

	public void render(WorldObject object, IGraphicsContext context);
}
