java-world-engine
=================
A World Engine and Graphics/Rendering Framework.

https://github.com/eib/java-world-engine


User Guide
==========
How to run the demos:

* Download one of the "Executable JAR" packages: https://github.com/eib/java-physics-demos/downloads
* Make sure Java 1.6 (or greater) is installed on your machine: http://java.com/en/download/index.jsp
* Windows users can double-click on the Executable JAR file to run the suite of demos.
  Otherwise, try typing this into the command-line/terminal/shell (from the same folder as the JAR):
    java -jar WorldEngine-[version].jar


World Engine
============
The "World" model contains the following built-in conceptual types:
* Positionable - Objects that have a Position
* Movable - Objects that have a Velocity

The world engine uses a timer to send periodic update events to world objects
of the "Updatable" interface. These update events contain the time elapsed since the last update,
which allows the objects to update their position, speed, or any other property.


Rendering Framework
===================
The rendering framework has support for drawing objects across a number of layers.

For example, in the "Cursor" demo, the current position is marked with a triangle;
this is rendered in the "main" layer. The triangle has a drop-shadow, which is drawn
in a "shadow" layer and the name of the cursor ("You") is drawn in an "overlay" layer.

Without a true multi-layer rendering framework, the drop-shadow and text could be
realized as separate objects in a specific layer (and all objects in lower layers
would have to be drawn before all the objects in upper layers). This leads to
complex object-management schemes.

Alternatively, separate renderers could be implemented, each drawing one "layer" of the object
at a time -- or the same renderer would need to handle multiple passes on the same object.
Either way, code to configure the Graphics context (Stroke, AffineTransforms, etc)
starts to be duplicated across the different layer renderers.

Thus, the "IGraphicsContext" interface is a great solution for both simplifying object-management
and eliminating duplicate configuration code. The API contains methods for getting
separate Graphics2D instances per layer. It also contains methods to manipulate all the
layers at the same time.

This snippet is taken from the Renderer implementation of the "Cursor" demo:

	public void drawCursor(Positionable cursor, IGraphicsContext context) {
		Position location = cursor.getPosition();

		context.setStroke(new BasicStroke(1));

		Graphics2D mainLayer = context.getMainLayer();
		Graphics2D overlayLayer = context.getOverlayLayer();
		Graphics2D shadowLayer = context.getShadowLayer();

		//define the shape of the cursor (relative to the origin)
		int[] xPoints = { 0, 3, -3 };
		int[] yPoints = { -6, 3, 3 };
		
		//translates all layers so the cursor can be rendered at the origin
		context.translate(location.x, location.y);

		//draw the cursor's drop-shadow (on the shadow layer)
		shadowLayer.translate(2, 2);
		shadowLayer.setColor(Color.GRAY);
		shadowLayer.fillPolygon(xPoints, yPoints, xPoints.length);
		shadowLayer.translate(-2, -2);

		//draw the cursor (on the main layer) 
		mainLayer.setColor(Color.GREEN);
		mainLayer.fillPolygon(xPoints, yPoints, xPoints.length);

		//write the name of the cursor (on the overlay layer)
		if (cursor instanceof Named) {
			Named named = (Named) cursor;
			overlayLayer.setColor(Color.BLACK);
			overlayLayer.drawString(named.getName(), 5, 3);	
		}
		
		//Reverts the transforms for all layers -- to play nicely with other renderers. 
		context.translate(-location.x, -location.y);
	}


Demos
=====
The following built-in demos are implemented using graphics/rendering framework:
* Letters      - Type a key to watch that letter (in various fonts) float by.
* Colors       - A multi-hued "snake" follows your mouse cursor.
* Cursor       - Use the arrow keys to move a marker around the screen.
* Daisies      - Falling, spinning daisies.
* Popcorn      - Falling, spinning popcorn. Perfect for making yourself feel hungry.
* Family Words - Family-related words spiraling off into the horizon.

The demos all use Swing coordinates as the basis for their "world":
* The point { 0, 0 } is the upper-left-hand corner of the window.
* positive Y is down.
* positive X is right.

The reason for this is because drawing text becomes a challenge if, for example,
the positive Y direction is flipped. If there is no text output involved, it is safe to transform
the GraphicsContext to your coordinate system of choice before sending them through the rendering system.


License
=======
MIT license: http://eib.mit-license.org/
