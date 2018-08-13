package org.tutsplus.example3;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.animation.AnimationTimer;

import java.util.stream.IntStream;

// Global of Earth rotating around the sun. (Hello, world!)
public class Example3AI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("AnimatedImage Example");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = Global.createCanvas();
        root.getChildren().add(canvas);

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        final Image earth = new Image("earth.png");
        final Image sun = new Image("sun.png");
        final Image space = new Image("space.png");

        final AnimatedImage ufo = new AnimatedImage();
        Image[] imageArray = new Image[6];
        IntStream.range(0, imageArray.length - 1).forEach(i ->
                imageArray[i] = new Image("ufo_" + i + ".png"));
        ufo.frames = imageArray;
        ufo.duration = 0.100;

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                int sunImageSize = 128;
                double x = canvas.getWidth() / 2 + sunImageSize * Math.cos(t);
                double y = canvas.getHeight() / 2 + sunImageSize * Math.sin(t);

                gc.drawImage(space, 0, 0);
                gc.drawImage(earth, x, y);
                gc.drawImage(sun, 196, 196);
                gc.drawImage(ufo.getFrame(t), 450, 25);
            }
        }.start();

        theStage.show();
    }
}