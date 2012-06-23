package com.eblackwelder.graphics.images;

import java.io.IOException;

public class PopcornMain extends ImageDriver {

	public PopcornMain() throws IOException {
		super("Popcorn", "popcorn.gif", 0.955);
	}

	public static void main(String[] args) throws Exception {
		new PopcornMain().run();
	}
}
