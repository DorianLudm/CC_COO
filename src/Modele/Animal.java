package Modele;

public abstract class Animal extends MapObject{
    protected int current_hunger;
    protected AnimalState currentState;

    @Override
    public Integer[] play(MapObject[][] map){
        return this.currentState.play(map, this);
    }

    @Override
    public void attacked(MapObject[][] map){
        this.currentState.attacked(map, this);
    }

    public abstract int getMaxHunger();
}
