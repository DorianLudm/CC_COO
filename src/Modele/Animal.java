package Modele;

public abstract class Animal extends MapObject{
    protected int current_hunger;
    protected AnimalState currentState;

    public void play(String map){
        this.currentState.play(map, this);
    }
}
