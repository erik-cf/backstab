package com.mpec.backstab.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonWriter;
import com.mpec.backstab.game.Backstab;

import java.util.ArrayList;

public class MapGenerator {

    public static final int WORLD_WIDTH = 3200;
    public static final int WORLD_HEIGHT = 3200;

    int i;
    int j;

    TextureAtlas mapAtlas;
    Array<AtlasRegion> otree;
    Array<AtlasRegion> ytree;
    Array<AtlasRegion> ground;
    Array<AtlasRegion> water;
    Array<AtlasRegion> hole;

    Array<CollisionableObject> collisionableObjects;
    Array<GroundObject> groundObjects;

    Actor[][] mapActors;

    AtlasRegion[][] groundMap;
    AtlasRegion[][] objectsMap;

    int whereToDrawX;
    int whereToDrawY;
    Rectangle rect;
    public static ArrayList<String> paintedNumbers;
    public static Array<Rectangle> collision = new Array<Rectangle>();

    public MapGenerator(){
        mapAtlas = new TextureAtlas(Gdx.files.internal("Map/tilesetvarios.txt"));
        otree = mapAtlas.findRegions("otree");
        ytree = mapAtlas.findRegions("ytree");
        ground = mapAtlas.findRegions("ground");
        water = mapAtlas.findRegions("water");
        hole = mapAtlas.findRegions("hole");
        mapActors = new Actor[WORLD_HEIGHT / 16][WORLD_WIDTH / 16];
        paintedNumbers = new ArrayList<String>();
    }

    private boolean whatToPaint(int random, int x, int y){
        switch(random){
            case 0:
                return paintWater(x, y);
            case 1:
                return paintHole(x, y);
            case 2:
                return paintYTree(y, x);
            case 3:
                return paintOTree(y, x);
        }
        return false;
    }

    public void createMap(){
        collision.clear();
        paintedNumbers.clear();
        groundMap = new AtlasRegion[WORLD_HEIGHT / 16][WORLD_WIDTH / 16];
        objectsMap = new AtlasRegion[WORLD_HEIGHT / 16][WORLD_WIDTH / 16];
        for(i = (WORLD_HEIGHT / 16) - 1; i >= 0; i--){
            for(j = 0; j < (WORLD_WIDTH / 16); j++){
                if(!paintedNumbers.contains(i + "," + j)) {
                    if(paintObject()){
                        if(!whatToPaint((int)(Math.random() * 4), j, i)){
                            groundMap[i][j] = ground.get((int) (Math.random() * 10));
                        }
                    }else {
                        groundMap[i][j] = ground.get((int) (Math.random() * 10));
                    }
                }
            }
        }
/*
        for(int i = 0; i < (768 / 16); i++) {
            for (int j = 0; j < (1024 / 16); j++) {
                if (groundMap[i][j] == null) {
                    groundMap[i][j] = ground.get((int) (Math.random() * 10));
                }
            }
        }*/
    }

    public void dispose(){
        for(i = (WORLD_HEIGHT / 16) - 1; i >= 0; i--){
            for(j = 0; j < (WORLD_WIDTH / 16); j++){
                if(groundMap[i][j] != null) {
                    groundMap[i][j].getTexture().dispose();
                }else{
                    objectsMap[i][j].getTexture().dispose();
                }
            }
        }
    }

    public AtlasRegion[][] getGroundMap(){
        return groundMap;
    }

    public void paintMap(SpriteBatch batch){

        whereToDrawY = 0;
        whereToDrawX = 0;
        for(i = 0; i < WORLD_HEIGHT / 16; i++){
            for(j = 0; j < (WORLD_WIDTH / 16); j++){
                if(groundMap[i][j] != null) {
                    batch.draw(groundMap[i][j], j * 16, i * 16);
                }else{
//                    rect = new Rectangle();
                    batch.draw(objectsMap[i][j], j*16, i * 16);
//                    rect.setPosition(j * 16 - 32, i*16);
//                    rect.setSize(16, 16);
//                    collision.add(rect);
                }
                whereToDrawX += 16;
            }
            whereToDrawX = 0;
            whereToDrawY += 16;
        }
    }

    private boolean paintObject(){
        int n = (int)(Math.random() * Backstab.collisionProb);
        if(n == 5)
            return true;
        return false;
    }

    private boolean paintWater(int x, int y){
        int randI = (int)((Math.random() * 2) + 3);
        int randJ = (int)((Math.random() * 2) + 3);
        if(checkOverlap(x, y, randJ, randI)){
            return false;
        }
        for(int i = randI - 1; i >= 0; i--){
            for(int j = 0; j < randJ; j++){
                if(i == randI - 1){
                    if(j == 0){
                        objectsMap[y - i][x + j] = water.get(9);
                    }else if(j == (randJ - 1)){
                        objectsMap[y - i][x + j] = water.get(11);
                    }else{
                        objectsMap[y - i][x + j] = water.get(10);
                    }
                }else if(i == (randI - 2)){
                    if(j == 0){
                        objectsMap[y - i][x + j] = water.get(6);
                    }else if(j == (randJ - 1)){
                        objectsMap[y - i][x + j] = water.get(8);
                    }else{
                        objectsMap[y - i][x + j] = water.get(7);
                    }
                }else if(i == 0){
                    if(j == 0){
                        objectsMap[y - i][x + j] = water.get(0);
                    }else if(j == (randJ - 1)){
                        objectsMap[y - i][x + j] = water.get(2);
                    }else{
                        objectsMap[y - i][x + j] = water.get(1);
                    }
                }else{
                    if(j == 0){
                        objectsMap[y - i][x + j] = water.get(3);
                    }else if(j == (randJ - 1)){
                        objectsMap[y - i][x + j] = water.get(5);
                    }else{
                        objectsMap[y - i][x + j] = water.get(4);
                    }
                }
                //realPositions.put(x * 16, y * 16);
                paintedNumbers.add((y - i) + "," + (x + j));
                rect = new Rectangle();
                rect.setSize(16, 16);
                rect.setPosition((x+j) * 16, (y-i) * 16);
                collision.add(rect);
            }
        }
        return true;
    }

    private boolean paintHole(int x, int y){
        int randI = (int)((Math.random() * 2) + 3);
        int randJ = (int)((Math.random() * 2) + 3);

        if(checkOverlap(x, y, randJ, randI)){
            return false;
        }
        for(int i = randI - 1; i >= 0; i--){
            for(int j = 0; j < randJ; j++){
                if(i == randI - 1){
                    if(j == 0){
                        objectsMap[y - i][x + j] = hole.get(9);
                    }else if(j == (randJ - 1)){
                        objectsMap[y - i][x + j] = hole.get(11);
                    }else{
                        objectsMap[y - i][x + j] = hole.get(10);
                    }
                }else if(i == (randI - 2)){
                    if(j == 0){
                        objectsMap[y - i][x + j] = hole.get(6);
                    }else if(j == (randJ - 1)){
                        objectsMap[y - i][x + j] = hole.get(8);
                    }else{
                        objectsMap[y - i][x + j] = hole.get(7);
                    }
                }else if(i == 0){
                    if(j == 0){
                        objectsMap[y - i][x + j] = hole.get(0);
                    }else if(j == (randJ - 1)){
                        objectsMap[y - i][x + j] = hole.get(2);
                    }else{
                        objectsMap[y - i][x + j] = hole.get(1);
                    }
                }else{
                    if(j == 0){
                        objectsMap[y - i][x + j] = hole.get(3);
                    }else if(j == (randJ - 1)){
                        objectsMap[y - i][x + j] = hole.get(5);
                    }else{
                        objectsMap[y - i][x + j] = hole.get(4);
                    }
                }
                paintedNumbers.add((y - i) + "," + (x + j));
                rect = new Rectangle();
                rect.setSize(16, 16);
                rect.setPosition((x-j) * 16, (y-i) * 16);
                collision.add(rect);
            }
        }
        return true;
    }

    private boolean paintOTree(int y, int x){
        int cont = 8;
        if(checkOverlap(x, y, 3, 3)){
            return false;
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(j == 0){
                    objectsMap[y - i][x + j] = otree.get(cont - 2);
                }else if(j == 2){
                    objectsMap[y - i][x + j] = otree.get(cont + 2);
                }else {
                    objectsMap[y - i][x + j] = otree.get(cont);
                }
                cont--;
                paintedNumbers.add((y-i) + "," + (x+j));
                rect = new Rectangle();
                rect.setSize(16, 16);
                rect.setPosition((x-j) * 16, (y-i) * 16);
                collision.add(rect);
            }
        }
        return true;
    }

    private boolean paintYTree(int y, int x){
        int cont = 8;
        if(checkOverlap(x, y, 3, 3)){
            return false;
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(j == 0){
                    objectsMap[y - i][x + j] = ytree.get(cont - 2);
                }else if(j == 2){
                    objectsMap[y - i][x + j] = ytree.get(cont + 2);
                }else {
                    objectsMap[y - i][x + j] = ytree.get(cont);
                }
                cont--;
                paintedNumbers.add((y - i) + "," + (x + j));
                rect = new Rectangle();
                rect.setSize(16, 16);
                rect.setPosition((x-j) * 16, (y-i) * 16);
                collision.add(rect);
            }
        }
        return true;
    }

    private boolean checkOverlap(int x, int y, int width, int height){
        if(paintedNumbers.contains(y + "," + x)){
            return true;
        }else if(paintedNumbers.contains((y - height) + "," + (x + width))){
            return true;
        }else if(paintedNumbers.contains((y) + "," + (x + width))){
            return true;
        }else if(paintedNumbers.contains((y - height) + "," + (x))){
            return true;
        }
        if((x + width) >= groundMap[0].length){
            return true;
        }
        if((y - height) < 0){
            return true;
        }
        return false;
    }
}
