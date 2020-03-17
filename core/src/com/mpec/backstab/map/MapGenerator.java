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

public class MapGenerator {

    public static final int OTREE = 0x00001f;
    public static final int YTREE = 0x00002f;
    public static final int WATER = 0x00003f;
    public static final int HOLE = 0x00004f;
    public static final int ROCK = 0x00005f;

    TextureAtlas mapAtlas;
    Array<AtlasRegion> otree;
    Array<AtlasRegion> ytree;
    Array<AtlasRegion> ground;
    Array<AtlasRegion> water;
    Array<AtlasRegion> hole;
    Array<AtlasRegion> rock;

    AtlasRegion[][] map;

    int whereToDrawX;
    int whereToDrawY;

    ArrayList<String> paintedNumbers;

    public MapGenerator(){
        mapAtlas = new TextureAtlas(Gdx.files.internal("Map/tilesetvarios.txt"));
        otree = mapAtlas.findRegions("otree");
        ytree = mapAtlas.findRegions("ytree");
        ground = mapAtlas.findRegions("ground");
        water = mapAtlas.findRegions("water");
        hole = mapAtlas.findRegions("hole");

        paintedNumbers = new ArrayList<String>();
        map = new AtlasRegion[768 / 16][1024 / 16];

    }

    private int whatToPaint(int random){
        switch(random){
            case 0:
                return WATER;
            case 1:
                return HOLE;
            case 2:
                return YTREE;
            case 3:
                return OTREE;
            case 4:
                return ROCK;

        }
        return 0;
    }

    public void createMap(){
        for(int i = 0; i < (768 / 16); i++){
            for(int j = 0; j < (1024 / 16); j++){
                //if(!paintedNumbers.contains(i + "," + j)) {
                    /*if(paintObject() && j < ((1024/16) - 5) && i < ((768/16) - 5)){
                        paintWater(j, i);
                        //int n = whatToPaint((int)(Math.random() * 5));
                    }else {*/
                map[i][j] = ground.get((int) (Math.random() * 10));
                //}
            }
            //batch.draw(ground.get((int)(Math.random() * 12)), whereToDrawX, whereToDrawY);
            // whereToDrawX += 16;
        }
        //whereToDrawX = 0;
        // whereToDrawY += 16;
        //}

        /*for(int i = 0; i < (768 / 16); i++) {
            for (int j = 0; j < (1024 / 16); j++) {
                if (map[i][j] == null) {
                    map[i][j] = ground.get((int) (Math.random() * 11));
                }
            }
        }*/
    }

    public AtlasRegion[][] getMap(){
        return map;
    }

    public void paintMap(SpriteBatch batch){
        whereToDrawY = 0;
        whereToDrawX = 0;
        for(int i = 0; i < (768 / 16); i++){
            for(int j = 0; j < (1024 / 16); j++){
                batch.draw(map[i][j], whereToDrawX, whereToDrawY);
                whereToDrawX += 16;
            }
            whereToDrawX = 0;
            whereToDrawY += 16;
        }
    }

    private boolean paintObject(){
        int n = (int)(Math.random() * 1000);
        if(n == 5)
            return true;
        return false;
    }

    private void paintWater(int x, int y){
        int randI = (int)((Math.random() * 2) + 3);
        int randJ = (int)((Math.random() * 2) + 3);
        for(int i = 0; i < randI; i++){
            for(int j = 0; j < randJ; j++){
                if(i == 0){

                    if(j == 0){
                        map[y - i][x - j] = water.get(0);
                    }else if(j == (randJ - 1)){
                        map[y - i][x - j] = water.get(2);
                    }else{
                        map[y - i][x - j] = water.get(1);
                    }
                }else if(i == 1){
                    if(j == 0){
                        map[y - i][x - j] = water.get(3);
                    }else if(j == (randJ - 1)){
                        map[y - i][x - j] = water.get(5);
                    }else{
                        map[y - i][x - j] = water.get(4);
                    }
                }else if(i == randI - 1){
                    if(j == 0){
                        map[y - i][x - j] = water.get(9);
                    }else if(j == (randJ - 1)){
                        map[y - i][x - j] = water.get(11);
                    }else{
                        map[y - i][x - j] = water.get(10);
                    }
                }else{
                    if(j == 0){
                        map[y - i][x - j] = water.get(6);
                    }else if(j == (randJ - 1)){
                        map[y - i][x - j] = water.get(8);
                    }else{
                        map[y - i][x - j] = water.get(7);
                    }
                }
                paintedNumbers.add((y - i) + "," + (x - j));
            }
        }
    }

    private void paintOTree(int y, int x){
        for(int i = 0; i < 3; i++) {
            if(i == 0){
                map[y][x] = otree.get(0);
            }
        }
    }
}
