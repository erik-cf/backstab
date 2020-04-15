package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import com.mpec.backstab.enemy_character.Enemy;
import com.mpec.backstab.enemy_character.Golem;
import com.mpec.backstab.enemy_character.SwordZombie;
import com.mpec.backstab.enemy_character.WizardZombie;
import com.mpec.backstab.main_character.OtherPlayer;
import com.mpec.backstab.map.MapGenerator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.Date;
import java.util.HashMap;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class GameScreen implements Screen {

    private final float UPDATE_TIME = 1/60f;

    float timer;

    String id;

    final Backstab game;
    Stage stage;
    TouchPadTest touchpad;
    Array<Enemy> enemyAL;
    Date startDate= new Date();
    boolean enemyToBeCreated =false;
    Date endDate;
    int numSeconds;

    private Socket socket;

    JSONObject updater;

    HashMap<String, OtherPlayer> otherPlayers;


    float movingX;
    float movingY;


    public GameScreen(Backstab game){
        this.game = game;
        stage = new Stage(game.viewport, game.batch);
        Gdx.input.setInputProcessor(stage);
        otherPlayers = new HashMap<String, OtherPlayer>();

        touchpad=new TouchPadTest();
        stage.addActor(game.timmy);
        stage.addActor(touchpad);
        enemyAL = new Array<Enemy>();
        Gdx.app.postRunnable(new Runnable(){

            @Override
            public void run() {
                connectSocket();
                configSocketEvents();
            }
        });

    }

    public void updateServer(float delta){
        timer += delta;
        if(timer >= UPDATE_TIME && game.timmy.hasMoved()){
            System.out.println("Entra a actalizar server!");
            updater = new JSONObject();
            try{
                updater.put("x", game.timmy.getX());
                updater.put("y", game.timmy.getY());
                socket.emit("playerMoved", updater);
            }catch(JSONException e){
                Gdx.app.log("SOCKETIO", "Error sending data to server! in updateServer()");
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        updateServer(Gdx.graphics.getDeltaTime());
        game.stateTime = game.stateTime + 1 + Gdx.graphics.getDeltaTime();
        checkCharacterAction();
        touchpad.setBounds(game.camera.position.x - touchpad.getWidth() / 2, game.camera.position.y - stage.getHeight() / 2 + 15, 150, 150);
        endDate= new Date();
        numSeconds = (int)((endDate.getTime() - startDate.getTime()) / 1000);
        movingX = (float)(game.timmy.getX() + touchpad.getKnobPercentX() * game.timmy.getMovement_speed());
        movingY = (float) (game.timmy.getY() + touchpad.getKnobPercentY() * game.timmy.getMovement_speed());

        game.moveCamera();

        Gdx.app.log("HashMapSize", String.valueOf(otherPlayers.size()));


        stage.act();

        if(game.timmy.getX() < 0){
            movingX = 0;
        }
        if(game.timmy.getX() + game.timmy.getAction().getWidth() > MapGenerator.WORLD_WIDTH){
            movingX = MapGenerator.WORLD_WIDTH - game.timmy.getAction().getWidth();
        }

        if(game.timmy.getY() < 0){
            movingY = 0;
        }

        if(game.timmy.getY() + game.timmy.getAction().getHeight() > MapGenerator.WORLD_HEIGHT){
            movingY = MapGenerator.WORLD_HEIGHT - game.timmy.getAction().getHeight();
        }
        game.timmy.setX(movingX);
        game.timmy.setY(movingY);
        game.batch.begin();
        game.mapGenerator.paintMap(game.batch);
        game.batch.end();



        try {
            whichEnemy((int)(Math.random()*3));
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkMovement();

        if(game.timmy.getVidaActual()<=0){
            game.setScreen(new EndMenuScreen(game,numSeconds));
        }

        stage.draw();

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
        for(Enemy enemy : enemyAL){
            enemy.getSlashEnemy().dispose();
            enemy.getHealthRedBar().dispose();
            enemy.getEnemySprite().getTexture().dispose();
            enemy.getEnemyAtlas().dispose();
        }
        game.mapGenerator.dispose();
        game.timmy.getAction().getTexture().dispose();
        game.timmy.getWalkPlayer().dispose();
        game.timmy.getPlayerAtlas().dispose();
        game.timmy.getWalkPlayer().dispose();
        stage.dispose();
    }

    private void checkCharacterAction(){
        if(game.stateTime < 5){
            game.timmy.goIdle();
        }
        if(touchpad.isTouched()){
            if(touchpad.getKnobPercentX() > 0.4){

                game.timmy.setDirection(AvailableActions.LOOK_RIGHT);
                game.timmy.goMove(AvailableActions.MOVE_RIGHT);

            }else if(touchpad.getKnobPercentX() < -0.4){

                game.timmy.setDirection(AvailableActions.LOOK_LEFT);
                game.timmy.goMove(AvailableActions.MOVE_LEFT);

            }else if(touchpad.getKnobPercentY() > 0){

                game.timmy.setDirection(AvailableActions.LOOK_UP);
                game.timmy.goMove(AvailableActions.MOVE_UP);

            }else if(touchpad.getKnobPercentY() < 0){

                game.timmy.setDirection(AvailableActions.LOOK_DOWN);
                game.timmy.goMove(AvailableActions.MOVE_DOWN);

            }else{
                game.timmy.goIdle();
            }
        }else{
            game.timmy.goIdle();
        }
    }

    private void checkMovement(){
        for(Rectangle r : MapGenerator.collision){
            if(game.timmy.getPlayableRectangle().overlaps(r)){
                switch(game.timmy.getDirection()){
                    case AvailableActions.LOOK_LEFT:
                        game.timmy.setX(game.timmy.getPlayableRectangle().getX() + 1);
                        break;
                    case AvailableActions.LOOK_RIGHT:
                        game.timmy.setX(game.timmy.getPlayableRectangle().getX() - 1);
                        break;
                    case AvailableActions.LOOK_UP:
                        game.timmy.setY(game.timmy.getPlayableRectangle().getY() - 1);
                        break;
                    case AvailableActions.LOOK_DOWN:
                        game.timmy.setY(game.timmy.getPlayableRectangle().getY() + 1);
                        break;
                }
            }
        }

    }


    private void createEnemy(int whichEnemy){
        if(numSeconds%3!=0){
            enemyToBeCreated =true;
        }
        else if(numSeconds%3==0 && enemyToBeCreated ==true){
            Enemy enemy = null;
            switch(whichEnemy){
                case AvailableActions.CREATE_GOLEM:
                    enemy = new Golem(game, Golem.baseAttack * game.multiplier,Golem.baseDefense * game.multiplier, Golem.baseAttackSpeed * game.multiplier, Golem.baseHp * game.multiplier, Golem.baseMovementSpeed * game.multiplier, Golem.baseRange * game.multiplier);
                    enemyAL.add(enemy);
                    break;
                case AvailableActions.CREATE_SWORD_ZOMBIE:
                    enemy = new SwordZombie(game, SwordZombie.baseAttack * game.multiplier,SwordZombie.baseDefense * game.multiplier, SwordZombie.baseAttackSpeed * game.multiplier, SwordZombie.baseHp * game.multiplier, SwordZombie.baseMovementSpeed * game.multiplier, SwordZombie.baseRange * game.multiplier);
                    enemyAL.add(enemy);
                    break;
                case AvailableActions.CREATE_WIZARD_ZOMBIE:
                    enemy = new WizardZombie(game, WizardZombie.baseAttack * game.multiplier,WizardZombie.baseDefense * game.multiplier, WizardZombie.baseAttackSpeed * game.multiplier, WizardZombie.baseHp * game.multiplier, WizardZombie.baseMovementSpeed * game.multiplier, WizardZombie.baseRange * game.multiplier);
                    enemyAL.add(enemy);
                    break;
            }
            stage.addActor(enemy);
            MapGenerator.collision.add(enemy.getEnemyRectangle());
            enemyToBeCreated = false;
        }
    }

    private void whichEnemy(int rdm) throws Exception {
        switch(rdm){
            case 0:
                createEnemy(AvailableActions.CREATE_GOLEM);
                break;
            case 1:
                createEnemy(AvailableActions.CREATE_WIZARD_ZOMBIE);
                break;
            case 2:
                createEnemy(AvailableActions.CREATE_SWORD_ZOMBIE);
                break;
            default:
                throw new Exception("Error! Number out of range (0-2)!");
        }
    }

        public void connectSocket(){
            try {
                socket = IO.socket("http://localhost:3000/?x=" + game.timmy.getX() + "&y=" + game.timmy.getY());
                socket.connect();
            } catch(Exception e){
                System.out.println(e);
            }
        }

    public void configSocketEvents(){
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                Gdx.app.log("SocketIO", "Connected");
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {

                    id = data.getString("id");
                    game.timmy.setId(id);
                    Gdx.app.log("SocketIO", "My ID: " + id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting ID");
                }
            }
        }).on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerId = data.getString("id");
                    Gdx.app.log("SocketIO", "New Player Connect: " + playerId);
                    OtherPlayer otherPlayer = new OtherPlayer(game, game.timmy.getAttack(), game.timmy.getDefense(), game.timmy.getAttack_speed(), game.timmy.getHp(), game.timmy.getMovement_speed());
                    otherPlayer.setId(playerId);
                    //double attack, double defense, double attack_speed, double hp, double movement_speed
                    stage.addActor(otherPlayer);
                    otherPlayers.put(playerId, otherPlayer);
                }catch(JSONException e){
                    Gdx.app.log("SocketIO", "Error getting New PlayerID");
                }
            }
        }).on("playerDisconnected", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerId = data.getString("id");
                    stage.getActors().removeValue(otherPlayers.get(playerId), true);
                    otherPlayers.remove(playerId);

                }catch(JSONException e){
                    Gdx.app.log("SocketIO", "Error getting disconnected PlayerID");
                }
            }
        }).on("playerMoved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerId = data.getString("id");
                    Double x = data.getDouble("x");
                    Double y = data.getDouble("y");
                    if(otherPlayers.get(playerId) != null) {
                        System.out.println("Ha entrado aqui!!!!!");
                        otherPlayers.get(playerId).setPosition(x.floatValue(), y.floatValue());
                    }
                }catch(JSONException e){
                    Gdx.app.log("SocketIO", "Error getting a player position.");
                }
            }
        }).on("getPlayers", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray objects = (JSONArray) args[0];

                try {
                    for(int i = 0; i < objects.length(); i++){
                        OtherPlayer coopPlayer = new OtherPlayer(game, game.timmy.getAttack(), game.timmy.getDefense(), game.timmy.getAttack_speed(), game.timmy.getHp(), game.timmy.getMovement_speed());
                        coopPlayer.setId(objects.getJSONObject(i).getString("id"));
                        Vector2 position = new Vector2();
                        position.x = ((Double) objects.getJSONObject(i).getDouble("x")).floatValue();
                        position.y = ((Double) objects.getJSONObject(i).getDouble("y")).floatValue();
                        coopPlayer.setPosition(position.x, position.y);
                        stage.addActor(coopPlayer);
                        otherPlayers.put(coopPlayer.getId(), coopPlayer);
                    }
                } catch(JSONException e){
                    Gdx.app.log("ERROR_ARRAY", "Error getting getPlayers Array... Error: " + e.getMessage());
                }
            }
        });
    }
}
