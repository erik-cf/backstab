package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mpec.backstab.main_character.MainCharacter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mpec.backstab.map.MapGenerator;

public class GameScreen implements Screen {

    final Backstab game;
    MapGenerator mapGenerator;
    MainCharacter mainCharacter;
    private float blockSpeed=5;
    Stage stage;
    TouchPadTest touchpad= new TouchPadTest();

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

        game.setStateTime(game.getStateTime() + 1 + Gdx.graphics.getDeltaTime());



        //Move blockSprite with TouchPad
        touchpad.getBlockSprite().setX(touchpad.getBlockSprite().getX() + touchpad.getTouchpad().getKnobPercentX()*blockSpeed);
        touchpad.getBlockSprite().setY(touchpad.getBlockSprite().getY() + touchpad.getTouchpad().getKnobPercentY()*blockSpeed);
        game.batch.begin();
        mapGenerator.paintMap(game.batch);
        touchpad.getTouchpad().draw(game.batch,1);
        touchpad.getBlockSprite().draw(game.batch);

        asMove.draw(game.batch,1);

        game.batch.draw(checkCharacterAction(), 50, 50);
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

    }

    private Sprite checkCharacterAction(){
        if(game.stateTime < 5){
            mainCharacter.setAction(mainCharacter.doIdle(game.stateTime));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mainCharacter.setDirection(AvailableActions.LOOK_UP);
            mainCharacter.setAction(mainCharacter.doMove(AvailableActions.MOVE_UP, game.stateTime));
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mainCharacter.setDirection(AvailableActions.LOOK_DOWN);
            mainCharacter.setAction(mainCharacter.doMove(AvailableActions.MOVE_DOWN, game.stateTime));
        }else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            mainCharacter.setDirection(AvailableActions.LOOK_LEFT);
            mainCharacter.setAction(mainCharacter.doMove(AvailableActions.MOVE_LEFT, game.stateTime));
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mainCharacter.setDirection(AvailableActions.LOOK_RIGHT);
            mainCharacter.setAction(mainCharacter.doMove(AvailableActions.MOVE_RIGHT, game.stateTime));
        }else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            mainCharacter.setAction(mainCharacter.doAttack(game.stateTime));
        }else{
            mainCharacter.setAction(mainCharacter.doIdle(game.stateTime));
        }
        return mainCharacter.getAction();
    }
}
