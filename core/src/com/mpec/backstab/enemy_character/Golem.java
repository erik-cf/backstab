package com.mpec.backstab.enemy_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;

public class Golem extends Enemy implements AvailableActions {

    public Golem(Backstab game){
        super(game);
        enemyRectangle =new Rectangle();
        enemyAtlas = new TextureAtlas(Gdx.files.internal("Enemy/golemEnemy.txt"));
        direction= AvailableActions.LOOK_DOWN;
        goIdle();
        this.setX((float)Math.random()*800);
        this.setY((float)Math.random()*800);
    }

    private void actionToDraw(int action) {
        switch(action){
            case MOVE_UP:
                enemyAnimation = new Animation<TextureRegion>(2f, enemyAtlas.findRegions(name_move_up), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                enemyAnimation = new Animation<TextureRegion>(3f, enemyAtlas.findRegions(name_move_down), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_right), Animation.PlayMode.LOOP);
                break;
            case MOVE_LEFT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_left), Animation.PlayMode.LOOP);
                break;

        }
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));

    }

    private void goIdle(){
        enemyAnimation = new Animation<TextureRegion>(3f, enemyAtlas.findRegions(name_move_down), Animation.PlayMode.LOOP);
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));


    }

    public Rectangle getEnemyRectangle() {
        return enemyRectangle;
    }

    public void setEnemyRectangle(Rectangle enemyRectangle) {
        this.enemyRectangle = enemyRectangle;
    }

    public void followPlayer(float playerPositionX, float playerPositionY){

        float golemPositionX= this.getX();
        float golemPositionY= this.getY();
        if (((playerPositionY-golemPositionY>=-1)&& (playerPositionY-golemPositionY<=1)) && playerPositionX>golemPositionX){

            if(((playerPositionY - golemPositionY <= 15) && (playerPositionX - golemPositionX <= 15)) && ((playerPositionY - golemPositionY >= -15) && (playerPositionX - golemPositionX >= -15))){
                goIdle();
            }
            else {

                actionToDraw(MOVE_RIGHT);


                this.setY(golemPositionY);
                this.setX(golemPositionX + 2);
            }
        }
        else if (((playerPositionY-golemPositionY>=-1)&& (playerPositionY-golemPositionY<=1)) && playerPositionX<golemPositionX){
            if(((playerPositionY - golemPositionY <= 15) && (playerPositionX - golemPositionX <= 15)) && ((playerPositionY - golemPositionY >= -15) && (playerPositionX - golemPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_LEFT);


                this.setY(golemPositionY);
                this.setX(golemPositionX - 2);
            }
        }

        else if (((playerPositionX-golemPositionX>=-1)&& (playerPositionX-golemPositionX<=1)) && playerPositionY>golemPositionY){

            if(((playerPositionY - golemPositionY <= 15) && (playerPositionX - golemPositionX <= 15)) && ((playerPositionY - golemPositionY >= -15) && (playerPositionX - golemPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_UP);


                this.setY(golemPositionY + 2);
                this.setX(golemPositionX);
            }
        }
        else if (((playerPositionX-golemPositionX>=-1)&& (playerPositionX-golemPositionX<=1)) && playerPositionY<golemPositionY){
            if(((playerPositionY - golemPositionY <= 15) && (playerPositionX - golemPositionX <= 15)) && ((playerPositionY - golemPositionY >= -15) && (playerPositionX - golemPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_DOWN);


                this.setY(golemPositionY - 2);
                this.setX(golemPositionX);
            }
        }

        else if(playerPositionY>golemPositionY && playerPositionX>golemPositionX  ){

            if(((playerPositionY - golemPositionY <= 15) && (playerPositionX - golemPositionX <= 15)) && ((playerPositionY - golemPositionY >= -15) && (playerPositionX - golemPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_RIGHT);

                this.setY(golemPositionY + 2);
                this.setX(golemPositionX + 2);
            }
        }
        else if(playerPositionY>golemPositionY  && playerPositionX<golemPositionX  ){
            if(((playerPositionY - golemPositionY <= 15) && (playerPositionX - golemPositionX <= 15)) && ((playerPositionY - golemPositionY >= -15) && (playerPositionX - golemPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_LEFT);
                this.setY(golemPositionY + 2);
                this.setX(golemPositionX - 2);
            }
        }

        else if(playerPositionY<golemPositionY && playerPositionX>golemPositionX   ){
            if(((playerPositionY - golemPositionY <= 15) && (playerPositionX - golemPositionX <= 25)) && ((playerPositionY - golemPositionY >= -15) && (playerPositionX - golemPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_RIGHT);

                this.setY(golemPositionY - 2);
                this.setX(golemPositionX + 2);
            }
        }
        else if(playerPositionY<golemPositionY && playerPositionX<golemPositionX){
            if(((playerPositionY - golemPositionY <= 15) && (playerPositionX - golemPositionX <= 25)) && ((playerPositionY - golemPositionY >= -15) && (playerPositionX - golemPositionX >= -15))){
                goIdle();
            }

            else {
                actionToDraw(MOVE_LEFT);
                this.setY(golemPositionY - 2);
                this.setX(golemPositionX - 2);
            }
        }

    }


}
