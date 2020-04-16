package com.mpec.backstab.main_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.mpec.backstab.game.Backstab;

public class OtherPlayer extends Playable {

    public OtherPlayer(Backstab game, double attack, double defense, double attack_speed, double hp, double movement_speed) {
        super(game, attack, defense, attack_speed, hp, movement_speed);
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                playerAtlas = new TextureAtlas(Gdx.files.internal("OtherPlayers/other-players.txt"));
                goIdle();
            }
        });
    }

    @Override
    public void initialize(double attack, double defense, double attack_speed, double hp, double movement_speed) {
        super.initialize(attack, defense, attack_speed, hp, movement_speed);
        playerAtlas = new TextureAtlas(Gdx.files.internal("OtherPlayers/other-players.txt"));
        goIdle();
    }

    public OtherPlayer(Backstab game){
        super(game);
    }
}
