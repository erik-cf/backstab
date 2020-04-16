package com.mpec.backstab.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Drop extends Actor {

    protected String name;
    protected int value;
    protected int duration;
    protected int min_range;
    protected int max_range;

    protected Texture dropTexture;
    protected Rectangle dropRectangle;

    protected boolean active;

    public Drop(String name, int value, int duration, int min_range, int max_range){
        this.name = name;
        this.value = value;
        this.duration = duration;
        this.min_range = min_range;
        this.max_range = max_range;
        active = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
}
