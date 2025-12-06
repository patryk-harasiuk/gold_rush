package edu.io.player;

import edu.io.token.*;

public class Player {
        private PlayerToken token;
        public Gold gold = new Gold();
        public final Shed shed = new Shed();

    public Player() {}

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public void interactWithToken(Token token) {
          switch (token) {
              case GoldToken goldToken -> usePickaxeOnGold(goldToken);
              case PickaxeToken pickaxe -> {
                  shed.add(pickaxe);
              }
              case SluiceboxToken sluicebox -> {
                  shed.add(sluicebox);
              }
              case AnvilToken anvilToken -> {
                  if (shed.getTool() instanceof Repairable tool) {
                      tool.repair();
                  }
              }
               default -> {}
          }
    }

    private void usePickaxeOnGold(GoldToken goldToken) {
        double amount = goldToken.amount();
        Tool tool = shed.getTool();

        tool.useWith(goldToken)
                .ifWorking(() -> {
                    if (tool instanceof PickaxeToken pickaxe) {
                        pickaxe.use();
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
