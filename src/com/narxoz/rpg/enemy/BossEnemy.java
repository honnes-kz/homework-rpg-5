package com.narxoz.rpg.enemy;

public class BossEnemy {
    private String name;
    private int health;
    private int attackPower;

    public BossEnemy(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void takeDamage(int amount) {
        if (amount <= 0) {
            return;
        }
        health = Math.max(0, health - amount);
    }

    public boolean isAlive() {
        return health > 0;
    }
}
