package com.mpec.backstab.game;

public interface AvailableActions {

    String name_gun_up = "gun_up";
    String name_gun_left = "gun_left";
    String name_gun_down = "gun_down";
    String name_gun_right = "gun_right";

    String name_move_up = "move_up";
    String name_move_left = "move_left";
    String name_move_down = "move_down";
    String name_move_right = "move_right";

    String name_idle_up = "idle_up";
    String name_idle_left = "idle_left";
    String name_idle_down = "idle_down";
    String name_idle_right = "idle_right";

    String name_bow_up = "bow_up";
    String name_bow_left = "bow_left";
    String name_bow_down = "bow_down";
    String name_bow_right = "bow_right";

    String name_sword_up = "sword_up";
    String name_sword_left = "sword_left";
    String name_sword_down = "sword_down";
    String name_sword_right = "sword_right";

    String name_die = "die";

    int LOOK_UP = 0x000001f;
    int LOOK_LEFT = 0x000002f;
    int LOOK_DOWN = 0x000003f;
    int LOOK_RIGHT = 0x000004f;

    int MOVE_LEFT = 0x100001f;
    int MOVE_DOWN = 0x1000002f;
    int MOVE_RIGHT = 0x100003f;
    int MOVE_UP = 0x100004f;

    int IDLE_UP = 0x900001f;
    int IDLE_DOWN = 0x900002f;
    int IDLE_LEFT = 0x900003f;
    int IDLE_RIGHT = 0x900004f;




}
