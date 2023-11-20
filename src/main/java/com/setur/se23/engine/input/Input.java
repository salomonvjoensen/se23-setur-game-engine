package com.setur.se23.engine.input;

import com.setur.se23.Globals;

import javafx.scene.input.KeyCode;

public class Input {

    public static void addInputs(Runnable jump) {
        Globals.mainStage.getScene().setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case SPACE, W, UP:
                    jump.run();
                    break;
            
                default:
                    break;
            }
        });
    }
}
