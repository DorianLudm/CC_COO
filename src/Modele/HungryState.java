package Modele;

public class HungryState extends AnimalState{
    @Override
    public Integer[] play(MapObject[][] map, Animal animal){
        Integer[] res = new Integer[2];
        res[0] = null;
        
        System.out.println("Comportement animal affamé!");

        return res;
    }

    @Override
    public void attacked(MapObject[][] map, Animal animal){}
}
