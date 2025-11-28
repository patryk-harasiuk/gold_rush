package edu.io;

import edu.io.token.PlayerToken;
import edu.io.token.PlayerToken.Move;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player player = new Player();
        Scanner sc = new Scanner(System.in);


;

        System.out.println("Wpisz: left, right, up, down, none lub exit.");

        game.join(player);
        while (true) {
            game.start();

            System.out.print("Twój ruch: ");
            String input = sc.nextLine().trim().toLowerCase();

            if ("exit".equals(input)) {
                System.out.println("Koniec gry");
                break;
            }


            PlayerToken.Move move;
            switch (input) {
                case "left":  move = Move.LEFT; break;
                case "right":  move = Move.RIGHT; break;
                case "up":  move = Move.UP; break;
                case "down":  move = Move.DOWN; break;
                case "none":  move = Move.NONE; break;
               default: System.out.println("Błędna komenda, spróbuj jeszcze raz"); continue;
            }
            try {
                player.token().move(move);
            } catch (IllegalArgumentException e) {
                System.out.println("Nie mozesz poruszać się dalej w tym kierunku.");
            }
        }
        sc.close();
    }
}
