package com.mpec.backstab.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mpec.backstab.api.ApiTools;
import com.mpec.backstab.main_character.Timmy;
import com.mpec.backstab.map.MapGenerator;

import org.json.JSONObject;

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
	public Timmy timmy;

	JSONObject getter;

	FitViewport viewport;
	public float stateTime;

	@Override
	public void create () {


		batch = new SpriteBatch();
		mapGenerator = new MapGenerator();
		float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		viewport = new FitViewport(700f * aspectRatio, 700f, camera);
		stateTime = 0;
		timmy = new Timmy(this);
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
		moveCamera();
		camera.update();
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
		timmy.getAction().getTexture().dispose();
		timmy.getWalkPlayer().dispose();
		timmy.getPlayerAtlas().dispose();
	}

	public void moveCamera(){
		if((timmy.getX() + (camera.viewportWidth / 2)) < MapGenerator.WORLD_WIDTH && (timmy.getX() - (camera.viewportWidth / 2)) > 0){
			camera.position.x = timmy.getX();
		}

		if((timmy.getX() + (camera.viewportWidth / 2)) >= MapGenerator.WORLD_WIDTH){
			camera.position.x = MapGenerator.WORLD_WIDTH - camera.viewportWidth / 2;
		}

		if((timmy.getX() - (camera.viewportWidth / 2)) <= 0){
			camera.position.x =  camera.viewportWidth / 2;
		}

		if((timmy.getY() + (camera.viewportHeight / 2) < MapGenerator.WORLD_HEIGHT) && (timmy.getY() - (camera.viewportHeight / 2) > 0)){
			camera.position.y = timmy.getY();
		}

		if((timmy.getY() + (camera.viewportHeight / 2) >= MapGenerator.WORLD_HEIGHT)){
			camera.position.y = MapGenerator.WORLD_HEIGHT - camera.viewportHeight / 2;
		}

		if((timmy.getY() - (camera.viewportHeight / 2) <= 0)){
			camera.position.y = camera.viewportHeight / 2;
		}
	}

	public void setInitialCameraView(){

	}

}
