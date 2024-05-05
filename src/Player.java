public class Player {
    private String name;
    private int wins;
    public Player(String name){
        this.name = name;
    }

    public void clearScore(){
        wins = 0;
    }
    public String getName(){
        return name;
    }

    public void addWin(){
        wins++;
    }

    public String toString(){
        return name + " has won " + wins + " games!";
    }
}
