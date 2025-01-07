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
        if (newState instanceof HungryState){
            this.setFontColor("\u001B[30m");
        } else if (newState instanceof NotHungryState){
            this.setFontColor("\u001B[34m");
        } else if (newState instanceof FriendlyState){
            this.setFontColor("\u001B[35m");
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
}
