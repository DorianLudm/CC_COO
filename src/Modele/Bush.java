package Modele;

public class Bush extends Decoration{

    public Bush(int x, int y) {
        this.posX = x;
        this.posY = y;
        setBgColor("\u001B[40m");
        setFontColor("\u001B[32m");
        setRepresentation("B");
    }

    @Override
    public void attacked(){}
}
