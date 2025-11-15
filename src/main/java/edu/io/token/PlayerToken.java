package edu.io.token;

import edu.io.Board;

public class PlayerToken extends Token {
    private Board board;
    private Board.Coords pos;

    public enum Move {
        RIGHT,
        LEFT,
        UP,
        DOWN, NONE
    }

    public PlayerToken(Board board) {
        super();
        this.board = board;

        this.pos = new Board.Coords(0, 0);
        board.placeToken(pos.col(), pos.row(), this);
    }

    public Board.Coords pos() {
        return pos;
    }

    public void move(Move direction) {
        int newCol = pos.col();
        int newRow = pos.row();

        switch (direction) {
            case RIGHT: newCol ++; break;
            case LEFT: newCol --; break;
            case UP: newRow --; break;
            case DOWN: newRow ++; break;
            case NONE: return;
        }

        boolean isOutOfBounds = (newCol < 0 || newCol >= board.size() || newRow < 0 || newRow >= board.size());

        if (isOutOfBounds) {
            throw new IllegalArgumentException("Moved out of bounds");
        }

        board.placeToken(pos.col(), pos.row(), new EmptyToken());
        pos = new Board.Coords(newCol, newRow);

        board.placeToken(newCol, newRow, this);
    }

    @Override
    public String label() {
        return Label.PLAYER_TOKEN_LABEL;
    }
}
