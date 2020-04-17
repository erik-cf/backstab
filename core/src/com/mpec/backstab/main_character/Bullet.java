package com.mpec.backstab.main_character;

import com.badlogic.gdx.Gdx;
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

    public Bullet(double angleToEnemy, double bulletX, double bulletY, Stage stage, double attack, double range) {
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

        auxBulletX = bulletX;
        auxBulletY = bulletY;
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
        for(Enemy enemy : GameScreen.enemyAL){

            if(this.bulletRectangle.overlaps(enemy.getEnemyRectangle())){
                hitEnemy(enemy);
            }



        }

    }

    public void hitEnemy(Enemy enemy){
        stage.getActors().removeValue(this,true);
        enemy.setVidaActual(enemy.getVidaActual()-(attackDamage-enemy.getDefense()));
        if(enemy.getVidaActual() <= 0){
            stage.getActors().removeValue(enemy, true);
            GameScreen.enemyAL.removeValue(enemy, true);
            MapGenerator.collision.removeValue(enemy.getEnemyRectangle(), true);
            GameScreen.killedEnemies.add(enemy);
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
}
