import edu.io.Board;
import edu.io.RandomPlacementStrategy;
import edu.io.SequentialPlacementStrategy;
import edu.io.token.EmptyToken;
import edu.io.token.GoldToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlacementStrategyTest {
    Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    void default_strategy_returns_first_empty_square() {
        Board.Coords pos = board.getAvailableSquare();
        Assertions.assertEquals(0, pos.col());
        Assertions.assertEquals(0, pos.row());
        Assertions.assertInstanceOf(EmptyToken.class, board.peekToken(0, 0));
    }

    @Test
    void sequential_strategy_throws_when_board_full() {
        fillBoard();
        Assertions.assertThrows(IllegalStateException.class, () -> board.getAvailableSquare());
    }

    @Test
    void random_strategy_throws_when_board_full() {
        board.setPlacementStrategy(new RandomPlacementStrategy());
        fillBoard();
        Assertions.assertThrows(IllegalStateException.class, () -> board.getAvailableSquare());
    }

    @Test
    void sequential_strategy_finds_empty_squares() {
        board.setPlacementStrategy(new SequentialPlacementStrategy());

        board.placeToken(0, 0, new GoldToken());

        Board.Coords pos = board.getAvailableSquare();
        Assertions.assertEquals(1, pos.col());
        Assertions.assertInstanceOf(EmptyToken.class, board.peekToken(1, 0));
    }

    @Test
    void random_strategy_finds_empty_squares() {
        board.setPlacementStrategy(new RandomPlacementStrategy());
        Board.Coords pos = board.getAvailableSquare();
        Assertions.assertInstanceOf(EmptyToken.class, board.peekToken(pos.col(), pos.row()));
    }


    private void fillBoard() {
        int size = board.size();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board.placeToken(col, row, new GoldToken());
            }
        }
    }
}
