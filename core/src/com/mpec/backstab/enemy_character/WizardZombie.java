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
        this.setX((float)Math.random()*800);
        this.setY((float)Math.random()*800);
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

    private void goAttack(int direction){
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

        float wizardZombiePositionX = (float) this.getX();
        float wizardZombiePositionY = (float) this.getY();
        int range=25;
        if (((playerPositionY - wizardZombiePositionY >= -1) && (playerPositionY - wizardZombiePositionY <= 1)) && playerPositionX > wizardZombiePositionX) {


            if(((playerPositionY - wizardZombiePositionY <= 25) && (playerPositionX - wizardZombiePositionX <= 25)) && ((playerPositionY - wizardZombiePositionY >= -25) && (playerPositionX - wizardZombiePositionX >= -25))){
                goAttack(LOOK_RIGHT);
            }
            else {
                actionToDraw(MOVE_RIGHT);


                this.setY(wizardZombiePositionY);
                this.setX(wizardZombiePositionX + 2);
            }
        } else if (((playerPositionY - wizardZombiePositionY >= -1) && (playerPositionY - wizardZombiePositionY <= 1)) && playerPositionX < wizardZombiePositionX) {
            if(((playerPositionY - wizardZombiePositionY <= 25) && (playerPositionX - wizardZombiePositionX <= 25)) && ((playerPositionY - wizardZombiePositionY >= -25) && (playerPositionX - wizardZombiePositionX >= -25))){
                goAttack(LOOK_LEFT);
            }
            else {
                actionToDraw(MOVE_LEFT);


                this.setY(wizardZombiePositionY);
                this.setX(wizardZombiePositionX - 2);
            }
        } else if (((playerPositionX - wizardZombiePositionX >= -1) && (playerPositionX - wizardZombiePositionX <= 1)) && playerPositionY > wizardZombiePositionY) {
            if(((playerPositionY - wizardZombiePositionY <= 25) && (playerPositionX - wizardZombiePositionX <= 25)) && ((playerPositionY - wizardZombiePositionY >= -25) && (playerPositionX - wizardZombiePositionX >= -25))){
                goAttack(LOOK_UP);

            }
            else {
                actionToDraw(MOVE_UP);


                this.setY(wizardZombiePositionY + 2);
                this.setX(wizardZombiePositionX);
            }
        } else if (((playerPositionX - wizardZombiePositionX >= -1) && (playerPositionX - wizardZombiePositionX <= 1)) && playerPositionY < wizardZombiePositionY) {

            if(((playerPositionY - wizardZombiePositionY <= 25) && (playerPositionX - wizardZombiePositionX <= 25)) && ((playerPositionY - wizardZombiePositionY >= -25) && (playerPositionX - wizardZombiePositionX >= -25))){
                goAttack(LOOK_DOWN);
            }
            else {
                actionToDraw(MOVE_DOWN);


                this.setY(wizardZombiePositionY - 2);
                this.setX(wizardZombiePositionX);
            }
        } else if (playerPositionY > wizardZombiePositionY && playerPositionX > wizardZombiePositionX) {
            if(((playerPositionY - wizardZombiePositionY <= 25) && (playerPositionX - wizardZombiePositionX <= 25)) && ((playerPositionY - wizardZombiePositionY >= -25) && (playerPositionX - wizardZombiePositionX >= -25))){
                goAttack(LOOK_RIGHT);
            }
            else {
                actionToDraw(MOVE_RIGHT);


                this.setY(wizardZombiePositionY + 2);
                this.setX(wizardZombiePositionX + 2);
            }

        } else if (playerPositionY > wizardZombiePositionY && playerPositionX < wizardZombiePositionX) {
            if(((playerPositionY - wizardZombiePositionY <= 25) && (playerPositionX - wizardZombiePositionX <= 25)) && ((playerPositionY - wizardZombiePositionY >= -25) && (playerPositionX - wizardZombiePositionX >= -25))){
                goAttack(LOOK_LEFT);
            }
            else {

                actionToDraw(MOVE_LEFT);
                this.setY(wizardZombiePositionY + 2);
                this.setX(wizardZombiePositionX - 2);
            }
        } else if (playerPositionY < wizardZombiePositionY && playerPositionX > wizardZombiePositionX) {
            if(((playerPositionY - wizardZombiePositionY <= 25) && (playerPositionX - wizardZombiePositionX <= 25)) && ((playerPositionY - wizardZombiePositionY >= -25) && (playerPositionX - wizardZombiePositionX >= -25))){
                goAttack(LOOK_RIGHT);
            }
            else {
                actionToDraw(MOVE_RIGHT);

                this.setY(wizardZombiePositionY - 2);
                this.setX(wizardZombiePositionX + 2);
            }
        } else if (playerPositionY < wizardZombiePositionY && playerPositionX < wizardZombiePositionX) {
            if(((playerPositionY - wizardZombiePositionY <= 25) && (playerPositionX - wizardZombiePositionX <= 25)) && ((playerPositionY - wizardZombiePositionY >= -25) && (playerPositionX - wizardZombiePositionX >= -25))){
                goAttack(LOOK_LEFT);
            }
            else {
                actionToDraw(MOVE_LEFT);
                this.setY(wizardZombiePositionY - 2);
                this.setX(wizardZombiePositionX - 2);
            }
        }
    }
}
