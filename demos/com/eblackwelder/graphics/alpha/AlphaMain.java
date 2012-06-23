package com.eblackwelder.graphics.alpha;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import com.eblackwelder.math.Function1;
import com.eblackwelder.world.ui.UIWorldDriver;

public class AlphaMain extends UIWorldDriver {

	private final FontMap fonts = new FontMap("Arial");
	private int numLettersPerKeyPress = 4;
	private int minSize = 25;
	private int maxSize = 250;
	private Random random = new Random(System.currentTimeMillis());
	
	private final Function1<Double> resizeFunction = new Function1<Double>() {
		@Override public Double Evaluate(Double... args) {
			double origSize = args[0];
			double period = args[1];
			
			return origSize *= 0.95 * period / 16.0;
		}
	};

	public AlphaMain() {
		super("Letters");
		gridPanel.setRendererForType(new MultiFontLetterRenderer(), Letter.letterType);
		frame.addKeyListener(EventHandler.create(KeyListener.class, this, "keyPressed", "keyChar", "keyPressed"));
	}
	
	public void keyPressed(char keyChar) {
		if (keyChar != KeyEvent.CHAR_UNDEFINED) {
			for (int ii = 0; ii < numLettersPerKeyPress; ii++) {
				Dimension panelSize = frame.getContentPane().getSize();
				double x = random.nextDouble() * panelSize.getWidth();
				double y = random.nextDouble() * panelSize.getHeight();
				double size = random.nextInt(maxSize - minSize) + minSize;
				String fontName = fonts.getRandomFontNameThatSupportsCharacter(keyChar);
				Letter letter = new Letter(keyChar, size, fontName, resizeFunction);
				letter.setLocation(x, y);
				world.addObject(letter);
			}
		}
	}

	@Override
	protected void afterPaint() {
		Collection<Letter> deadLetters = new ArrayList<Letter>();
		for (Object object : world.getObjects()) {
			if (object instanceof Letter) {
				Letter letter = (Letter) object;
				letter.resize(period);
				if (letter.size < 2) {
					deadLetters.add(letter);
				}
			}
		}
		world.discardAll(deadLetters);
	}

	public static void main(String[] args) {
		new AlphaMain().run();
	}
}
