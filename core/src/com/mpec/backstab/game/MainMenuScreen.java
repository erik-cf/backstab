package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenuScreen implements Screen {

    final Backstab game;
    TextButton startButton;
    TextButton.TextButtonStyle startButtonStyle;
    Skin skin;
    TextureAtlas buttonAtlas;
    BitmapFont font;
    Music introMusic;
    Stage stage;

    public MainMenuScreen(Backstab game){
        stage = new Stage(game.viewport, game.batch);
        game.moveCamera();
        introMusic= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Menu/menuIntro.wav"));
        Gdx.input.setInputProcessor(stage);
        this.game = game;
        buttonAtlas = new TextureAtlas(Gdx.files.internal("Buttons/start-button.txt"));
        skin = new Skin();
        skin.addRegions(buttonAtlas);
        font = new BitmapFont();
        startButtonStyle = new TextButton.TextButtonStyle();
        startButtonStyle.font = font;
        startButtonStyle.down = skin.getDrawable("touched");
        startButtonStyle.up = skin.getDrawable("untouched");
        startButton = new TextButton("", startButtonStyle);
        startButton.setBackground(skin.getDrawable("untouched"));
        startButton.setX(game.camera.position.x - startButton.getWidth() / 2);
        startButton.setY((game.camera.position.y - stage.getHeight() / 2) + 100f);
        startButton.setWidth(510f);
        startButton.setHeight(78f);
        introMusic.play();
        game.mapGenerator.createMap();
        stage.addActor(startButton);
        stage.addActor(game.timmy);
        listeners();
        game.timmy.setVidaActual(game.timmy.getHp());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();

        game.stateTime = game.stateTime + 1 + Gdx.graphics.getDeltaTime();

        game.batch.begin();
        game.mapGenerator.paintMap(game.batch);
        game.batch.end();

        stage.draw();
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
        game.batch.dispose();
        game.mapGenerator.dispose();
        game.timmy.getAction().getTexture().dispose();
        game.timmy.getWalkPlayer().dispose();
        game.timmy.getPlayerAtlas().dispose();
        game.timmy.getWalkPlayer().dispose();
        stage.dispose();
    }

    private void listeners(){
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    introMusic.stop();
                    game.setScreen(new Loader(game));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //game.setScreen(new GameScreen(game));
            }
        });
    }
}
