package com.mpec.backstab.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class TouchPadTest extends Touchpad {

    private Skin touchpadSkin;

    public TouchPadTest(){
        super(10f, new TouchpadStyle());
        touchpadSkin = new Skin();

        // Add the drawable to the touchpad skin
        touchpadSkin.add("touchBackground", new Texture("touchpad/analog_base.png"));
        touchpadSkin.add("touchKnob", new Texture("touchpad/analog_button.png"));

        // Add the background and the knob to the touchpad style
        this.getStyle().background = touchpadSkin.getDrawable("touchBackground");
        this.getStyle().knob = touchpadSkin.getDrawable("touchKnob");
    }
}
