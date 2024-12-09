package Modele;

public class Banana extends Fruit{

    public Banana() {
        setBgColor("\u001B[40m");
        setFontColor("\u001B[33m");
        setRepresentation("B");
    }

    @Override
    public void attacked(MapObject[][] map){}
}
