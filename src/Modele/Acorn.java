package Modele;

public class Acorn extends Fruit{

    public Acorn(int x, int y) {
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[41m");
        setFontColor("\u001B[33m");
        setRepresentation("G");
        pickable = true;
    }

    @Override
    public void attacked(){}
}
