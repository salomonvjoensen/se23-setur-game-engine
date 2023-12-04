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
                case SPACE, UP:
                    events.event("Jump");
                    break;
                case P:
                    events.event("toggle_FPS_Counter");
                    break;
                case O:
                    events.event("toggle_Gizmos");
                    break;
                case R:
                    events.event("Restart");
                    break;
                    
                case W:
                    events.event("W");
                    break;
                case A:
                    events.event("A");
                    break;
                case S:
                    events.event("S");
                    break;
                case D:
                    events.event("D");
                    break;
            
                default:
                    break;
            }
        });

        Globals.mainStage.getScene().setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case SPACE, UP:
                    events.event("Jump_Ready");
                    break;
                case W:
                    events.event("W_r");
                    break;
                case A:
                    events.event("A_r");
                    break;
                case S:
                    events.event("S_r");
                    break;
                case D:
                    events.event("D_r");
                    break;
            
                default:
                    break;
            }
        });
    }
}
