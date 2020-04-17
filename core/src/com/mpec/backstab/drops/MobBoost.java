package com.mpec.backstab.drops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.game.Backstab;
import com.mpec.backstab.game.GameScreen;

public class MobBoost extends Drop {

    public static String staticName;
    public static int staticValue;
    public static int staticDuration;
    public static int staticMin_Range;
    public static int staticMax_Range;

    public static boolean mobBoostActive = false;

    public MobBoost(Backstab game){
        super(game, staticName, staticValue, staticDuration, staticMin_Range, staticMax_Range);
    }

    public void initialize(){
        dropTexture = new Texture(Gdx.files.internal("PowerUp/mobsup.png"));
        dropRectangle = new Rectangle();
        dropRectangle.setPosition(getX(), getY());
        dropRectangle.setSize(dropTexture.getWidth(), dropTexture.getHeight());
    }

    @Override
    public void changeStats() {
        for(int i = 0; i < GameScreen.enemyAL.size; i++){
            GameScreen.enemyAL.get(i).setAttack(GameScreen.enemyAL.get(i).getAttack() + (GameScreen.enemyAL.get(i).getAttack() * (this.value / 100)));
        }
        mobBoostActive = true;
    }

    public void applyDrop(){
        for(int i = 0; i < GameScreen.enemyAL.size; i++){
            GameScreen.enemyAL.get(i).setAttack(GameScreen.enemyAL.get(i).getAttack() + (GameScreen.enemyAL.get(i).getAttack() * (this.value / 100)));
        }
    }

}
