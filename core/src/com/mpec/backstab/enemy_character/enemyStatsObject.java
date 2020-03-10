package com.mpec.backstab.enemy_character;

public class enemyStatsObject {




        private double attack;
        private double defense;
        private double attack_speed;
        private double hp;
        private double movement_speed;



        public enemyStatsObject(double attack, double defense, double attack_speed, double hp, double movement_speed) {
            this.attack = attack;
            this.defense = defense;
            this.attack_speed = attack_speed;
            this.hp = hp;
            this.movement_speed = movement_speed;
        }

        public double getAttack() {
            return attack;
        }

        public void setAttack(double attack) {
            this.attack = attack;
        }

        public double getDefense() {
            return defense;
        }

        public void setDefense(double defense) {
            this.defense = defense;
        }

        public double getAttack_speed() {
            return attack_speed;
        }

        public void setAttack_speed(double attack_speed) {
            this.attack_speed = attack_speed;
        }

        public double getHp() {
            return hp;
        }

        public void setHp(double hp) {
            this.hp = hp;
        }

        public double getMovement_speed() {
            return movement_speed;
        }

        public void setMovement_speed(double movement_speed) {
            this.movement_speed = movement_speed;
        }


}
