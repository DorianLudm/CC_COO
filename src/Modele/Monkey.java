package Modele;

public class Monkey extends Animal{
    private static int MAX_HUNGER = 3;

    public Monkey(int x, int y) {
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[43m");
        setFontColor("\u001B[30m");
        setRepresentation("M");
    }

    public int getMaxHunger(){return MAX_HUNGER;}
}
