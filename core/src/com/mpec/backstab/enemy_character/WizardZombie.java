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

    public WizardZombie(Backstab game){
        super(game);
        enemyAtlas = new TextureAtlas(Gdx.files.internal("Enemy/zombie/zombie_two_enemies.txt"));
        direction = LOOK_DOWN;
        enemyRectangle = new Rectangle();
        goIdle();
    }

    private void actionToDraw(int action) {
        switch(action){
            case MOVE_UP:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_LEFT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_left_mgzomb), Animation.PlayMode.LOOP);
                break;
        }
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goIdle(){
        switch(direction){
            case LOOK_UP:
                enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_left_mgzomb), Animation.PlayMode.LOOP);
                break;
        }
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goAttack(){
        switch(direction){
            case LOOK_UP:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_left_mgzomb), Animation.PlayMode.LOOP);
                break;
        }
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goDie(){
        switch(direction){
            case LOOK_UP:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_up_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_right_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_down_mgzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_left_mgzomb), Animation.PlayMode.LOOP);
                break;
        }
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    public void followPlayer(float playerPositionX, float playerPositionY) {

        float wizardZombiePositionX = (float) enemySprite.getX();
        float wizardZombiePositionY = (float) enemySprite.getY();
        if (((playerPositionY - wizardZombiePositionY >= -1) && (playerPositionY - wizardZombiePositionY <= 1)) && playerPositionX > wizardZombiePositionX) {
            actionToDraw(MOVE_RIGHT);


            enemySprite.setY(wizardZombiePositionY);
            enemySprite.setX(wizardZombiePositionX + 2);
        } else if (((playerPositionY - wizardZombiePositionY >= -1) && (playerPositionY - wizardZombiePositionY <= 1)) && playerPositionX < wizardZombiePositionX) {
            actionToDraw(MOVE_LEFT);


            enemySprite.setY(wizardZombiePositionY);
            enemySprite.setX(wizardZombiePositionX - 2);
        } else if (((playerPositionX - wizardZombiePositionX >= -1) && (playerPositionX - wizardZombiePositionX <= 1)) && playerPositionY > wizardZombiePositionY) {
            actionToDraw(MOVE_UP);


            enemySprite.setY(wizardZombiePositionY + 2);
            enemySprite.setX(wizardZombiePositionX);
        } else if (((playerPositionX - wizardZombiePositionX >= -1) && (playerPositionX - wizardZombiePositionX <= 1)) && playerPositionY < wizardZombiePositionY) {
            actionToDraw(MOVE_DOWN);


            enemySprite.setY(wizardZombiePositionY - 2);
            enemySprite.setX(wizardZombiePositionX);
        } else if (playerPositionY > wizardZombiePositionY && playerPositionX > wizardZombiePositionX) {
            actionToDraw(MOVE_RIGHT);


            enemySprite.setY(wizardZombiePositionY + 2);
            enemySprite.setX(wizardZombiePositionX + 2);

        } else if (playerPositionY > wizardZombiePositionY && playerPositionX < wizardZombiePositionX) {
            actionToDraw(MOVE_LEFT);
            enemySprite.setY(wizardZombiePositionY + 2);
            enemySprite.setX(wizardZombiePositionX - 2);
        } else if (playerPositionY < wizardZombiePositionY && playerPositionX > wizardZombiePositionX) {
            actionToDraw(MOVE_RIGHT);

            enemySprite.setY(wizardZombiePositionY - 2);
            enemySprite.setX(wizardZombiePositionX + 2);
        } else if (playerPositionY < wizardZombiePositionY && playerPositionX < wizardZombiePositionX) {
            actionToDraw(MOVE_LEFT);
            enemySprite.setY(wizardZombiePositionY - 2);
            enemySprite.setX(wizardZombiePositionX - 2);
        }
    }
}
