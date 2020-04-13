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

import java.util.Date;

public class Golem extends Enemy implements AvailableActions {

    public static double baseAttack;
    public static double baseDefense;
    public static double baseMovementSpeed;
    public static double baseHp;
    public static double baseAttackSpeed;
    public static double baseRange;



    private Date tiempo= new Date();
    Date endDate;
    float enemyPositionX;
    float enemyPositionY;
    private boolean ataqueRealizado=false;
    int range=25;
    int numSeconds;
    public int vidaActualEnemy=100;

    boolean playSoundSlash=true;
    int contadorSlash=0;
    double atackSpeed=2;
    double atackDamage=10;

    public Golem(Backstab game, double attack, double defense, double attack_speed, double hp, double movement_speed, double range){
        super(game, attack, defense, attack_speed, hp, movement_speed, range);
        enemyRectangle =new Rectangle();
        enemyAtlas = new TextureAtlas(Gdx.files.internal("Enemy/golemEnemy.txt"));
        direction= AvailableActions.LOOK_DOWN;
        setVidaActual(vidaActualEnemy);
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
    private void goAttack(int direction){
        game.timmy.setVidaActual(game.timmy.getVidaActual()-atackDamage);
        slashEnemy.play(1);

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
        contadorSlash++;

        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    public Rectangle getEnemyRectangle() {
        return enemyRectangle;
    }

    public void setEnemyRectangle(Rectangle enemyRectangle) {
        this.enemyRectangle = enemyRectangle;
    }

    public void followPlayer(float playerPositionX, float playerPositionY) {

        enemyPositionX = (float) this.getX();
        enemyPositionY = (float) this.getY();
        endDate=new Date();
        numSeconds = (int)((endDate.getTime() - tiempo.getTime()) / 1000);


        if (((playerPositionY - enemyPositionY >= -1) && (playerPositionY - enemyPositionY <= 1)) && playerPositionX > enemyPositionX) {


            if(((playerPositionY - enemyPositionY <= range) && (playerPositionX - enemyPositionX <= 25)) && ((playerPositionY - enemyPositionY >= -range) && (playerPositionX - enemyPositionX >= -range))){

                if(numSeconds%atackSpeed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_RIGHT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%atackSpeed!=0){
                    ataqueRealizado=false;
                }

            }
            else {
                actionToDraw(MOVE_RIGHT);


                this.setY(enemyPositionY);
                this.setX(enemyPositionX + (int)getMovement_speed());
            }
        } else if (((playerPositionY - enemyPositionY >= -1) && (playerPositionY - enemyPositionY <= 1)) && playerPositionX < enemyPositionX) {
            if(((playerPositionY - enemyPositionY <= range) && (playerPositionX - enemyPositionX <= range)) && ((playerPositionY - enemyPositionY >= -range) && (playerPositionX - enemyPositionX >= -range))){
                if(numSeconds%atackSpeed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_LEFT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%atackSpeed!=0){
                    ataqueRealizado=false;
                }
            }
            else {
                actionToDraw(MOVE_LEFT);


                this.setY(enemyPositionY);
                this.setX(enemyPositionX - (int)getMovement_speed());
            }
        } else if (((playerPositionX - enemyPositionX >= -1) && (playerPositionX - enemyPositionX <= 1)) && playerPositionY > enemyPositionY) {
            if(((playerPositionY - enemyPositionY <= range) && (playerPositionX - enemyPositionX <= range)) && ((playerPositionY - enemyPositionY >= -range) && (playerPositionX - enemyPositionX >= -range))){
                if(numSeconds%atackSpeed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_UP);
                    ataqueRealizado=true;

                }
                else if(numSeconds%atackSpeed!=0){
                    ataqueRealizado=false;
                }

            }
            else {
                actionToDraw(MOVE_UP);


                this.setY(enemyPositionY + (int)getMovement_speed());
                this.setX(enemyPositionX);
            }
        } else if (((playerPositionX - enemyPositionX >= -1) && (playerPositionX - enemyPositionX <= 1)) && playerPositionY < enemyPositionY) {

            if(((playerPositionY - enemyPositionY <= range) && (playerPositionX - enemyPositionX <= range)) && ((playerPositionY - enemyPositionY >= -range) && (playerPositionX - enemyPositionX >= -range))){
                if(numSeconds%atackSpeed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_DOWN);
                    ataqueRealizado=true;
                }
                else if(numSeconds%atackSpeed!=0){
                    ataqueRealizado=false;
                }
            }
            else {
                actionToDraw(MOVE_DOWN);


                this.setY(enemyPositionY - (int)getMovement_speed());
                this.setX(enemyPositionX);
            }
        } else if (playerPositionY > enemyPositionY && playerPositionX > enemyPositionX) {
            if(((playerPositionY - enemyPositionY <= range) && (playerPositionX - enemyPositionX <= range)) && ((playerPositionY - enemyPositionY >= -range) && (playerPositionX - enemyPositionX >= -range))){
                if(numSeconds%atackSpeed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_RIGHT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%atackSpeed!=0){
                    ataqueRealizado=false;
                }
            }
            else {
                actionToDraw(MOVE_RIGHT);


                this.setY(enemyPositionY + (int)getMovement_speed());
                this.setX(enemyPositionX + (int)getMovement_speed());
            }

        } else if (playerPositionY > enemyPositionY && playerPositionX < enemyPositionX) {
            if(((playerPositionY - enemyPositionY <= range) && (playerPositionX - enemyPositionX <= range)) && ((playerPositionY - enemyPositionY >= -range) && (playerPositionX - enemyPositionX >= -range))){
                if(numSeconds%atackSpeed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_LEFT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%atackSpeed!=0){
                    ataqueRealizado=false;
                }
            }
            else {

                actionToDraw(MOVE_LEFT);
                this.setY(enemyPositionY + (int)getMovement_speed());
                this.setX(enemyPositionX - (int)getMovement_speed());
            }
        } else if (playerPositionY < enemyPositionY && playerPositionX > enemyPositionX) {
            if(((playerPositionY - enemyPositionY <= range) && (playerPositionX - enemyPositionX <= range)) && ((playerPositionY - enemyPositionY >= -range) && (playerPositionX - enemyPositionX >= -range))){
                if(numSeconds%atackSpeed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_RIGHT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%atackSpeed!=0){
                    ataqueRealizado=false;
                }
            }
            else {
                actionToDraw(MOVE_RIGHT);

                this.setY(enemyPositionY - (int)getMovement_speed());
                this.setX(enemyPositionX + (int)getMovement_speed());
            }
        } else if (playerPositionY < enemyPositionY && playerPositionX < enemyPositionX) {
            if(((playerPositionY - enemyPositionY <= range) && (playerPositionX - enemyPositionX <= range)) && ((playerPositionY - enemyPositionY >= -range) && (playerPositionX - enemyPositionX >= -range))){
                if(numSeconds%atackSpeed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_LEFT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%atackSpeed!=0){
                    ataqueRealizado=false;
                }
            }
            else {
                actionToDraw(MOVE_LEFT);
                this.setY(enemyPositionY - (int)getMovement_speed());
                this.setX(enemyPositionX - (int)getMovement_speed());
            }
        }
    }


}
