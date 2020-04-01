package com.mpec.backstab.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mpec.backstab.api.ApiTools;
import com.mpec.backstab.enemy_character.Golem;
import com.mpec.backstab.enemy_character.SwordZombie;
import com.mpec.backstab.enemy_character.WizardZombie;

import org.json.JSONArray;
import org.json.JSONObject;

public class Loader implements Screen {

    JSONObject getter;
    JSONArray jsonArray;
    String name;
    String str = "Loading data...";
    boolean loading = false;
    public static boolean finish = false;
    BitmapFont font;

    final Backstab game;
    public Loader(Backstab game) {
        this.game = game;

        font = new BitmapFont();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!loading){
            loading = true;
            try {
                getter = ApiTools.InfoRequest("Enemy");
                jsonArray = getter.getJSONArray("enemy");
                for(int i = 0; i < jsonArray.length(); i++) {
                    getter = jsonArray.getJSONObject(i);
                    name = getter.getString("name");
                    if(name.equalsIgnoreCase("golem")){
                        str = "Loading Golem data";
                        Golem.baseAttack = getter.getDouble("attack");
                        Golem.baseDefense = getter.getDouble("defense");
                        Golem.baseHp = getter.getDouble("hp");
                        Golem.baseMovementSpeed = getter.getDouble("movement_speed");
                        Golem.baseRange = getter.getDouble("range");
                        Golem.baseAttackSpeed = getter.getDouble("attack_speed");
                    }else if(name.equalsIgnoreCase("Zombie-Wizard")){
                        str = "Loading Zombie-Wizard data";
                        WizardZombie.baseAttack = getter.getDouble("attack");
                        WizardZombie.baseDefense = getter.getDouble("defense");
                        WizardZombie.baseHp = getter.getDouble("hp");
                        WizardZombie.baseMovementSpeed = getter.getDouble("movement_speed");
                        WizardZombie.baseRange = getter.getDouble("range");
                        WizardZombie.baseAttackSpeed = getter.getDouble("attack_speed");
                    }else if(name.equalsIgnoreCase("Melee-Zombie")){
                        str = "Loading Melee-Zombie data";
                        SwordZombie.baseAttack = getter.getDouble("attack");
                        SwordZombie.baseDefense = getter.getDouble("defense");
                        SwordZombie.baseHp = getter.getDouble("hp");
                        SwordZombie.baseMovementSpeed = getter.getDouble("movement_speed");
                        SwordZombie.baseRange = getter.getDouble("range");
                        SwordZombie.baseAttackSpeed = getter.getDouble("attack_speed");
                    }
                }
                str = "Loading Timmy data";
                getter = ApiTools.InfoRequest("MainCharacter/Timmy").getJSONArray("mainCharacter").getJSONObject(0);
                game.mainCharacter.setAttack(getter.getDouble("attack"));
                game.mainCharacter.setDefense(getter.getDouble("defense"));
                game.mainCharacter.setHp(getter.getDouble("hp"));
                game.mainCharacter.setMovement_speed(getter.getDouble("movement_speed"));
                game.mainCharacter.setAttack_speed(getter.getDouble("attack_speed"));
                game.mainCharacter.setRange(getter.getDouble("range"));

                finish = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(finish){
            game.setScreen(new GameScreen(game));
        }
        game.batch.begin();
        font.draw(game.batch, "Loading data...", (int)(Gdx.graphics.getWidth() / 2 - str.length() / 2),Gdx.graphics.getHeight() / 2 - 3);
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
}
