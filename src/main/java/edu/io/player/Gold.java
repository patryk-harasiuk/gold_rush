package edu.io.player;

public class Gold {
    private double amount;

    public Gold() {
        this.amount = 0.0;
    }

    public Gold(double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Kwota musi być dodatnia");
        }

        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public void gain(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota musi być dodatnia");
        }
        this.amount += amount;
    }

    public void lose(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Kwota musi być dodatnia");
        }
        if (this.amount < amount) {
            throw new IllegalArgumentException("Niewystarczająca ilość złota");
        }
        this.amount -= amount;
    }
}
