package Modele;

public class SmallRock extends Decoration{

    public SmallRock(int x, int y) {
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[47m");
        setFontColor("\u001B[30m");
        setRepresentation("X");
    }

    public void attacked(MapObject[][] map){}
}
