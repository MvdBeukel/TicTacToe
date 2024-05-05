import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private TicTacToe ticTacToe;
    private Scanner scan;
    private ArrayList<Player> players;
    private int gameType;

    public UserInterface(TicTacToe ticTacToe, Scanner scan, ArrayList<Player> players) {
        this.ticTacToe = ticTacToe;
        this.scan = scan;
        this.players = players;
        start();
    }
    public void start(){
        System.out.println("Let's play a game of Tic Tac Toe!");
        while (true) {

            System.out.println("===COMMANDS===");
            System.out.println("1. One Player game\n2. Two Player game\n3. Quit");
            String input = scan.nextLine();


            switch (Integer.parseInt(input)) {
                case 1:
                    gameType = 1;
                    soloGame();
                    break;
                case 2:
                    gameType = 2;
                    duoGame();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public void soloGame(){
        while (true) {
            System.out.println("You have chosen to play vs the computer!");
            System.out.print("Name player one? ");

            String playerOne = scan.nextLine();

            if (playerOne.equalsIgnoreCase("quit")) {
                break;
            }

            ticTacToe.addPlayers(new Player(playerOne), new Player("COMPUTER"));
            gameRules();
            playGameOnePlayer();
        }

    }

    public void playGameOnePlayer(){
        ticTacToe.makeBoard();
        ticTacToe.printBoard();
        String mark;
        System.out.println(ticTacToe.getCurrentPlayer() + " begins.");
        while(true) {
            System.out.println(ticTacToe.getCurrentPlayer() + ", where do you want to place your mark?");
            //System.out.println("Turn: " + ticTacToe.getTurn());
            if (ticTacToe.getCurrentPlayer().equals("COMPUTER")){
                mark = String.valueOf(calculateComputerMove());
                //System.out.println(mark);
            } else {
                mark = scan.nextLine();
                if (mark.equalsIgnoreCase("quit")) {
                    return;
                }
            }
            try {
                ticTacToe.fillGrid(Integer.parseInt(mark));
            } catch (Exception e) {
                System.out.println("Invalid option. Please try again.");

            }
            //if game won or draw let user decide to play another game or quit
            if (ticTacToe.checkWin() || ticTacToe.checkDraw()){
                playMore();
                return;
            }
        }
    }


    public int calculateComputerMove(){
        return ticTacToe.calculateComputerMove();
    }
    public void duoGame() {
        System.out.println("You have chosen to play with each other!");
        System.out.print("Name player one? ");

        String playerOne = scan.nextLine();
        if (playerOne.equalsIgnoreCase("quit")) {
            return;
        }
        System.out.print("Name player two? ");
        String playerTwo = scan.nextLine();
        ticTacToe.addPlayers(new Player(playerOne), new Player(playerTwo));
        gameRules();
        playGameTwoPlayer();
    }

    public void gameRules(){
        System.out.println("Welcome " + ticTacToe.getPlayerNameInt(0) + " & " + ticTacToe.getPlayerNameInt(1) + ".");
        System.out.println("     |     |\n" +
                "  1  |  2  |  3\n" +
                "_____|_____|_____\n" +
                "     |     |\n" +
                "  4  |  5  |  6\n" +
                "_____|_____|_____\n" +
                "     |     |\n" +
                "  7  |  8  |  9\n" +
                "     |     |\n");

        System.out.println("Choose a number to place your X or O.\nFirst player with 3 consecutive marks wins! ");
        System.out.print("Press enter to continue...");
        scan.nextLine();
    }

    public void playGameTwoPlayer(){
        ticTacToe.makeBoard();
        ticTacToe.printBoard();
        System.out.println(ticTacToe.getCurrentPlayer() + " begins.");
        while(true) {
            System.out.println(ticTacToe.getCurrentPlayer() + ", where do you want to place your mark?");
            String mark = scan.nextLine();
            if (mark.equalsIgnoreCase("quit")) {
                return;
            }
            try {
                ticTacToe.fillGrid(Integer.parseInt(mark));
            } catch (Exception e) {
                System.out.println("Invalid option. Please try again.");

            }
            //if game won or draw let user decide to play another game or quit
            if (ticTacToe.checkWin() || ticTacToe.checkDraw()){
                playMore();
                return;
            }
        }
    }

    public void playMore(){
        System.out.println("What do you want to do now?");
        while(true) {
            System.out.println("1. Play a one player game\n2. Play a two player game\n3. Quit");
            System.out.println("4. Show scores\n5. Clear scores");
            String input = scan.nextLine();
            try {
                switch (Integer.parseInt(input)) {
                    case 1:
                        if (gameType == 2) {
                            gameType = 1;
                            soloGame();
                        } else {
                            playGameOnePlayer();
                        }
                        break;
                    case 2:
                        if (gameType == 1) {
                            gameType = 2;
                            duoGame();
                        } else {
                            playGameTwoPlayer();
                        }
                        break;
                    case 3:
                        System.exit(0);
                    case 4:
                        printScores();
                        break;
                    case 5:
                        clearScore();
                        break;
                }
            } catch  (NumberFormatException eNumForExc) {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void printScores(){
        for (Player players : players){
            System.out.println(players);
        }
        System.out.println();
    }

    public void clearScore(){
        for (Player players : players){
            players.clearScore();
        }
    }
}
