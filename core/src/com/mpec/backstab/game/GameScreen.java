package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
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
    Rectangle player;

    boolean canMoveUp = true;
    boolean canMoveDown = true;
    boolean canMoveLeft = true;
    boolean canMoveRight = true;

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
        player = new Rectangle();
        player.setX(mainCharacter.getAction().getX());
        player.setY(mainCharacter.getAction().getY());
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


        player.setX((float) (player.getX() + touchpad.getTouchpad().getKnobPercentX() * mainCharacter.getMovement_speed()));
        player.setY((float) (player.getY() + touchpad.getTouchpad().getKnobPercentY() * mainCharacter.getMovement_speed()));
        checkMovement();
        game.batch.begin();
        mapGenerator.paintMap(game.batch);
        game.batch.draw(playerSprite, player.x, player.y);
        //playerSprite.draw(game.batch);
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

    private void checkMovement(){
        for(Rectangle r : MapGenerator.collision){
            if(player.overlaps(r)){
                switch(mainCharacter.getDirection()){
                    case AvailableActions.LOOK_LEFT:
                        player.setX(player.getX() + 5);
                        break;
                    case AvailableActions.LOOK_RIGHT:
                        player.setX(player.getX() - 5);
                        break;
                    case AvailableActions.LOOK_UP:
                        player.setY(player.getY() - 5);
                        break;
                    case AvailableActions.LOOK_DOWN:
                        player.setY(player.getY() + 5);
                        break;
                }
            }
        }

    }


}
