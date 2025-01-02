package Modele;

public class ForestMushroom extends Mushroom{
    private boolean isVenomous;

    public ForestMushroom(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.isVenomous = this.rollAttribute();
        setBgColor("\u001B[47m");
        initFontColor();
        setRepresentation("C");
        pickable = true;
    }

    @Override
    public void attacked(){}

    @Override
    public boolean isWeirdMushroom(){
        return this.isVenomous;
    }
}
