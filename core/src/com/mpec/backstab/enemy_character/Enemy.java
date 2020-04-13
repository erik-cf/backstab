package com.mpec.backstab.enemy_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mpec.backstab.game.Backstab;

public class Enemy extends Actor {
    protected Sound slashEnemy;
    protected Sprite enemySprite;
    protected Rectangle enemyRectangle;
    protected TextureAtlas enemyAtlas;
    protected Texture healthRedBar;
    protected Circle enemyCircle;
    protected Animation<TextureRegion> enemyAnimation;
    protected int direction;
    public final double vidaTotal=100;
    public double vidaActual;



    private double attack;
    private double defense;
    private double attack_speed;
    private double hp;
    private double movement_speed;
    private double range;

    final Backstab game;

    public Enemy(Backstab game, double attack, double defense, double attack_speed, double hp, double movement_speed, double range) {
        this.game = game;
        this.attack = attack;
        this.defense = defense;
        this.attack_speed = attack_speed;
        this.hp = hp;
        this.movement_speed = movement_speed;
        this.range = range;
        this.healthRedBar=new Texture(Gdx.files.internal("Enemy/enemyHealthBar/redbar.png"));
        slashEnemy=Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/swordSlashPlayer.wav"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
       if(vidaActual>0) {


           batch.draw(enemySprite, getX(), getY());
           batch.draw(healthRedBar, getX()+2, getY()+65, (int) (healthRedBar.getWidth() * (vidaActual/vidaTotal)), healthRedBar.getHeight());
       }
       else{

       }
    }



    public double getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(double vidaActual) {
        this.vidaActual = vidaActual;
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

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public Sprite getEnemySprite() {
        return enemySprite;
    }

    public void setEnemySprite(Sprite enemySprite) {
        this.enemySprite = enemySprite;
    }

    public Rectangle getEnemyRectangle() {
        return enemyRectangle;
    }

    public void setEnemyRectangle(Rectangle enemyRectangle) {
        this.enemyRectangle = enemyRectangle;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Sound getSlashEnemy() {
        return slashEnemy;
    }

    public void setSlashEnemy(Sound slashEnemy) {
        this.slashEnemy = slashEnemy;
    }

    public TextureAtlas getEnemyAtlas() {
        return enemyAtlas;
    }

    public void setEnemyAtlas(TextureAtlas enemyAtlas) {
        this.enemyAtlas = enemyAtlas;
    }

    public Texture getHealthRedBar() {
        return healthRedBar;
    }

    public void setHealthRedBar(Texture healthRedBar) {
        this.healthRedBar = healthRedBar;
    }

    public Circle getEnemyCircle() {
        return enemyCircle;
    }

    public void setEnemyCircle(Circle enemyCircle) {
        this.enemyCircle = enemyCircle;
    }

    public Animation<TextureRegion> getEnemyAnimation() {
        return enemyAnimation;
    }

    public void setEnemyAnimation(Animation<TextureRegion> enemyAnimation) {
        this.enemyAnimation = enemyAnimation;
    }

    public Backstab getGame() {
        return game;
    }
}
