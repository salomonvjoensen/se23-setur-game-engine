package com.setur.se23.dependency.render.GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DynamicString {
    
    public final StringProperty property;

    public DynamicString(String startingValue) {
        property = new SimpleStringProperty(startingValue);
    }

    public void setString(String value) {
        property.set(value);
    }

    public String getString() {
        return property.get();
    }
}
