package com.mpec.backstab.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mpec.backstab.game.Backstab;

import java.util.ArrayList;

public class MapGenerator implements Screen {

    TextureAtlas mapAtlas;
    Array<AtlasRegion> otree;
    Array<AtlasRegion> ytree;
    Array<AtlasRegion> ground;
    Array<AtlasRegion> water;
    Array<AtlasRegion> hole;

    AtlasRegion[][] map;

    SpriteBatch batch;

    int whereToDrawX;
    int whereToDrawY;

    ArrayList<Integer> paintedNumbers;

    final Backstab game;

    public MapGenerator(Backstab game){
        this.game = game;
        mapAtlas = new TextureAtlas(Gdx.files.internal("Map/tilesetvarios.txt"));
        otree = mapAtlas.findRegions("otree");
        ytree = mapAtlas.findRegions("ytree");
        ground = mapAtlas.findRegions("ground");
        water = mapAtlas.findRegions("water");
        hole = mapAtlas.findRegions("hole");
        batch = new SpriteBatch();
        map = new AtlasRegion[768 / 16][1024 / 16];
        for(int i = 0; i < (768 / 16); i++){
            for(int j = 0; j < (1024 / 16); j++){

                map[i][j] = ground.get((int)(Math.random() * 11));
                //batch.draw(ground.get((int)(Math.random() * 12)), whereToDrawX, whereToDrawY);
                //whereToDrawX += 16;
            }
            //whereToDrawX = 0;
            //whereToDrawY += 16;
        }
        paintedNumbers = new ArrayList<Integer>();
        
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        whereToDrawY = 0;
        whereToDrawY = 0;
        batch.begin();
        for(int i = 0; i < (768 / 16); i++){
            for(int j = 0; j < (1024 / 16); j++){
                batch.draw(map[i][j], whereToDrawX, whereToDrawY);
                whereToDrawX += 16;
            }
            whereToDrawX = 0;
            whereToDrawY += 16;
        }


        batch.end();
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
        batch.dispose();
        mapAtlas.dispose();
    }
}
