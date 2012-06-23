package com.eblackwelder.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.eblackwelder.graphics.alpha.AlphaMain;
import com.eblackwelder.graphics.colorPoints.ColorPointMain;
import com.eblackwelder.graphics.cursor.CursorMain;
import com.eblackwelder.graphics.images.FlowersMain;
import com.eblackwelder.graphics.images.PopcornMain;
import com.eblackwelder.graphics.words.WordsMain;
import com.eblackwelder.world.ui.UIWorldDriver;

public class GraphicsMain {

	private static final Class<?>[] demos = {
		AlphaMain.class,
		ColorPointMain.class,
		CursorMain.class,
		FlowersMain.class,
		PopcornMain.class,
		WordsMain.class,
	};
	
	public static void main(String[] args) {
		JFrame parent = new JFrame("Graphics Demos");
		parent.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		List<UIWorldDriver> drivers = createDrivers();
		
		JPanel contentPane = new JPanel(new BorderLayout(3, 3));
		contentPane.add(createDriversPanel(drivers, parent), BorderLayout.NORTH);
		parent.setContentPane(contentPane);
		
		parent.setPreferredSize(new Dimension(300, 250));
		parent.pack();
		parent.setLocationRelativeTo(null);
		parent.setVisible(true);
	}
	
	private static JPanel createDriversPanel(List<UIWorldDriver> drivers, final Window parent) {
		JPanel panel = new JPanel(new GridLayout(drivers.size(), 1, 5, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
		for (UIWorldDriver driverPrototype : drivers) {
			final Class<? extends UIWorldDriver> driverClass = driverPrototype.getClass();
			
			AbstractButton button = new JButton("Run");
			button.setPreferredSize(new Dimension(100, button.getPreferredSize().height));
			button.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					try {
						UIWorldDriver driver = instantiateWorldDriver(driverClass);
						driver.run();
					} catch (Exception ex) {
						System.out.println("Couldn't instantiate a new driver of class " + driverClass.getName());
					}
				}
			});
			
			JPanel driverPanel = new JPanel(new BorderLayout());
			driverPanel.add(new JLabel(driverPrototype.getDemoName()), BorderLayout.WEST);
			driverPanel.add(button, BorderLayout.EAST);
			panel.add(driverPanel);
		}
		return panel;
	}

	private static List<UIWorldDriver> createDrivers() {
		List<UIWorldDriver> drivers = new ArrayList<UIWorldDriver>();
		for (Class<?> clazz : demos) {
			try  {
				UIWorldDriver driver = instantiateWorldDriver(clazz);
				drivers.add(driver);
			} catch (Exception e) {
				System.out.println("Skipping driver of class " + clazz.getName());
				e.printStackTrace(System.out);
			}
		}
		return drivers;
	}

	private static UIWorldDriver instantiateWorldDriver(Class<?> clazz) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		if (!UIWorldDriver.class.isAssignableFrom(clazz)) {
			throw new RuntimeException("Class " + clazz.getName() + " is not an instance of " + UIWorldDriver.class.getName());
		}
		Constructor<?> constructor = clazz.getConstructor();
		UIWorldDriver driver = (UIWorldDriver) constructor.newInstance();
		return driver;
	}
}
