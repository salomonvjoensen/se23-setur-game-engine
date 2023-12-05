package com.setur.se23.engine.input;

import javafx.scene.input.KeyCode;

public interface Input {
    public void addInput(InputType inputType, KeyCode code, String gameEvent);
    public void setInputs();
}
