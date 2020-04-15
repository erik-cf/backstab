package com.mpec.backstab.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;



public class CollisionableObject extends Actor {
    Texture objectTexture;

    Rectangle rectangle;

    public CollisionableObject(float x, float y){
        rectangle = new Rectangle();
        setX(x);
        setY(y);
        rectangle.setSize(objectTexture.getWidth(), objectTexture.getHeight());
        rectangle.setPosition(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(objectTexture, getX(), getY());
    }

}
