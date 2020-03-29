package com.mpec.backstab.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.lang.reflect.Array;

public class GroundObject extends Actor {
    Texture objectTexture;

    World world;

    public GroundObject(World world, float x, float y){
        this.world = world;
        setX(x);
        setY(y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(objectTexture, getX(), getY());
    }

}
