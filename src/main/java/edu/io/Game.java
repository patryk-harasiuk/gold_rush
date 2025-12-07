package edu.io;

import edu.io.player.NoTool;
import edu.io.player.Player;
import edu.io.player.Tool;
import edu.io.token.PickaxeToken;
import edu.io.token.PlayerToken;
import edu.io.token.SluiceboxToken;

import java.util.Scanner;

public class Game {
    private final Board board = new Board();
    private Player player;

    public Game() {
       RandomPlacementStrategy randomStrategy = new RandomPlacementStrategy();
       board.setPlacementStrategy(randomStrategy);
       board.spawnRandomGame(8, 1, 1, 1);
    }

    public void join(Player player) {
        this.player = player;
        new PlayerToken(player, board);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Wpisz: left, right, up, down, none lub exit.");
        while (true) {
            Tool tool = player.shed().getTool();

            if (tool instanceof NoTool) {
                System.out.print("Brak narzędzia");
            } else if (tool instanceof PickaxeToken pickaxe) {
                System.out.printf("\u001B[35mKilof %.1fx (trwałość: %d)\u001B[0m",
                        pickaxe.gainFactor(), pickaxe.durability());
            } else if (tool instanceof SluiceboxToken sluicebox) {
                System.out.printf("\u001B[36mRynna %.1fx (trwałość: %d)\u001B[0m",
                        sluicebox.gainFactor(), sluicebox.durability());
            }

            System.out.println("\n");

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
            System.out.println("Zloto: " + player.gold.amount());
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


