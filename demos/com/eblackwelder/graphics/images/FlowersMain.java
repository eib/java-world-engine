package com.eblackwelder.graphics.images;

import java.io.IOException;

public class FlowersMain extends ImageDriver {

	public FlowersMain() throws IOException {
		super("Daisies", "daisy.gif", 0.985);
	}

	public static void main(String[] args) throws Exception {
		new FlowersMain().run();
	}
}
