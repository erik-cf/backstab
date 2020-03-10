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
import com.mpec.backstab.game.Backstab;
import com.mpec.backstab.game.AvailableActions;

public class EnemyAnimation implements Screen, AvailableActions {

    public TextureAtlas enemyAtlas;
    public SpriteBatch batch;
    public Animation<TextureRegion> animation;
    public Sprite enemyAction;

    public float stateTime;

    final Backstab game;

    int direction;


    public EnemyAnimation(Backstab game){
        this.game = game;
        enemyAtlas = new TextureAtlas(Gdx.files.internal("Enemy/zombie1.txt"));
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
            batch.draw(enemyAction, 100, 100,57,85);

        }else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            direction = LOOK_DOWN;
            actionToDraw(MOVE_DOWN);
            batch.draw(enemyAction, 100, 100,57,85);
        }else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction = LOOK_LEFT;
            actionToDraw(MOVE_LEFT);
            batch.draw(enemyAction, 100, 100,57,85);
        }else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction = LOOK_RIGHT;
            actionToDraw(MOVE_RIGHT);
            batch.draw(enemyAction, 100, 100,57,85);
        }else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            goAttack();
            batch.draw(enemyAction, 100, 100,57,85);
        }else{
            goIdle();
            batch.draw(enemyAction, 100, 100,57,85);
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
                animation = new Animation<TextureRegion>(2f, enemyAtlas.findRegions(name_Run), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                animation = new Animation<TextureRegion>(3f, enemyAtlas.findRegions(name_Walk), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                animation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_Dead), Animation.PlayMode.LOOP);
                break;

        }
        enemyAction = new Sprite(animation.getKeyFrame(stateTime, true));
        resize(100,200);
    }

    private void goIdle(){
        animation = new Animation<TextureRegion>(10f,enemyAtlas.findRegions(name_Idle), Animation.PlayMode.LOOP);
        enemyAction = new Sprite(animation.getKeyFrame(stateTime, true));
        resize(100,200);

    }

    private void goAttack(){
        animation = new Animation<TextureRegion>(6f,enemyAtlas.findRegions(name_Attack), Animation.PlayMode.LOOP);
        enemyAction = new Sprite(animation.getKeyFrame(stateTime, true));

    }
}
