package Modele;

public class Banana extends Fruit{

    public Banana(int x, int y){
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[40m");
        setFontColor("\u001B[33m");
        setRepresentation("B");
        pickable = true;
    }

    @Override
    public void attacked(MapObject[][] map){}
}
