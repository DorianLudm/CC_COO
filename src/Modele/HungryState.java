package Modele;

import java.util.ArrayList;
import java.util.Random;

public class HungryState extends AnimalState{
    private boolean shouldEatObject(MapObject toEat, MapObject candidate){
        if(candidate == null){return false;}
        if(toEat instanceof Fruit){return false;}
        if(toEat instanceof Mushroom && candidate instanceof Fruit){return true;}
        if(candidate instanceof Fruit || candidate instanceof Mushroom){return true;}
        return false;
    }

    @Override
    public void play(MapTile[][] map, Animal animal){
        if (animal.hasPlayed){return;}
        animal.hasPlayed = true;
        int x = animal.getPosX();
        int y =  animal.getPosY();

        ArrayList<MapTile> moveSpaces = new ArrayList<>();

        MapObject toEat = null;
        for(MapTile tile: Map.getInstance().getSurroudings(x, y)){
            if (tile != null){
                MapObject obj = tile.getBackground();
                boolean shouldEat = shouldEatObject(toEat, obj);
                toEat = shouldEat ? obj : toEat;
                if(obj != null && obj.isReachable()){moveSpaces.add(tile);}
            }
        }
        if (toEat != null) {
            int foodX = toEat.getPosX(); int foodY = toEat.getPosY();
            // Check if the player is around the food the animal is going to eat and set the according state
            MapTile[] foodSurroundings = Map.getInstance().getSurroudings(foodX, foodY);
            boolean befriend = false;
            for(MapTile obj: foodSurroundings){if(obj != null && obj.getForeground() instanceof Player){befriend = true;}}
            animal.current_friendship = befriend ? animal.current_friendship+1 : 0;
            animal.setEtat(animal.current_friendship == animal.getMaxFriendship() ? new FriendlyState() : new NotHungryState());
            animal.current_hunger = animal.getMaxHunger();
            map[toEat.getPosX()][toEat.getPosY()].setForeground(animal);
            animal.setCoords(toEat.getPosX(), toEat.getPosY());
            map[x][y].setForeground(null);
        } else {
            // Go to random emptyspace around itself
            Random rd = new Random();
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
