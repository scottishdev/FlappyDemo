package com.github.scottishdev.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;

    private Texture bird;

    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0,0);
        bird = new Texture("bird.png");
    }

    public void update(float dt){
        velocity.add(0, GRAVITY, 0); //Gravity only affects Y axis
        velocity.scl(dt);
        position.add(0, velocity.y,0); // changes only the y axis position

        velocity.scl(1/dt); // check this one
    }

    
}
