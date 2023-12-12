package com.setur.se23.engine.GUI;

import com.setur.se23.dependency.render.canvas.CanvasRenderer;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FX_GUI implements GUI {

    @Override
    public void AddText(double x, double y, StringProperty textProperty, int size, String backgroundHexCode, int padding) {
        
        Text text = new Text();

        text.textProperty().bind(textProperty);
        
        text.fillProperty().set(Color.WHITE);
        text.setFont(new Font("Arial", size));
        
        StackPane stackPane = new StackPane(text);

        String options = "";

        if (backgroundHexCode != null) {
            options += "-fx-background-color: " + backgroundHexCode + ";";
        }

        options += "-fx-padding: " + padding + ";";
        
        stackPane.setStyle(options);
        

        CanvasRenderer.addToGUI(new FX_GUI_Item(stackPane, x, y));
    }

    @Override
    public void AddButton(double x, double y, double width, double height, String text, int textSize, Runnable action) {

        Button button = new Button(text);

        button.setOnAction(evt -> action.run());
        button.setMinSize(width, height);
        button.setStyle("-fx-font-size:" + textSize + ";");

        CanvasRenderer.addToGUI(new FX_GUI_Item(button, x, y));
    }

    public void loadGUI() {
        CanvasRenderer.loadGUI();
    }
}
