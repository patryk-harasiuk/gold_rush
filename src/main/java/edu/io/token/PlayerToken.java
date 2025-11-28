package edu.io.token;

import edu.io.Board;
import edu.io.Player;

public class PlayerToken extends Token {
    private final Player player;
    private final Board board;
    private Board.Coords pos;

    public enum Move {
        RIGHT,
        LEFT,
        UP,
        DOWN, NONE
    }

    public PlayerToken(Player player, Board board) {
        super();
        this.player = player;
        this.board = board;

        this.pos = board.getAvailableSquare();
        board.placeToken(pos.col(), pos.row(), this);
        player.assignToken(this);
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

        if (newCol < 0 || newCol >= board.size() || newRow < 0 || newRow >= board.size()) {
            throw new IllegalArgumentException("Ruch poza planszę");
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
