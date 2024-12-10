package Modele;

public class FriendlyState extends AnimalState{
    @Override
    public Integer[] play(MapObject[][] map, Animal animal){
        Integer[] res = new Integer[2];
        res[0] = null;

        animal.current_hunger--;
        if(animal.current_hunger == 0){
            animal.currentState = new HungryState();
        }

        return res;
    }

    @Override
    public void attacked(MapObject[][] map, Animal animal){
        animal.currentState = animal.getMaxHunger() == animal.current_hunger ? new NotHungryState() : new HungryState();
    }
}
