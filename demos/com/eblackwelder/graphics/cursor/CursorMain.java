package com.eblackwelder.graphics.cursor;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.beans.EventHandler;

import com.eblackwelder.world.ui.UIWorldDriver;

public class CursorMain extends UIWorldDriver {

	private final Cursor cursor = new Cursor("You");
	
	public CursorMain() {
		super("Cursor");
		cursor.setLocation(100, 100);
		
		gridPanel.setBackground(Color.WHITE);
		gridPanel.setRendererForType(new CursorRenderer(), Cursor.cursorType);
		world.addObject(cursor);
		
		frame.addKeyListener(EventHandler.create(KeyListener.class, this, "keyPressed", "keyCode", "keyPressed"));
		frame.addKeyListener(EventHandler.create(KeyListener.class, this, "keyReleased", "keyCode", "keyReleased"));
	}

	public void keyPressed(int keyCode) {
		boolean toggle = true;
		cursor.movement.toggleDirectionForKey(keyCode, toggle);
	}

	public void keyReleased(int keyCode) {
		boolean toggle = false;
		cursor.movement.toggleDirectionForKey(keyCode, toggle);
	}

	public static void main(String[] args) {
		new CursorMain().run();
	}
	
	@Override
	protected boolean needsRepaint() {
		return cursor.movement.isMoving();
	}
}
