package com.eblackwelder.graphics.words;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.eblackwelder.world.WorldObject;
import com.eblackwelder.world.ui.UIWorldDriver;

public class WordsMain extends UIWorldDriver {

	private Random random = new Random(System.currentTimeMillis());
	private double startingRadiusSize = 200;
	private double fontScaleFactor = 3.0;
	private long createWordPeriod = 2500; //millis

	private String[] words = {
		"Mommy",
		"Dad", "Father",
		"Daughter", "Sister",
		"Son", "Brother",
		"Baby",
		"Family", "Love", "Home", "Happy", "Friends",
	};
	private TimerTask createWordTask = new TimerTask() {
		@Override public void run() {
			int index = random.nextInt(words.length);
			String word = words[index];
//			word = "Hello";
			createWord(word);
		}
	};
	
	public WordsMain() {
		super("Family Words");
		
		super.gridPanel.setRendererForType(new WordRenderer(), Word.wordType);
		super.frame.setPreferredSize(new Dimension(900, 700));
		super.gridPanel.setBackground(Color.WHITE);
	}

	private void createWord(String wordString) {
		Dimension panelSize = gridPanel.getSize();
		Word word = new Word(wordString, startingRadiusSize, fontScaleFactor, 0);

		double insetFraction = 1.0 / 5.0;
		double width = panelSize.getWidth();
		double height = panelSize.getHeight();
		double x = random.nextDouble() * width * (1.0 - 2.0 * insetFraction);
		double y = random.nextDouble() * height * (1.0 - 2.0 * insetFraction);
		x += (width * insetFraction);
		y += (height * insetFraction);
		word.setLocation(x, y);
		world.addObject(word);
	}
	
	@Override
	public void windowOpened() {
		super.windowOpened();
		new Timer("Create word timer", true).scheduleAtFixedRate(createWordTask, 1000, createWordPeriod);
	}

	@Override
	public void windowClosing() {
		createWordTask.cancel();
		super.windowClosing();
	}

	@Override
	protected void afterPaint() {
		Collection<WorldObject> deadWords = new ArrayList<WorldObject>();
		for (WorldObject object : world.getObjectsByType(Word.wordType)) {
			Word word = (Word) object;
			if (word.radius < Word.MIN_RADIUS) {
				deadWords.add(word);
			}
		}
		world.discardAll(deadWords);
	}

	public static void main(String[] args) {
		new WordsMain().run();
	}
}
