package com.mpec.backstab.drops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.game.Backstab;

public class ASBoost extends Drop {

    public static String staticName;
    public static int staticValue;
    public static int staticDuration;
    public static int staticMin_Range;
    public static int staticMax_Range;

    public ASBoost(Backstab game){
        super(game, staticName, staticValue, staticDuration, staticMin_Range, staticMax_Range);
    }

    @Override
    public void initialize(){
        dropTexture = new Texture(Gdx.files.internal("PowerUp/asup.png"));
        dropRectangle = new Rectangle();
        dropRectangle.setPosition(getX(), getY());
        dropRectangle.setSize(dropTexture.getWidth(), dropTexture.getHeight());
    }

    @Override
    public void changeStats() {
        game.timmy.setAttack_speed(game.timmy.getAttack_speed() - (game.timmy.getAttack_speed() * (this.value / 100)));
    }
}
