package org.tutsplus.example3;

import javafx.scene.canvas.Canvas;

class Global {

    final static int sunImageSize = 128;
    private final static int CANVAS_HEIGHT = 512;
    private final static int CANVAS_WIDTH = 512;

    final static Canvas createCanvas() {
        return new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    }
}
