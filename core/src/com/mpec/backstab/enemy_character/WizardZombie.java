package com.mpec.backstab.enemy_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;

public class WizardZombie extends Enemy implements AvailableActions {

    public TextureAtlas wizardZombieAtlas;
    public Animation<TextureRegion> wizardZombieAnimation;
    public Sprite wizardZombieAction;
    final Backstab game;
    int direction;
    Rectangle wizardZombieRectangle;

    public WizardZombie(Backstab game){
        this.game = game;
        wizardZombieAtlas = new TextureAtlas(Gdx.files.internal("Enemy/zombie/zombie_two_enemies.txt"));
        direction = LOOK_DOWN;
        wizardZombieRectangle = new Rectangle();
        goIdle();
    }

    private void actionToDraw(int action) {
        switch(action){
            case MOVE_UP:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_move_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_move_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_move_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_LEFT:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_move_left_mgzomb), Animation.PlayMode.LOOP);
                break;
        }
        wizardZombieAction = new Sprite(wizardZombieAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goIdle(){
        switch(direction){
            case LOOK_UP:
                wizardZombieAnimation = new Animation<TextureRegion>(10f, wizardZombieAtlas.findRegions(name_move_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                wizardZombieAnimation = new Animation<TextureRegion>(10f, wizardZombieAtlas.findRegions(name_move_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                wizardZombieAnimation = new Animation<TextureRegion>(10f, wizardZombieAtlas.findRegions(name_move_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                wizardZombieAnimation = new Animation<TextureRegion>(10f, wizardZombieAtlas.findRegions(name_move_left_mgzomb), Animation.PlayMode.LOOP);
                break;
        }
        wizardZombieAction = new Sprite(wizardZombieAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goAttack(){
        switch(direction){
            case LOOK_UP:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_attack_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_attack_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_attack_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_attack_left_mgzomb), Animation.PlayMode.LOOP);
                break;
        }
        wizardZombieAction = new Sprite(wizardZombieAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goDie(){
        switch(direction){
            case LOOK_UP:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_die_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_die_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_die_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                wizardZombieAnimation = new Animation<TextureRegion>(5f, wizardZombieAtlas.findRegions(name_die_left_mgzomb), Animation.PlayMode.LOOP);
                break;
        }
        wizardZombieAction = new Sprite(wizardZombieAnimation.getKeyFrame(game.stateTime, true));
    }

    public void followPlayer(int playerPositionX, int playerPositionY) {

        int wizardZombiePositionX = (int) wizardZombieAction.getX();
        int wizardZombiePositionY = (int) wizardZombieAction.getY();
        if (((playerPositionY - wizardZombiePositionY >= -1) && (playerPositionY - wizardZombiePositionY <= 1)) && playerPositionX > wizardZombiePositionX) {
            actionToDraw(MOVE_RIGHT);


            wizardZombieAction.setY(wizardZombiePositionY);
            wizardZombieAction.setX(wizardZombiePositionX + 2);
        } else if (((playerPositionY - wizardZombiePositionY >= -1) && (playerPositionY - wizardZombiePositionY <= 1)) && playerPositionX < wizardZombiePositionX) {
            actionToDraw(MOVE_LEFT);


            wizardZombieAction.setY(wizardZombiePositionY);
            wizardZombieAction.setX(wizardZombiePositionX - 2);
        } else if (((playerPositionX - wizardZombiePositionX >= -1) && (playerPositionX - wizardZombiePositionX <= 1)) && playerPositionY > wizardZombiePositionY) {
            actionToDraw(MOVE_UP);


            wizardZombieAction.setY(wizardZombiePositionY + 2);
            wizardZombieAction.setX(wizardZombiePositionX);
        } else if (((playerPositionX - wizardZombiePositionX >= -1) && (playerPositionX - wizardZombiePositionX <= 1)) && playerPositionY < wizardZombiePositionY) {
            actionToDraw(MOVE_DOWN);


            wizardZombieAction.setY(wizardZombiePositionY - 2);
            wizardZombieAction.setX(wizardZombiePositionX);
        } else if (playerPositionY > wizardZombiePositionY && playerPositionX > wizardZombiePositionX) {
            actionToDraw(MOVE_RIGHT);


            wizardZombieAction.setY(wizardZombiePositionY + 2);
            wizardZombieAction.setX(wizardZombiePositionX + 2);

        } else if (playerPositionY > wizardZombiePositionY && playerPositionX < wizardZombiePositionX) {
            actionToDraw(MOVE_LEFT);
            wizardZombieAction.setY(wizardZombiePositionY + 2);
            wizardZombieAction.setX(wizardZombiePositionX - 2);
        } else if (playerPositionY < wizardZombiePositionY && playerPositionX > wizardZombiePositionX) {
            actionToDraw(MOVE_RIGHT);

            wizardZombieAction.setY(wizardZombiePositionY - 2);
            wizardZombieAction.setX(wizardZombiePositionX + 2);
        } else if (playerPositionY < wizardZombiePositionY && playerPositionX < wizardZombiePositionX) {
            actionToDraw(MOVE_LEFT);
            wizardZombieAction.setY(wizardZombiePositionY - 2);
            wizardZombieAction.setX(wizardZombiePositionX - 2);
        }
    }
}
