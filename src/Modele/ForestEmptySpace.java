package Modele;

public class ForestEmptySpace extends EmptySpace{

    public ForestEmptySpace(int x, int y) {
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[42m");
        setFontColor("");
        setRepresentation(" ");
        reachable = true;
    }

    @Override
    public void attacked(MapObject[][] map){}
}
