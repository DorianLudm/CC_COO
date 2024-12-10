package Modele;

public abstract class Animal extends MapObject{
    protected int current_hunger;
    protected int current_friendship;
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
    public abstract int getMaxFriendship();

    // protected Integer[] checkFood(){
    //     Integer[] res = new Integer[4];
    //     // Res[0]: Direction to eat (null if none) North/East/South/West | 1/2/3/4
    //     // Res[1]: Type of food (null if none) Fruit 1 | Mushroom 2
    //     // Res[2]: Animal posX
    //     // Res[3]: Animal posY
    //     // Res[4]: EmptyTile direction
    //     return res;
    // }
}
