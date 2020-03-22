package com.mpec.backstab.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.main_character.MainCharacter;
import com.mpec.backstab.map.MapGenerator;

public class Backstab extends Game {

	public OrthographicCamera camera;
	SpriteBatch batch;
	MapGenerator mapGenerator;
	MainCharacter mainCharacter;
	Rectangle mainCharacterRectangle;

	public float stateTime;

	@Override
	public void create () {


		batch = new SpriteBatch();
		mapGenerator = new MapGenerator();
		float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10f*aspectRatio, 10f);

		stateTime = 0;
		mainCharacter = new MainCharacter(this);
		mainCharacterRectangle = new Rectangle();
		mainCharacterRectangle.setX(mainCharacter.getAction().getX());
		mainCharacterRectangle.setY(mainCharacter.getAction().getY());

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}


}
