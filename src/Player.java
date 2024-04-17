public class Player {
    private String name;
    private static int nextId = 1;
    private int id;
    private int wins;
    public Player(String name){
        this.id = nextId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void clearScore(){
        wins = 0;
    }
    public String getName(){
        return name;
    }
    public int getWins(){
        return wins;
    }

    public void addWin(){
        wins++;
    }

    public String toString(){
        return name + " has won " + wins + " games!";
    }
}
