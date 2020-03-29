package com.mpec.backstab.game;

import com.badlogic.gdx.Screen;
import com.mpec.backstab.api.ApiTools;

import org.json.JSONObject;

public class Loader implements Screen {


    final Backstab game;
    public Loader(Backstab game) throws Exception {
        this.game = game;
        ApiTools.InfoRequest("DropsData");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.setScreen(new GameScreen(game));
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
