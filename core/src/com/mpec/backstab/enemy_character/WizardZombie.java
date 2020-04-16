package com.mpec.backstab.enemy_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;


public class WizardZombie extends Enemy implements AvailableActions {

    public static double baseAttack;
    public static double baseDefense;
    public static double baseMovementSpeed;
    public static double baseHp;
    public static double baseAttackSpeed;
    public static double baseRange;
    Stage stage;

    public WizardZombie(Backstab game, double attack, double defense, double attack_speed, double hp, double movement_speed, double range,Stage stage){
        super(game, attack, defense, attack_speed, hp, movement_speed, range,stage);
        enemyAtlas = new TextureAtlas(Gdx.files.internal("Enemy/WizardZombie/WizardZombie.txt"));
        goIdle();
        enemyRectangle.setSize(enemySprite.getWidth(), enemySprite.getHeight());
    }

    public WizardZombie(Backstab game){
        super(game);
    }

    @Override
    public void initialize(double attack, double defense, double attack_speed, double hp, double movement_speed, double range) {
        super.initialize(attack, defense, attack_speed, hp, movement_speed, range);
        enemyAtlas = new TextureAtlas(Gdx.files.internal("Enemy/WizardZombie/WizardZombie.txt"));
        goIdle();
        enemyRectangle.setSize(enemySprite.getWidth(), enemySprite.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
