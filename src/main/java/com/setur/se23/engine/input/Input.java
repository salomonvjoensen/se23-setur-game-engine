package com.setur.se23.engine.input;

import com.setur.se23.Globals;
import com.setur.se23.game.GameEvents;

import javafx.scene.input.KeyCode;

public class Input {

    private GameEvents events;

    public Input(GameEvents events) {
        this.events = events;
    }

    public void addInputs() {
        Globals.mainStage.getScene().setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case SPACE, W, UP:
                    events.event("Jump");
                    break;
                case P:
                    events.event("FPS_Counter");
                    break;
            
                default:
                    break;
            }
        });

        Globals.mainStage.getScene().setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case SPACE, W, UP:
                    events.event("Jump_Ready");
                    break;
            
                default:
                    break;
            }
        });
    }
}
