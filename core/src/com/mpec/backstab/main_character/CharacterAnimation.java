package com.mpec.backstab.main_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;

public class CharacterAnimation implements AvailableActions {

    public Sound slashPlayer;
    public TextureAtlas playerAtlas;
    public Animation<TextureRegion> animation;
    public Sprite action;
    public Sound walkPlayer;
    int direction;
    boolean playSoundWalk=true;
    private int contadorWalk=0;
    private int velocityWalk=10;

    final Backstab game;

    public CharacterAnimation(Backstab game){
        this.game = game;
        playerAtlas = new TextureAtlas(Gdx.files.internal("Player/tilesetCaracter.txt"));
        slashPlayer= Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/swordSlashPlayer.wav"));
        walkPlayer=Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/footstepGrass.wav"));

        direction = LOOK_DOWN;


        action = new Sprite();
        action.setPosition(Gdx.graphics.getWidth()/2-action.getWidth()/2, Gdx.graphics.getHeight()/2-action.getHeight()/2);
        goIdle();
    }

    public void goMove(int action) {

        if(playSoundWalk==true ) {
            walkPlayer.play(1);
            playSoundWalk=false;

        }
        else if (contadorWalk%velocityWalk==0){
            playSoundWalk=true;
        }

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
        contadorWalk++;
        float x = this.action.getX();
        float y = this.action.getY();
        this.action = new Sprite(animation.getKeyFrame(game.stateTime, true));
        this.action.setPosition(x, y);
    }

    public void goIdle(){
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
        float x = action.getX();
        float y = action.getY();
        action = new Sprite(animation.getKeyFrame(game.stateTime, true));
        action.setPosition(x, y);
    }

    public void goAttack(int direction){

        slashPlayer.play(1);
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
        float x = action.getX();
        float y = action.getY();
        action = new Sprite(animation.getKeyFrame(game.stateTime, true));
        action.setPosition(x, y);
    }

    public TextureAtlas getPlayerAtlas() {
        return playerAtlas;
    }

    public void setPlayerAtlas(TextureAtlas playerAtlas) {
        this.playerAtlas = playerAtlas;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public Sprite getAction() {
        return action;
    }

    public void setAction(Sprite action) {
        this.action = action;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
