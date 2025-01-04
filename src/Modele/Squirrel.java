package Modele;

public class Squirrel extends Animal{
    private static int MAX_HUNGER = 5;
    private static int MAX_FRIENDSHIP = 1;
    private static int MAX_FEAR = 3;

    public Squirrel(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.junkieMoveRange = 2;
        setBgColor("\u001B[43m");
        setFontColor("\u001B[30m");
        setRepresentation("E");
        this.current_hunger = MAX_HUNGER;
        this.current_friendship = 0;
        this.setEtat(new NotHungryState());
    }

    public int getMaxHunger(){return MAX_HUNGER;}
    public int getMaxFriendship(){return MAX_FRIENDSHIP;}
    public int getMaxFear(){return MAX_FEAR;}
}
