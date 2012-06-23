package com.eblackwelder.graphics.cursor;

import java.awt.event.KeyEvent;

import com.eblackwelder.math.Vector2D;

public class Movement {
	boolean left = false;
	boolean right = false;
	boolean up = false;
	boolean down = false;
	
	public int getUnitX() {
		int deltaX = 0;
		if (left) {
			deltaX -= 1;
		}
		if (right) {
			deltaX += 1;
		}
		return deltaX;
	}
	public int getUnitY() {
		int deltaY = 0;
		if (up) {
			deltaY -= 1;
		}
		if (down) {
			deltaY += 1;
		}
		return deltaY;
	}
	
	public Vector2D asVector(double scale) {
		double vx = getUnitX();
		double vy = getUnitY();
		double magnitude = Math.sqrt(vx * vx + vy * vy);  //otherwise, movement occurs at sqrt(2) (which is > 1).
		if (magnitude > 0.0) {
			vx /= magnitude;
			vy /= magnitude;
		}
		vx *= scale;
		vy *= scale;
		return new Vector2D(vx, vy);
	}
	
	public boolean isMoving() {
		return up || down || left || right;
	}
	
	public void toggleDirectionForKey(int keyCode, boolean toggle) {
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			left = toggle;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			right = toggle;
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			up = toggle;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			down = toggle;
			break;
		}
	}
}