package com.mpec.backstab.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class CollisionableObject extends Actor {
    Texture objectTexture;

    Body body;

    Fixture fixture;

    World world;

    public CollisionableObject(World world, float x, float y){
        this.world = world;

        BodyDef def = new BodyDef();
        def.position.set(x, y);
        body = world.createBody(def);
        setX(x);
        setY(y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(objectTexture, getX(), getY());
    }

}
