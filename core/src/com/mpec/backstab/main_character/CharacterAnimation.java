package com.mpec.backstab.main_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class CharacterAnimation implements Screen {

    public static final int MOVE_LEFT = 0x100001f;
    public static final int MOVE_DOWN = 0x1000002f;
    public static final int MOVE_RIGHT = 0x100003f;
    public static final int MOVE_UP = 0x100004f;
    

    public TextureAtlas atlas;

    public CharacterAnimation(){
        atlas = new TextureAtlas(Gdx.files.internal("Player/tilesetCaracter.txt"));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
