package com.setur.se23.dependency.input;

import java.io.InvalidObjectException;
import java.util.ArrayList;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.input.Input;
import com.setur.se23.engine.input.InputEvents;
import com.setur.se23.engine.input.InputType;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FX_Input implements Input {

    private InputEvents events;

    private ArrayList<Object[]> inputMap = new ArrayList<Object[]>();

    public void initialize(InputEvents events) {
        this.events = events;
    }

    /**
     * Assigns a key and its type of input to a game event.
     * Refer to JavaFX KeyCode javadoc for correct key syntax:
     * https://docs.oracle.com/javafx/2/api/javafx/scene/input/KeyCode.html
     * 
     * @param inputType the type of input
     * @param key       the key in JavaFX KeyCode syntax
     * @param gameEvent the game event
     */
    public void addInput(InputType inputType, String key, String gameEvent) {
        KeyCode code = stringToKeyCode(key);

        Object[] input = {inputType, code, gameEvent};

        inputMap.add(input);
    }

    private KeyCode stringToKeyCode(String key) {
        for (KeyCode code : KeyCode.values()) {
            if (key.toUpperCase().equals(code.toString())) {
                return code;
            }
        }
        try {
            throw new InvalidObjectException("Given key not found in JavaFX "
                                             + "KeyCode enum");
        } catch (InvalidObjectException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void keyPressed(KeyEvent event) {
        KeyCode code = event.getCode();

        String gameEvent = "";
            
        for (Object[] input : inputMap) {
            if (input[0].equals(InputType.onPress) && input[1].equals(code)) {
                gameEvent = ((String) input[2]);
                break;
            }
        }

        events.event(gameEvent);
    }

    private void keyReleased(KeyEvent event) {
        KeyCode code = event.getCode();

        String gameEvent = "";
            
        for (Object[] input : inputMap) {
            if (input[0].equals(InputType.onRelease) && input[1].equals(code)) {
                gameEvent = ((String) input[2]);
                break;
            }
        }

        events.event(gameEvent);
    }

    public void setInputs() {
        Core.getStage().getScene().setOnKeyPressed(event -> keyPressed(event));
        Core.getStage().getScene().setOnKeyReleased(event -> keyReleased(event));
    }
}
