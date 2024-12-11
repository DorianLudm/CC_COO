package Modele;

import java.util.ArrayList;
import java.util.Random;

public class FriendlyState extends AnimalState{
    @Override
    public void play(MapObject[][] map, Animal animal){
        animal.current_hunger--;
        animal.hasPlayed = true;
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
    public void attacked(Animal animal){
        animal.setEtat(animal.current_hunger == 0 ? new HungryState() : new NotHungryState());
    }
}
