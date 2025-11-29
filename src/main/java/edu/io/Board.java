package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.GoldToken;
import edu.io.token.Token;

public class Board {
    public final int size = 10;
    private final Token[][] grid;
    private PlacementStrategy placementStrategy = new SequentialPlacementStrategy();

    public void setPlacementStrategy(PlacementStrategy strategy) {
        this.placementStrategy = strategy;
    }

    public Board() {
        this.grid = new Token[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new EmptyToken();
            }
        }
    }

    public int size() {
        return size;
    }

    public Token square(int x, int y) {
        return grid[x][y];
    }

    public void placeToken(int x, int y, Token token) {
        grid[x][y] = token;
    }


    public void clean() {
        Token emptyToken = new EmptyToken();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = emptyToken;
            }
        }
    }

    public Token peekToken(int x, int y) {
        return grid[x][y];
    }

    public void display() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(peekToken(col, row).label() + " ");
            }
            System.out.println();
        }
    }

    public record Coords(int col, int row) {}

    public Board.Coords getAvailableSquare() {
        return placementStrategy.getAvailableSquare(this);
    }

    public void spawnGoldTokens(int count) {
        for (int goldIndex = 0; goldIndex < size; goldIndex++) {
            Board.Coords pos = getAvailableSquare();
            placeToken(pos.col(), pos.row(), new GoldToken());
        }
    }
}
