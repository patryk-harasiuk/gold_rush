package edu.io;

import edu.io.token.EmptyToken;

import java.util.Random;

public class RandomPlacementStrategy implements PlacementStrategy {
    private final Random random = new Random();

    @Override
    public Board.Coords getAvailableSquare(Board board) {
        int size = board.size();
        int maxAttempts = size * size;

        while (maxAttempts > 0) {
            maxAttempts--;

            int col = random.nextInt(size);
            int row = random.nextInt(size);

            if (board.peekToken(col, row) instanceof EmptyToken) {
                return new Board.Coords(col, row);
            }
        }
        throw new IllegalStateException("Plansza jest pelna");
    }
}
