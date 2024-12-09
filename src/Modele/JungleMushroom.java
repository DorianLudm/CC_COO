package Modele;

public class JungleMushroom extends Mushroom{

    public JungleMushroom() {
        setBgColor("\u001B[43m");
        setFontColor("\u001B[35m");
        setRepresentation("C");
    }

    @Override
    public void attacked(MapObject[][] map){}
}
