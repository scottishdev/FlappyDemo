package com.github.scottishdev.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.github.scottishdev.game.FlappyDemo;
import com.github.scottishdev.game.sprites.Bird;
import com.github.scottishdev.game.sprites.Tube;

import java.util.Vector;

public class PlayState extends State{
    private static final int TUBE_SPACING_X = 125; //Space between tubes on X axis
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPosition1, groundPosition2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) { //Constructor
        super(gsm);
        bird = new Bird(50,300);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(cam.position.x - cam.viewportWidth / 2, 0);
        groundPosition2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), 0); //x co-ords needs to be added onto original "ground"

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

        for (int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);

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

        //Draws both sets of ground.png
        sb.draw(ground, groundPosition1.x, groundPosition1.y);
        sb.draw(ground, groundPosition2.x, groundPosition2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for (Tube tube: tubes) {
            tube.dispose();
            System.out.println("Playstate disposed"); //DELETE
        }
    }
}
