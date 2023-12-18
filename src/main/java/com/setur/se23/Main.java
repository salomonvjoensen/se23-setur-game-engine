package com.setur.se23;

import com.setur.se23.SceneManager.SceneManager;
import com.setur.se23.dependency.FX_Globals;
import com.setur.se23.dependency.backgroundMusic.AudioPlayer;
import com.setur.se23.dependency.input.FX_Input;
import com.setur.se23.dependency.loop.JavaFxGameLoop;
import com.setur.se23.dependency.render.canvas.CanvasRenderer;

import com.setur.se23.engine.audio.BackgroundMusicManager;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.input.InputManager;
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

        FX_Globals.stage = stage;

        FX_Globals.stage.setWidth(816.0f);
        FX_Globals.stage.setHeight(839.0f);
        FX_Globals.stage.setTitle("Game Engine Boilerplate!");
        FX_Globals.stage.resizableProperty().set(false);

        Core.WindowWidth = FX_Globals.getStage().getWidth();
        Core.WindowHeight = FX_Globals.getStage().getHeight();

        SceneManager.manage();

        InputManager.Instantiate(new FX_Input());

        initializeRenderer(FX_Globals.stage);
        initializeGameLoop();
        BackgroundMusicManager.initialize(new AudioPlayer());
        
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