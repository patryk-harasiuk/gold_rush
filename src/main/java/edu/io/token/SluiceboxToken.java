package edu.io.token;

import edu.io.player.Tool;

public class SluiceboxToken extends Token implements Tool {
    private double currentGainFactor;
    private final double initialGainFactor = 1.2;
    private int durability;
    private final int initialDurability = 5;

    private Token targetToken;

    public SluiceboxToken() {
        this.currentGainFactor = initialGainFactor;
        this.durability = initialDurability;
    }

    public double gainFactor() {
        return this.currentGainFactor;
    }

    @Override
    public boolean isBroken() {
        return this.durability == 0;
    }


    public void use() {
        if (!isBroken()) {
            this.durability--;
            double DEGRADATION_RATE = 0.04;
            this.currentGainFactor = Math.max(1.0, currentGainFactor - DEGRADATION_RATE);
        }
    }

    @Override
    public Tool useWith(Token token) {
        this.targetToken = token;
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action) {
        if (targetToken instanceof GoldToken && !this.isBroken()) {
            this.use();
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifBroken(Runnable action) {
        if (this.isBroken()) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action) {
        if (!(targetToken instanceof GoldToken) && !isBroken()) {
            action.run();
        }
        return this;
    }

    public int durability() {
        return this.durability;
    }

    @Override
    public String label() {
        return Label.SLUICEBOX_TOKEN_LABEL;

    }
}
