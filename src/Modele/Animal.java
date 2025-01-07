package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Animal extends MapObject{
    protected int current_hunger;
    protected int current_friendship;
    protected int junkieMoveRange;
    protected int scaredCooldown;
    protected int detectionRadius;
    private AnimalState currentState;

    @Override
    public void play(MapTile[][] map){
        this.currentState.play(map, this);
    }

    @Override
    public void attacked(){
        this.currentState.attacked(this);
    }

    public abstract int getMaxHunger();
    public abstract int getMaxFriendship();
    public abstract int getMaxFear();
    public void resetFearCooldown(){this.scaredCooldown = this.getMaxFear();}
    public int getDetectionRadius(){return this.detectionRadius;}

    public void setEtat(AnimalState newState){
        this.currentState = newState;
        pickable = false;
        if (newState instanceof HungryState){
            this.setFontColor("\u001B[30m");
        } else if (newState instanceof NotHungryState){
            this.setFontColor("\u001B[34m");
        } else if (newState instanceof FriendlyState){
            this.setFontColor("\u001B[35m");
            pickable = true;
        } else if (newState instanceof AteWeirdShroomState){
            System.out.println("Passage en mode junkie!");
            this.setFontColor("\u001B[31m");
        }
    }

    public int getJunkieMoveRange(){
        return this.junkieMoveRange;
    }

    public boolean predatorAttack(MapTile[][] map, Animal prey, Class<?> escapeType){
        MapTile[] surroundings = Map.getInstance().getSurroundings(prey.getPosX(), prey.getPosY());
        List<MapTile> validSpots = new ArrayList<>();
        for(MapTile tile: surroundings){
            if(tile != null && escapeType.isInstance(tile.getBackground()) && tile.getForeground() == null){
                validSpots.add(tile);
            }
        }
        if(validSpots.isEmpty()){return false;}
        Random rd = new Random();
        MapTile escapeSpot = validSpots.get(rd.nextInt(validSpots.size()));
        map[prey.getPosX()][prey.getPosY()].setForeground(null);
        map[escapeSpot.getPosX()][escapeSpot.getPosY()].setForeground(prey);
        prey.setEtat(new FearState());
        prey.resetFearCooldown();
        return true;
    }

    protected abstract void friendlyBehavior();

    protected static void escapeFromPredator(MapTile[][] map, Animal animal, MapTile predator){
        int deltaX = animal.getPosX() - predator.getPosX();
        int deltaY = animal.getPosY() - predator.getPosY();
        int x = animal.getPosX();
        int y = animal.getPosY();

        // Calculate potential escape tiles
        MapTile[] surroundings = Map.getInstance().getSurroundings(x, y);
        List<MapTile> potentialEscapeTiles = new ArrayList<>();
        if (deltaX > 0 && surroundings[2] != null) potentialEscapeTiles.add(surroundings[2]); // Move right
        if (deltaX < 0 && surroundings[0] != null) potentialEscapeTiles.add(surroundings[0]); // Move left
        if (deltaY > 0 && surroundings[3] != null) potentialEscapeTiles.add(surroundings[3]); // Move down
        if (deltaY < 0 && surroundings[1] != null) potentialEscapeTiles.add(surroundings[1]); // Move up

        // Find the best available tile
        MapTile bestTile = null;
        for (MapTile tile : potentialEscapeTiles) {
            if (tile != null && tile.isReachable() && tile.getForeground() == null) {
                bestTile = tile;
                break;
            }
        }

        // Special case: Animal is cornered and both the optimal and sub-optimal paths are not valid, the make the squirrel play a random other tile
        if (bestTile == null) {
            List<MapTile> availableTiles = new ArrayList<>();
            for (MapTile tile : surroundings) {
                if (tile != null && tile.isReachable() && tile.getForeground() == null) {
                    availableTiles.add(tile);
                }
            }
            if (!availableTiles.isEmpty()) {
                Random rd = new Random();
                bestTile = availableTiles.get(rd.nextInt(availableTiles.size()));
            }
        }

        if (bestTile != null) {
            bestTile.setForeground(animal);
            animal.setCoords(bestTile.getPosX(), bestTile.getPosY());
            map[x][y].setForeground(null);
        }
    }
}
