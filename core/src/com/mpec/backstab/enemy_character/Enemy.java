package com.mpec.backstab.enemy_character;

import com.badlogic.gdx.Game;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mpec.backstab.game.AvailableActions;
import com.mpec.backstab.game.Backstab;
import com.mpec.backstab.game.GameScreen;
import com.mpec.backstab.main_character.OtherPlayer;
import com.mpec.backstab.main_character.Playable;
import com.mpec.backstab.map.MapGenerator;

import java.util.Date;
import java.util.Map;

public class Enemy extends Actor implements AvailableActions {
    protected Sound slashEnemy;
    protected Sprite enemySprite;
    protected Rectangle enemyRectangle;
    protected TextureAtlas enemyAtlas;
    protected Texture healthRedBar;
    protected Circle enemyCircle;
    protected Animation<TextureRegion> enemyAnimation;
    protected int direction;

    public boolean isInitialized;

    private Playable nearestPlayer;

    public double vidaActual;

    boolean playSoundSlash=true;
    int contadorSlash=0;
    float distanceAux;
    protected double attack;
    protected double defense;
    protected double attack_speed;
    protected double hp;
    protected double movement_speed;
    protected double range;
    float minDistance;

    protected float multiplier;

    private boolean ataqueRealizado=false;
    int numSeconds;

    private Date tiempo= new Date();

    final Backstab game;

    Stage stage;

    public Enemy(Backstab game, double attack, double defense, double attack_speed, double hp, double movement_speed, double range,Stage stage) {
        this.game = game;
        this.attack = attack;
        this.defense = defense;
        this.attack_speed = attack_speed;
        this.hp = hp;
        this.stage=stage;
        this.movement_speed = movement_speed;
        this.range = range;
        this.vidaActual = hp;
        direction = LOOK_DOWN;
        enemyRectangle =new Rectangle();
        this.healthRedBar=new Texture(Gdx.files.internal("Enemy/enemyHealthBar/redbar.png"));
        isInitialized = true;
        slashEnemy=Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/swordSlashPlayer.wav"));
        this.setX((float)Math.random()* MapGenerator.WORLD_WIDTH);
        this.setY((float)Math.random()* MapGenerator.WORLD_HEIGHT);
        minDistance = 9999;
        distanceAux = 0;
    }

    public Enemy(Backstab game){
        this.game = game;
        isInitialized = false;
        minDistance = 9999;
        distanceAux = 0;
    }

    public void initialize(double attack, double defense, double attack_speed, double hp, double movement_speed, double range){
        this.attack = attack * multiplier;
        this.defense = defense * multiplier;
        this.attack_speed = attack_speed * multiplier;
        this.hp = hp * multiplier;
        this.movement_speed = movement_speed * multiplier;
        this.range = range;
        this.vidaActual = hp;
        direction = LOOK_DOWN;
        enemyRectangle =new Rectangle();
        healthRedBar=new Texture(Gdx.files.internal("Enemy/enemyHealthBar/redbar.png"));
        isInitialized = true;
        slashEnemy=Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/swordSlashPlayer.wav"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(vidaActual>0) {
            enemyRectangle.setPosition(getX(), getY());
            batch.draw(enemySprite, getX(), getY());
            batch.draw(healthRedBar, getX()+2, getY()+65, (int) (healthRedBar.getWidth() * (vidaActual/hp)), healthRedBar.getHeight());
        }
        else{
            if(this == null) {
                stage.getActors().removeValue(this, true);
                GameScreen.enemyAL.removeValue(this, true);
            }

        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(this.getClass().equals(Golem.class)){
            followPlayerGolem();
        }else {
            followPlayer();
        }
    }

    protected void actionToDraw(int action) {
        if(enemyAtlas != null) {
            switch (action) {
                case MOVE_UP:
                    enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_up), Animation.PlayMode.LOOP);
                    direction = LOOK_UP;
                    break;
                case MOVE_DOWN:
                    enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_down), Animation.PlayMode.LOOP);
                    direction = LOOK_DOWN;
                    break;
                case MOVE_RIGHT:
                    enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_right), Animation.PlayMode.LOOP);
                    direction = LOOK_RIGHT;
                    break;
                case MOVE_LEFT:
                    enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_move_left), Animation.PlayMode.LOOP);
                    direction = LOOK_LEFT;
                    break;

            }
            enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
        }
    }

    protected void goIdle() {
        if(enemyAtlas != null) {
            switch (direction) {
                case LOOK_UP:
                    enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_up), Animation.PlayMode.LOOP);
                    break;
                case LOOK_RIGHT:
                    enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_right), Animation.PlayMode.LOOP);
                    break;
                case LOOK_DOWN:
                    enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_down), Animation.PlayMode.LOOP);
                    break;
                case LOOK_LEFT:
                    enemyAnimation = new Animation<TextureRegion>(10f, enemyAtlas.findRegions(name_move_left), Animation.PlayMode.LOOP);
                    break;
            }
            enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
        }
    }

    protected void goAttack(int direction) {
        game.timmy.setVidaActual(game.timmy.getVidaActual() - (attack - game.timmy.getDefense()));
        if(playSoundSlash==true ) {
            slashEnemy.play(1);
            playSoundSlash=false;

        }
        else if (contadorSlash%(int)(getAttack_speed())==0){
            playSoundSlash=true;
        }

        switch (direction) {
            case LOOK_UP:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_up), Animation.PlayMode.LOOP);


                break;
            case LOOK_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_right), Animation.PlayMode.LOOP);

                break;
            case LOOK_DOWN:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_down), Animation.PlayMode.LOOP);

                break;
            case LOOK_LEFT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_attack_left), Animation.PlayMode.LOOP);

                break;
        }
        contadorSlash++;
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    protected void goDie(){
        switch(direction){
            case LOOK_UP:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_up), Animation.PlayMode.LOOP);
                break;
            case LOOK_RIGHT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_right), Animation.PlayMode.LOOP);
                break;
            case LOOK_DOWN:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_down), Animation.PlayMode.LOOP);
                break;
            case LOOK_LEFT:
                enemyAnimation = new Animation<TextureRegion>(5f, enemyAtlas.findRegions(name_die_left), Animation.PlayMode.LOOP);
                break;
        }
        enemySprite = new Sprite(enemyAnimation.getKeyFrame(game.stateTime, true));
    }

    public void followPlayer() {

        for(Map.Entry<String, OtherPlayer> entry : GameScreen.otherPlayers.entrySet()){
            distanceAux = GameScreen.getDistance(this, entry.getValue());
            if(distanceAux < minDistance){
                nearestPlayer = entry.getValue();
                minDistance = distanceAux;
            }
        }
        distanceAux = GameScreen.getDistance(this, game.timmy);
        if(distanceAux < minDistance){
            nearestPlayer = game.timmy;
            minDistance = distanceAux;
        }

        numSeconds = (int)((new Date().getTime() - tiempo.getTime()) / 1000);

        if (((nearestPlayer.getY() - getY() >= -1) && (nearestPlayer.getY() - getY() <= 1)) && nearestPlayer.getX() > getX()) {


            if(((nearestPlayer.getY() - getY() <= range) && (nearestPlayer.getX() - getX() <= range)) && ((nearestPlayer.getY() - getY() >= -range) && (nearestPlayer.getX() - getX() >= -range))){

                if(numSeconds%attack_speed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_RIGHT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%attack_speed!=0){
                    ataqueRealizado=false;
                }

            }
            else {
                actionToDraw(MOVE_RIGHT);
                this.setX(getX() + (int)getMovement_speed());
            }
        } else if (((nearestPlayer.getY() - getY() >= -1) && (nearestPlayer.getY() - getY() <= 1)) && nearestPlayer.getX() < getX()) {
            if(((nearestPlayer.getY() - getY() <= range) && (nearestPlayer.getX() - getX() <= range)) && ((nearestPlayer.getY() - getY() >= -range) && (nearestPlayer.getX() - getX() >= -range))){
                if(numSeconds%attack_speed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_LEFT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%attack_speed!=0){
                    ataqueRealizado=false;
                }
            }
            else {
                actionToDraw(MOVE_LEFT);

                this.setX(getX() - (int)getMovement_speed());
            }
        } else if (((nearestPlayer.getX() - getX() >= -1) && (nearestPlayer.getX() - getX() <= 1)) && nearestPlayer.getY() > getY()) {
            if(((nearestPlayer.getY() - getY() <= range) && (nearestPlayer.getX() - getX() <= range)) && ((nearestPlayer.getY() - getY() >= -range) && (nearestPlayer.getX() - getX() >= -range))){
                if(numSeconds%attack_speed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_UP);
                    ataqueRealizado=true;

                }
                else if(numSeconds%attack_speed!=0){
                    ataqueRealizado=false;
                }

            }
            else {
                actionToDraw(MOVE_UP);


                this.setY(getY() + (int)getMovement_speed());
                this.setX(getX());
            }
        } else if (((nearestPlayer.getX() - getX() >= -1) && (nearestPlayer.getX() - getX() <= 1)) && nearestPlayer.getY() < getY()) {

            if(((nearestPlayer.getY() - getY() <= range) && (nearestPlayer.getX() - getX() <= range)) && ((nearestPlayer.getY() - getY() >= -range) && (nearestPlayer.getX() - getX() >= -range))){
                if(numSeconds%attack_speed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_DOWN);
                    ataqueRealizado=true;
                }
                else if(numSeconds%attack_speed!=0){
                    ataqueRealizado=false;
                }
            }
            else {
                actionToDraw(MOVE_DOWN);
                this.setY(getY() - (int)getMovement_speed());
            }
        } else if (nearestPlayer.getY() > getY() && nearestPlayer.getX() > getX()) {
            if(((nearestPlayer.getY() - getY() <= range) && (nearestPlayer.getX() - getX() <= range)) && ((nearestPlayer.getY() - getY() >= -range) && (nearestPlayer.getX() - getX() >= -range))){
                if(numSeconds%attack_speed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_RIGHT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%attack_speed!=0){
                    ataqueRealizado=false;
                }
            }
            else {
                actionToDraw(MOVE_RIGHT);


                this.setY(getY() + (int)getMovement_speed());
                this.setX(getX() + (int)getMovement_speed());
            }

        } else if (nearestPlayer.getY() > getY() && nearestPlayer.getX() < getX()) {
            if(((nearestPlayer.getY() - getY() <= range) && (nearestPlayer.getX() - getX() <= range)) && ((nearestPlayer.getY() - getY() >= -range) && (nearestPlayer.getX() - getX() >= -range))){
                if(numSeconds%attack_speed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_LEFT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%attack_speed!=0){
                    ataqueRealizado=false;
                }
            }
            else {

                actionToDraw(MOVE_LEFT);
                this.setY(getY() + (int)getMovement_speed());
                this.setX(getX() - (int)getMovement_speed());
            }
        } else if (nearestPlayer.getY() < getY() && nearestPlayer.getX() > getX()) {
            if(((nearestPlayer.getY() - getY() <= range) && (nearestPlayer.getX() - getX() <= range)) && ((nearestPlayer.getY() - getY() >= -range) && (nearestPlayer.getX() - getX() >= -range))){
                if(numSeconds%attack_speed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_RIGHT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%attack_speed!=0){
                    ataqueRealizado=false;
                }
            }
            else {
                actionToDraw(MOVE_RIGHT);

                this.setY(getY() - (int)getMovement_speed());
                this.setX(getX() + (int)getMovement_speed());
            }
        } else if (nearestPlayer.getY() < getY() && nearestPlayer.getX() < getX()) {
            if(((nearestPlayer.getY() - getY() <= range) && (nearestPlayer.getX() - getX() <= range)) && ((nearestPlayer.getY() - getY() >= -range) && (nearestPlayer.getX() - getX() >= -range))){
                if(numSeconds%attack_speed==0 && ataqueRealizado==false) {
                    goAttack(LOOK_LEFT);
                    ataqueRealizado=true;
                }
                else if(numSeconds%attack_speed!=0){
                    ataqueRealizado=false;
                }
            }
            else {
                actionToDraw(MOVE_LEFT);
                this.setY(getY() - (int)getMovement_speed());
                this.setX(getX() - (int)getMovement_speed());
            }
        }
    }

    public void followPlayerGolem() {
        numSeconds = (int)((new Date().getTime() - tiempo.getTime()) / 1000);


        if (((game.timmy.getY() - getY() >= -1) && (game.timmy.getY() - getY() <= 1)) && game.timmy.getX() > getX()) {


            actionToDraw(MOVE_RIGHT);


            this.setY(getY());
            this.setX(getX() + (int)getMovement_speed());
        } else if (((game.timmy.getY() - getY() >= -1) && (game.timmy.getY() - getY() <= 1)) && game.timmy.getX() < getX()) {

            actionToDraw(MOVE_LEFT);


            this.setY(getY());
            this.setX(getX() - (int)getMovement_speed());

        } else if (((game.timmy.getX() - getX() >= -1) && (game.timmy.getX() - getX() <= 1)) && game.timmy.getY() > getY()) {

            actionToDraw(MOVE_UP);


            this.setY(getY() + (int)getMovement_speed());
            this.setX(getX());

        } else if (((game.timmy.getX() - getX() >= -1) && (game.timmy.getX() - getX() <= 1)) && game.timmy.getY() < getY()) {


            actionToDraw(MOVE_DOWN);


            this.setY(getY() - (int)getMovement_speed());
            this.setX(getX());

        } else if (game.timmy.getY() > getY() && game.timmy.getX() > getX()) {

            actionToDraw(MOVE_RIGHT);


            this.setY(getY() + (int)getMovement_speed());
            this.setX(getX() + (int)getMovement_speed());


        } else if (game.timmy.getY() > getY() && game.timmy.getX() < getX()) {


            actionToDraw(MOVE_LEFT);
            this.setY(getY() + (int)getMovement_speed());
            this.setX(getX() - (int)getMovement_speed());

        } else if (game.timmy.getY() < getY() && game.timmy.getX() > getX()) {

            actionToDraw(MOVE_RIGHT);

            this.setY(getY() - (int)getMovement_speed());
            this.setX(getX() + (int)getMovement_speed());

        } else if (game.timmy.getY() < getY() && game.timmy.getX() < getX()) {

            actionToDraw(MOVE_LEFT);
            this.setY(getY() - (int)getMovement_speed());
            this.setX(getX() - (int)getMovement_speed());

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

    public void setMultiplier(float multiplier){
        this.multiplier = multiplier;
    }

    public float getMultiplier(){
        return this.multiplier;
    }
}
