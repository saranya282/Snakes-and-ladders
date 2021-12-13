import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.*;



public class Game {
    private int nofsnakes;
    private int nofladders;

    private Queue<Player> players;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    private Board board;
    private Dice dice;

    public Game(int nofladders, int nofsnakes ,
                int boardsize) {
        this.nofladders = nofladders;
        this.nofsnakes = nofsnakes;
        this.players = new ArrayDeque<>();
        board = new Board(boardsize);
        dice = new Dice();
        ladders = new ArrayList<>(nofladders);
        snakes = new ArrayList<>(nofsnakes);
        initializeboard();
    }

    private void initializeboard() {
        Set<String> set1 = new HashSet<>();
        for (int i = 0; i < nofladders; i++) {
            while (true) {
                Random rand = new Random();
                int ladderfirst = rand.nextInt(board.getStart(), board.getsize());
                int ladderlast = rand.nextInt(board.getStart(), board.getsize());
                if (ladderfirst<= ladderlast)
                    continue;
                String firstlastpair = String.valueOf(ladderfirst) + ladderlast;
                if (!set1.contains(firstlastpair)) {
                    Ladder ladder = new Ladder(ladderfirst, ladderlast);
                    ladders.add(ladder);
                    set1.add(firstlastpair);
                    break;
                }
            }
        }
        for (int i = 0; i < nofsnakes; i++) {
            while (true) {
                Random rand = new Random();
                int snakefirst = rand.nextInt(board.getStart(), board.getsize());
                int snakelast = rand.nextInt(board.getStart(), board.getsize());
                if (snakelast <= snakefirst)
                    continue;
                String firstLastPair = String.valueOf(snakefirst) + snakelast;
                if (!set1.contains(firstLastPair)) {
                    Snake snake = new Snake(snakefirst, snakelast);
                    snakes.add(snake);
                    set1.add(firstLastPair);
                    break;
                }
            }
        }
    }
    
    public void addingPlayer(Player players) {
        this.players.add(players);
    }

    public void letsplay() {
        while (true) {
            Player player = players.poll();
            int value = dice.roll();
            
            int changedposition = player.getpositionofplayer() + value;
            if (board.getEnd() < changedposition ) {
                player.setpositionofplayer(player.getpositionofplayer());
                players.offer(player);
            } else {
                player.setpositionofplayer(getNewPosition(changedposition));
                if (player.getpositionofplayer() == board.getEnd()) {
                    player.setpositionofplayer(0);
                    System.out.println(player.getName() + " Won.");
                } else {
                    System.out.println(player.getName()  + "  new position  " + player.getpositionofplayer());
                    players.offer(player);
                }
            }
            if (players.size() < 2) {
                break;
            }
        }
    }

    private int getNewPosition(int changedposition) {
        for (Snake snake : snakes) {
            if (snake.getStart() == changedposition) {
                System.out.println("Bitten by snake");
                return snake.getEnd();
            }
        }
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == changedposition) {
                System.out.println("climbed ladder");
                return ladder.getEnd();
            }
        }
        return changedposition;
    }
    
    
}
