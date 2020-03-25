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
        enemySprite.setX((float)Math.random()*800);
        enemySprite.setY((float)Math.random()*800);
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

    public Sprite getEnemySprite() {
        return enemySprite;
    }

    public void setEnemySprite(Sprite enemySprite) {
        this.enemySprite = enemySprite;

    }

    public Rectangle getEnemyRectangle() {
        return enemyRectangle;
    }

    public void setEnemyRectangle(Rectangle enemyRectangle) {
        this.enemyRectangle = enemyRectangle;
    }

    public void followPlayer(float playerPositionX, float playerPositionY){

        float golemPositionX= enemySprite.getX();
        float golemPositionY= enemySprite.getY();
        if (((playerPositionY-golemPositionY>=-1)&& (playerPositionY-golemPositionY<=1)) && playerPositionX>golemPositionX){
            actionToDraw(MOVE_RIGHT);


            enemySprite.setY(golemPositionY);
            enemySprite.setX(golemPositionX+2);
        }
        else if (((playerPositionY-golemPositionY>=-1)&& (playerPositionY-golemPositionY<=1)) && playerPositionX<golemPositionX){
            actionToDraw(MOVE_LEFT);


            enemySprite.setY(golemPositionY);
            enemySprite.setX(golemPositionX-2);
        }

        else if (((playerPositionX-golemPositionX>=-1)&& (playerPositionX-golemPositionX<=1)) && playerPositionY>golemPositionY){
            actionToDraw(MOVE_UP);


            enemySprite.setY(golemPositionY+2);
            enemySprite.setX(golemPositionX);
        }
        else if (((playerPositionX-golemPositionX>=-1)&& (playerPositionX-golemPositionX<=1)) && playerPositionY<golemPositionY){
            actionToDraw(MOVE_DOWN);


            enemySprite.setY(golemPositionY-2);
            enemySprite.setX(golemPositionX);
        }

        else if(playerPositionY>golemPositionY && playerPositionX>golemPositionX  ){
            actionToDraw(MOVE_RIGHT);

            enemySprite.setY(golemPositionY+2);
            enemySprite.setX(golemPositionX+2);

        }
        else if(playerPositionY>golemPositionY  && playerPositionX<golemPositionX  ){
            actionToDraw(MOVE_LEFT);
            enemySprite.setY(golemPositionY+2);
            enemySprite.setX(golemPositionX-2);
        }

        else if(playerPositionY<golemPositionY && playerPositionX>golemPositionX   ){
            actionToDraw(MOVE_RIGHT);

            enemySprite.setY(golemPositionY-2);
            enemySprite.setX(golemPositionX+2);
        }
        else if(playerPositionY<golemPositionY && playerPositionX<golemPositionX){
            actionToDraw(MOVE_LEFT);
            enemySprite.setY(golemPositionY-2);
            enemySprite.setX(golemPositionX-2);
        }

    }


}
