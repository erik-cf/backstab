package com.mpec.backstab.main_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mpec.backstab.game.Backstab;

public class CharacterAnimation implements Screen {

    public static final int MOVE_LEFT = 0x100001f;
    public static final int MOVE_DOWN = 0x1000002f;
    public static final int MOVE_RIGHT = 0x100003f;
    public static final int MOVE_UP = 0x100004f;


    public TextureAtlas playerAtlas;
    public SpriteBatch batch;
    public Animation<TextureRegion> animation;
    public Sprite playerAction;

    public float stateTime;

    final Backstab game;


    public CharacterAnimation(Backstab game){
        this.game = game;
        playerAtlas = new TextureAtlas(Gdx.files.internal("Player/tilesetCaracter.txt"));
        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += 1 + Gdx.graphics.getDeltaTime();

        batch.begin();
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            actionToDraw(MOVE_UP, delta);
            batch.draw(playerAction, 50, 50);
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            actionToDraw(MOVE_DOWN, delta);
            batch.draw(playerAction, 50, 50);
        }else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            actionToDraw(MOVE_LEFT, delta);
            batch.draw(playerAction, 50, 50);
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            actionToDraw(MOVE_RIGHT, delta);
            batch.draw(playerAction, 50, 50);
        }

        batch.end();
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
        playerAtlas.dispose();

    }

    private void actionToDraw(int action, float delta) {

        switch(action){
            case MOVE_UP:
                animation = new Animation<TextureRegion>(6f, playerAtlas.findRegions("move_up"), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                animation = new Animation<TextureRegion>(6f, playerAtlas.findRegions("move_down"), Animation.PlayMode.LOOP);
                break;
            case MOVE_LEFT:
                animation = new Animation<TextureRegion>(6f, playerAtlas.findRegions("move_left"), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                animation = new Animation<TextureRegion>(6f, playerAtlas.findRegions("move_right"), Animation.PlayMode.LOOP);
                break;
        }

        playerAction = new Sprite(animation.getKeyFrame(delta, true));
        animation = new Animation<TextureRegion>(6f, playerAtlas.findRegions("move_left"), Animation.PlayMode.LOOP);
    }
}
