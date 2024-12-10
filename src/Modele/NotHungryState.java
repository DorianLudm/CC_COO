package Modele;

import java.util.ArrayList;
import java.util.Random;

public class NotHungryState extends AnimalState{
    @Override
    public void play(MapObject[][] map, Animal animal){
        if (animal.hasPlayed){return;}
        animal.hasPlayed = true;
        animal.current_hunger--;
        if(animal.current_hunger == 0){
            animal.setEtat(new HungryState());
        }

        int x = animal.getPosX(); int y =  animal.getPosY();
        ArrayList<EmptySpace> moveSpaces = new ArrayList<>();
        for(MapObject obj: Map.getInstance().getSurroudings(x, y)){
            if(obj instanceof EmptySpace){moveSpaces.add((EmptySpace) obj);}
        }

        Random rd = new Random();
        int numberOfSpaces = moveSpaces.size();
        if (numberOfSpaces > 0){
            EmptySpace moveLocation = moveSpaces.get(rd.nextInt(numberOfSpaces));
            map[moveLocation.getPosX()][moveLocation.getPosY()] = animal;
            animal.setCoords(moveLocation.getPosX(), moveLocation.getPosY());
            map[x][y] = Map.getInstance().getFactory().instanciatEmptySpace(x, y);
        }
    }

    @Override
    public void attacked(MapObject[][] map, Animal animal){}
}
