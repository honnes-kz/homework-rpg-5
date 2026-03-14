package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward";
        }
        String winner = battleResult.getWinner();
        if (winner == null || winner.isBlank() || "No contest".equals(winner)) {
            return "No reward";
        }
        if ("Draw".equals(winner)) {
            return "Stalemate: minor supplies";
        }
        if (winner.startsWith("Boss:")) {
            return "Consolation: bandages and travel rations";
        }
        int rounds = battleResult.getRounds();
        if (rounds <= 3) {
            return "Legendary chest";
        }
        if (rounds <= 6) {
            return "Gold chest";
        }
        return "Pouch of coins";
    }
}
