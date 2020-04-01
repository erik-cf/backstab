package com.mpec.backstab.enemy_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;

public class SwordZombie extends Enemy implements AvailableActions {

    public SwordZombie(Backstab game) {
        super(game);
        enemyAtlas = new TextureAtlas(Gdx.files.internal("Enemy/zombie/zombie_two_enemies.txt"));
        enemyRectangle = new Rectangle();
        enemyCircle= new Circle();
        direction = LOOK_DOWN;
        goIdle();
        this.setX((float)Math.random()*800);
        this.setY((float)Math.random()*800);
    }

    private void actionToDraw(int action) {
        switch (action) {
            case MOVE_UP:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_up_swzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_down_swzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_right_swzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_LEFT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_left_swzomb), Animation.PlayMode.LOOP);
                break;

        }
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goIdle() {
        switch (direction) {
            case LOOK_UP:
                enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_up_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_right_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_down_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_left_swzomb), Animation.PlayMode.LOOP);
                break;
        }
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goAttack(int direction) {
        switch (direction) {
            case LOOK_UP:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_up_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_right_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_down_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_left_swzomb), Animation.PlayMode.LOOP);
                break;
        }
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goDie(){
        switch(direction){
            case LOOK_UP:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_up_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_right_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_down_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_left_swzomb), Animation.PlayMode.LOOP);
                break;
        }
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    public void followPlayer(float playerPositionX, float playerPositionY) {

        float swordZombiePositionX = this.getX();
        float swordZombiePositionY = this.getY();

         if (((playerPositionY - swordZombiePositionY >= -1) && (playerPositionY - swordZombiePositionY <= 1)) && playerPositionX > swordZombiePositionX) {

             if(((playerPositionY - swordZombiePositionY <= 25) && (playerPositionX - swordZombiePositionX <= 25)) && ((playerPositionY - swordZombiePositionY >= -25) && (playerPositionX - swordZombiePositionX >= -25))){
                goAttack(LOOK_RIGHT);
             }
             else {
                 actionToDraw(MOVE_RIGHT);

                 this.setY(swordZombiePositionY);
                 this.setX(swordZombiePositionX + 2);
             }
        } else if (((playerPositionY - swordZombiePositionY >= -1) && (playerPositionY - swordZombiePositionY <= 1)) && playerPositionX < swordZombiePositionX) {

             if(((playerPositionY - swordZombiePositionY <= 25) && (playerPositionX - swordZombiePositionX <= 25)) && ((playerPositionY - swordZombiePositionY >= -25) && (playerPositionX - swordZombiePositionX >= -25))){
                 goAttack(LOOK_LEFT);
             }
             else {
                 actionToDraw(MOVE_LEFT);

                 this.setY(swordZombiePositionY);
                 this.setX(swordZombiePositionX - 2);
             }
        } else if (((playerPositionX - swordZombiePositionX >= -1) && (playerPositionX - swordZombiePositionX <= 1)) && playerPositionY > swordZombiePositionY) {


             if(((playerPositionY - swordZombiePositionY <= 25) && (playerPositionX - swordZombiePositionX <= 25)) && ((playerPositionY - swordZombiePositionY >= -25) && (playerPositionX - swordZombiePositionX >= -25))){
                 goAttack(LOOK_UP);
             }
             else {
                 actionToDraw(MOVE_UP);


                 this.setY(swordZombiePositionY + 2);
                 this.setX(swordZombiePositionX);
             }
        } else if (((playerPositionX - swordZombiePositionX >= -1) && (playerPositionX - swordZombiePositionX <= 1)) && playerPositionY < swordZombiePositionY) {
             if(((playerPositionY - swordZombiePositionY <= 25) && (playerPositionX - swordZombiePositionX <= 25)) && ((playerPositionY - swordZombiePositionY >= -25) && (playerPositionX - swordZombiePositionX >= -25))){
                 goAttack(LOOK_DOWN);
             }
             else {
                 actionToDraw(MOVE_DOWN);


                 this.setY(swordZombiePositionY - 2);
                 this.setX(swordZombiePositionX);
             }
        } else if (playerPositionY > swordZombiePositionY && playerPositionX > swordZombiePositionX) {
             if(((playerPositionY - swordZombiePositionY <= 25) && (playerPositionX - swordZombiePositionX <= 25)) && ((playerPositionY - swordZombiePositionY >= -25) && (playerPositionX - swordZombiePositionX >= -25))){
                 goAttack(LOOK_RIGHT);
             }
             else {

                 actionToDraw(LOOK_RIGHT);

                 this.setY(swordZombiePositionY + 2);
                 this.setX(swordZombiePositionX + 2);
             }

        } else if (playerPositionY > swordZombiePositionY && playerPositionX < swordZombiePositionX) {

             if(((playerPositionY - swordZombiePositionY <= 25) && (playerPositionX - swordZombiePositionX <= 25)) && ((playerPositionY - swordZombiePositionY >= -25) && (playerPositionX - swordZombiePositionX >= -25))){
                 goAttack(LOOK_LEFT);
             }
             else {
                 actionToDraw(MOVE_LEFT);
                 this.setY(swordZombiePositionY + 2);
                 this.setX(swordZombiePositionX - 2);
             }
        } else if (playerPositionY < swordZombiePositionY && playerPositionX > swordZombiePositionX) {

             if(((playerPositionY - swordZombiePositionY <= 25) && (playerPositionX - swordZombiePositionX <= 25)) && ((playerPositionY - swordZombiePositionY >= -25) && (playerPositionX - swordZombiePositionX >= -25))){
                 goAttack(LOOK_RIGHT);
             }
             else {
                 actionToDraw(MOVE_RIGHT);

                 this.setY(swordZombiePositionY - 2);
                 this.setX(swordZombiePositionX + 2);
             }
        } else if (playerPositionY < swordZombiePositionY && playerPositionX < swordZombiePositionX) {
             if(((playerPositionY - swordZombiePositionY <= 25) && (playerPositionX - swordZombiePositionX <= 25)) && ((playerPositionY - swordZombiePositionY >= -25) && (playerPositionX - swordZombiePositionX >= -25))){
                 goAttack(LOOK_LEFT);
             }
             else {
                 actionToDraw(MOVE_LEFT);
                 this.setY(swordZombiePositionY - 2);
                 this.setX(swordZombiePositionX - 2);
             }
        }
    }
}
