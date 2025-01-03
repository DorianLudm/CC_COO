package Modele;

public class JungleMushroom extends Mushroom{
    private boolean isHalucinatif;

    public JungleMushroom(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.isHalucinatif = this.rollAttribute();
        initColor();
        setRepresentation("C");
        pickable = true;
    }

    @Override
    public void attacked(){}

    @Override
    public boolean isWeirdMushroom(){
        return this.isHalucinatif;
    }
}
