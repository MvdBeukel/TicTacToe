import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();
        TicTacToe ticTacToe = new TicTacToe(players);

        new UserInterface(ticTacToe, scan, players);



    }
}