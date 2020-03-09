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
import com.mpec.backstab.game.AvailableActions;

public class CharacterAnimation implements Screen, AvailableActions {

    public TextureAtlas playerAtlas;
    public SpriteBatch batch;
    public Animation<TextureRegion> animation;
    public Sprite playerAction;

    public float stateTime;

    final Backstab game;

    int direction;


    public CharacterAnimation(Backstab game){
        this.game = game;
        playerAtlas = new TextureAtlas(Gdx.files.internal("Player/tilesetCaracter.txt"));
        batch = new SpriteBatch();
        stateTime = 1;
        direction = LOOK_DOWN;

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
        if(delta < 1){
            goIdle();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction = LOOK_UP;
            actionToDraw(MOVE_UP);
            batch.draw(playerAction, 50, 50);
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction = LOOK_DOWN;
            actionToDraw(MOVE_DOWN);
            batch.draw(playerAction, 50, 50);
        }else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = LOOK_LEFT;
            actionToDraw(MOVE_LEFT);
            batch.draw(playerAction, 50, 50);
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = LOOK_RIGHT;
            actionToDraw(MOVE_RIGHT);
            batch.draw(playerAction, 50, 50);
        }else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            goAttack();
            batch.draw(playerAction, 50, 50);
        }else{
            goIdle();
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

    private void actionToDraw(int action) {
        switch(action){
            case MOVE_UP:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_move_up), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_move_down), Animation.PlayMode.LOOP);
                break;
            case MOVE_LEFT:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_move_left), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_move_right), Animation.PlayMode.LOOP);
                break;
        }
        playerAction = new Sprite(animation.getKeyFrame(stateTime, true));
    }

    private void goIdle(){
        switch(direction){
            case LOOK_UP:
                animation = new Animation<TextureRegion>(10f, playerAtlas.findRegions(name_idle_up), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                animation = new Animation<TextureRegion>(10f, playerAtlas.findRegions(name_idle_right), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                animation = new Animation<TextureRegion>(10f, playerAtlas.findRegions(name_idle_down), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                animation = new Animation<TextureRegion>(10f, playerAtlas.findRegions(name_idle_left), Animation.PlayMode.LOOP);
                break;
        }
        playerAction = new Sprite(animation.getKeyFrame(stateTime, true));

    }

    private void goAttack(){
        switch(direction){
            case LOOK_UP:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_bow_up), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_bow_right), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_bow_down), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_bow_left), Animation.PlayMode.LOOP);
                break;
        }
        playerAction = new Sprite(animation.getKeyFrame(stateTime, true));
    }
}
