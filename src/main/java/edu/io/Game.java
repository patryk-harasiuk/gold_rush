package edu.io;

import edu.io.token.PlayerToken;

import java.util.Scanner;

public class Game {
    private final Board board = new Board();
    private Player player;

    public Game() {
        board.spawnGoldTokens(5);
    }

    public void join(Player player) {
        this.player = player;
      new PlayerToken(player, board);

    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Wpisz: left, right, up, down, none lub exit.");
        while (true) {
            board.display();

            System.out.print("Twój ruch: ");
            String input = sc.nextLine().trim().toLowerCase();

            if ("exit".equals(input)) {
                System.out.println("Koniec gry");
                break;
            }

            processMove(input);

        }
    }

    private void processMove(String move) {
        PlayerToken.Move parsedMove = parseMove(move);
        if (parsedMove == null) {
            System.out.println("Nieznana komenda. Uzyj: left,  right, up, down, none lub exit.");
            return;
        }

        try {
            player.token().move(parsedMove);
            System.out.println("Zloto: " + player.gold());
        } catch (IllegalArgumentException e) {
            System.out.println("Nie mozesz poruszać się dalej w tym kierunku.");
        }

    }

    private PlayerToken.Move parseMove(String input) {
        return switch (input) {
            case "up" -> PlayerToken.Move.UP;
            case "down" -> PlayerToken.Move.DOWN;
            case "left" -> PlayerToken.Move.LEFT;
            case "right" -> PlayerToken.Move.RIGHT;
            case "none" -> PlayerToken.Move.NONE;
            default -> null;
            };
        }
    }


