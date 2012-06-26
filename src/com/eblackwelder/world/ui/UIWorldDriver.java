package com.eblackwelder.world.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.beans.EventHandler;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.eblackwelder.world.Type;
import com.eblackwelder.world.World;
import com.eblackwelder.world.driver.WorldDriverBase;

public class UIWorldDriver extends WorldDriverBase {
	
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
	}
	
	protected final String demoName;
	protected final JFrame frame = new JFrame("World - Demo");
	protected final GridPanel gridPanel;
	
	public UIWorldDriver(String demoName) {
		this(demoName, new World(), 60.0);
	}

	public UIWorldDriver(String demoName, World world, double hertz) {
		super(world, hertz);

		this.demoName = demoName;
		this.gridPanel = new GridPanel(world);
		frame.setTitle(demoName + " - Demo");
		
		initFrame();
		initListeners();
	}
	
	private void initFrame() {
		frame.setContentPane(gridPanel);
		frame.setPreferredSize(new Dimension(600, 600));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void initListeners() {
		frame.addWindowListener(EventHandler.create(WindowListener.class, this, "windowOpened", null, "windowOpened"));
		frame.addWindowListener(EventHandler.create(WindowListener.class, this, "windowClosing", null, "windowClosing"));
	}
	
	public void setRendererForType(Renderer renderer, Type type) {
		gridPanel.setRendererForType(renderer, type);
	}

	public void addRenderer(Renderer renderer) {
		gridPanel.addRenderer(renderer);
	}
	
	public void setBackgroundColor(Color color) {
		gridPanel.setBackground(color);
	}

	public void setPreferredSize(Dimension size) {
		frame.setPreferredSize(size);
	}

	public JFrame getFrame() {
		return frame;
	}

	public String getDemoName() {
		return demoName;
	}

	@Override
	public void run() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/* Painting (part of the main loop) */

	@Override
	protected final void render() {
		beforePaint();
		if (needsRepaint()) {
			gridPanel.repaint();
		}
		afterPaint();
	}
	
	protected boolean needsRepaint() {
		return !world.getObjects().isEmpty();
	}

	protected void beforePaint() { }
	
	protected void afterPaint() { }

	
	/* Window listener callbacks to start/stop the timed repaint task. */

	public void windowOpened() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override public void run() {
				//auto-correct for window borders, etc.
				Dimension preferredSize = frame.getPreferredSize();
				Dimension actualSize = frame.getContentPane().getSize();
				frame.setSize(2*preferredSize.width - actualSize.width, 2*preferredSize.height - actualSize.height);
				
				startLoopTimerTask();
			}
		});
	}
	
	public void windowClosing() {
		stopLoopTimerTask();
	}

}