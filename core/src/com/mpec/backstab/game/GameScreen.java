package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mpec.backstab.map.MapGenerator;

public class GameScreen implements Screen {

    final Backstab game;
    MapGenerator mapGenerator;
    private float blockSpeed=5;
    Stage stage;
    TouchPadTest touchpad= new TouchPadTest();

    public GameScreen(Backstab game){
        this.game = game;
        touchpad=new TouchPadTest();
        mapGenerator = new MapGenerator();
        mapGenerator.createMap();
        //Create a Stage and add TouchPad
        stage = new Stage();
        stage.addActor(touchpad.getTouchpad());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        //Move blockSprite with TouchPad
        touchpad.getBlockSprite().setX(touchpad.getBlockSprite().getX() + touchpad.getTouchpad().getKnobPercentX()*blockSpeed);
        touchpad.getBlockSprite().setY(touchpad.getBlockSprite().getY() + touchpad.getTouchpad().getKnobPercentY()*blockSpeed);
        game.batch.begin();


        mapGenerator.paintMap(game.batch);
        touchpad.getTouchpad().draw(game.batch,1);
        touchpad.getBlockSprite().draw(game.batch);
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
