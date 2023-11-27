package com.setur.se23.game.Flappy_Bird_Objects;

import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Pipe extends Entity {

    private double speed = 200;
    private boolean reverse;

    public Pipe(double xPos, double yPos, int width, int height, boolean reverse) {
        super(reverseMaterial(reverse, width, height), 
                xPos,
                yPos,
                width,
                height);
        this.reverse = reverse;
    }

    private static Material reverseMaterial(boolean reverse, int width, int height) {
        if (reverse) {
            return new Material(
                new Texture2D(Core.getResorcePath("sprites/reverse-pipe-green.png"), width, height),
                new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f));
        } else {
            return new Material(
                new Texture2D(Core.getResorcePath("sprites/pipe-green.png"), width, height),
                new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f));
        }
    }

    @Override
    public void update(double deltaTime) {
        setX(getX() - 1 * speed * deltaTime);

        if (getX() < -100) {
            if (reverse) {
            
                setX(getX() + 1200);

                setY(-500 + Core.randomInt(1, 9) * 50);
            } else {
                setX(getX() + 1200);

                setY(Core.previousRandomInt * 50 + 250);
            }
        }
    }
}
