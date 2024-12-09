package Modele;

public class ForestTree extends Tree{

    public ForestTree() {
        setBgColor("\u001B[40m");
        setFontColor("\u001B[32m");
        setRepresentation("A");
    }

    @Override
    public void attacked(MapObject[][] map){}
}
