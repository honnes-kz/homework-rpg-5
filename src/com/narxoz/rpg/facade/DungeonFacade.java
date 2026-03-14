package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class DungeonFacade {
    private PreparationService preparationService = new PreparationService();
    private BattleService battleService = new BattleService();
    private RewardService rewardService = new RewardService();

    public DungeonFacade setRandomSeed(long seed) {
        battleService.setRandomSeed(seed);
        return this;
    }

    public AdventureResult runAdventure(HeroProfile hero, BossEnemy boss, AttackAction action) {
        String preparationSummary = preparationService.prepare(hero, boss, action);
        if (hero == null || boss == null || action == null) {
            AdventureResult result = new AdventureResult();
            result.setWinner("No contest");
            result.setRounds(0);
            result.addLine(preparationSummary);
            result.setReward("No reward");
            return result;
        }

        AdventureResult result = battleService.battle(hero, boss, action);
        result.addLineAtStart(preparationSummary);
        result.setReward(rewardService.determineReward(result));
        return result;
    }
}
