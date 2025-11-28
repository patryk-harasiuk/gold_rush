package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.Token;
import edu.io.token.PlayerToken;

public class Player {
        private PlayerToken token;
        private double goldAmount = 0.0;

    public Player() {
    }

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken goldToken) {
            gainGold(goldToken.amount());
        }
    }

    public double gold() {
        return goldAmount;
    }

    public void gainGold(double amount) {
        if  (amount < 0) {
         throw new IllegalArgumentException("Kwota musi być dodatnia");
        }
        goldAmount += amount;
    }

    public void loseGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota musi być dodatnia");
        }
        if  (goldAmount < amount) {
            throw new IllegalArgumentException("Niewystarczająca ilość złota");
        }
        goldAmount -= amount;
    }
}
