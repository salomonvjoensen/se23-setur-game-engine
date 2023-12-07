package com.setur.se23.FlappyBird;

import com.setur.se23.engine.core.Core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Score {
    int score;
    
    public Score() {
        score = 0;
    }
    
    /**
     * Add amount to score.
     * 
     * @param amount
     */
    public void updateScore(int amount) {
        score += amount;
    }
   
    /**
     * Reset score.
     */
    public void resetScore() {
        score = 0;
    }
   
    /**
     * Render score on GraphicsContext.
     * 
     * @param gc
     */
    public void render(GraphicsContext gc) {
        gc.setFont(new Font(22));
        gc.setFill(Color.RED);
        gc.fillText("Score: " + score,
                    Core.getStageWidth() * 0.025,
                    Core.getStageHeight() * 0.975);
    }
}
