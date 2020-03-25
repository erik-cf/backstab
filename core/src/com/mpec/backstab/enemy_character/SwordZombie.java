package com.mpec.backstab.enemy_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;

public class SwordZombie extends Enemy implements AvailableActions {

    public TextureAtlas swordZombieAtlas;
    public Animation<TextureRegion> swordZombieAnimation;
    public Sprite swordZombieSprite;
    private Rectangle swordZombieRectangle;

    final Backstab game;
    int direction;


    public SwordZombie(Backstab game) {
        this.game = game;
        swordZombieAtlas = new TextureAtlas(Gdx.files.internal("Enemy/zombie/zombie_two_enemies.txt"));
        swordZombieRectangle = new Rectangle();
        direction = LOOK_DOWN;
        goIdle();
    }

    private void actionToDraw(int action) {
        switch (action) {
            case MOVE_UP:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_move_up_swzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_move_down_swzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_move_right_swzomb), Animation.PlayMode.LOOP);
                break;
            case MOVE_LEFT:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_move_left_swzomb), Animation.PlayMode.LOOP);
                break;

        }
        swordZombieSprite = new Sprite(swordZombieAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goIdle() {
        switch (direction) {
            case LOOK_UP:
                swordZombieAnimation = new Animation<TextureRegion>(10f, swordZombieAtlas.findRegions(name_move_up_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                swordZombieAnimation = new Animation<TextureRegion>(10f, swordZombieAtlas.findRegions(name_move_right_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                swordZombieAnimation = new Animation<TextureRegion>(10f, swordZombieAtlas.findRegions(name_move_down_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                swordZombieAnimation = new Animation<TextureRegion>(10f, swordZombieAtlas.findRegions(name_move_left_swzomb), Animation.PlayMode.LOOP);
                break;
        }
        swordZombieSprite = new Sprite(swordZombieAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goAttack() {
        switch (direction) {
            case LOOK_UP:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_attack_up_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_attack_right_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_attack_down_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_attack_left_swzomb), Animation.PlayMode.LOOP);
                break;
        }
        swordZombieSprite = new Sprite(swordZombieAnimation.getKeyFrame(game.stateTime, true));
    }

    private void goDie(){
        switch(direction){
            case LOOK_UP:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_die_up_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_die_right_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_die_down_swzomb), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                swordZombieAnimation = new Animation<TextureRegion>(5f, swordZombieAtlas.findRegions(name_die_left_swzomb), Animation.PlayMode.LOOP);
                break;
        }
        swordZombieSprite = new Sprite(swordZombieAnimation.getKeyFrame(game.stateTime, true));
    }

    public void followPlayer(int playerPositionX, int playerPositionY) {

        int swordZombiePositionX = (int) swordZombieSprite.getX();
        int swordZombiePositionY = (int) swordZombieSprite.getY();
        if (((playerPositionY - swordZombiePositionY >= -1) && (playerPositionY - swordZombiePositionY <= 1)) && playerPositionX > swordZombiePositionX) {
            actionToDraw(MOVE_RIGHT);


            swordZombieSprite.setY(swordZombiePositionY);
            swordZombieSprite.setX(swordZombiePositionX + 2);
        } else if (((playerPositionY - swordZombiePositionY >= -1) && (playerPositionY - swordZombiePositionY <= 1)) && playerPositionX < swordZombiePositionX) {
            actionToDraw(MOVE_LEFT);


            swordZombieSprite.setY(swordZombiePositionY);
            swordZombieSprite.setX(swordZombiePositionX - 2);
        } else if (((playerPositionX - swordZombiePositionX >= -1) && (playerPositionX - swordZombiePositionX <= 1)) && playerPositionY > swordZombiePositionY) {
            actionToDraw(MOVE_UP);


            swordZombieSprite.setY(swordZombiePositionY + 2);
            swordZombieSprite.setX(swordZombiePositionX);
        } else if (((playerPositionX - swordZombiePositionX >= -1) && (playerPositionX - swordZombiePositionX <= 1)) && playerPositionY < swordZombiePositionY) {
            actionToDraw(MOVE_DOWN);


            swordZombieSprite.setY(swordZombiePositionY - 2);
            swordZombieSprite.setX(swordZombiePositionX);
        } else if (playerPositionY > swordZombiePositionY && playerPositionX > swordZombiePositionX) {
            actionToDraw(MOVE_RIGHT);


            swordZombieSprite.setY(swordZombiePositionY + 2);
            swordZombieSprite.setX(swordZombiePositionX + 2);

        } else if (playerPositionY > swordZombiePositionY && playerPositionX < swordZombiePositionX) {
            actionToDraw(MOVE_LEFT);
            swordZombieSprite.setY(swordZombiePositionY + 2);
            swordZombieSprite.setX(swordZombiePositionX - 2);
        } else if (playerPositionY < swordZombiePositionY && playerPositionX > swordZombiePositionX) {
            actionToDraw(MOVE_RIGHT);

            swordZombieSprite.setY(swordZombiePositionY - 2);
            swordZombieSprite.setX(swordZombiePositionX + 2);
        } else if (playerPositionY < swordZombiePositionY && playerPositionX < swordZombiePositionX) {
            actionToDraw(MOVE_LEFT);
            swordZombieSprite.setY(swordZombiePositionY - 2);
            swordZombieSprite.setX(swordZombiePositionX - 2);
        }
    }
}
