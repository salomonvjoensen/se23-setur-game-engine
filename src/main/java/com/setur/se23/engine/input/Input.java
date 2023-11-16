package com.setur.se23.engine.input;

import com.setur.se23.Globals;

import javafx.scene.input.KeyCode;

public class Input {

    public Input() {
        Globals.mainStage.getScene().setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.A) {
                //_xDir = -1;
                //_yDir = 0;
            } else if (code == KeyCode.D) {
                //_xDir = 1;
                //_yDir = 0;
            }

            if (code == KeyCode.W) {
                //_yDir = -1;
                //_xDir = 0;
            } else if (code == KeyCode.S) {
                //_yDir = 1;
                //_xDir = 0;
            }
        });
    }
}
