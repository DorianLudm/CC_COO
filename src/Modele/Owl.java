package Modele;

import java.util.List;
import java.util.Random;

public class Owl extends ForestPredator{
    private int stunned;

    Owl(int x, int y){
        this.posX = x;
        this.posY = y;
        this.detectionRadius = 3;
        this.moveRadius = 1;
        this.stunned = 0;
        setBgColor("\u001B[45m");
        setFontColor("\u001B[37m");
        setRepresentation("H");
    }

    @Override
    public void play(MapTile[][] map){
        if(hasPlayed){return;}
        if(stunned > 1){stunned--; return;}
        if(stunned == 1){stunned--; setBgColor("\u001B[45m");}

        if(hasPlayed){return;}
        List<MapTile> toEat = this.findClosestPreys(map, this, Squirrel.class);
        if(toEat.size() > 0){
            Random rd = new Random();
            MapTile target = toEat.get(rd.nextInt(toEat.size()));
            Animal prey = (Animal) target.getForeground();
            prey.predatorAttack(map, prey, Bush.class);

            // Handle swapping positions
            map[this.getPosX()][this.getPosY()].setForeground(null);
            map[target.getPosX()][target.getPosY()].setForeground(this);
            this.setCoords(target.getPosX(), target.getPosY());

            // Change owl state to be on cooldown
            setBgColor("\u001B[44m");
            this.stunned = 3;
        }
        else{
            this.randomMove(map, this);
        }
        hasPlayed = true;        
        return;
    }

    @Override
    public void attacked(){
        return;
    }
}
