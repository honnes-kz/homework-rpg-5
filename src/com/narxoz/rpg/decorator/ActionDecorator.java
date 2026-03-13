package com.narxoz.rpg.decorator;

public abstract class ActionDecorator implements AttackAction {
    private final AttackAction wrappedAction;

    protected ActionDecorator(AttackAction wrappedAction) {
        this.wrappedAction = wrappedAction;
    }

    protected AttackAction getWrappedAction() {
        return wrappedAction;
    }

    protected String appendEffect(String extraEffect) {
        String base = wrappedAction.getEffectSummary();
        if (base == null || base.isBlank() || "No extra effects".equals(base)) {
            return extraEffect;
        }
        return base + "; " + extraEffect;
    }

    @Override
    public String getActionName() {
        return wrappedAction.getActionName();
    }

    @Override
    public int getDamage() {
        return wrappedAction.getDamage();
    }

    @Override
    public String getEffectSummary() {
        return wrappedAction.getEffectSummary();
    }
}
