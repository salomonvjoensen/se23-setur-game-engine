package com.setur.se23;

import com.setur.se23.dependency.render.canvas.CanvasRenderer;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.ViewPort;
import com.setur.se23.snake.SnakeGame;

import javafx.application.Application;
import javafx.stage.Stage;

public class Snake extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        Core.mainStage = stage;

        Core.mainStage.setWidth(800.0f);
        Core.mainStage.setHeight(800.0f);
        Core.mainStage.setTitle("Snake!");

        initializeRenderer(Core.mainStage);
        SnakeGame snakeGame = new SnakeGame();
        snakeGame.gameLoop.start();


    }


    private void initializeRenderer(Stage stage) {
        var canvasRenderer = new CanvasRenderer(stage);

        Renderer.Instantiate(canvasRenderer)
                .initialize(new ViewPort(stage.getWidth(), stage.getHeight()));
    }
}