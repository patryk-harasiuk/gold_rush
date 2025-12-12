package edu.io.player;

import edu.io.token.*;

import java.util.Objects;

public class Player {
        private PlayerToken token;
        public Gold gold = new Gold();
        public final Shed shed = new Shed();
        public final Vitals vitals = new Vitals();

    public Player() {
        vitals.setOnDeathHandler(() -> System.out.println("Odwodnienie!"));
    }

    public Shed shed() {
        return shed;
    }

    public void assignToken(PlayerToken token) {
        this.token = Objects.requireNonNull(token, "Token cannot be null");
    }

    public PlayerToken token() {
        return token;
    }

    public void interactWithToken(Token token) {
            Objects.requireNonNull(token, "Token cannot be null");

            if (!vitals.isAlive()) {
                throw new IllegalStateException("Gracz nie żyje");
            }

          switch (token) {
              case GoldToken goldToken -> {
                  vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
                  usePickaxeOnGold(goldToken);
              }
              case PickaxeToken pickaxe -> {
                  shed.add(pickaxe);
              }
              case SluiceboxToken sluicebox -> {
                  shed.add(sluicebox);
              }
              case AnvilToken anvilToken -> {
                  vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);
                  if (shed.getTool() instanceof Repairable tool) {
                      tool.repair();
                  }
              }
              case WaterToken water -> vitals.hydrate(water.amount());
              default -> vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
          }
    }

    private void usePickaxeOnGold(GoldToken goldToken) {
        double amount = goldToken.amount();
        Tool tool = shed.getTool();

        tool.useWith(goldToken)
                .ifWorking(() -> {
                    if (tool instanceof PickaxeToken pickaxe) {
                        gold.gain(amount * pickaxe.gainFactor());
                    }

                    if (tool instanceof SluiceboxToken sluicebox) {
                        sluicebox.use();
                        gold.gain(amount * sluicebox.gainFactor());
                    }
                })
                .ifBroken(() -> {
                    shed.dropTool();
                    gold.gain(amount);
                })
                .ifIdle(() -> {
                    gold.gain(amount);
                });
    }
}
