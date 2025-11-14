package edu.io;

public class Board {
    public final int size = 5;
    private Token[][] grid;

    public Board() {
        grid = new Token[size][size];
        clean();
    }

    public Token square(int x, int y) {
        return grid[x][y];
    }

    public void placeToken(int x, int y, Token token) {
        grid[x][y] = token;
    }

    public void clean() {
        Token empty = new Token("・");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = empty;
            }
        }
    }

    public void display() {

    }
}
