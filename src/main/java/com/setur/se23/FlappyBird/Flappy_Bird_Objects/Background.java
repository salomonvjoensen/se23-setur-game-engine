package com.setur.se23.FlappyBird.Flappy_Bird_Objects;

import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Background extends Entity {

    public Background() {
        super(new Material(
                    new Texture2D(Resource.getSprite("background-day.png"), 800, 800),
                    new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)), 
              0, 
              0,
              0, 
              1, 
              1);

        addMaterial(new Material(
                        new Texture2D(Resource.getSprite("background-night.png"), 800, 800),
                        new MaterialColour(1.0f, 1.0f, 1.0f, 1.0f)));
    }
}
