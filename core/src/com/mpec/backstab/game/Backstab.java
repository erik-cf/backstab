package com.mpec.backstab.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.api.ApiTools;
import com.mpec.backstab.main_character.MainCharacter;
import com.mpec.backstab.map.MapGenerator;

import org.json.JSONObject;

import java.util.Date;

public class Backstab extends Game {

	public double multiplier = 1;

	public int phaseTime;
	public int minDrop;
	public int maxDrop;
	public int baseMonsters;
	public static int collisionProb;


	public OrthographicCamera camera;
	SpriteBatch batch;
	MapGenerator mapGenerator;
	MainCharacter mainCharacter;
	Rectangle mainCharacterRectangle;

	JSONObject getter;

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
		try {
			getter = ApiTools.InfoRequest("gameparams").getJSONArray("gameParams").getJSONObject(0);
			phaseTime = getter.getInt("phase_time");
			minDrop = getter.getInt("min_drop_time");
			maxDrop = getter.getInt("max_drop_time");
			baseMonsters = getter.getInt("base_monsters_count");
			collisionProb = getter.getInt("collision_probability");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		mapGenerator.dispose();
		mainCharacter.getAction().getTexture().dispose();
		mainCharacter.getWalkPlayer().dispose();
		mainCharacter.getPlayerAtlas().dispose();
		mainCharacter.getWalkPlayer().dispose();
	}


}
