package Modele;

public class ForestTree extends Tree{

    public ForestTree(int x, int y) {
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[40m");
        setFontColor("\u001B[32m");
        setRepresentation("A");
    }

    @Override
    public void attacked(){}
}
