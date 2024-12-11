package Modele;

public class CoconutTree extends Tree{

    public CoconutTree(int x, int y) {
        this.posX = x;
        this.posY =y;
        setBgColor("\u001B[40m");
        setFontColor("\u001B[33m");
        setRepresentation("G");
    }

    @Override
    public void attacked(){}
}
