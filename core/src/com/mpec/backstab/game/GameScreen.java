package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mpec.backstab.main_character.MainCharacter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mpec.backstab.map.MapGenerator;

public class GameScreen implements Screen {

    final Backstab game;
    MapGenerator mapGenerator;
    MainCharacter mainCharacter;
    Stage stage;
    Sprite playerSprite;
    TouchPadTest touchpad;

    public GameScreen(Backstab game){
        this.game = game;
        touchpad=new TouchPadTest();
        mapGenerator = new MapGenerator();
        mapGenerator.createMap();
        mainCharacter = new MainCharacter();
        //Create a Stage and add TouchPad
        stage = new Stage();
        stage.addActor(touchpad.getTouchpad());
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
        game.setStateTime(game.getStateTime() + 1 + Gdx.graphics.getDeltaTime());
        playerSprite = checkCharacterAction();


        //Move blockSprite with TouchPad
        game.camera.translate((float)(touchpad.getTouchpad().getKnobPercentX()*mainCharacter.getMovement_speed()), (float)(touchpad.getTouchpad().getKnobPercentY()*mainCharacter.getMovement_speed()));
        playerSprite.setX((float)(playerSprite.getX() + touchpad.getTouchpad().getKnobPercentX()*mainCharacter.getMovement_speed()));
        playerSprite.setY((float)(playerSprite.getY() + touchpad.getTouchpad().getKnobPercentY()*mainCharacter.getMovement_speed()));
        game.batch.begin();
        mapGenerator.paintMap(game.batch);
        touchpad.getTouchpad().draw(game.batch,1);
        playerSprite.draw(game.batch);
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
            mainCharacter.goIdle(game.stateTime);
        }
        if(touchpad.getTouchpad().isTouched()){
            if(touchpad.getTouchpad().getKnobPercentX() > 0.4){

                mainCharacter.setDirection(AvailableActions.LOOK_RIGHT);
                mainCharacter.goMove(AvailableActions.MOVE_RIGHT, game.stateTime);

            }else if(touchpad.getTouchpad().getKnobPercentX() < -0.4){

                mainCharacter.setDirection(AvailableActions.LOOK_LEFT);
                mainCharacter.goMove(AvailableActions.MOVE_LEFT, game.stateTime);

            }else if(touchpad.getTouchpad().getKnobPercentY() > 0){

                mainCharacter.setDirection(AvailableActions.LOOK_UP);
                mainCharacter.goMove(AvailableActions.MOVE_UP, game.stateTime);

            }else if(touchpad.getTouchpad().getKnobPercentY() < 0){

                mainCharacter.setDirection(AvailableActions.LOOK_DOWN);
                mainCharacter.goMove(AvailableActions.MOVE_DOWN, game.stateTime);

            }else{
                mainCharacter.goIdle(game.stateTime);
            }
        }else{
            mainCharacter.goIdle(game.stateTime);
        }

        return mainCharacter.getAction();
    }
}
