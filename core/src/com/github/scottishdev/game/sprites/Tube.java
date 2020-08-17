package com.github.scottishdev.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    private static final int FLUCTUATION = 130;//tube moves randomly between 0 - 130 on Y axis
    private static final int TUBE_GAP = 100;//Gaps between tubes is 100
    private static final int LOWEST_OPENING = 120; //Top tube cannot be below bottom of screen
    private Texture topTube, bottomTube;
    private Vector2 positionTopTube, positionBottomTube;
    private Random rand;

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        positionTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        positionBottomTube = new Vector2(x, positionTopTube.y - TUBE_GAP - bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPositionTopTube() {
        return positionTopTube;
    }

    public Vector2 getPositionBottomTube() {
        return positionBottomTube;
    }
}
