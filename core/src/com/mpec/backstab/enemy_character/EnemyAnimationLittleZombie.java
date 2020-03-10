package com.mpec.backstab.enemy_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;

public class EnemyAnimationLittleZombie implements Screen, AvailableActions {

    public TextureAtlas enemyAtlas;
    public SpriteBatch batch;
    public Animation<TextureRegion> animation;
    public Sprite enemyAction;

    public float stateTime;

    final Backstab game;

    int direction;


    public EnemyAnimationLittleZombie(Backstab game){
        this.game = game;
        enemyAtlas = new TextureAtlas(Gdx.files.internal("Enemy/zombie/zombie_two_enemies.txt"));
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
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            direction = LOOK_UP;
            actionToDraw(MOVE_UP);
            batch.draw(enemyAction, 100, 100);

        }else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            direction = LOOK_DOWN;
            actionToDraw(MOVE_DOWN);
            batch.draw(enemyAction, 100, 100);
        }else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction = LOOK_LEFT;
            actionToDraw(MOVE_LEFT);
            batch.draw(enemyAction, 100, 100);
        }else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction = LOOK_RIGHT;
            actionToDraw(MOVE_RIGHT);
            batch.draw(enemyAction, 100, 100);
        }else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            goAttack();
            batch.draw(enemyAction, 100, 100);
        }else{
            goIdle();
            batch.draw(enemyAction, 100, 100);
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
        enemyAtlas.dispose();

    }

    private void actionToDraw(int action) {
        switch(action){
            case MOVE_UP:
                animation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                animation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                animation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_LEFT:
                animation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_left_mgzomb), Animation.PlayMode.LOOP);
                break;

        }
        enemyAction = new Sprite(animation.getKeyFrame(stateTime, true));
        resize(100,200);
    }

    private void goIdle(){
        switch(direction){
            case LOOK_UP:
                animation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                animation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                animation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                animation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_left_mgzomb), Animation.PlayMode.LOOP);
                break;
        }
        enemyAction = new Sprite(animation.getKeyFrame(stateTime, true));
    }

    private void goAttack(){
        switch(direction){
            case LOOK_UP:
                animation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                animation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                animation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                animation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_left_mgzomb), Animation.PlayMode.LOOP);
                break;
        }
        enemyAction = new Sprite(animation.getKeyFrame(stateTime, true));

    }
}
