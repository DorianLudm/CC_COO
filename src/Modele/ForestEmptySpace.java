package Modele;

public class ForestEmptySpace extends EmptySpace{

    public ForestEmptySpace() {
        setBgColor("\u001B[42m");
        setFontColor("");
        setRepresentation(" ");
        reachable = true;
    }

    @Override
    public void attacked(MapObject[][] map){}
}
