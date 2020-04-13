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
    Sprite playerSprite;
    TouchPadTest touchpad;
    ArrayList<Enemy> enemyAL = new ArrayList<Enemy>();
    Date startDate= new Date();
    boolean enemyToBeCreated =false;
    Date endDate;
    int numSeconds;

    float movingX;
    float movingY;

    double vidaTotalPlayer=100;


    public GameScreen(Backstab game){
        this.game = game;
        touchpad=new TouchPadTest();
        float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        FitViewport viewport = new FitViewport(1000f * aspectRatio, 1000f, game.camera);
        game.timmy.setVidaActual(100);
        stage = new Stage(viewport, game.batch);
        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);

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
        playerSprite = checkCharacterAction();
        touchpad.setBounds(game.camera.position.x - touchpad.getWidth()/2, game.camera.position.y - stage.getHeight() / 2 + 15, 150, 150);
        endDate= new Date();
        numSeconds = (int)((endDate.getTime() - startDate.getTime()) / 1000);
        movingX = (float)(game.mainCharacterRectangle.getX() + touchpad.getKnobPercentX() * game.timmy.getMovement_speed());
        movingY = (float) (game.mainCharacterRectangle.getY() + touchpad.getKnobPercentY() * game.timmy.getMovement_speed());
        game.camera.position.x = game.mainCharacterRectangle.getX();
        game.camera.position.y = game.mainCharacterRectangle.getY();
        game.mainCharacterRectangle.setX(movingX);
        game.mainCharacterRectangle.setY(movingY);
        if(game.timmy.getVidaActual()<=0){
            game.setScreen(new EndMenuScreen(game,numSeconds));
        }
        checkMovement();
        for (Enemy enemy : enemyAL) {
            if(enemy.getClass().equals(Golem.class)){
                ((Golem)enemy).followPlayer(game.mainCharacterRectangle.getX(), game.mainCharacterRectangle.getY());
            }else if(enemy.getClass().equals(WizardZombie.class)){
                ((WizardZombie)enemy).followPlayer(game.mainCharacterRectangle.getX(), game.mainCharacterRectangle.getY());
            }else if(enemy.getClass().equals(SwordZombie.class)){
                ((SwordZombie)enemy).followPlayer(game.mainCharacterRectangle.getX(), game.mainCharacterRectangle.getY());
            }

        }

        try {
            whichEnemy((int)(Math.random()*3));
        } catch (Exception e) {
            e.printStackTrace();
        }

        game.batch.begin();
        game.mapGenerator.paintMap(game.batch);

        /*for (Enemy enemy : enemyAL) {
            enemy.draw(game.batch, 1);
            //game.batch.draw(enemy.getEnemyTexture(), enemy.getX(),enemy.getY());
        }*/

        game.batch.draw(playerSprite, game.mainCharacterRectangle.x, game.mainCharacterRectangle.y);
        game.batch.draw(game.timmy.healthRedBar, game.mainCharacterRectangle.x+3 , game.mainCharacterRectangle.y +60,
                (int)(game.timmy.healthRedBar.getWidth()*(game.timmy.getVidaActual()/vidaTotalPlayer)), game.timmy.healthRedBar.getHeight());

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
        for(Enemy enemy: enemyAL){
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

    private Sprite checkCharacterAction(){
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

        return game.timmy.getAction();
    }

    private void checkMovement(){
        for(Rectangle r : MapGenerator.collision){
            if(game.mainCharacterRectangle.overlaps(r)){
                switch(game.timmy.getDirection()){
                    case AvailableActions.LOOK_LEFT:
                        game.mainCharacterRectangle.setX(game.mainCharacterRectangle.getX() + 5);
                        break;
                    case AvailableActions.LOOK_RIGHT:
                        game.mainCharacterRectangle.setX(game.mainCharacterRectangle.getX() - 5);
                        break;
                    case AvailableActions.LOOK_UP:
                        game.mainCharacterRectangle.setY(game.mainCharacterRectangle.getY() - 5);
                        break;
                    case AvailableActions.LOOK_DOWN:
                        game.mainCharacterRectangle.setY(game.mainCharacterRectangle.getY() + 5);
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
