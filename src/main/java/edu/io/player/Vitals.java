package edu.io.player;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class Vitals {
    private int hydration;

    private Runnable onDeathCallback = () -> {};

    public Vitals() {
        this.hydration = 100;
        this.onDeathCallback = () -> {};
    }

    public int hydration() {
        return this.hydration;
    }

    public boolean isAlive() {
        return hydration > 0;
    }

    public void hydrate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Wartość nawodnienia nie moze byc ujemna");
        }

        this.hydration = Math.min(100, hydration + amount);
    }

    public void dehydrate(int amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Wartość odwodnienia musi być dodatnia.");
        }

        int oldHydration = this.hydration;

        this.hydration = Math.max(0, this.hydration - amount);

        if (oldHydration > 0 && this.hydration == 0) {
            onDeathCallback.run();
        }
    }

    public void setOnDeathHandler(@NotNull Runnable callback) {
        this.onDeathCallback = Objects.requireNonNull(callback, "callback cannot be null");
    }
}
