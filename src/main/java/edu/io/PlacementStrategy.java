package edu.io;

public interface PlacementStrategy {
    Board.Coords getAvailableSquare(Board board);
}
