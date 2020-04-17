package com.mpec.backstab.drops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mpec.backstab.game.Backstab;
import com.mpec.backstab.game.GameScreen;
import com.mpec.backstab.main_character.OtherPlayer;

import java.util.Map;

public class ASReduce extends Drop {

    public static String staticName;
    public static int staticValue;
    public static int staticDuration;
    public static int staticMin_Range;
    public static int staticMax_Range;

    public static boolean asReduceActive = false;

    public ASReduce(Backstab game) {
        super(game, staticName, staticValue, staticDuration, staticMin_Range, staticMax_Range);
    }

    public void initialize(){
        dropTexture = new Texture(Gdx.files.internal("PowerUp/asdown.png"));
        dropRectangle = new Rectangle();
        dropRectangle.setPosition(getX(), getY());
        dropRectangle.setSize(dropTexture.getWidth(), dropTexture.getHeight());
    }

    @Override
    public void changeStats() {
        asReduceActive = true;
    }

    public void applyDrop(){
        game.timmy.setAttack_speed(game.timmy.getAttack_speed() + (game.timmy.getAttack_speed() * (this.value / 100)));
    }

}
