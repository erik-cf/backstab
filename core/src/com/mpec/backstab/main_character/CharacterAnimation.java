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
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.GameScreen;

public class CharacterAnimation implements AvailableActions {

    public TextureAtlas playerAtlas;
    public SpriteBatch batch;
    public Animation<TextureRegion> animation;
    public Sprite playerAction;

    public float stateTime;

    int direction;


    public CharacterAnimation(){
        playerAtlas = new TextureAtlas(Gdx.files.internal("Player/tilesetCaracter.txt"));
        batch = new SpriteBatch();
        stateTime = 1;
        direction = LOOK_DOWN;
    }

    protected Sprite goMove(int action, float stateTime) {
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
        return new Sprite(animation.getKeyFrame(GameScreen.staticDelta, true));
    }

    protected Sprite goIdle(float stateTime){
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
        return new Sprite(animation.getKeyFrame(GameScreen.staticDelta, true));
    }

    protected Sprite goAttack(float stateTime){
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
        return new Sprite(animation.getKeyFrame(GameScreen.staticDelta, true));
    }
}
