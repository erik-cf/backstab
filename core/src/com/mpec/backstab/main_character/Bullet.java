package com.mpec.backstab.main_character;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mpec.backstab.enemy_character.Enemy;
import com.mpec.backstab.game.AvailableActions;

import com.mpec.backstab.game.GameScreen;
import com.mpec.backstab.map.MapGenerator;


public class Bullet extends Actor implements AvailableActions {

    double angleToEnemy;
    double bulletX;
    double bulletY;
    double auxBulletX;
    double auxBulletY;
    public TextureAtlas energyBall;
    Rectangle bulletRectangle;
    Stage stage;
    double attackDamage;
    double range;
    boolean isLocal;

    public Sound energyBulletSound;

    public Bullet(double angleToEnemy, double bulletX, double bulletY, Stage stage, double attack, double range, boolean isLocal) {
        this.angleToEnemy = angleToEnemy;
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.stage=stage;
        this.attackDamage=attack;
        this.range = range;
        bulletRectangle=new Rectangle();
        bulletRectangle.setPosition((float)bulletX,(float)bulletY);
        bulletRectangle.setSize(48, 48);
        energyBall = new TextureAtlas(Gdx.files.internal("Weapon/energyball.txt"));
        this.isLocal = isLocal;
        auxBulletX = bulletX;
        auxBulletY = bulletY;
        GameScreen.bulletIsShot = true;
        energyBulletSound=Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/Shoots/energyBallSound.wav"));
        energyBulletSound.play(0.2f);
    }

    public Bullet(double angleToEnemy, double bulletX, double bulletY, Stage stage, double range, boolean isLocal){
        this.angleToEnemy = angleToEnemy;
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.stage=stage;
        this.range = range;
        auxBulletX = bulletX;
        auxBulletY = bulletY;
        bulletRectangle=new Rectangle();
        bulletRectangle.setPosition((float)bulletX,(float)bulletY);
        bulletRectangle.setSize(48, 48);
        this.isLocal = false;
    }

    public void initialize(){
        energyBall = new TextureAtlas(Gdx.files.internal("Weapon/energyball.txt"));
        energyBulletSound=Gdx.audio.newSound(Gdx.files.internal("Sounds/Player/Shoots/energyBallSound.wav"));
        energyBulletSound.play(0.2f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch,parentAlpha);
        batch.draw(energyBall.findRegion(littleball),(int)(bulletX),(int)(bulletY),48,48);

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        bulletX += 2 * Math.cos(angleToEnemy) * Gdx.graphics.getDeltaTime() * 200;
        bulletY += 2 * Math.sin(angleToEnemy)* Gdx.graphics.getDeltaTime()*200;
        bulletRectangle.setPosition((float) bulletX,(float)bulletY);


        if(Math.sqrt(Math.pow((bulletX-auxBulletX), 2) + Math.pow((bulletY-auxBulletY), 2))>range){

            stage.getActors().removeValue(this,true);

        }
        for(int i = 0; i < GameScreen.enemyAL.size; i++){//Enemy enemy : GameScreen.enemyAL){

            if(this.bulletRectangle.overlaps(GameScreen.enemyAL.get(i).getEnemyRectangle())){
                hitEnemy(GameScreen.enemyAL.get(i));
            }



        }

    }

    public void hitEnemy(Enemy enemy){
        stage.getActors().removeValue(this,true);
        if(isLocal) {
            enemy.setVidaActual(enemy.getVidaActual() - (attackDamage - enemy.getDefense()));
            if (enemy.getVidaActual() <= 0) {
                stage.getActors().removeValue(enemy, true);
                GameScreen.enemyAL.removeValue(enemy, true);
                MapGenerator.collision.removeValue(enemy.getEnemyRectangle(), true);
                GameScreen.killedEnemies.add(enemy.getId());
                GameScreen.contadorMatados++;
            }
        }
    }


    public double getAngleToEnemy() {
        return angleToEnemy;
    }

    public void setAngleToEnemy(double angleToEnemy) {
        this.angleToEnemy = angleToEnemy;
    }

    public double getBulletX() {
        return bulletX;
    }

    public void setBulletX(double bulletX) {
        this.bulletX = bulletX;
    }

    public double getBulletY() {
        return bulletY;
    }

    public void setBulletY(double bulletY) {
        this.bulletY = bulletY;
    }

    public double getRange(){
        return this.range;
    }
}
