package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mpec.backstab.map.MapGenerator;

public class GameScreen implements Screen {

    final Backstab game;
    MapGenerator mapGenerator;

    public GameScreen(Backstab game){
        this.game = game;
        mapGenerator = new MapGenerator();
        mapGenerator.createMap();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        mapGenerator.paintMap(game.batch);
        AnalogStick asMove = new AnalogStick(400, 15);
        asMove.draw(game.batch,1);

        game.batch.end();
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
