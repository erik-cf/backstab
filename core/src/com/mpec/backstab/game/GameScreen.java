package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
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


    public GameScreen(Backstab game){
        this.game = game;
        touchpad=new TouchPadTest();

        Golem golem1=new Golem(game);

        enemyAL.add(golem1);
        stage = new Stage();
        stage.addActor(touchpad.getTouchpad());
        Gdx.input.setInputProcessor(stage);
        golem1.setX(100);
        golem1.setY(100);
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

        endDate= new Date();
        numSeconds = (int)((endDate.getTime() - startDate.getTime()) / 1000);
        game.mainCharacterRectangle.setX((float) (game.mainCharacterRectangle.getX() + touchpad.getTouchpad().getKnobPercentX() * game.mainCharacter.getMovement_speed()));
        game.mainCharacterRectangle.setY((float) (game.mainCharacterRectangle.getY() + touchpad.getTouchpad().getKnobPercentY() * game.mainCharacter.getMovement_speed()));
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
            whichEnemy((int)(Math.random() * 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        game.batch.begin();
        game.mapGenerator.paintMap(game.batch);

        for (Enemy enemy : enemyAL) {
            enemy.draw(game.batch, 1);
            //game.batch.draw(enemy.getEnemyTexture(), enemy.getX(),enemy.getY());
        }

        game.batch.draw(playerSprite, game.mainCharacterRectangle.x, game.mainCharacterRectangle.y);

        touchpad.getTouchpad().draw(game.batch,1);
        game.batch.end();
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

    }

    private Sprite checkCharacterAction(){
        if(game.stateTime < 5){
            game.mainCharacter.goIdle();
        }
        if(touchpad.getTouchpad().isTouched()){
            if(touchpad.getTouchpad().getKnobPercentX() > 0.4){

                game.mainCharacter.setDirection(AvailableActions.LOOK_RIGHT);
                game.mainCharacter.goMove(AvailableActions.MOVE_RIGHT);

            }else if(touchpad.getTouchpad().getKnobPercentX() < -0.4){

                game.mainCharacter.setDirection(AvailableActions.LOOK_LEFT);
                game.mainCharacter.goMove(AvailableActions.MOVE_LEFT);

            }else if(touchpad.getTouchpad().getKnobPercentY() > 0){

                game.mainCharacter.setDirection(AvailableActions.LOOK_UP);
                game.mainCharacter.goMove(AvailableActions.MOVE_UP);

            }else if(touchpad.getTouchpad().getKnobPercentY() < 0){

                game.mainCharacter.setDirection(AvailableActions.LOOK_DOWN);
                game.mainCharacter.goMove(AvailableActions.MOVE_DOWN);

            }else{
                game.mainCharacter.goIdle();
            }
        }else{
            game.mainCharacter.goIdle();
        }

        return game.mainCharacter.getAction();
    }

    private void checkMovement(){
        for(Rectangle r : MapGenerator.collision){
            if(game.mainCharacterRectangle.overlaps(r)){
                switch(game.mainCharacter.getDirection()){
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
        else if(numSeconds%5==0 && enemyToBeCreated ==true){
            switch(whichEnemy){
                case AvailableActions.CREATE_GOLEM:
                    enemyAL.add(new Golem(game));
                    break;
                case AvailableActions.CREATE_SWORD_ZOMBIE:
                    enemyAL.add(new SwordZombie(game));
                    break;
                case AvailableActions.CREATE_WIZARD_ZOMBIE:
                    enemyAL.add(new WizardZombie(game));
                    break;
            }
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
