package com.eblackwelder.graphics.images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.imageio.ImageIO;

import com.eblackwelder.math.Function1;
import com.eblackwelder.world.ui.UIWorldDriver;

public class ImageDriver extends UIWorldDriver {
	
	private final BufferedImage bufferedImage;

	private int numImagesPerFrame = 1; //TODO: scale per elapsed millis
	
	private final double resizeScale;
	private final Function1<Double> resizeFunction = new Function1<Double>() {
		@Override public Double Evaluate(Double... args) {
			double origSize = args[0];
			double period = args[1];
			
			return origSize *= resizeScale * period / 16.0;
		}
	};
	
	public ImageDriver(String demoName, String resourceName, double resizeScale) throws IOException {
		super(demoName);
		
		super.gridPanel.setRendererForType(new ImageRenderer(), Image.imageType);
		this.resizeScale = resizeScale;
		this.bufferedImage = ImageIO.read(this.getClass().getResourceAsStream(resourceName));
		frame.getContentPane().setBackground(Color.BLACK);
	}

	private int minSize = 100;
	private int maxSize = 300;
	private Random random = new Random(System.currentTimeMillis());
	private Image createImage() {
		Dimension panelSize = frame.getContentPane().getSize();
		double x = random.nextDouble() * panelSize.getWidth();
		double y = random.nextDouble() * panelSize.getHeight();
		double size = random.nextInt(maxSize - minSize) + minSize;
		int startingDegrees = random.nextInt(360);
		Image image = new Image(bufferedImage, size, startingDegrees, resizeFunction);
		image.setLocation(x, y);
		return image;
	}

	@Override
	protected boolean needsRepaint() {
		return !world.getObjects().isEmpty();
	}

	@Override
	protected void beforePaint() {
		for (int ii = 0; ii < numImagesPerFrame; ii++) {
			Image image = createImage();
			world.addObject(image);
		}
	}
	
	@Override
	protected void afterPaint() {
		Collection<Image> deadLetters = new ArrayList<Image>();
		for (Object object : world.getObjects()) {
			if (object instanceof Image) {
				Image letter = (Image) object;
				letter.resize(period);
				if (letter.size < 10) {
					deadLetters.add(letter);
				}
			}
		}
		world.discardAll(deadLetters);
	}
}
