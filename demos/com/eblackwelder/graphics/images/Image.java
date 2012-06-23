package com.eblackwelder.graphics.images;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import com.eblackwelder.math.Function1;
import com.eblackwelder.world.Type;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.model.impl.PositionedObject;


public class Image extends PositionedObject implements WorldObject {
	
	public static final Type imageType = new Type("Image");
	
	private BufferedImage image;
	public Dimension origImageSize;
	public double size;
	public Function1<Double> resizeFunction;
	private int rotationDegrees = 0;
	
	private static boolean nextRotateRight = true;
	public boolean rotateRight;
	
	public Image(BufferedImage image, double size, int startingRotationDegrees, Function1<Double> resizeFunction) {
		this.image = image;
		this.size = size;
		this.rotationDegrees = startingRotationDegrees;
		this.resizeFunction = resizeFunction;
		this.origImageSize = new Dimension(image.getWidth(), image.getHeight());
		this.rotateRight = nextRotateRight;
		nextRotateRight = !nextRotateRight;
	}
	
	@Override
	public Type getType() {
		return imageType;
	}
	
	public Dimension getOrigImageSize() {
		return origImageSize;
	}
	
	public double getRotationDegrees() {
		return rotationDegrees * (rotateRight ? 1 : -1);
	}

	public BufferedImage getBufferedImage() {
		return image;
	}
	
	public void resize(double period) {
		this.size = resizeFunction.Evaluate(size, period);
		rotationDegrees++; //TODO: as a scale of the period
	}
}
