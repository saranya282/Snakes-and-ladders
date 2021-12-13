import java.util.Scanner;

public class Display {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("size of the board");
            int sizeofboard = scanner.nextInt();
            System.out.println("toatl players");
            int totplayers = scanner.nextInt();
            System.out.println("totladders");
            int totladders = scanner.nextInt();
            System.out.println("total snakes");
            int totsnakes = scanner.nextInt();
            Game game = new Game(totladders,totsnakes, sizeofboard);
            for (int i = 0; i < totplayers; i++) {
                System.out.println("Playername is");
                String playername = scanner.next();
                Player player = new Player(playername);
                game.addingPlayer(player);
            }
            game.letsplay();
        }
    }
}
