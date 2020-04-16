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
    public TextureAtlas energyBall;
    Rectangle bulletRectangle;
    Stage stage;
    Enemy enemy;
    double attackDamage;

    public Bullet(double angleToEnemy, double bulletX, double bulletY, Stage stage, Enemy enemy,double attack) {
        this.angleToEnemy = angleToEnemy;
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.stage=stage;
        this.enemy=enemy;
        this.attackDamage=attack;
        bulletRectangle=new Rectangle();
        bulletRectangle.setPosition((float)bulletX,(float)bulletY);
        energyBall = new TextureAtlas(Gdx.files.internal("Weapon/energyball.txt"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch,parentAlpha);
        batch.draw(energyBall.findRegion(littleball),(int)(bulletX),(int)(bulletY),48,48);

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        bulletX += 2 * Math.cos(angleToEnemy)*Gdx.graphics.getDeltaTime()*200;
        bulletY += 2 * Math.sin(angleToEnemy)*Gdx.graphics.getDeltaTime()*200;
        bulletRectangle.setPosition((float) bulletX,(float)bulletY);

        for(Rectangle rect: MapGenerator.collision ){

            if(this.bulletRectangle.overlaps(rect)){
                hitEnemy();
            }

        }

    }

    public void hitEnemy(){





        stage.getActors().removeValue(this,true);
        enemy.setVidaActual(enemy.getVidaActual()-(attackDamage-enemy.getDefense()));

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
