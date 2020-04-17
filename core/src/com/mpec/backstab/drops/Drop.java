package com.mpec.backstab.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mpec.backstab.game.Backstab;
import com.mpec.backstab.main_character.Playable;

public abstract class Drop extends Actor {

    protected long id;

    protected String dropName;
    protected int value;
    protected int duration;
    protected int min_range;
    protected int max_range;

    protected Texture dropTexture;
    protected Rectangle dropRectangle;

    protected long pickedAt;

    protected Playable owner;

    protected boolean active;

    protected final Backstab game;

    public Drop(Backstab game, String dropName, int value, int duration, int min_range, int max_range){
        this.game = game;
        this.dropName = dropName;
        this.value = value;
        this.duration = duration;
        this.min_range = min_range;
        this.max_range = max_range;
        active = false;
    }

    public abstract void initialize();

    public abstract void changeStats();

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(dropTexture != null){
            batch.draw(dropTexture, getX(), getY());
        }
    }

    public String getDropName() {
        return dropName;
    }

    public void setDropName(String name) {
        this.dropName = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMin_range() {
        return min_range;
    }

    public void setMin_range(int min_range) {
        this.min_range = min_range;
    }

    public int getMax_range() {
        return max_range;
    }

    public void setMax_range(int max_range) {
        this.max_range = max_range;
    }

    public Texture getDropTexture() {
        return dropTexture;
    }

    public void setDropTexture(Texture dropTexture) {
        this.dropTexture = dropTexture;
    }

    public Rectangle getDropRectangle() {
        return dropRectangle;
    }

    public void setDropRectangle(Rectangle dropRectangle) {
        this.dropRectangle = dropRectangle;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Playable getOwner(){
        return this.owner;
    }

    public void setOwner(Playable owner){
        this.owner = owner;
    }

    public long getPickedAt() {
        return pickedAt;
    }

    public void setPickedAt(long pickedAt) {
        this.pickedAt = pickedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
