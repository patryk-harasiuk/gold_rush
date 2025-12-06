package edu.io.token;

import edu.io.player.Repairable;
import edu.io.player.Tool;

public class PickaxeToken extends Token implements Tool, Repairable {
    private final double gainFactor;
    private final int initialDurability;
    private int durability;
    private Token targetToken;

    public PickaxeToken(double gainFactor, int durability) {
        if (gainFactor <= 0) {
            throw new IllegalArgumentException("Współczynnik wzmocnienia musi być dodatni");
        }
        if (durability <= 0) {
            throw new IllegalArgumentException("Wytrzymałość musi być nieujemna");
        }
        this.gainFactor = gainFactor;
        this.initialDurability = durability;
        this.durability = durability;
    }

    public PickaxeToken(double gainFactor) {
        this(gainFactor, 3);
    }

    public PickaxeToken() {
      this(1.5, 3);
    }

    public double gainFactor() {
        return this.gainFactor;
    }

    public int durability() {
        return this.durability;
    }

    public void use() {
        if (durability > 0) {
            this.durability--;
        }
    }

    public boolean isBroken() {
        return this.durability == 0;
    }

    public Tool useWith(Token token) {
        this.targetToken = token;
        return this;
    }

    public Tool ifWorking(Runnable action) {
        if (targetToken instanceof GoldToken && !this.isBroken()) {
            this.use();
            action.run();
        }
        return this;
    }

    public Tool ifBroken(Runnable action) {
        if (this.isBroken()) {
            action.run();
        }
        return this;
    }

    public Tool ifIdle(Runnable action) {
        if (!(targetToken instanceof GoldToken) && !isBroken()) {
            action.run();
        }
        return this;
    }

    @Override
    public void repair() {
        this.durability = initialDurability;
    }

    @Override
    public String label() {
        return Label.PICKAXE_TOKEN_LABEL;
    }
}
