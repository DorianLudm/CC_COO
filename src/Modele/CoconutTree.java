package Modele;

public class CoconutTree extends Tree{

    public CoconutTree() {
        setBgColor("\u001B[40m");
        setFontColor("\u001B[33m");
        setRepresentation("G");
    }

    @Override
    public void attacked(MapObject[][] map){}
}
