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
    public void play(MapObject[][] map, Animal animal){
        if (animal.hasPlayed){return;}
        animal.hasPlayed = true;
        int x = animal.getPosX();
        int y =  animal.getPosY();

        ArrayList<EmptySpace> moveSpaces = new ArrayList<>();

        MapObject toEat = null;
        for(MapObject obj: Map.getInstance().getSurroudings(map, x, y)){
            boolean shouldEat = shouldEatObject(toEat, obj);
            toEat = shouldEat ? obj : toEat;
            if(obj instanceof EmptySpace){moveSpaces.add((EmptySpace) obj);}
        }
        if (toEat != null) {
            int foodX = toEat.getPosX(); int foodY = toEat.getPosY();
            int playerX = Player.getInstance().getPosX(); int playerY = Player.getInstance().getPosY();
            // Check if the player is around the food the animal is going to eat and set the according state
            boolean befriend = (Math.abs(foodX - playerX) == 1 && foodY == playerY) || (foodX == playerX && Math.abs(foodY - playerY) == 1);
            animal.current_friendship = befriend ? animal.current_friendship++ : 0;
            animal.currentState = animal.current_friendship == animal.getMaxFriendship() ? new FriendlyState() : new NotHungryState();
            animal.current_hunger = animal.getMaxHunger();
            map[toEat.getPosX()][toEat.getPosY()] = animal;
            animal.setCoords(toEat.getPosX(), toEat.getPosY());
            map[x][y] = Map.getInstance().getFactory().instanciatEmptySpace(x, y);
        } else {
            // Go to random emptyspace around itself
            Random rd = new Random();
            int numberOfSpaces = moveSpaces.size();
            if (numberOfSpaces > 0){
                EmptySpace moveLocation = moveSpaces.get(rd.nextInt(numberOfSpaces));
                map[moveLocation.getPosX()][moveLocation.getPosY()] = animal;
                animal.setCoords(moveLocation.getPosX(), moveLocation.getPosY());
                map[x][y] = Map.getInstance().getFactory().instanciatEmptySpace(x, y);
            }
        }
    }

    @Override
    public void attacked(MapObject[][] map, Animal animal){}
}
