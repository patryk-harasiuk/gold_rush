package edu.io;

import edu.io.token.*;

import java.util.Objects;
import java.util.Random;

public class Board {
    public final int size = 10;
    private final Token[][] grid;
    private PlacementStrategy placementStrategy = new SequentialPlacementStrategy();
    private final Random random = new Random();

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

    public void placeToken(int col, int row, Token token) {
        Objects.requireNonNull(token, "Token cannot be null");

        grid[row][col] = token;
    }


    public void clean() {
        Token emptyToken = new EmptyToken();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = emptyToken;
            }
        }
    }

    public Token peekToken(int col, int row) {
        return grid[row][col];
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

    public void spawnRandomGame(int goldCount, int pickaxeCount, int anvilCount, int sluiceboxCount) {
        spawnGoldTokens(goldCount);
        spawnPickaxeTokens(pickaxeCount);
        spawnAnvilTokens(anvilCount);
        spawnGoldTokens(goldCount);
        spawnSluiceboxTokens(sluiceboxCount);
    }

    private void spawnGoldTokens(int count) {
        for (int index = 0; index < count; index++) {
            Board.Coords pos = getAvailableSquare();
            placeToken(pos.col(), pos.row(), new GoldToken());
        }
    }

    private void spawnPickaxeTokens(int count) {
        for (int index = 0; index < count; index++) {
            Board.Coords pos = getAvailableSquare();
            placeToken(pos.col(), pos.row(), new PickaxeToken());
        }
    }

    private void spawnAnvilTokens(int count) {
        for (int index = 0; index < count; index++) {
            Board.Coords pos = getAvailableSquare();
            placeToken(pos.col(), pos.row(), new AnvilToken());
        }
    }

    private void spawnSluiceboxTokens(int count) {
        for (int index = 0; index < count; index++) {
            Board.Coords pos = getAvailableSquare();
            placeToken(pos.col(), pos.row(), new SluiceboxToken());
        }
    }
}
