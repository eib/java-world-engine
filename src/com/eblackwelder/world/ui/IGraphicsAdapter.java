package com.eblackwelder.world.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

public interface IGraphicsAdapter {

	/* Colors, fonts, strokes, etc. */
	
	public void setColor(Color c);

	public void setBackground(Color color);

	public void setFont(Font font);

	public void setPaint(Paint paint);

	public void setPaintMode();

	public void setStroke(Stroke stroke);

	public void setClip(int x, int y, int width, int height);

	/* AffineTransforms */
	
	public void rotate(double theta);

	public void scale(double sx, double sy);

	public void setTransform(AffineTransform transform);

	public void shear(double shx, double shy);

	public void transform(AffineTransform transform);

	public void translate(double tx, double ty);

	public void translate(int x, int y);

}