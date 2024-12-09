package Modele;

public class Player extends MapObject{
    public Player(int posX, int posY) {
        setBgColor("\u001B[47m");
        setFontColor("\u001B[35m");
        setRepresentation("@");
        this.posX = posX;
        this.posY = posY;
    }

    public void moveTop(){ posY--; }
    public void moveBottom(){ posY++; }
    public void moveLeft(){ posX--; }
    public void moveRight(){ posX++; }

    @Override
    public void attacked(MapObject[][] map){}
}
