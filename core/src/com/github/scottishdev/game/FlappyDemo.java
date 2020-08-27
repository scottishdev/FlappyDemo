package com.github.scottishdev.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.scottishdev.game.States.GameStateManager;
import com.github.scottishdev.game.States.MenuState;

public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Bird";
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music gameSoundTrack;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		//Gamesoundtrack plays throughout whole game. On game startup
		gameSoundTrack = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		gameSoundTrack.setLooping(true);
		gameSoundTrack.setVolume(0.1f); //10% volume
		gameSoundTrack.play();

		Gdx.gl.glClearColor(1, 0, 0, 1); //wipes screen clean then redraws everything
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		gameSoundTrack.dispose();
	}
}
