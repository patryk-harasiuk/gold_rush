package edu.io.token;

import edu.io.player.VitalsValues;

public class WaterToken extends Token {
    private final int amount;

    public WaterToken() {
        this.amount = VitalsValues.WATER_AMOUNT;
    }

    public WaterToken(int amount) {
        if (amount < 0 || amount > 100) {
            throw new IllegalArgumentException("Ilość wody musi być w zakresie 0-100");
        }

        this.amount = amount;
    }

    public int amount() {
        return amount;
    }

    @Override
    public String label() {
        return Label.WATER_TOKEN_LABEL;
    }
}
