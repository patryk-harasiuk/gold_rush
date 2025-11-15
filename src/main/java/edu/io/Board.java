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

    }

    public static class Coords {
        private final int col;
        private final int row;

        public Coords(int col, int row) {
            this.col = col;
            this.row = row;
        }

        public int col() {
            return col;
        }

        public int row() {
            return row;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (!(object instanceof Coords)) return false;
            Coords coords = (Coords) object;
            return this.col == coords.col && this.row == coords.row;
        }

        @Override
        public int hashCode() {
            return Objects.hash(col, row);
        }
    }
}
