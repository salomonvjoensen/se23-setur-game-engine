package com.setur.se23.engine.input;

public class InputManager {
    private static InputManager instance;
    private final Input INPUT; 

    private InputManager(Input input) {
        INPUT = input;
    }

    public static InputManager Instantiate(Input input) {
        synchronized (InputManager.class) {
            if (instance == null) {
                instance = new InputManager(input);
            }
        }
        return instance;
    }

    public static void Destroy() {
        instance = null;
    }

    public static InputManager getInstance() {
        return instance;
    }

    public void initialize(InputEvents events) {
        INPUT.initialize(events);
    }

    public void addInput(InputType inputType, String key, String gameEvent) {
        INPUT.addInput(inputType, key, gameEvent);
    }

    public void setInputs() {
        INPUT.setInputs();
    }
}
