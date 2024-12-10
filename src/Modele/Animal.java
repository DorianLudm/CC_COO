package Modele;

public abstract class Animal extends MapObject{
    protected int current_hunger;
    protected int current_friendship;
    protected AnimalState currentState;

    @Override
    public void play(MapObject[][] map){
        this.currentState.play(map, this);
    }

    @Override
    public void attacked(MapObject[][] map){
        this.currentState.attacked(map, this);
    }

    public abstract int getMaxHunger();
    public abstract int getMaxFriendship();
}
