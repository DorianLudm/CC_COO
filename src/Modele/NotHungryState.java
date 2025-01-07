package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotHungryState extends AnimalState{
    @Override
    public void play(MapTile[][] map, Animal animal){
        if (animal.hasPlayed){return;}
        animal.hasPlayed = true;
        animal.current_hunger--;
        if(animal.current_hunger == 0){
            animal.setEtat(new HungryState());
        }

        // Looks for closest predator in the detection range
        List<MapTile> predators = Map.findClosestEntityOfType(map, animal, animal.getDetectionRadius(), BiomePredator.class);
        Random rd = new Random();
        if(predators.size() > 0){
            // If a predator is spotted, run away from it
            MapTile predator = predators.get(rd.nextInt(predators.size()));
            Animal.escapeFromPredator(map, animal, predator);
        }
        else{
            int x = animal.getPosX(); int y =  animal.getPosY();
            ArrayList<MapTile> moveSpaces = new ArrayList<>();
            for(MapTile obj: Map.getInstance().getSurroundings(x, y)){
                if(obj != null && obj.isReachable()){moveSpaces.add(obj);}
            }
            
            int numberOfSpaces = moveSpaces.size();
            if (numberOfSpaces > 0){
                MapTile moveLocation = moveSpaces.get(rd.nextInt(numberOfSpaces));
                moveLocation.setForeground(animal);
                animal.setCoords(moveLocation.getPosX(), moveLocation.getPosY());
                map[x][y].setForeground(null);
            }
        }
    }

    @Override
    public void attacked(Animal animal){}
}
