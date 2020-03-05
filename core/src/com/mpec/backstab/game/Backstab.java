package com.mpec.backstab.game;

import com.badlogic.gdx.Game;
import com.mpec.backstab.main_character.CharacterAnimation;

public class Backstab extends Game {
	
	@Override
	public void create () {
        this.setScreen(new CharacterAnimation(this));
	}

	@Override
	public void render () {
	super.render();
	}
	
	@Override
	public void dispose () {
	}
}