package edu.io.token;

public class PyriteToken extends GoldToken {
    public PyriteToken() {
        super(0.0);
    }

    @Override
    public String label() {
        return Label.GOLD_TOKEN_LABEL;
    }
}
