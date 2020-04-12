package com.mpec.backstab.enemy_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;

public class Golem extends Enemy implements AvailableActions {

    public static double baseAttack;
    public static double baseDefense;
    public static double baseMovementSpeed;
    public static double baseHp;
    public static double baseAttackSpeed;
    public static double baseRange;

    public Golem(Backstab game, double attack, double defense, double attack_speed, double hp, double movement_speed, double range){
        super(game, attack, defense, attack_speed, hp, movement_speed, range);
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

        float enemyPositionX= this.getX();
        float enemyPositionY= this.getY();
        if (((playerPositionY-enemyPositionY>=-1)&& (playerPositionY-enemyPositionY<=1)) && playerPositionX>enemyPositionX){

            if(((playerPositionY - enemyPositionY <= 15) && (playerPositionX - enemyPositionX <= 15)) && ((playerPositionY - enemyPositionY >= -15) && (playerPositionX - enemyPositionX >= -15))){
                goIdle();
            }
            else {

                actionToDraw(MOVE_RIGHT);


                this.setY(enemyPositionY);
                this.setX(enemyPositionX + (int)getMovement_speed());
            }
        }
        else if (((playerPositionY-enemyPositionY>=-1)&& (playerPositionY-enemyPositionY<=1)) && playerPositionX<enemyPositionX){
            if(((playerPositionY - enemyPositionY <= 15) && (playerPositionX - enemyPositionX <= 15)) && ((playerPositionY - enemyPositionY >= -15) && (playerPositionX - enemyPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_LEFT);


                this.setY(enemyPositionY);
                this.setX(enemyPositionX - (int)getMovement_speed());
            }
        }

        else if (((playerPositionX-enemyPositionX>=-1)&& (playerPositionX-enemyPositionX<=1)) && playerPositionY>enemyPositionY){

            if(((playerPositionY - enemyPositionY <= 15) && (playerPositionX - enemyPositionX <= 15)) && ((playerPositionY - enemyPositionY >= -15) && (playerPositionX - enemyPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_UP);


                this.setY(enemyPositionY + (int)getMovement_speed());
                this.setX(enemyPositionX);
            }
        }
        else if (((playerPositionX-enemyPositionX>=-1)&& (playerPositionX-enemyPositionX<=1)) && playerPositionY<enemyPositionY){
            if(((playerPositionY - enemyPositionY <= 15) && (playerPositionX - enemyPositionX <= 15)) && ((playerPositionY - enemyPositionY >= -15) && (playerPositionX - enemyPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_DOWN);


                this.setY(enemyPositionY - (int)getMovement_speed());
                this.setX(enemyPositionX);
            }
        }

        else if(playerPositionY>enemyPositionY && playerPositionX>enemyPositionX  ){

            if(((playerPositionY - enemyPositionY <= 15) && (playerPositionX - enemyPositionX <= 15)) && ((playerPositionY - enemyPositionY >= -15) && (playerPositionX - enemyPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_RIGHT);

                this.setY(enemyPositionY + (int)getMovement_speed());
                this.setX(enemyPositionX + (int)getMovement_speed());
            }
        }
        else if(playerPositionY>enemyPositionY  && playerPositionX<enemyPositionX  ){
            if(((playerPositionY - enemyPositionY <= 15) && (playerPositionX - enemyPositionX <= 15)) && ((playerPositionY - enemyPositionY >= -15) && (playerPositionX - enemyPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_LEFT);
                this.setY(enemyPositionY + (int)getMovement_speed());
                this.setX(enemyPositionX - (int)getMovement_speed());
            }
        }

        else if(playerPositionY<enemyPositionY && playerPositionX>enemyPositionX   ){
            if(((playerPositionY - enemyPositionY <= 15) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -15) && (playerPositionX - enemyPositionX >= -15))){
                goIdle();
            }
            else {
                actionToDraw(MOVE_RIGHT);

                this.setY(enemyPositionY - (int)getMovement_speed());
                this.setX(enemyPositionX + (int)getMovement_speed());
            }
        }
        else if(playerPositionY<enemyPositionY && playerPositionX<enemyPositionX){
            if(((playerPositionY - enemyPositionY <= 15) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -15) && (playerPositionX - enemyPositionX >= -15))){
                goIdle();
            }

            else {
                actionToDraw(MOVE_LEFT);
                this.setY(enemyPositionY - (int)getMovement_speed());
                this.setX(enemyPositionX - (int)getMovement_speed());
            }
        }

    }


}
