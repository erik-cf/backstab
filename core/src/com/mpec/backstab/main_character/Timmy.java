package com.mpec.backstab.main_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mpec.backstab.game.Backstab;

public class Timmy extends Playable {

    public Timmy(Backstab game){
        super(game, 0, 0, 0, 0, 0);
        this.healthRedBar=new Texture(Gdx.files.internal("Enemy/enemyHealthBar/redbar.png"));
    }

    public Timmy(Backstab game, double attack, double defense, double attack_speed, double hp, double movement_speed) {
        super(game, attack, defense, attack_speed, hp, movement_speed);
    }
}