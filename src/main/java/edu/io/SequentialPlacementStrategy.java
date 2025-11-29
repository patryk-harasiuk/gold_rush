package edu.io;

import edu.io.token.EmptyToken;

public class SequentialPlacementStrategy implements PlacementStrategy {
    @Override
    public Board.Coords getAvailableSquare(Board board) {
        int size = board.size();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.peekToken(col, row) instanceof EmptyToken) {
                    return new Board.Coords(col, row);
                }
            }
        }
        throw new IllegalStateException("Plansza jest pelna");
    }
}
