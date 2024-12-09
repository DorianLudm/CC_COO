package Modele;

public class SmallRock extends Decoration{

    public SmallRock() {
        setBgColor("\u001B[47m");
        setFontColor("\u001B[30m");
        setRepresentation("X");
    }

    public void attacked(MapObject[][] map){}
}
