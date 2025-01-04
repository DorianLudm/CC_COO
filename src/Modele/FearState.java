package Modele;

public class FearState extends AnimalState{
    @Override
    public void play(MapTile[][] map, Animal animal){
        if (animal.hasPlayed){return;}
        animal.current_hunger--;
        animal.scaredCooldown--;
        if(animal.scaredCooldown == 0){
            animal.setEtat(animal.current_hunger == 0 ? new HungryState() : new NotHungryState());
        }
        animal.hasPlayed = true;
    }

    @Override
    public void attacked(Animal animal){}
}
