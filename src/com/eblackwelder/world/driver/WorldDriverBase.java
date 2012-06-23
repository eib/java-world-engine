package com.eblackwelder.world.driver;

import java.util.Timer;
import java.util.TimerTask;

import com.eblackwelder.world.World;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.model.Updatable;


public abstract class WorldDriverBase implements Runnable {

	protected long period; //in milliseconds
	protected final World world;
	protected TimerTask mainTimerTask = new TimerTask() {
		@Override public void run() {
			mainLoop();
		}
	};

	public WorldDriverBase(World world, double hertz) {
		this.world = world;
		this.period =  (long) (1000.0 / hertz);
	}

	public World getWorld() {
		return world;
	}
	
	protected final void mainLoop() {
		updateObjects();
		render();
	}
	
	private long lastUpdateMillis;
	protected void updateObjects() {
		long newMillis = System.currentTimeMillis();
		for (WorldObject object : world.getObjects()) {
			if (object instanceof Updatable) {
				Updatable u = (Updatable)object;
				u.update(newMillis - lastUpdateMillis, world);
			}
		}
		lastUpdateMillis = newMillis;
	}
	
	protected abstract void render();
	
	protected void startLoopTimerTask() {
		//start main timer
		lastUpdateMillis = System.currentTimeMillis();
		new Timer("World main-loop thread", true).schedule(mainTimerTask, 0, period);
	}

	protected void stopLoopTimerTask() {
		mainTimerTask.cancel();
	}
}
