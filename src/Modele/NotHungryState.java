package Modele;

public class NotHungryState extends AnimalState{
    @Override
    public Integer[] play(MapObject[][] map, Animal animal){
        Integer[] res = new Integer[2];
        res[0] = null;

        System.out.println("Comportement normal d'un animal!");
        System.out.println("Cet animal est en "+animal.posX+" "+animal.posY);
        animal.current_hunger--;
        if(animal.current_hunger == 0){
            animal.currentState = new HungryState();
        }

        return res;
    }

    @Override
    public void attacked(MapObject[][] map, Animal animal){}
}
