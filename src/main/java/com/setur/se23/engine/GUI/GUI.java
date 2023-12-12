package com.setur.se23.engine.GUI;

import javafx.beans.property.StringProperty;

public interface GUI {
    public void AddText(double x, double y, StringProperty text, int size, String backgroundHexCode, int padding);
    public void AddButton(double x, double y, double width, double height, String text, int fontSize, Runnable action);
    public void loadGUI();
}
