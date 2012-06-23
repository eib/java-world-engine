
Changes
=======

v0.01 (6/23/2012)
-----------------

* World engine features:
	- Conceptual model types: World, Positionable, Movable.
	- Updatable interface: provides "per frame" updates to the world and/or objects.

* Graphics framework features:
	- Support for multi-layered rendering:
		  Renderers can access Graphics2D objects for separate layers at the same time,
		  simplifying layered rendering implementations.
	- UIWorldDriver implementation -- for running/displaying World instances inside a Swing JFrame.

* Demos:
	Letters      - Type a key to watch that letter (in various fonts) float by.
	Colors       - A multi-hued, snake-like being follows your mouse cursor.
	Cursor       - Use arrow keys to move a cursor around the screen.
	Daisies      - Falling, spinning daisies.
	Popcorn      - Falling, spinning popcorn. Perfect for making yourself hungry.
	Family Words - Family-related words spiraling off into the horizon.
