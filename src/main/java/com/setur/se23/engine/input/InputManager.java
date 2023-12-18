package com.setur.se23.engine.input;

public class InputManager {
    private static InputManager instance;
    private final Input INPUT; 

    // Private constructor to prevent instantiation from other classes
    private InputManager(Input input) {
        INPUT = input;
    }

    /**
     * Instantiates the InputManager with the given instance of Input.
     *
     * @param input The Input to use for managing inputs.
     * @return The singleton instance of Input system.
     */
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

    /**
     * Gets the singleton instance of the Input system.
     *
     * @return The singleton instance of the Input system.
     */
    public static InputManager getInstance() {
        return instance;
    }

    /**
     * Initialize input manager with an instance of InputEvents.
     * 
     * @param events InputEvents
     */
    public void initialize(InputEvents events) {
        INPUT.initialize(events);
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
        INPUT.addInput(inputType, key, gameEvent);
    }

    /**
     * Method sets all inputs that have been added with addInput()
     */
    public void setInputs() {
        INPUT.setInputs();
    }
}
