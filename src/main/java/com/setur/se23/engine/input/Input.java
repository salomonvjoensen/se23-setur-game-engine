package com.setur.se23.engine.input;

public interface Input {
    public abstract void addInput(InputType inputType, String key, String gameEvent);
    public abstract void setInputs();
}
