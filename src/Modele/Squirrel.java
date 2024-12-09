package Modele;

public class Squirrel extends Animal{
    private static int MAX_HUNGER = 5;

    public Squirrel() {
        setBgColor("\u001B[43m");
        setFontColor("\u001B[30m");
        setRepresentation("E");
        this.currentState = new NotHungryState();
        this.current_hunger = MAX_HUNGER;
    }

    public int getMaxHunger(){return MAX_HUNGER;}
}
