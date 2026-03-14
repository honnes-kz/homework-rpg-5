package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            return "Preparation failed: missing hero, boss, or action.";
        }
        return String.format(
                "Preparation: %s (HP %d) vs %s (HP %d, ATK %d). Action: %s. Effects: %s.",
                hero.getName(),
                hero.getHealth(),
                boss.getName(),
                boss.getHealth(),
                boss.getAttackPower(),
                action.getActionName(),
                action.getEffectSummary()
        );
    }
}
