package com.github.scottishdev.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture birdTexture;
    private Sound flapSound;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0,0);
        birdTexture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
        bounds = new Rectangle(x, y, birdTexture.getWidth() / 3, birdTexture.getHeight()); //collision box of bird
        flapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0); //If Y position is > 0 then add GRAVITY
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y,0); // changes only the y axis position
        if(position.y < 0){
            position.y = 0; //makes bird not fall off screen
        }
        velocity.scl(1/dt); // check this one
        bounds.setPosition(position.x, position.y); //update collision box of bird each time it moves
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 250;
        flapSound.play(0.3f); //30% Volume
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        birdTexture.dispose();
        flapSound.dispose();
    }
}
