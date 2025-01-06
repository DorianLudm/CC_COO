package Modele;

public class Monkey extends Animal{
    private static int MAX_HUNGER = 3;
    private static int MAX_FRIENDSHIP = 2;
    private static int MAX_FEAR = 3;

    public Monkey(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.junkieMoveRange = 1;
        setBgColor("\u001B[43m");
        setFontColor("\u001B[30m");
        setRepresentation("M");
        this.current_hunger = MAX_HUNGER;
        this.current_friendship = 0;
        this.setEtat(new NotHungryState());
    }

    public int getMaxHunger(){return MAX_HUNGER;}
    public int getMaxFriendship(){return MAX_FRIENDSHIP;}
    public int getMaxFear(){return MAX_FEAR;}
}
