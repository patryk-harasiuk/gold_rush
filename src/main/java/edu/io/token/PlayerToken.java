package edu.io.token;

import edu.io.Board;
import edu.io.player.Player;

import java.util.Objects;

public class  PlayerToken extends Token {
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
        this.player = Objects.requireNonNull(player, "Player cannot be null");
        this.board = Objects.requireNonNull(board, "Board cannot be null");

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
            case RIGHT -> newCol ++;
            case LEFT -> newCol --;
            case UP -> newRow --;
            case DOWN -> newRow ++;
            case NONE -> {
                return;
            }
        }

        if (newCol < 0 || newCol >= board.size() || newRow < 0 || newRow >= board.size()) {
            throw new IllegalArgumentException("Ruch poza planszę");
        }

        Token tokenOnNewPos = board.peekToken(newCol, newRow);

        board.placeToken(pos.col(), pos.row(), new EmptyToken());


        if (!(tokenOnNewPos instanceof EmptyToken)) {
            player.interactWithToken(tokenOnNewPos);
        }
        pos = new Board.Coords(newCol, newRow);
        board.placeToken(newCol, newRow, this);
    }

    @Override
    public String label() {
        return Label.PLAYER_TOKEN_LABEL;
    }
}
