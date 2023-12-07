package com.setur.se23.engine.GUI;

import com.setur.se23.engine.core.Core;

import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class GUI {

    private final Stage STAGE;

    private static Canvas canvas;

    public GUI(Stage stage) {
        this.STAGE = stage;
        canvas = new Canvas(Core.getStageWidth(), Core.getStageHeight());
        STAGE.getScene().getRoot();
    }

    
    public static void renderGUI() {

        var context = canvas.getGraphicsContext2D();

        context.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());

    }
}
