package Modele;

public class RareRock extends MapObject{

    private int rewindValue;

    public RareRock(int x, int y, int rewindValue) {
        posX = x;
        posY = y;
        this.rewindValue = rewindValue;
        setBgColor("\u001B[46m");
        setFontColor("\u001B[31m");
        setRepresentation("*");
        pickable = true;
    }

    public int getRewindValue() { return rewindValue; }

    @Override
    public void attacked() {

    }
}
