package com.setur.se23.snake.Snake_Objects;

import com.setur.se23.engine.render.common.Texture2D;
import com.setur.se23.engine.core.Resource;

public enum SnakePart {
    HEAD(new Texture2D(Resource.getSprite("snake-head.png"), 160, 160)),
    BODY(new Texture2D(Resource.getSprite("snake-body.png"), 160, 160)),
    TAIL(new Texture2D(Resource.getSprite("snake-tail.png"), 160, 160)),
    BODY_CLOCKWISE(new Texture2D(Resource.getSprite("Snake-body-up-right.png"), 160, 160)),
    BODY_COUNTER_CLOCKWISE(new Texture2D(Resource.getSprite("Snake-body-down-right.png"), 160, 160));

    private final Texture2D texture;

    private SnakePart(Texture2D texture) {
        this.texture = texture;
    }

    public Texture2D getTexture() {
        return texture;
    }
}
