package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

import java.util.Objects;

public class Board {
    public final int size = 5;
    private Token[][] grid;

    public Board() {
        grid = new Token[size][size];
        clean();
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
                System.out.print(peekToken(row, col).label() + " ");
            }
            System.out.println();
        }
    }

    public record Coords(int col, int row) {}

    public Board.Coords getAvailableSquare() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (peekToken(col, row) instanceof EmptyToken) {
                    return new Board.Coords(col, row);
                }
            }
        }
        throw new IllegalStateException("Plansza jest pelna");
    }
}
