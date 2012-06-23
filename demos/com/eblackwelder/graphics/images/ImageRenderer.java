package com.eblackwelder.graphics.images;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.eblackwelder.math.Position;
import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.ui.IGraphicsContext;
import com.eblackwelder.world.ui.Renderer;


public class ImageRenderer implements Renderer {

	@Override
	public void render(WorldObject object, IGraphicsContext context) {
		Image image = (Image)object;
		Graphics2D g2d = context.getMainLayer();
		
		Position location = image.getPosition();
		
//		m00 - the X coordinate scaling element of the 3x3 matrix
//		m10 - the Y coordinate shearing element of the 3x3 matrix
//		m01 - the X coordinate shearing element of the 3x3 matrix
//		m11 - the Y coordinate scaling element of the 3x3 matrix
//		m02 - the X coordinate translation element of the 3x3 matrix
//		m12 - the Y coordinate translation element of the 3x3 matrix
		
		Dimension imageSize = image.getOrigImageSize();
		float scaleX = (float) (image.size / imageSize.getWidth()); //scale down the original image to the new (fluctuating) size
		float scaleY = (float) (image.size / imageSize.getHeight());
		float shearX = 0f;
		float shearY = 0f;
		float translateX = (float) (-image.size / 2.0); //additional translation to center the image
		float translateY = (float) (-image.size / 2.0);
		AffineTransform transform = new AffineTransform(scaleX, shearY, shearX, scaleY, translateX, translateY);
		
		double theta = Math.toRadians(image.getRotationDegrees());
		
		g2d.translate(location.x, location.y); //center on the image's location
		g2d.rotate(theta); //rotate
		
		g2d.drawImage(image.getBufferedImage(), transform, null);
		
		g2d.rotate(-theta);
		g2d.translate(-location.x, -location.y);
	}
	
}
