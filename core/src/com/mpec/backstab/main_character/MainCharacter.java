package com.mpec.backstab.main_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mpec.backstab.game.Backstab;

public class MainCharacter extends CharacterAnimation {

    public static double baseAttack;
    public static double baseDefense;
    public static double baseMovementSpeed;
    public static double baseHp;
    public static double baseAttackSpeed;
    public static double baseRange;

    private double attack;
    private double defense;
    private double attack_speed;
    private double hp;
    private double movement_speed = 10;
    private double range;
    public Texture healthRedBar;




    public MainCharacter(Backstab game){
        super(game);
        this.healthRedBar=new Texture(Gdx.files.internal("Enemy/enemyHealthBar/redbar.png"));

    }

    public MainCharacter(Backstab game, double attack, double defense, double attack_speed, double hp, double movement_speed) {
        super(game);
        this.attack = attack;
        this.defense = defense;
        this.attack_speed = attack_speed;
        this.hp = hp;
        this.movement_speed = movement_speed;

    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getAttack_speed() {
        return attack_speed;
    }

    public void setAttack_speed(double attack_speed) {
        this.attack_speed = attack_speed;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getMovement_speed() {
        return movement_speed;
    }

    public void setMovement_speed(double movement_speed) {
        this.movement_speed = movement_speed;
    }

    public int getDirection(){
        return this.direction;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    public Sprite getAction(){
        return this.action;
    }

    public void setAction(Sprite action){
        this.action = action;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }
}
