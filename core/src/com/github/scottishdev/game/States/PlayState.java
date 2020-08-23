package com.github.scottishdev.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.github.scottishdev.game.FlappyDemo;
import com.github.scottishdev.game.sprites.Bird;
import com.github.scottishdev.game.sprites.Tube;

public class PlayState extends State{
    private static final int TUBE_SPACING_X = 125; //Space between tubes on X axis
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");

        tubes = new Array<Tube>();

        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING_X + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for (Tube tube: tubes) {
            if (cam.position.x - (cam.viewportWidth/2) > tube.getPositionTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPositionTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING_X) * TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));
            }

        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2),0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

        for (Tube tube: tubes) {
            sb.draw(tube.getTopTube(), tube.getPositionTopTube().x, tube.getPositionTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPositionBottomTube().x, tube.getPositionBottomTube().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
