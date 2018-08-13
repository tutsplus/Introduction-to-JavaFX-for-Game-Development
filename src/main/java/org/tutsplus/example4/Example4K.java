package org.tutsplus.example4;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.HashSet;

public class Example4K extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    private static Scene mainScene;
    private static GraphicsContext graphicsContext;
    private static final int WIDTH = 512;
    private static final int HEIGHT = 256;

    private static Image left;
    private static Image leftGreen;

    private static Image right;
    private static Image rightGreen;

    private static HashSet<String> currentlyActiveKeys;

    @Override
    public void start(Stage mainStage)
    {
        mainStage.setTitle("Event Handling");

        Group root = new Group();
        mainScene = new Scene(root);
        mainStage.setScene(mainScene);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        prepareActionHandlers();

        graphicsContext = canvas.getGraphicsContext2D();

        loadGraphics();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                tickAndRender();
            }
        }.start();

        mainStage.show();
    }

    private static void prepareActionHandlers()
    {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new HashSet<>();
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.add(event.getCode().toString());
            }
        });
        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                currentlyActiveKeys.remove(event.getCode().toString());
            }
        });
    }

    private static void loadGraphics()
    {
        left = new Image(getResource("left.png"));
        leftGreen = new Image(getResource("leftG.png"));

        right = new Image(getResource("right.png"));
        rightGreen = new Image(getResource("rightG.png"));
    }

    private static String getResource(String filename)
    {
        return Example4K.class.getResource(filename).toString();
    }

    private static void tickAndRender()
    {
        // clear canvas
        graphicsContext.clearRect(0, 0, WIDTH, HEIGHT);

        if (currentlyActiveKeys.contains("LEFT"))
        {
            graphicsContext.drawImage(leftGreen, 64 ,64);
        }
        else
        {
            graphicsContext.drawImage(left, 64 ,64);
        }

        if (currentlyActiveKeys.contains("RIGHT"))
        {
            graphicsContext.drawImage(rightGreen, 320, 64);
        }
        else
        {
            graphicsContext.drawImage(right, 320, 64);
        }
    }
}
