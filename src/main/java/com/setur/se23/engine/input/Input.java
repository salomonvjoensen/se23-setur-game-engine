package com.setur.se23.engine.input;


public interface Input {
    public void initialize(InputEvents events);
    public void addInput(InputType inputType, String key, String gameEvent);
    public void setInputs();
}
