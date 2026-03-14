package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        HeroProfile hero = new HeroProfile("Ayla Stormblade", 120);
        BossEnemy boss = new BossEnemy("Obsidian Golem", 140, 14);

        AttackAction basic = new BasicAttack("Sword Slash", 12);
        AttackAction fireOnly = new FireRuneDecorator(basic);
        AttackAction poisonOnly = new PoisonCoatingDecorator(basic);
        AttackAction criticalOnly = new CriticalFocusDecorator(basic);
        AttackAction fireThenPoison = new FireRuneDecorator(new PoisonCoatingDecorator(basic));
        AttackAction poisonThenFire = new PoisonCoatingDecorator(new FireRuneDecorator(basic));
        AttackAction fullStack = new CriticalFocusDecorator(new FireRuneDecorator(new PoisonCoatingDecorator(basic)));

        System.out.println("--- Decorator Preview ---");
        printActionDetails("Base", basic);
        printActionDetails("Fire Only", fireOnly);
        printActionDetails("Poison Only", poisonOnly);
        printActionDetails("Critical Only", criticalOnly);
        printActionDetails("Fire -> Poison", fireThenPoison);
        printActionDetails("Poison -> Fire", poisonThenFire);
        printActionDetails("Full Stack", fullStack);

        System.out.println("\n--- Facade Preview ---");
        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, fullStack);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        System.out.println("--- Battle Log ---");
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }

    private static void printActionDetails(String label, AttackAction action) {
        System.out.println(label + ": " + action.getActionName());
        System.out.println("Damage: " + action.getDamage());
        System.out.println("Effects: " + action.getEffectSummary());
        System.out.println();
    }
}
