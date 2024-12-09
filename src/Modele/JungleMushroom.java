package Modele;

public class JungleMushroom extends Mushroom{

    public JungleMushroom(int x, int y) {
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[43m");
        setFontColor("\u001B[35m");
        setRepresentation("C");
    }

    @Override
    public void attacked(MapObject[][] map){}
}
