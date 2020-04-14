package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mpec.backstab.enemy_character.Enemy;
import com.mpec.backstab.enemy_character.Golem;
import com.mpec.backstab.enemy_character.SwordZombie;
import com.mpec.backstab.enemy_character.WizardZombie;
import com.mpec.backstab.map.MapGenerator;

import java.util.ArrayList;
import java.util.Date;

public class GameScreen implements Screen {


    final Backstab game;
    Stage stage;
    TouchPadTest touchpad;
    ArrayList<Enemy> enemyAL = new ArrayList<Enemy>();
    Date startDate= new Date();
    boolean enemyToBeCreated =false;
    Date endDate;
    int numSeconds;

    float movingX;
    float movingY;


    public GameScreen(Backstab game){
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        Gdx.input.setInputProcessor(stage);
        touchpad=new TouchPadTest();
        stage.addActor(game.timmy);
        stage.addActor(touchpad);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();

        game.stateTime = game.stateTime + 1 + Gdx.graphics.getDeltaTime();
        checkCharacterAction();
        touchpad.setBounds(game.camera.position.x - touchpad.getWidth() / 2, game.camera.position.y - stage.getHeight() / 2 + 15, 150, 150);
        endDate= new Date();
        numSeconds = (int)((endDate.getTime() - startDate.getTime()) / 1000);
        movingX = (float)(game.timmy.getX() + touchpad.getKnobPercentX() * game.timmy.getMovement_speed());
        movingY = (float) (game.timmy.getY() + touchpad.getKnobPercentY() * game.timmy.getMovement_speed());
        System.out.println(game.timmy.getPlayableRectangle().getX());
        System.out.println(game.timmy.getAction().getX());
        System.out.println(game.camera.position.x);
        System.out.println(game.timmy.getHp());
        game.moveCamera();
        if(game.timmy.getX() < 0){
            movingX = 0;
        }
        if(game.timmy.getX() + game.timmy.getAction().getWidth() > MapGenerator.WORLD_WIDTH){
            movingX = MapGenerator.WORLD_WIDTH - game.timmy.getAction().getWidth();
        }

        if(game.timmy.getY() < 0){
            movingY = 0;
        }

        if(game.timmy.getY() + game.timmy.getAction().getHeight() > MapGenerator.WORLD_HEIGHT){
            movingY = MapGenerator.WORLD_HEIGHT - game.timmy.getAction().getHeight();
        }

        game.timmy.setX(movingX);
        game.timmy.setY(movingY);
        if(game.timmy.getVidaActual()<=0){
            game.setScreen(new EndMenuScreen(game,numSeconds));
        }
        checkMovement();
        for (Enemy enemy : enemyAL) {
            if(enemy.getClass().equals(Golem.class)){
                ((Golem)enemy).followPlayer(game.timmy.getPlayableRectangle().getX(), game.timmy.getPlayableRectangle().getY());
            }else if(enemy.getClass().equals(WizardZombie.class)){
                ((WizardZombie)enemy).followPlayer(game.timmy.getPlayableRectangle().getX(), game.timmy.getPlayableRectangle().getY());
            }else if(enemy.getClass().equals(SwordZombie.class)){
                ((SwordZombie)enemy).followPlayer(game.timmy.getPlayableRectangle().getX(), game.timmy.getPlayableRectangle().getY());
            }

        }

        try {
            whichEnemy((int)(Math.random()*3));
        } catch (Exception e) {
            e.printStackTrace();
        }

        game.batch.begin();
        game.mapGenerator.paintMap(game.batch);
        game.batch.end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
        for(Enemy enemy : enemyAL){
            enemy.getSlashEnemy().dispose();
            enemy.getHealthRedBar().dispose();
            enemy.getEnemySprite().getTexture().dispose();
            enemy.getEnemyAtlas().dispose();
        }
        game.mapGenerator.dispose();
        game.timmy.getAction().getTexture().dispose();
        game.timmy.getWalkPlayer().dispose();
        game.timmy.getPlayerAtlas().dispose();
        game.timmy.getWalkPlayer().dispose();
        stage.dispose();
    }

    private void checkCharacterAction(){
        if(game.stateTime < 5){
            game.timmy.goIdle();
        }
        if(touchpad.isTouched()){
            if(touchpad.getKnobPercentX() > 0.4){

                game.timmy.setDirection(AvailableActions.LOOK_RIGHT);
                game.timmy.goMove(AvailableActions.MOVE_RIGHT);

            }else if(touchpad.getKnobPercentX() < -0.4){

                game.timmy.setDirection(AvailableActions.LOOK_LEFT);
                game.timmy.goMove(AvailableActions.MOVE_LEFT);

            }else if(touchpad.getKnobPercentY() > 0){

                game.timmy.setDirection(AvailableActions.LOOK_UP);
                game.timmy.goMove(AvailableActions.MOVE_UP);

            }else if(touchpad.getKnobPercentY() < 0){

                game.timmy.setDirection(AvailableActions.LOOK_DOWN);
                game.timmy.goMove(AvailableActions.MOVE_DOWN);

            }else{
                game.timmy.goIdle();
            }
        }else{
            game.timmy.goIdle();
        }
    }

    private void checkMovement(){
        for(Rectangle r : MapGenerator.collision){
            if(game.timmy.getPlayableRectangle().overlaps(r)){
                switch(game.timmy.getDirection()){
                    case AvailableActions.LOOK_LEFT:
                        game.timmy.setX(game.timmy.getPlayableRectangle().getX() + 1);
                        break;
                    case AvailableActions.LOOK_RIGHT:
                        game.timmy.setX(game.timmy.getPlayableRectangle().getX() - 1);
                        break;
                    case AvailableActions.LOOK_UP:
                        game.timmy.setY(game.timmy.getPlayableRectangle().getY() - 1);
                        break;
                    case AvailableActions.LOOK_DOWN:
                        game.timmy.setY(game.timmy.getPlayableRectangle().getY() + 1);
                        break;
                }
            }
        }

    }


    private void createEnemy(int whichEnemy){
        if(numSeconds%3!=0){
            enemyToBeCreated =true;
        }
        else if(numSeconds%3==0 && enemyToBeCreated ==true){
            Enemy enemy = null;
            switch(whichEnemy){
                case AvailableActions.CREATE_GOLEM:
                    enemy = new Golem(game, Golem.baseAttack * game.multiplier,Golem.baseDefense * game.multiplier, Golem.baseAttackSpeed * game.multiplier, Golem.baseHp * game.multiplier, Golem.baseMovementSpeed * game.multiplier, Golem.baseRange * game.multiplier);
                    enemyAL.add(enemy);
                    break;
                case AvailableActions.CREATE_SWORD_ZOMBIE:
                    enemy = new SwordZombie(game, SwordZombie.baseAttack * game.multiplier,SwordZombie.baseDefense * game.multiplier, SwordZombie.baseAttackSpeed * game.multiplier, SwordZombie.baseHp * game.multiplier, SwordZombie.baseMovementSpeed * game.multiplier, SwordZombie.baseRange * game.multiplier);
                    enemyAL.add(enemy);
                    break;
                case AvailableActions.CREATE_WIZARD_ZOMBIE:
                    enemy = new WizardZombie(game, WizardZombie.baseAttack * game.multiplier,WizardZombie.baseDefense * game.multiplier, WizardZombie.baseAttackSpeed * game.multiplier, WizardZombie.baseHp * game.multiplier, WizardZombie.baseMovementSpeed * game.multiplier, WizardZombie.baseRange * game.multiplier);
                    enemyAL.add(enemy);
                    break;
            }
            stage.addActor(enemy);
            MapGenerator.collision.add(enemy.getEnemyRectangle());
            enemyToBeCreated = false;
        }
    }

    private void whichEnemy(int rdm) throws Exception {
        switch(rdm){
            case 0:
                createEnemy(AvailableActions.CREATE_GOLEM);
                break;
            case 1:
                createEnemy(AvailableActions.CREATE_WIZARD_ZOMBIE);
                break;
            case 2:
                createEnemy(AvailableActions.CREATE_SWORD_ZOMBIE);
                break;
            default:
                throw new Exception("Error! Number out of range (0-2)!");
        }
    }
}
