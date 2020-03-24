package com.mpec.backstab.enemy_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;

public class Golem extends enemyStatsObject implements AvailableActions {

    private Sprite golemSprite;
    private Rectangle golemRectangle;
    public TextureAtlas golemAtlas;
    public Animation<TextureRegion> golemAnimation;
    final Backstab game;

    @Override
    public void setMovement_speed(double movement_speed) {
        super.setMovement_speed(movement_speed);
    }

    int direction;
    public Golem(Backstab game){
        this.game = game;

        //golemSprite=new Sprite();

        golemRectangle=new Rectangle();
        golemAtlas = new TextureAtlas(Gdx.files.internal("Enemy/golemEnemy.txt"));
        direction= AvailableActions.LOOK_DOWN;
        goIdle();




    }
    private void actionToDraw(int action) {
        switch(action){
            case MOVE_UP:
                golemAnimation = new Animation<TextureRegion>(2f, golemAtlas.findRegions(name_move_up), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                golemAnimation = new Animation<TextureRegion>(3f, golemAtlas.findRegions(name_move_down), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                golemAnimation = new Animation<TextureRegion>(5f, golemAtlas.findRegions(name_move_right), Animation.PlayMode.LOOP);
                break;
            case MOVE_LEFT:
                golemAnimation = new Animation<TextureRegion>(5f, golemAtlas.findRegions(name_move_left), Animation.PlayMode.LOOP);
                break;

        }
        golemSprite = new Sprite(golemAnimation.getKeyFrame(game.stateTime, true));

    }

    private void goIdle(){
        golemAnimation = new Animation<TextureRegion>(3f, golemAtlas.findRegions(name_move_down), Animation.PlayMode.LOOP);
        golemSprite = new Sprite(golemAnimation.getKeyFrame(game.stateTime, true));


    }

    public Sprite getGolemSprite() {
        return golemSprite;
    }

    public void setGolemSprite(Sprite golemSprite) {
        this.golemSprite = golemSprite;

    }

    public Rectangle getGolemRectangle() {
        return golemRectangle;
    }

    public void setGolemRectangle(Rectangle golemRectangle) {
        this.golemRectangle = golemRectangle;
    }

    public void followPlayer(int playerPositionX, int playerPositionY){

        int golemPositionX=(int)golemSprite.getX();
        int golemPositionY=(int)golemSprite.getY();
        if (((playerPositionY-golemPositionY>=-1)&& (playerPositionY-golemPositionY<=1)) && playerPositionX>golemPositionX){
            actionToDraw(MOVE_RIGHT);


            golemSprite.setY(golemPositionY);
            golemSprite.setX(golemPositionX+2);
        }
        else if (((playerPositionY-golemPositionY>=-1)&& (playerPositionY-golemPositionY<=1)) && playerPositionX<golemPositionX){
            actionToDraw(MOVE_LEFT);


            golemSprite.setY(golemPositionY);
            golemSprite.setX(golemPositionX-2);
        }

        else if (((playerPositionX-golemPositionX>=-1)&& (playerPositionX-golemPositionX<=1)) && playerPositionY>golemPositionY){
            actionToDraw(MOVE_UP);


            golemSprite.setY(golemPositionY+2);
            golemSprite.setX(golemPositionX);
        }
        else if (((playerPositionX-golemPositionX>=-1)&& (playerPositionX-golemPositionX<=1)) && playerPositionY<golemPositionY){
            actionToDraw(MOVE_DOWN);


            golemSprite.setY(golemPositionY-2);
            golemSprite.setX(golemPositionX);
        }

        else if(playerPositionY>golemPositionY && playerPositionX>golemPositionX  ){
            actionToDraw(MOVE_RIGHT);


           golemSprite.setY(golemPositionY+2);
            golemSprite.setX(golemPositionX+2);

        }
        else if(playerPositionY>golemPositionY  && playerPositionX<golemPositionX  ){
            actionToDraw(MOVE_LEFT);
            golemSprite.setY(golemPositionY+2);
            golemSprite.setX(golemPositionX-2);
        }

        else if(playerPositionY<golemPositionY && playerPositionX>golemPositionX   ){
            actionToDraw(MOVE_RIGHT);

            golemSprite.setY(golemPositionY-2);
            golemSprite.setX(golemPositionX+2);
        }
        else if(playerPositionY<golemPositionY && playerPositionX<golemPositionX){
            actionToDraw(MOVE_LEFT);
            golemSprite.setY(golemPositionY-2);
            golemSprite.setX(golemPositionX-2);
        }




    }


}
