package Modele;

public class Acorn extends Fruit{

    public Acorn() {
        setBgColor("\u001B[41m");
        setFontColor("\u001B[33m");
        setRepresentation("G");
    }

    @Override
    public void attacked(MapObject[][] map){}
}
