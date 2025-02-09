package Modele;

import java.util.ArrayList;
import java.util.Random;

public class FriendlyState extends AnimalState{
    @Override
    public void play(MapTile[][] map, Animal animal){
        if (animal.hasPlayed){return;}
        animal.current_hunger--;
        animal.hasPlayed = true;
        if(animal.current_hunger == 0){
            animal.setEtat(new HungryState());
        }

        MapObject MOb = map[animal.getPosX()][animal.getPosY()].getForeground();
        // Check que l'animal est sur la carte
        if(MOb != null && MOb instanceof Animal && MOb.getPosX() == animal.getPosX() && MOb.getPosY() == animal.getPosY()){
            int x = animal.getPosX(); int y =  animal.getPosY();
            ArrayList<MapTile> moveSpaces = new ArrayList<>();
            for(MapTile obj: Map.getInstance().getSurroundings(x, y)){
                if(obj != null && obj.isReachable()){moveSpaces.add(obj);}
            }

            Random rd = new Random();
            int numberOfSpaces = moveSpaces.size();
            if (numberOfSpaces > 0){
                MapTile moveLocation = moveSpaces.get(rd.nextInt(numberOfSpaces));
                moveLocation.setForeground(animal);
                animal.setCoords(moveLocation.getPosX(), moveLocation.getPosY());
                map[x][y].setForeground(null);
            }
        }
        // Sinon dans l'inventaire
        else{
            animal.friendlyBehavior();
        }
    }

    @Override
    public void attacked(Animal animal){
        animal.setEtat(animal.current_hunger == 0 ? new HungryState() : new NotHungryState());
    }
}
