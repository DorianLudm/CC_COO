package Modele;

public class Bush extends Decoration{

    public Bush() {
        setBgColor("\u001B[40m");
        setFontColor("\u001B[32m");
        setRepresentation("B");
    }

    @Override
    public void attacked(MapObject[][] map){}
}
