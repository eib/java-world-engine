package com.eblackwelder.world.ui.layers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.List;

import com.eblackwelder.world.ui.IGraphicsAdapter;

public class GraphicsAdapter implements IGraphicsAdapter {
	
	protected Graphics2D[] graphics;

	public GraphicsAdapter(List<Graphics2D> graphics) {
		this.graphics = graphics.toArray(new Graphics2D[graphics.size()]);
	}
	
	public GraphicsAdapter(Graphics2D... graphics) {
		this.graphics = graphics;
	}

	/* getters/setters */
	
	public Graphics2D[] getGraphics() {
		return graphics;
	}

	public void setGraphics(Graphics2D[] graphics) {
		this.graphics = graphics;
	}

	/* Colors, fonts, strokes, etc */

	@Override
	public void setColor(Color c) {
		for (Graphics2D g : graphics) {
			g.setColor(c);
		}
	}
	
	@Override
	public void setBackground(Color color) {
		for (Graphics2D g : graphics) {
			g.setBackground(color);
		}
	}

	@Override
	public void setFont(Font font) {
		for (Graphics2D g : graphics) {
			g.setFont(font);
		}
	}

	@Override
	public void setPaint(Paint paint) {
		for (Graphics2D g : graphics) {
			g.setPaint(paint);
		}
	}

	@Override
	public void setPaintMode() {
		for (Graphics2D g : graphics) {
			g.setPaintMode();
		}
	}

	@Override
	public void setStroke(Stroke stroke) {
		for (Graphics2D g : graphics) {
			g.setStroke(stroke);
		}
	}

	@Override
	public void setClip(int x, int y, int width, int height) {
		for (Graphics2D g : graphics) {
			g.setClip(x, y, width, height);
		}
	}

	/* Transforms */
	
	@Override
	public void rotate(double theta) {
		for (Graphics2D g : graphics) {
			g.rotate(theta);
		}
	}

	@Override
	public void scale(double sx, double sy) {
		for (Graphics2D g : graphics) {
			g.scale(sx, sy);
		}
	}

	@Override
	public void setTransform(AffineTransform transform) {
		for (Graphics2D g : graphics) {
			g.setTransform(transform);
		}
	}

	@Override
	public void shear(double shx, double shy) {
		for (Graphics2D g : graphics) {
			g.shear(shx, shy);
		}
	}

	@Override
	public void transform(AffineTransform transform) {
		for (Graphics2D g : graphics) {
			g.transform(transform);
		}
	}

	@Override
	public void translate(double tx, double ty) {
		for (Graphics2D g : graphics) {
			g.translate(tx, ty);
		}
	}

	@Override
	public void translate(int x, int y) {
		for (Graphics2D g : graphics) {
			g.translate(x, y);
		}
	}
}
