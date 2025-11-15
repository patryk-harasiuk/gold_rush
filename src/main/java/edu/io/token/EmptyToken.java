package edu.io.token;

public class EmptyToken extends Token {
    @Override
    public String label() {
        return Label.EMPTY_TOKEN_LABEL;
    }
}
