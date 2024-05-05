import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class TicTacToe {
    private int[] gridSpots = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private final int[] rowStart = {0, 0, 0, 6, 6, 6, 12, 12, 12};
    private final int[] colStart = {0, 9, 18, 0, 9, 18, 0, 9, 18};
    private final String[][] myBoard = new String[17][26];
    private boolean playerTurn;
    private ArrayList<Player> players;
    //private int turn = 1;
    private final int[] cornerOptions = {1, 3, 7, 9};

    private final int[][] winningConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Columns
            {0, 4, 8}, {2, 4, 6}             // Diagonals
    };
    public TicTacToe(ArrayList<Player> players){
        this.players = players;
    }

    public void addPlayers(Player playerOne, Player playerTwo){
        players.clear();
        players.add(playerOne);
        players.add(playerTwo);
        setRandomStartPlayer();
    }

    public void setRandomStartPlayer(){
        Random rand = new Random();
        int randInt = rand.nextInt(2);
        // true = player two
        // false = player one
        playerTurn = randInt != 0;
    }

    public String getPlayerNameInt(int player) {
        return players.get(player).getName();
    }

    public int calculateComputerMove() {
        
        int winOptionOne = checkWinOptions(2);
        int winOptionTwo = checkWinOptions(1);
        if (winOptionOne >= 1){ return winOptionOne; } // check if COMP can win first

        if (winOptionTwo >= 1){ return winOptionTwo; } // if COMP can't win, check to prevent opponent from winning

        int[] gridSpotsCenter = {0, 0, 0, 0, 1, 0, 0, 0, 0}; // if player starts in center
        if (Arrays.equals(gridSpots, gridSpotsCenter)){
            //System.out.println("return random spots 1, 3, 7, 9");
            return pickRandomOpenSpot(cornerOptions); // pick 1, 3, 7 or 9 or else computer loses
        }
        if (gridSpots[4] == 0){ // if player does not start in center, then computer takes center
            return 5;
        }
            return pickRandomOpenSpot();
    }

    public void resetGrid(){
        gridSpots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
    }
    public int pickRandomOpenSpot(){
        ArrayList<Integer> tempArrayList = new ArrayList<>();
        Random rand = new Random();
        for (int g = 0; g < gridSpots.length; g++) {
            if (gridSpots[g] == 0) {
                tempArrayList.add(g);
            }
        }
        return tempArrayList.get(rand.nextInt(tempArrayList.size()))+1;
    }
    public int pickRandomOpenSpot(int[] options){
        Random rand = new Random();
        return options[rand.nextInt(options.length)];
    }

    public void toggleTurn(){
        playerTurn = !playerTurn;
    }
    public void makeBoard() {
        for (int col = 0; col < myBoard[1].length; col++) {
            for (int row = 0; row < myBoard.length; row++)
                if (col == 8 || col == 17) {
                    myBoard[row][col] = "|";
                } else {
                    if (row == 5 || row == 11) {
                        myBoard[row][col] = "*";
                    } else {
                        myBoard[row][col] = " ";
                    }
                }
        }

        myBoard[1][2] = "1";
        myBoard[1][11] = "2";
        myBoard[1][20] = "3";
        myBoard[7][2] = "4";
        myBoard[7][11] = "5";
        myBoard[7][20] = "6";
        myBoard[13][2] = "7";
        myBoard[13][11] = "8";
        myBoard[13][20] = "9";

    }

    public void printBoard(){
        for (String[] strings : myBoard) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println(); // Print a newline after each row
        }
        System.out.println();
    }

    public String getCurrentPlayer(){
        return playerTurn ? players.get(0).getName() : players.get(1).getName();
    }
    public void fillGrid(int gridPosition){
        String oMark = "O";
        String xMark = "X";

        gridPosition--; // correct position due to index starting at 0
        if (gridSpots[gridPosition] > 0){
            System.out.println("That spot is not allowed!\nPlease try again.");
            return;
        }

        if (playerTurn) {
            gridSpots[gridPosition] = 1; // secure position
        } else {
            gridSpots[gridPosition] = 2; // secure position
        }

        int row = rowStart[gridPosition];
        int col = colStart[gridPosition];

        //System.out.println(row + "," + col);

        if (playerTurn) {
            // X icon
            myBoard[row][col] = xMark;
            myBoard[row][col + 1] = xMark;
            myBoard[row][col + 6] = xMark;
            myBoard[row][col + 7] = xMark;
            myBoard[row + 1][col + 1] = xMark;
            myBoard[row + 1][col + 2] = xMark;
            myBoard[row + 1][col + 5] = xMark;
            myBoard[row + 1][col + 6] = xMark;
            myBoard[row + 2][col + 2] = xMark;
            myBoard[row + 2][col + 3] = xMark;
            myBoard[row + 2][col + 4] = xMark;
            myBoard[row + 2][col + 5] = xMark;
            myBoard[row + 3][col + 1] = xMark;
            myBoard[row + 3][col + 2] = xMark;
            myBoard[row + 3][col + 5] = xMark;
            myBoard[row + 3][col + 6] = xMark;
            myBoard[row + 4][col]     = xMark;
            myBoard[row + 4][col + 1] = xMark;
            myBoard[row + 4][col + 6] = xMark;
            myBoard[row + 4][col + 7] = xMark;
        } else {
            //O Icon
            myBoard[row][col + 2] = oMark;
            myBoard[row][col + 3] = oMark;
            myBoard[row][col + 4] = oMark;
            myBoard[row][col + 5] = oMark;
            myBoard[row + 1][col + 1] = oMark;
            myBoard[row + 1][col + 2] = oMark;
            myBoard[row + 1][col + 5] = oMark;
            myBoard[row + 1][col + 6] = oMark;
            myBoard[row + 2][col]     = oMark;
            myBoard[row + 2][col + 1] = oMark;
            myBoard[row + 2][col + 6] = oMark;
            myBoard[row + 2][col + 7] = oMark;
            myBoard[row + 3][col + 1] = oMark;
            myBoard[row + 3][col + 2] = oMark;
            myBoard[row + 3][col + 5] = oMark;
            myBoard[row + 3][col + 6] = oMark;
            myBoard[row + 4][col + 2] = oMark;
            myBoard[row + 4][col + 3] = oMark;
            myBoard[row + 4][col + 4] = oMark;
            myBoard[row + 4][col + 5] = oMark;
        }
        printBoard();
        toggleTurn();
    }


    public boolean checkWin() {
        for (int[] condition : winningConditions) {
            if (gridSpots[condition[0]] == 1 &&
                    gridSpots[condition[1]] == 1 &&
                    gridSpots[condition[2]] == 1) {
                resetGrid();
                System.out.println(getPlayerNameInt(0)+ " has won the game!");
                players.get(0).addWin();
                return true;
            }
            if (gridSpots[condition[0]] == 2 &&
                    gridSpots[condition[1]] == 2 &&
                    gridSpots[condition[2]] == 2) {
                resetGrid();
                System.out.println(getPlayerNameInt(1) + " has won the game!");
                players.get(1).addWin();
                return true;
            }
        }
        return false;
    }

    public int checkWinOptions(int player) {
        int[] array = Arrays.copyOf(gridSpots, gridSpots.length);
        for (int intPosition = 0; intPosition < array.length; intPosition++) {
            if (array[intPosition] == 0) {
                array[intPosition] = player;
                for (int[] condition : winningConditions) {
                    if (array[condition[0]] == player &&
                            array[condition[1]] == player &&
                            array[condition[2]] == player) {
                        //System.out.println("Winning option is " + (intPosition + 1));
                        return intPosition+1;
                    }
                }
                array[intPosition] = 0;
            }
        }
        return -1;
    }

    public boolean checkDraw(){
        for (int gridSpot : gridSpots) {
            if (gridSpot == 0){
                return false;
            }
        }
        System.out.println("It's a tie!");
        resetGrid();
        return true;
    }
}