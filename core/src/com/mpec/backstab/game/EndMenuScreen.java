package com.mpec.backstab.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class EndMenuScreen implements Screen {

    final Backstab game;
    TextButton endButton;
    TextButton.TextButtonStyle endButtonStyle;
    Skin skin;
    Texture endGameMenu;
    TextureAtlas buttonAtlas;
    BitmapFont font;
    BitmapFont gameTimeFont;
    BitmapFont totalKillsFont;
    BitmapFont totalObjectsFont;
    Stage stage;
    Music introMusic;
    int totalTiempo;

    public EndMenuScreen(Backstab game,int totalTiempo){
        stage = new Stage();
        introMusic= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Menu/menuIntro.wav"));
        Gdx.input.setInputProcessor(stage);
        this.game = game;
        endGameMenu= new Texture(Gdx.files.internal("Menu/endMenu.png"));
        buttonAtlas = new TextureAtlas(Gdx.files.internal("Buttons/end-button.txt"));
        skin = new Skin();
        skin.addRegions(buttonAtlas);
        font = new BitmapFont();
        gameTimeFont = new BitmapFont();
        gameTimeFont.setColor(0,0,0,1);
        totalKillsFont = new BitmapFont();
        totalKillsFont.setColor(0,0,0,1);
        totalObjectsFont = new BitmapFont();
        totalObjectsFont.setColor(0,0,0,1);
        endButtonStyle = new TextButton.TextButtonStyle();
        endButtonStyle.font = font;
        endButtonStyle.down = skin.getDrawable("touched");
        endButtonStyle.up = skin.getDrawable("untouched");
        endButton = new TextButton("", endButtonStyle);
        endButton.setBackground(skin.getDrawable("untouched"));
        endButton.setX(Gdx.graphics.getWidth() / 2 - endButton.getWidth() / 2);
        endButton.setY(100f);
        endButton.setWidth(510f);
        endButton.setHeight(78f);
        introMusic.play();
        this.totalTiempo=totalTiempo;


        stage.addActor(endButton);
        listeners();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.stateTime = game.stateTime + 1 + Gdx.graphics.getDeltaTime();

        game.timmy.goIdle();

        game.batch.begin();
        game.mapGenerator.paintMap(game.batch);
       game.batch.draw(endGameMenu, Gdx.graphics.getWidth() / 2 - endGameMenu.getWidth() / 2, Gdx.graphics.getHeight() / 2 - endGameMenu.getHeight() / 2);
      endButton.draw(game.batch, 1);
        gameTimeFont.draw(game.batch,"Tiempo de partida: "+totalTiempo,(Gdx.graphics.getWidth() / 2 - endGameMenu.getWidth() / 2)+100, (Gdx.graphics.getHeight() / 2 - endGameMenu.getHeight() / 2)+250);
        totalKillsFont.draw(game.batch,"Enemigos Matados: Over 9000",(Gdx.graphics.getWidth() / 2 - endGameMenu.getWidth() / 2)+100, (Gdx.graphics.getHeight() / 2 - endGameMenu.getHeight() / 2)+225);
        totalObjectsFont.draw(game.batch,"Objetos recogidos: 1",(Gdx.graphics.getWidth() / 2 - endGameMenu.getWidth() / 2)+100, (Gdx.graphics.getHeight() / 2 - endGameMenu.getHeight() / 2)+200);
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
        game.mapGenerator.dispose();
        game.timmy.getAction().getTexture().dispose();
        game.timmy.getWalkPlayer().dispose();
        game.timmy.getPlayerAtlas().dispose();
        game.timmy.getWalkPlayer().dispose();
    }

    private void listeners(){
        endButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    introMusic.stop();
                    game.setScreen(new MainMenuScreen(game));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //game.setScreen(new GameScreen(game));
            }
        });
    }
}

