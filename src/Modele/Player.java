package Modele;

public class Player extends MapObject{
    private static Player instance;

    private Player(int posX, int posY) {
        setBgColor("\u001B[47m");
        setFontColor("\u001B[35m");
        setRepresentation("@");
        this.posX = posX;
        this.posY = posY;
    }

    public static Player getInstance(int posX, int posY) {
        if (instance == null) {
            instance = new Player(posX, posY);
        }
        return instance;
    }

    public static Player getInstance(){
        return instance;
    }

    public void moveTop(){ posY--; }
    public void moveBottom(){ posY++; }
    public void moveLeft(){ posX--; }
    public void moveRight(){ posX++; }

    @Override
    public void attacked(MapObject[][] map){}
}
