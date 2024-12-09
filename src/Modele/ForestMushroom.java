package Modele;

public class ForestMushroom extends Mushroom{

    public ForestMushroom() {
        setBgColor("\u001B[47m");
        setFontColor("\u001B[35m");
        setRepresentation("C");
    }

    @Override
    public void attacked(MapObject[][] map){}
}
