package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mpec.backstab.enemy_character.EnemyAnimation;
import com.mpec.backstab.enemy_character.EnemyAnimationSwordZombie;
import com.mpec.backstab.enemy_character.Golem;
import com.mpec.backstab.map.MapGenerator;

import java.util.ArrayList;
import java.util.Date;

import javax.management.openmbean.ArrayType;

public class GameScreen implements Screen {

    final Backstab game;
    Stage stage;
    Sprite playerSprite;
    TouchPadTest touchpad;
    ArrayList<Golem> golemAL= new ArrayList<Golem>();
    Date startDate= new Date();
    Boolean crearGolem=false;

    public GameScreen(Backstab game){
        this.game = game;
        touchpad=new TouchPadTest();

        Golem golem1=new Golem(game);

        golemAL.add(golem1);
        stage = new Stage();
        stage.addActor(touchpad.getTouchpad());
        Gdx.input.setInputProcessor(stage);
        golem1.getGolemSprite().setX(100);
        golem1.getGolemSprite().setY(100);



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

        Date endDate= new Date();
        int numSeconds = (int)((endDate.getTime() - startDate.getTime()) / 1000);
        System.out.println(numSeconds);
        game.mainCharacterRectangle.setX((float) (game.mainCharacterRectangle.getX() + touchpad.getTouchpad().getKnobPercentX() * game.mainCharacter.getMovement_speed()));
        game.mainCharacterRectangle.setY((float) (game.mainCharacterRectangle.getY() + touchpad.getTouchpad().getKnobPercentY() * game.mainCharacter.getMovement_speed()));
        checkMovement();
        int i=0;
        for (Golem golem : golemAL) {

            golemAL.get(i).followPlayer((int)game.mainCharacterRectangle.getX(),(int)game.mainCharacterRectangle.getY());
            i++;
        }
        createGolem(numSeconds);
        game.batch.begin();
        game.mapGenerator.paintMap(game.batch);
        int l=0;
        for (Golem golem : golemAL) {

            game.batch.draw(golemAL.get(l).getGolemSprite(),golemAL.get(l).getGolemSprite().getX(),golemAL.get(l).getGolemSprite().getY());
            l++;
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

    private void createGolem(int numSeconds){
        if(numSeconds%3!=0){
            crearGolem=true;
        }
        else if(numSeconds%3==0 && crearGolem==true){
            Golem golem1=new Golem(game);
            golem1.getGolemSprite().setX((float)Math.random()*800);
            golem1.getGolemSprite().setY((float)Math.random()*800);
            golemAL.add(golem1);
            crearGolem=false;
        }



    }


}
