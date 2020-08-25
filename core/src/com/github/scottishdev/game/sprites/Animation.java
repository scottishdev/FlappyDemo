package com.github.scottishdev.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {

    private Array<TextureRegion> frames;
    private float maxFrameTime; //How long a frame stays in view before switching to next
    private float currentFrameTime; //How long animation has been in current frame
    private int frameCount;
    private int frame;

    public Animation(TextureRegion region, int frameCount, float cycleTime){//region is all frames combined into 1 image.
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount; // Divides the whole image by the framecount(3), which gets the width of the individual animation

        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }//Loops through each of the 3 frames in the image. Starts on the bottom left of each image.
         // framewidth is 16 px. Length of bird
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0; // Start at first frame
    }

    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime= 0;
        } if(frame >= frameCount){ //If over edge of frames, then go back to start.
            frame = 0;
        }
    }

    public TextureRegion getFrame(){ // Get frame animation is currently on
        return frames.get(frame);
    }
}
