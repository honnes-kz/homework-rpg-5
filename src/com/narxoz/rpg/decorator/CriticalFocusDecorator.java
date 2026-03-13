package com.narxoz.rpg.decorator;

public class CriticalFocusDecorator extends ActionDecorator {
    public CriticalFocusDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Critical Focus";
    }

    @Override
    public int getDamage() {
        int base = super.getDamage();
        return (int) Math.round(base * 1.5);
    }

    @Override
    public String getEffectSummary() {
        return appendEffect("Critical focus (x1.5 damage)");
    }
}
