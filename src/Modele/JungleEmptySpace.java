package Modele;

public class JungleEmptySpace extends EmptySpace{

    public JungleEmptySpace(int x, int y) {
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[43m");
        setFontColor("");
        setRepresentation(" ");
        reachable = true;
    }

    @Override
    public void attacked(MapObject[][] map){}
}
