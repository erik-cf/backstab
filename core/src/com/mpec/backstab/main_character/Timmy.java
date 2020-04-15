package com.mpec.backstab.main_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mpec.backstab.game.Backstab;

public class Timmy extends Playable {


    public Timmy(Backstab game){
        super(game, 0, 0, 0, 0, 0);
        playerAtlas = new TextureAtlas(Gdx.files.internal("Player/tilesetCaracter.txt"));
        goIdle();
    }

    public Timmy(Backstab game, double attack, double defense, double attack_speed, double hp, double movement_speed) {
        super(game, attack, defense, attack_speed, hp, movement_speed);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}