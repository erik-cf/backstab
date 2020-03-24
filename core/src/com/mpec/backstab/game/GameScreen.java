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

public class GameScreen implements Screen {

    final Backstab game;
    Stage stage;
    Sprite playerSprite;
    TouchPadTest touchpad;
    Golem golem;

    public GameScreen(Backstab game){
        this.game = game;
        touchpad=new TouchPadTest();
        golem= new Golem(game);
        stage = new Stage();
        stage.addActor(touchpad.getTouchpad());
        Gdx.input.setInputProcessor(stage);
        golem.getGolemSprite().setX(100);
        golem.getGolemSprite().setY(100);

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


        game.mainCharacterRectangle.setX((float) (game.mainCharacterRectangle.getX() + touchpad.getTouchpad().getKnobPercentX() * game.mainCharacter.getMovement_speed()));
        game.mainCharacterRectangle.setY((float) (game.mainCharacterRectangle.getY() + touchpad.getTouchpad().getKnobPercentY() * game.mainCharacter.getMovement_speed()));
        checkMovement();
        golem.followPlayer((int)game.mainCharacterRectangle.getX(),(int)game.mainCharacterRectangle.getY());
        game.batch.begin();
        game.mapGenerator.paintMap(game.batch);
        game.batch.draw(golem.getGolemSprite(),golem.getGolemSprite().getX(),golem.getGolemSprite().getY());
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


}
