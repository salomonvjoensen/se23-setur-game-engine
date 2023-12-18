package com.setur.se23.FlappyBird.Flappy_Bird_Objects;

import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.core.Resource;
import com.setur.se23.engine.render.common.Texture2D;

public class Background extends Entity {

    public Background() {
        super(new Texture2D(Resource.getSprite("background-day.png"), 800, 800), 
              0, 
              0,
              0, 
              1, 
              1);

        addTexture(new Texture2D(Resource.getSprite("background-night.png"), 800, 800));
    }
}
