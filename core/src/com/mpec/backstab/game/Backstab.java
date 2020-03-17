package com.mpec.backstab.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mpec.backstab.enemy_character.EnemyAnimation;
import com.mpec.backstab.enemy_character.EnemyAnimationLittleZombie;
import com.mpec.backstab.enemy_character.EnemyAnimationSwordZombie;
import com.mpec.backstab.main_character.CharacterAnimation;
import com.mpec.backstab.map.MapGenerator;

public class Backstab extends Game {

	SpriteBatch batch;

	public float stateTime;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new GameScreen(this));
		stateTime = 0;
	}

	@Override
	public void render () {

		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
}
