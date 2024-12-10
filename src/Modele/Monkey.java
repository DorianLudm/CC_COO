package Modele;

public class Monkey extends Animal{
    private static int MAX_HUNGER = 3;
    private static int MAX_FRIENDSHIP = 2;

    public Monkey(int x, int y) {
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[43m");
        setFontColor("\u001B[30m");
        setRepresentation("M");
        this.currentState = new NotHungryState();
        this.current_hunger = MAX_HUNGER;
        this.current_friendship = 0;
    }

    public int getMaxHunger(){return MAX_HUNGER;}
    public int getMaxFriendship(){return MAX_FRIENDSHIP;}
}
