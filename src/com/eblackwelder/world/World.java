package com.eblackwelder.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;


public class World {

	private Collection<WorldObject> objects;
	
	public World() {
		this.objects = new Vector<WorldObject>();
	}

	public void addObject(WorldObject object) {
		this.objects.add(object);
	}
	
	public void addObjects(Collection<? extends WorldObject> objects) {
		this.objects.addAll(objects);
	}

	public void discardObject(WorldObject object) {
		this.objects.remove(object);
	}
	
	public void discardAll(Collection<? extends WorldObject> objects) {
		this.objects.removeAll(objects);
	}
	
	public Collection<WorldObject> getObjects() {
		return new ArrayList<WorldObject>(objects);
	}
	
	public Collection<? extends WorldObject> getObjectsByType(Type type) {
		Collection<WorldObject> objects = new ArrayList<WorldObject>();
		for (WorldObject object : getObjects()) {
			if (object.getType().equals(type)) {
				objects.add(object);
			}
		}
		return objects;
	}
}
