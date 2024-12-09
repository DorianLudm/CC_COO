package Modele;

public class ForestMushroom extends Mushroom{

    public ForestMushroom(int x, int y) {
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[47m");
        setFontColor("\u001B[35m");
        setRepresentation("C");
    }

    @Override
    public void attacked(MapObject[][] map){}
}
