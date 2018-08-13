package org.tutsplus.example3;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import static org.tutsplus.example3.Global.sunImageSize;

// An alternative implementation of Example 3,
//    using the Timeline, KeyFrame, and Duration classes.

// Global of Earth rotating around the sun. (Hello, world!)
public class Example3T extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Timeline Example");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = Global.createCanvas();
        root.getChildren().add(canvas);

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        final Image earth = new Image("earth.png");
        final Image sun = new Image("sun.png");
        final Image space = new Image("space.png");

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                ae -> {
                    double t = (System.currentTimeMillis() - timeStart) / 1000.0;

                    double x = canvas.getWidth() / 2 + sunImageSize * Math.cos(t);
                    double y = canvas.getHeight() / 2 + sunImageSize * Math.sin(t);

                    // Clear the canvas
                    gc.clearRect(0, 0, 512, 512);

                    // background image clears canvas
                    gc.drawImage(space, 0, 0);
                    gc.drawImage(earth, x, y);
                    gc.drawImage(sun, 196, 196);
                });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();

        theStage.show();
    }
}