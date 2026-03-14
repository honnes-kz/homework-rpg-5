package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        if (hero == null || boss == null || action == null) {
            result.setWinner("No contest");
            result.setRounds(0);
            result.addLine("Battle aborted: missing hero, boss, or action.");
            return result;
        }

        int maxRounds = 10;
        boolean heroStarts = random.nextBoolean();

        result.addLine(String.format(
                "Battle begins: %s vs %s using %s.",
                hero.getName(),
                boss.getName(),
                action.getActionName()
        ));
        result.addLine("Initiative: " + (heroStarts ? hero.getName() : boss.getName()) + " starts.");
        result.addLine("Hero effects: " + action.getEffectSummary());

        int rounds = 0;
        boolean heroTurn = heroStarts;
        while (hero.isAlive() && boss.isAlive() && rounds < maxRounds) {
            rounds++;
            if (heroTurn) {
                int damage = action.getDamage();
                boss.takeDamage(damage);
                result.addLine(String.format(
                        "Round %d: %s hits %s for %d damage. Boss HP: %d.",
                        rounds,
                        hero.getName(),
                        boss.getName(),
                        damage,
                        boss.getHealth()
                ));
                if (!boss.isAlive()) {
                    break;
                }
                int bossDamage = calculateBossDamage(boss.getAttackPower());
                hero.takeDamage(bossDamage);
                result.addLine(String.format(
                        "Round %d: %s strikes back for %d damage. Hero HP: %d.",
                        rounds,
                        boss.getName(),
                        bossDamage,
                        hero.getHealth()
                ));
            } else {
                int bossDamage = calculateBossDamage(boss.getAttackPower());
                hero.takeDamage(bossDamage);
                result.addLine(String.format(
                        "Round %d: %s hits first for %d damage. Hero HP: %d.",
                        rounds,
                        boss.getName(),
                        bossDamage,
                        hero.getHealth()
                ));
                if (!hero.isAlive()) {
                    break;
                }
                int damage = action.getDamage();
                boss.takeDamage(damage);
                result.addLine(String.format(
                        "Round %d: %s retaliates for %d damage. Boss HP: %d.",
                        rounds,
                        hero.getName(),
                        damage,
                        boss.getHealth()
                ));
            }
        }

        result.setRounds(rounds);
        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner("Hero: " + hero.getName());
        } else if (!hero.isAlive() && boss.isAlive()) {
            result.setWinner("Boss: " + boss.getName());
        } else if (hero.getHealth() > boss.getHealth()) {
            result.setWinner("Hero: " + hero.getName());
        } else if (boss.getHealth() > hero.getHealth()) {
            result.setWinner("Boss: " + boss.getName());
        } else {
            result.setWinner("Draw");
        }

        result.addLine(String.format(
                "Battle ends after %d rounds. Hero HP: %d. Boss HP: %d.",
                rounds,
                hero.getHealth(),
                boss.getHealth()
        ));
        result.addLine("Winner: " + result.getWinner());

        return result;
    }

    private int calculateBossDamage(int basePower) {
        int variance = random.nextInt(5) - 2;
        return Math.max(0, basePower + variance);
    }
}
