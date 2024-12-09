package Modele;

public class FriendlyState extends AnimalState{
    @Override
    public Integer[] play(MapObject[][] map, Animal animal){
        Integer[] res = new Integer[2];
        res[0] = null;

        System.out.println("Comportement d'un animal allié!");

        return res;
    }

    @Override
    public void attacked(MapObject[][] map, Animal animal){
        animal.currentState = animal.getMaxHunger() == animal.current_hunger ? new NotHungryState() : new HungryState();
    }
}
