package com.eblackwelder.graphics.colorPoints;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import com.eblackwelder.math.Function1;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.ui.UIWorldDriver;

public class ColorPointMain extends UIWorldDriver {
	
	private final Collection<ColorPoint> newPoints = new Vector<ColorPoint>(); //accessed by timer thread and UI thread

	private final MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override public void mousePressed(MouseEvent e) {
			addPoint(e.getPoint());
		}
		@Override public void mouseDragged(MouseEvent e) {
			addPoint(e.getPoint());
		}
		@Override public void mouseMoved(MouseEvent e) {
			addPoint(e.getPoint());
		}
		@Override
		public void mouseExited(MouseEvent e) {
			ColorPointMain.this.mouseExited();
		}
	};

	private final Function1<Double> heatDissipationFunction = new Function1<Double>() {
		@Override
		public Double Evaluate(Double... args) {
			double initial = args[0];
			double period = args[1];
			return initial -= 0.01 * period / 16.0;
		}
	};
	
	public ColorPointMain() {
		super("Colors");
		
		gridPanel.setRendererForType(new ColorPointRenderer(), ColorPoint.pointType);
		gridPanel.setBackground(Color.BLACK);
		gridPanel.addMouseListener(mouseAdapter);
		gridPanel.addMouseMotionListener(mouseAdapter);
	}

	//Static allows this to work across multiple "worlds" running on the same JVM. :D
	private static int hue = 0;
	protected static int nextHue() {
		hue++;
		hue %= 360;
		return hue;
	}

	private ColorPoint lastPoint = null;
	public void addPoint(Point location) {
		int nextHue = nextHue();
		ColorPoint colorPoint = new ColorPoint(nextHue);
		colorPoint.setLocation(location.getX(), location.getY());
		newPoints.add(colorPoint);
		if (lastPoint != null) {
			lastPoint.dissipationFunction = heatDissipationFunction;
		}
		lastPoint = colorPoint;
	}
	
	protected void mouseExited() {
		if (lastPoint != null) {
			lastPoint.dissipationFunction = heatDissipationFunction;
		}
	}

	@Override
	protected void updateObjects() {
		world.addObjects(newPoints);
		newPoints.clear();
		super.updateObjects();
		clearDeadPoints();
	}

	private void clearDeadPoints() {
		Collection<ColorPoint> deadPoints = new ArrayList<ColorPoint>();
		for (WorldObject object : world.getObjects()) {
			if (object instanceof ColorPoint) {
				ColorPoint point = (ColorPoint) object;
				if (point.heatLevel <= 0.01) {
					deadPoints.add(point);
				}
			}
		}
		world.discardAll(deadPoints);
	}
	
	public static void main(String[] args) {
		new ColorPointMain().run();
	}
}
