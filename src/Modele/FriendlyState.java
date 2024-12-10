package Modele;

public class FriendlyState extends AnimalState{
    @Override
    public void play(MapObject[][] map, Animal animal){
        animal.current_hunger--;
        animal.hasPlayed = true;
        if(animal.current_hunger == 0){
            animal.currentState = new HungryState();
        }
    }

    @Override
    public void attacked(MapObject[][] map, Animal animal){
        animal.currentState = animal.getMaxHunger() == animal.current_hunger ? new NotHungryState() : new HungryState();
    }
}
