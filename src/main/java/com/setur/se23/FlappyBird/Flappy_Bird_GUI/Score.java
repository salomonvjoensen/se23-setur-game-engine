package com.setur.se23.FlappyBird.Flappy_Bird_GUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Score {
    
    public static final StringProperty scoreProperty = new SimpleStringProperty("0");

    private static int score;
    
    public static void updateScore(int amount) {
        score += amount;
        scoreProperty.set(Integer.toString(score));
    }
   
    public static void resetScore() {
        score = 0;
        scoreProperty.set(Integer.toString(score));
    }
}
