package com.mpec.backstab.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

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

    AtlasRegion[][] groundMap;

    int whereToDrawX;
    int whereToDrawY;

    public static ArrayList<String> paintedNumbers;

    public MapGenerator(){
        mapAtlas = new TextureAtlas(Gdx.files.internal("Map/tilesetvarios.txt"));
        otree = mapAtlas.findRegions("otree");
        ytree = mapAtlas.findRegions("ytree");
        ground = mapAtlas.findRegions("ground");
        water = mapAtlas.findRegions("water");
        hole = mapAtlas.findRegions("hole");

        paintedNumbers = new ArrayList<String>();
        groundMap = new AtlasRegion[768 / 16][1024 / 16];

    }

    private void whatToPaint(int random, int x, int y){
        switch(random){
            case 0:
                paintWater(x, y);
            case 1:
                paintHole(x, y);
            case 2:
                paintYTree(y, x);
            case 3:
                paintOTree(y, x);
        }
    }

    public void createMap(){
        for(int i = (768 / 16) - 1; i >= 0; i--){
            for(int j = 0; j < (1024 / 16); j++){
                if(!paintedNumbers.contains(i + "," + j)) {
                    if(paintObject()){
                        //paintWater(j, i);
                        //int n = whatToPaint((int)(Math.random() * 5));
                        whatToPaint((int)(Math.random() * 4), j, i);
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

    public AtlasRegion[][] getGroundMap(){
        return groundMap;
    }

    public void paintMap(SpriteBatch batch){
        whereToDrawY = 0;
        whereToDrawX = 0;
        for(int i = (768 / 16) - 1; i >= 0; i--){
            for(int j = 0; j < (Gdx.graphics.getWidth() / 16); j++){
                batch.draw(groundMap[i][j], whereToDrawX, whereToDrawY);
                whereToDrawX += 16;
            }
            whereToDrawX = 0;
            whereToDrawY += 16;
        }
    }

    private boolean paintObject(){
        int n = (int)(Math.random() * 100);
        if(n == 5)
            return true;
        return false;
    }

    private void paintWater(int x, int y){
        int randI = (int)((Math.random() * 2) + 3);
        int randJ = (int)((Math.random() * 2) + 3);
        if(checkOverlap(x, y, randJ, randI)){
            return;
        }

        for(int i = randI - 1; i >= 0; i--){
            for(int j = 0; j < randJ; j++){
                if(i == randI - 1){
                    if(j == 0){
                        groundMap[y - i][x + j] = water.get(0);
                    }else if(j == (randJ - 1)){
                        groundMap[y - i][x + j] = water.get(2);
                    }else{
                        groundMap[y - i][x + j] = water.get(1);
                    }
                }else if(i == (randI - 2)){
                    if(j == 0){
                        groundMap[y - i][x + j] = water.get(3);
                    }else if(j == (randJ - 1)){
                        groundMap[y - i][x + j] = water.get(5);
                    }else{
                        groundMap[y - i][x + j] = water.get(4);
                    }
                }else if(i == 0){
                    if(j == 0){
                        groundMap[y - i][x + j] = water.get(9);
                    }else if(j == (randJ - 1)){
                        groundMap[y - i][x + j] = water.get(11);
                    }else{
                        groundMap[y - i][x + j] = water.get(10);
                    }
                }else{
                    if(j == 0){
                        groundMap[y - i][x + j] = water.get(6);
                    }else if(j == (randJ - 1)){
                        groundMap[y - i][x + j] = water.get(8);
                    }else{
                        groundMap[y - i][x + j] = water.get(7);
                    }
                }
                paintedNumbers.add((y - i) + "," + (x + j));
            }
        }
    }

    private void paintHole(int x, int y){
        int randI = (int)((Math.random() * 2) + 3);
        int randJ = (int)((Math.random() * 2) + 3);
        if(checkOverlap(x, y, randJ, randI)){
            return;
        }

        for(int i = randI - 1; i >= 0; i--){
            for(int j = 0; j < randJ; j++){
                if(i == randI - 1){
                    if(j == 0){
                        groundMap[y - i][x + j] = hole.get(0);
                    }else if(j == (randJ - 1)){
                        groundMap[y - i][x + j] = hole.get(2);
                    }else{
                        groundMap[y - i][x + j] = hole.get(1);
                    }
                }else if(i == (randI - 2)){
                    if(j == 0){
                        groundMap[y - i][x + j] = hole.get(3);
                    }else if(j == (randJ - 1)){
                        groundMap[y - i][x + j] = hole.get(5);
                    }else{
                        groundMap[y - i][x + j] = hole.get(4);
                    }
                }else if(i == 0){
                    if(j == 0){
                        groundMap[y - i][x + j] = hole.get(9);
                    }else if(j == (randJ - 1)){
                        groundMap[y - i][x + j] = hole.get(11);
                    }else{
                        groundMap[y - i][x + j] = hole.get(10);
                    }
                }else{
                    if(j == 0){
                        groundMap[y - i][x + j] = hole.get(6);
                    }else if(j == (randJ - 1)){
                        groundMap[y - i][x + j] = hole.get(8);
                    }else{
                        groundMap[y - i][x + j] = hole.get(7);
                    }
                }
                paintedNumbers.add((y - i) + "," + (x + j));
            }
        }
    }

    private void paintOTree(int y, int x){
        int cont = 8;
        if(checkOverlap(x, y, 3, 3)){
            return;
        }
        for(int i = 2; i >= 0; i--) {
            for(int j = 0; j < 3; j++) {
                if(j == 0){
                    groundMap[y - i][x + j] = otree.get(cont - 2);
                }else if(j == 2){
                    groundMap[y - i][x + j] = otree.get(cont + 2);
                }else {
                    groundMap[y - i][x + j] = otree.get(cont);
                }
                cont--;
                paintedNumbers.add((y-i) + "," + (x+j));
            }
        }
    }

    private void paintYTree(int y, int x){
        int cont = 8;
        if(checkOverlap(x, y, 3, 3)){
            return;
        }
        for(int i = 2; i >= 0; i--) {
            for(int j = 0; j < 3; j++) {
                if(j == 0){
                    groundMap[y - i][x + j] = ytree.get(cont - 2);
                }else if(j == 2){
                    groundMap[y - i][x + j] = ytree.get(cont + 2);
                }else {
                    groundMap[y - i][x + j] = ytree.get(cont);
                }
                cont--;
                paintedNumbers.add((y - i) + "," + (x + j));
            }
        }
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
