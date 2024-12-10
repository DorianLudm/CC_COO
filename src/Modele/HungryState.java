package Modele;

public class HungryState extends AnimalState{
    private boolean shouldEatObject(MapObject toEat, MapObject obj){
        if(toEat instanceof Fruit){return false;}
        if(toEat instanceof Mushroom && obj instanceof Fruit){return true;}
        if(obj instanceof Fruit || obj instanceof Mushroom){return true;}
        return false;
    }

    @Override
    public Integer[] play(MapObject[][] map, Animal animal){
        int x = animal.getPosX();
        int y =  animal.getPosY();

        Integer[] res = new Integer[2];
        res[0] = null;

        MapObject toEat = null;
        // Partie à refactoriser

        // Créer une fonction getSurroundings protected dans Animal
        // Elle retourne une Array<MapObject>
        // Si il n'y as rien (hors de la map), il y a nul à la place
        // Permet de faire un seul parcours, et utile pour les autres états

        if (x > 0) {
            MapObject obj = map[x - 1][y];
            boolean shouldEat = shouldEatObject(toEat, obj);
            toEat = shouldEat ? obj : toEat;
        }
        if (y > 0) {
            MapObject obj = map[x][y - 1];
            boolean shouldEat = shouldEatObject(toEat, obj);
            toEat = shouldEat ? obj : toEat;
        }
        if (x < map.length - 1) {
            MapObject obj = map[x + 1][y];
            boolean shouldEat = shouldEatObject(toEat, obj);
            toEat = shouldEat ? obj : toEat;
        }
        if (y < map[x].length - 1) {
            MapObject obj = map[x][y + 1];
            boolean shouldEat = shouldEatObject(toEat, obj);
            toEat = shouldEat ? obj : toEat;
        }
        // Fin de la partie a refactoriser
        if (toEat != null) {
            int foodX = toEat.getPosX(); int foodY = toEat.getPosY();
            int playerX = Player.getInstance().getPosX(); int playerY = Player.getInstance().getPosY();
            // Check if the player is around the food the animal is going to eat and set the according state
            boolean befriend = (Math.abs(foodX - playerX) == 1 && foodY == playerY) || (foodX == playerX && Math.abs(foodY - playerY) == 1);
            animal.current_friendship = befriend ? animal.current_friendship++ : 0;
            animal.currentState = animal.current_friendship == animal.getMaxFriendship() ? new FriendlyState() : new NotHungryState();
            animal.current_hunger = animal.getMaxHunger();
            map[toEat.getPosX()][toEat.getPosY()] = animal;
            map[x][y] = Map.getInstance().getFactory().instanciatEmptySpace(x, y);
        } else {
            // Go to random emptyspace around itself
            // Iterer sur les résultats stockés dans l'appel de getSurroundings précédemment
        }

        return res;
    }

    @Override
    public void attacked(MapObject[][] map, Animal animal){}
}
