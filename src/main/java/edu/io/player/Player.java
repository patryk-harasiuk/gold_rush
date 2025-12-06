package edu.io.player;

import edu.io.token.*;

public class Player {
        private PlayerToken token;
        public Gold gold = new Gold();
        private final Shed shed = new Shed();

    public Player() {
    }

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
              case AnvilToken anvilToken -> {
                  Tool tool = shed.getTool();

                  if (tool instanceof Repairable repairable) {
                      repairable.repair();
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
                        double gain = pickaxe.gainFactor();
                        gold.gain(amount * gain);

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
