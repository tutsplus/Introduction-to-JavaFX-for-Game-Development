package org.tutsplus.example3;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.animation.AnimationTimer;

import static org.tutsplus.example3.Global.sunImageSize;

// Global of Earth rotating around the sun. (Hello, world!)
public class Example3 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("AnimationTimer Example");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = Global.createCanvas();
        root.getChildren().add(canvas);

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        final Image earth = new Image("\\org\\tutsplus\\example3\\earth.png");
        final Image sun = new Image("\\org\\tutsplus\\example3\\sun.png");
        final Image space = new Image("\\org\\tutsplus\\example3\\space.png");

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                double x = canvas.getWidth() / 2 + sunImageSize * Math.cos(t);
                double y = canvas.getHeight() / 2 + sunImageSize * Math.sin(t);

                // Clear the canvas
                gc.clearRect(0, 0, 512, 512);

                // background image clears canvas
                gc.drawImage(space, 0, 0);
                gc.drawImage(earth, x, y);
                gc.drawImage(sun, 196, 196);
            }
        }.start();

        theStage.show();
    }
}