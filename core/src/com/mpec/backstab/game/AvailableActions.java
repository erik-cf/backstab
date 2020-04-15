package com.mpec.backstab.game;

public interface AvailableActions {

    /**
     *
     */
    String name_gun_up = "gun_up";
    String name_gun_left = "gun_left";
    String name_gun_down = "gun_down";
    String name_gun_right = "gun_right";

    String largeball = "bigball";
    String mediumball = "mediumball";
    String littleball = "littleball";

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

    String name_attack_up = "attack_up";
    String name_attack_left = "attack_left";
    String name_attack_right = "attack_right";
    String name_attack_down = "attack_down";

    String name_sword_up = "sword_up";
    String name_sword_left = "sword_left";
    String name_sword_down = "sword_down";
    String name_sword_right = "sword_right";

    String name_die = "die";

    String name_die_up_mgzomb = "die_up_mgzomb";
    String name_die_left_mgzomb = "die_left_mgzomb";
    String name_die_down_mgzomb = "die_down_mgzomb";
    String name_die_right_mgzomb = "die_right_mgzomb";

    String name_die_up = "die_up";
    String name_die_left = "die_left";
    String name_die_down = "die_down";
    String name_die_right = "die_right";

    String name_move_up_mgzomb = "move_up_mgzomb";
    String name_move_left_mgzomb = "move_left_mgzomb";
    String name_move_down_mgzomb = "move_down_mgzomb";
    String name_move_right_mgzomb = "move_right_mgzomb";

    String name_move_up_swzomb = "move_up_swzomb";
    String name_move_left_swzomb = "move_left_swzomb";
    String name_move_down_swzomb = "move_down_swzomb";
    String name_move_right_swzomb = "move_right_swzomb";

    String name_attack_up_mgzomb = "attack_up_mgzomb";
    String name_attack_left_mgzomb = "attack_left_mgzomb";
    String name_attack_down_mgzomb = "attack_down_mgzomb";
    String name_attack_right_mgzomb = "attack_right_mgzomb";

    String name_attack_up_swzomb = "attack_up_swzomb";
    String name_attack_left_swzomb = "attack_left_swzomb";
    String name_attack_down_swzomb = "attack_down_swzomb";
    String name_attack_right_swzomb = "attack_right_swzomb";

    String name_Run="Run";
    String name_Walk="Walk";
    String name_Idle="Idle";
    String name_Attack="Attack";
    String name_Dead="Dead";

    int LOOK_UP = 0x000001f;
    int LOOK_LEFT = 0x000002f;
    int LOOK_DOWN = 0x000003f;
    int LOOK_RIGHT = 0x000004f;

    int MOVE_LEFT = 0x100001f;
    int MOVE_DOWN = 0x1000002f;
    int MOVE_RIGHT = 0x100003f;
    int MOVE_UP = 0x100004f;
    int ENERGY_BALL = 0x100005f;

    int CREATE_GOLEM = 0x200001f;
    int CREATE_SWORD_ZOMBIE = 0x200002f;
    int CREATE_WIZARD_ZOMBIE = 0x200003f;

    int IDLE_UP = 0x900001f;
    int IDLE_DOWN = 0x900002f;
    int IDLE_LEFT = 0x900003f;
    int IDLE_RIGHT = 0x900004f;
}
