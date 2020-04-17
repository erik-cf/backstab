package com.mpec.backstab.drops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.game.Backstab;

public class ATKBoost extends Drop {

    public static String staticName;
    public static int staticValue;
    public static int staticDuration;
    public static int staticMin_Range;
    public static int staticMax_Range;

    public ATKBoost(Backstab game){
        super(game, staticName, staticValue, staticDuration, staticMin_Range, staticMax_Range);
    }

    public void initialize(){
        dropTexture = new Texture(Gdx.files.internal("PowerUp/atkup.png"));
        dropRectangle = new Rectangle();
        dropRectangle.setPosition(getX(), getY());
        dropRectangle.setSize(dropTexture.getWidth(), dropTexture.getHeight());
    }

    @Override
    public void changeStats() {
        game.timmy.setAttack(game.timmy.getAttack() + (game.timmy.getAttack() * (this.value / 100)));
    }

}
