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

    boolean playSoundSlash=true;
    int contadorSlash=0;

    public SwordZombie(Backstab game) {
        super(game);
        enemyAtlas = new TextureAtlas(Gdx.files.internal("Enemy/zombie/zombie_two_enemies.txt"));
        enemyRectangle = new Rectangle();
        enemyCircle= new Circle();
        direction = LOOK_DOWN;
        goIdle();
        this.setX((float)Math.random()*800);
        this.setY((float)Math.random()*800);
        setAttack_speed(10);
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

        if(playSoundSlash==true ) {
            slashEnemy.play(1);
            playSoundSlash=false;

        }
        else if (contadorSlash%(int)(getAttack_speed())==0){
            playSoundSlash=true;
        }

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
        contadorSlash++;


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

        float enemyPositionX = this.getX();
        float enemyPositionY = this.getY();

         if (((playerPositionY - enemyPositionY >= -1) && (playerPositionY - enemyPositionY <= 1)) && playerPositionX > enemyPositionX) {

             if(((playerPositionY - enemyPositionY <= 25) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -25) && (playerPositionX - enemyPositionX >= -25))){
                goAttack(LOOK_RIGHT);
             }
             else {
                 actionToDraw(MOVE_RIGHT);

                 this.setY(enemyPositionY);
                 this.setX(enemyPositionX + 2);
             }
        } else if (((playerPositionY - enemyPositionY >= -1) && (playerPositionY - enemyPositionY <= 1)) && playerPositionX < enemyPositionX) {

             if(((playerPositionY - enemyPositionY <= 25) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -25) && (playerPositionX - enemyPositionX >= -25))){
                 goAttack(LOOK_LEFT);
             }
             else {
                 actionToDraw(MOVE_LEFT);

                 this.setY(enemyPositionY);
                 this.setX(enemyPositionX - 2);
             }
        } else if (((playerPositionX - enemyPositionX >= -1) && (playerPositionX - enemyPositionX <= 1)) && playerPositionY > enemyPositionY) {


             if(((playerPositionY - enemyPositionY <= 25) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -25) && (playerPositionX - enemyPositionX >= -25))){
                 goAttack(LOOK_UP);
             }
             else {
                 actionToDraw(MOVE_UP);


                 this.setY(enemyPositionY + 2);
                 this.setX(enemyPositionX);
             }
        } else if (((playerPositionX - enemyPositionX >= -1) && (playerPositionX - enemyPositionX <= 1)) && playerPositionY < enemyPositionY) {
             if(((playerPositionY - enemyPositionY <= 25) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -25) && (playerPositionX - enemyPositionX >= -25))){
                 goAttack(LOOK_DOWN);
             }
             else {
                 actionToDraw(MOVE_DOWN);


                 this.setY(enemyPositionY - 2);
                 this.setX(enemyPositionX);
             }
        } else if (playerPositionY > enemyPositionY && playerPositionX > enemyPositionX) {
             if(((playerPositionY - enemyPositionY <= 25) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -25) && (playerPositionX - enemyPositionX >= -25))){
                 goAttack(LOOK_RIGHT);
             }
             else {

                 actionToDraw(LOOK_RIGHT);

                 this.setY(enemyPositionY + 2);
                 this.setX(enemyPositionX + 2);
             }

        } else if (playerPositionY > enemyPositionY && playerPositionX < enemyPositionX) {

             if(((playerPositionY - enemyPositionY <= 25) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -25) && (playerPositionX - enemyPositionX >= -25))){
                 goAttack(LOOK_LEFT);
             }
             else {
                 actionToDraw(MOVE_LEFT);
                 this.setY(enemyPositionY + 2);
                 this.setX(enemyPositionX - 2);
             }
        } else if (playerPositionY < enemyPositionY && playerPositionX > enemyPositionX) {

             if(((playerPositionY - enemyPositionY <= 25) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -25) && (playerPositionX - enemyPositionX >= -25))){
                 goAttack(LOOK_RIGHT);
             }
             else {
                 actionToDraw(MOVE_RIGHT);

                 this.setY(enemyPositionY - 2);
                 this.setX(enemyPositionX + 2);
             }
        } else if (playerPositionY < enemyPositionY && playerPositionX < enemyPositionX) {
             if(((playerPositionY - enemyPositionY <= 25) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -25) && (playerPositionX - enemyPositionX >= -25))){
                 goAttack(LOOK_LEFT);
             }
             else {
                 actionToDraw(MOVE_LEFT);
                 this.setY(enemyPositionY - 2);
                 this.setX(enemyPositionX - 2);
             }
        }
    }
}
