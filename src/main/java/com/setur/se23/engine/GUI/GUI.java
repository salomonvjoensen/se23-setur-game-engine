package com.setur.se23.engine.GUI;

import com.setur.se23.dependency.render.canvas.CanvasRenderer;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GUI {

    /**
     * Adds text to gui with given parameters.
     * 
     * @param <T> needs StringProperty or String to function
     * @param x x position
     * @param y y position
     * @param text text to be added
     * @param textSize text px size
     * @param padding text padding
     * @param textHexCode text color in hex code
     * @param backgroundHexCode background color in hex code
     */
    public static <T> void AddText(double x, double y, T text, int textSize, 
                                   int padding, String textHexCode, 
                                   String backgroundHexCode) {

        Text textNode = new Text();

        if (text instanceof StringProperty) {
            textNode.textProperty().bind((StringProperty) text);
        }

        if (text instanceof String) {
            textNode.textProperty().set((String) text);
        }

        int red = Integer.valueOf(textHexCode.substring(1, 3), 16);
        int green = Integer.valueOf(textHexCode.substring(3, 5), 16);
        int blue = Integer.valueOf(textHexCode.substring(5, 7), 16);

        textNode.setFill(Color.rgb(red, green, blue));
        textNode.setFont(new Font("Arial", textSize));

        
        StackPane stackPane = new StackPane(textNode);

        String options = "";

        if (backgroundHexCode != null) {
            options += "-fx-background-color: " + backgroundHexCode + ";";
        }

        options += "-fx-padding: " + padding + ";";
        
        stackPane.setStyle(options);
        

        CanvasRenderer.addToGUI(new GUI_Item(stackPane, x, y));
    }

    /**
     * Adds button to gui with given parameters.
     * 
     * @param x x position
     * @param y y position
     * @param text text to be added
     * @param width button width
     * @param height button height
     * @param textSize text px size
     * @param action button action
     */
    public static void AddButton(double x, double y, String text, 
                                 double width, double height, 
                                 int textSize, Runnable action) {

        Button button = new Button(text);

        button.setOnAction(evt -> action.run());
        button.setMinSize(width, height);
        button.setStyle("-fx-font-size:" + textSize + ";");

        CanvasRenderer.addToGUI(new GUI_Item(button, x, y));
    }

    /**
     * Removes old GUI and adds new GUI.
     */
    public static void loadGUI() {
        CanvasRenderer.loadGUI();
    }
}
