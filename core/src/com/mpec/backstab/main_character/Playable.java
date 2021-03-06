package com.mpec.backstab.main_character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mpec.backstab.enemy_character.Enemy;
import com.mpec.backstab.enemy_character.SwordZombie;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;
import com.mpec.backstab.game.GameScreen;
import com.mpec.backstab.map.MapGenerator;

public class Playable extends Actor implements AvailableActions {

    protected String id;

    protected double attack;
    protected double defense;
    protected double attack_speed;
    protected double hp;
    protected double movement_speed = 10;
    protected double range;
    private double distanceBetween;
    private double distanceAUX;
    private double angleToEnemy;
    private double bulletX;
    private double bulletY;
    public static Array<Bullet> bulletAL;

    public static Bullet bulletToSend;


    public boolean isInitialized;

    private Boolean realizarAtaque;
    public static Enemy enemigoMasCercano;

    protected Rectangle playableRectangle;
    public Texture healthRedBar;

    public TextureAtlas playerAtlas;
    public TextureAtlas energyBall;
    public Animation<TextureRegion> animation;

    public Sound slashPlayer;
    public Sound walkPlayer;

    protected Vector2 previousPosition;

    public Sprite action;
    int direction;
    boolean playSoundWalk = true;
    private int contadorWalk=0;
    private int velocityWalk=10;
    final Backstab game;
    protected double vidaActual;
    Stage stage;

    public Playable(Backstab game){
        this.game = game;
        this.isInitialized = false;
    }

    public Playable(Backstab game, double attack, double defense, double attack_speed, double hp, double movement_speed){
        this.game = game;
        this.attack = attack;
        this.defense = defense;
        this.attack_speed = attack_speed;
        this.hp = hp;
        this.movement_speed = movement_speed;
        realizarAtaque=false;
        this.vidaActual = hp;
        bulletAL=new Array<Bullet>();
        //enemigoMasCercano=new SwordZombie(game, SwordZombie.baseAttack * game.multiplier,SwordZombie.baseDefense * game.multiplier, SwordZombie.baseAttackSpeed * game.multiplier, SwordZombie.baseHp * game.multiplier, SwordZombie.baseMovementSpeed * game.multiplier, SwordZombie.baseRange * game.multiplier,stage);
        action = new Sprite();
        this.setPosition((float)(Math.random() * MapGenerator.WORLD_WIDTH), (float)(Math.random() * MapGenerator.WORLD_HEIGHT));
        playerAtlas = new TextureAtlas(Gdx.files.internal("Player/tilesetCaracter.txt"));
        energyBall = new TextureAtlas(Gdx.files.internal("Weapon/energyball.txt"));
        slashPlayer= Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/swordSlashPlayer.wav"));
        walkPlayer=Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/footstepGrass.wav"));
        distanceBetween=0;
        distanceAUX=100000;
        angleToEnemy=0;
        bulletY=0;
        bulletX=0;
        direction = LOOK_DOWN;
        playableRectangle = new Rectangle();
        playableRectangle.setX(action.getX());
        playableRectangle.setY(action.getY());

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                healthRedBar=new Texture(Gdx.files.internal("Enemy/enemyHealthBar/redbar.png"));
            }
        });

        this.isInitialized = true;
        previousPosition = new Vector2(getX(), getY());
    }

    public void initialize(double attack, double defense, double attack_speed, double hp, double movement_speed){

        this.attack = attack;
        this.defense = defense;
        this.attack_speed = attack_speed;
        this.hp = hp;
        this.movement_speed = movement_speed;
        realizarAtaque=false;
        this.vidaActual = hp;

        action = new Sprite();

        playerAtlas = new TextureAtlas(Gdx.files.internal("Player/tilesetCaracter.txt"));
        energyBall = new TextureAtlas(Gdx.files.internal("Weapon/energyball.txt"));
        slashPlayer= Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/swordSlashPlayer.wav"));
        walkPlayer=Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/footstepGrass.wav"));
        distanceBetween=0;
        distanceAUX=100000;
        angleToEnemy=0;
        bulletY=0;
        bulletX=0;
        direction = LOOK_DOWN;
        playableRectangle = new Rectangle();
        playableRectangle.setX(action.getX());
        playableRectangle.setY(action.getY());

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                healthRedBar=new Texture(Gdx.files.internal("Enemy/enemyHealthBar/redbar.png"));
            }
        });

        this.isInitialized = true;


        previousPosition = new Vector2(getX(), getY());
    }

    public void goAtackEnergyBall(boolean atacar, Stage stage){
        if(atacar){
            Bullet bullet=null;
            for(Enemy enemy : GameScreen.enemyAL){

                //calcula la ditancia entre el enemigo y el jugador
                distanceBetween=Math.sqrt(Math.pow((getX()-enemy.getX()), 2) + Math.pow((getY()-enemy.getY()), 2));

                if (distanceBetween<distanceAUX){
                    enemigoMasCercano=enemy;
                    distanceAUX=distanceBetween;
                }



            }
            //calcula el angulo al que esta el enemigo
            if(enemigoMasCercano != null) {
                goAttack(direction);
                angleToEnemy = Math.atan2(enemigoMasCercano.getY() - getY(), enemigoMasCercano.getX() - getX());
                bullet = new Bullet(angleToEnemy, getX(), getY(), stage, attack, range, true);
                bulletToSend = bullet;
                bulletAL.add(bullet);
                stage.addActor(bullet);

                distanceBetween = 0;
                distanceAUX = 1000000;
                angleToEnemy = 0;
            }else{
                goIdle();
            }

        }
        else{
            realizarAtaque=false;
        }


    }

    public void goMove(int action) {

        if(playSoundWalk==true ) {
            walkPlayer.play(1);
            playSoundWalk=false;

        }
        else if (contadorWalk%velocityWalk==0){
            playSoundWalk=true;
        }

        switch(action){

            case MOVE_UP:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_move_up), Animation.PlayMode.LOOP);
                break;
            case MOVE_DOWN:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_move_down), Animation.PlayMode.LOOP);
                break;
            case MOVE_LEFT:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_move_left), Animation.PlayMode.LOOP);
                break;
            case MOVE_RIGHT:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_move_right), Animation.PlayMode.LOOP);
                break;


        }
        contadorWalk++;
        this.action = new Sprite(animation.getKeyFrame(game.stateTime, true));

    }

    public void goIdle(){
        switch(direction){
            case LOOK_UP:
                animation = new Animation<TextureRegion>(10f, playerAtlas.findRegions(name_idle_up), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                animation = new Animation<TextureRegion>(10f, playerAtlas.findRegions(name_idle_right), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                animation = new Animation<TextureRegion>(10f, playerAtlas.findRegions(name_idle_down), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                animation = new Animation<TextureRegion>(10f, playerAtlas.findRegions(name_idle_left), Animation.PlayMode.LOOP);
                break;
        }
        action = new Sprite(animation.getKeyFrame(game.stateTime, true));

    }

    public void goAttack(int direction){
        switch(direction){
            case LOOK_UP:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_bow_up), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_bow_right), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_bow_down), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                animation = new Animation<TextureRegion>(2f, playerAtlas.findRegions(name_bow_left), Animation.PlayMode.LOOP);
                break;
        }

        action = new Sprite(animation.getKeyFrame(game.stateTime, true));


    }

    public boolean hasMoved(){
        if(previousPosition.x != getX() || previousPosition.y != getY()){
            previousPosition.x = getX();
            previousPosition.y = getY();
            return true;
        }
        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        playableRectangle.setPosition(getX(), getY());
        if(action.getTexture() != null && action != null && this != null && batch != null) {
            batch.draw(action, getX(), getY());
        }
        if(healthRedBar != null && this != null && batch != null) {
            batch.draw(healthRedBar, getX() + 3, getY() + 60, (int) (healthRedBar.getWidth() * (vidaActual / hp)), healthRedBar.getHeight());
        }

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TextureAtlas getPlayerAtlas() {
        return playerAtlas;
    }

    public void setPlayerAtlas(TextureAtlas playerAtlas) {
        this.playerAtlas = playerAtlas;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public Sprite getAction() {
        return action;
    }

    public void setAction(Sprite action) {
        this.action = action;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Sound getWalkPlayer() {
        return walkPlayer;
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

    public void setVidaActual(double vidaActual) {
        this.vidaActual = vidaActual;
    }

    public double getVidaActual() {
        return vidaActual;
    }

    public Rectangle getPlayableRectangle() {
        return playableRectangle;
    }
}
