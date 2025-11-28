package edu.io;

import edu.io.token.PlayerToken;

public class Game {
    private final Board board = new Board();


    public Game() {

    }

    public void join(Player player) {
      new PlayerToken(player, board);

    }

    public void start() {
        board.display();
    }
}
