package Modele;

public abstract class Animal extends MapObject{
    protected int current_hunger;
    protected int current_friendship;
    protected int junkieMoveRange;
    private AnimalState currentState;

    @Override
    public void play(MapTile[][] map){
        this.currentState.play(map, this);
    }

    @Override
    public void attacked(){
        this.currentState.attacked(this);
    }

    public abstract int getMaxHunger();
    public abstract int getMaxFriendship();

    public void setEtat(AnimalState newState){
        this.currentState = newState;
        if (newState instanceof HungryState){
            this.setFontColor("\u001B[30m");
        } else if (newState instanceof NotHungryState){
            this.setFontColor("\u001B[34m");
        } else if (newState instanceof FriendlyState){
            this.setFontColor("\u001B[35m");
        } else if (newState instanceof AteWeirdShroomState){
            System.out.println("Passage en mode junkie!");
            this.setFontColor("\u001B[31m");
        }
    }

    public int getJunkieMoveRange(){
        return this.junkieMoveRange;
    }
}