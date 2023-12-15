package com.setur.se23;

import com.setur.se23.SceneManager.SceneManager;
import com.setur.se23.dependency.loop.JavaFxGameLoop;
import com.setur.se23.dependency.render.canvas.CanvasRenderer;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.loop.GameLoop;
import com.setur.se23.engine.render.Renderer;
import com.setur.se23.engine.render.common.ViewPort;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        Core.mainStage = stage;

        Core.mainStage.setWidth(816.0f);
        Core.mainStage.setHeight(839.0f);
        Core.mainStage.setTitle("Game Engine Boilerplate!");
        Core.mainStage.resizableProperty().set(false);

        SceneManager.manage();

        initializeRenderer(Core.mainStage);
        initializeGameLoop();
        
        SceneManager.load();
    }

    private void initializeRenderer(Stage stage) {
        var canvasRenderer = new CanvasRenderer(stage);

        Renderer.Instantiate(canvasRenderer)
                .initialize(new ViewPort(stage.getWidth(), stage.getHeight()));
    }

    private void initializeGameLoop() {
        GameLoop.initialize(new JavaFxGameLoop());
    }
}