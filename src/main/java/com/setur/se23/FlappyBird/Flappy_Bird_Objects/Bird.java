package com.setur.se23.FlappyBird.Flappy_Bird_Objects;

import com.setur.se23.FlappyBird.FlappyBirdGUI;
import com.setur.se23.FlappyBird.Score;
import com.setur.se23.FlappyBird.SoundEffects;
import com.setur.se23.engine.Collision.CircleCollider;
import com.setur.se23.engine.Collision.Collidable;
import com.setur.se23.engine.Collision.Collider;
import com.setur.se23.engine.Physics.Physics;
import com.setur.se23.engine.Physics.PhysicsEntity;
import com.setur.se23.engine.audio.Music;
import com.setur.se23.engine.audio.SoundEffectsManager;
import com.setur.se23.engine.core.Core;
import com.setur.se23.engine.core.DynamicEntity;
import com.setur.se23.engine.core.Entity;
import com.setur.se23.engine.render.common.Material;
import com.setur.se23.engine.render.common.MaterialColour;
import com.setur.se23.engine.render.common.Texture2D;

public class Bird extends Entity implements DynamicEntity, Collidable, PhysicsEntity {

    private boolean alive = true;
    private boolean airborne = true;
    private boolean started = false;

    private Entity prevScoreCollider;

    public boolean jumpReady = true;

    public Collider collider;
    public Physics physics;


    public Bird(double xPos, double yPos) {
        super(new Material(
                    new Texture2D(Core.getSprite("flappy-bird.png"), 280, 200),
                    new MaterialColour(1.0f, 0.0f, 0.0f, 1.0f)), 
              xPos, 
              yPos, 
              0, 
              0.2, 
              0.2);

        setCollider(new CircleCollider(this, getHeight() / 2));
        setPhysics(new Physics(0, 100, 
                               0, 1.5,
                               0, 1000,
                               0, 0));
    }

    public void jump() {
        if (alive && jumpReady) {
            SoundEffectsManager.playLoaded(SoundEffects.FLAP.getFilePath());

            physics.setVelocityY(-300);
            physics.setVerticalAccel(300);
            
            jumpReady = false;

            started = true;
            Pipe.start();
        }
    }

    public void jumpIsReady() {
        jumpReady = true;
    }

    @Override
    public void updatePhysics(double deltaTime) {

        if (airborne && started) {
            physics.physicsUpdate(deltaTime);
        }

        if (i == 0) {
            // new Music("background-music/bensound-funnysong.mp3");
            // new Music(SoundEffects.HIT.getFilePath());
            
            // SoundEffectsManager.playLoaded(SoundEffects.HIT.getFilePath());
            i = 1;
        }
    }

    @Override
    public void update(double deltaTime) {

        if (airborne && started) {

            setAngle(physics.getVelocityY() / 20);

            setY(getY() + physics.getVelocityY() * deltaTime);

            if (getY() < 0) {
                setY(0);
            }
        }
    }

    @Override
    public void setPhysics(Physics physics) {
        this.physics = physics;
    }

    @Override
    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    @Override
    public Collider getCollider() {
        return collider;
    }

    @Override
    public void collisionEvent(Entity collisionEntity) {

        if (collisionEntity instanceof Pipe && alive) {
            SoundEffectsManager.playLoaded(SoundEffects.DIE.getFilePath());

            alive = false;
        }
        
        if (collisionEntity instanceof Ground && airborne) {
            SoundEffectsManager.playLoaded(SoundEffects.HIT.getFilePath());
            
            alive = false;
            airborne = false;

            Pipe.speed = 0;

            if (FlappyBirdGUI.gameover == false) {
                FlappyBirdGUI.gameOver();
                FlappyBirdGUI.setGUI();
            }
        }

        if (collisionEntity instanceof ScoringHitBox) {

            if (collisionEntity.equals(prevScoreCollider) == false) {
                Score.updateScore(1);

                prevScoreCollider = collisionEntity;
            }
        }
    }
}
