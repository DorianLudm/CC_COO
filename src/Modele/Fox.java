package Modele;

import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Fox extends ForestPredator{
    Fox(int x, int y){
        this.posX = x;
        this.posY = y;
        this.detectionRadius = 1;
        this.moveRadius = 1;
        this.setFontColor("\u001B[30m");
        this.setBgColor("\u001B[48m");
        this.setRepresentation("R");
    }

    @Override
    public void play(MapTile[][] map){
        if(hasPlayed){return;}
        List<MapTile> toEat = this.findClosestPreys(map, this, Squirrel.class);
        if(toEat.size() > 0){
            Random rd = new Random();
            MapTile target = toEat.get(rd.nextInt(toEat.size()));
            Animal prey = (Animal) target.getForeground();
            boolean escaped = prey.predatorAttack(map, prey, ForestTree.class);
            if(!escaped){
                target.setForeground(null);
            }
            map[this.getPosX()][this.getPosY()].setForeground(null);
            target.setForeground(this);
        }
        else{
            this.randomMove(map, this);
        }
        hasPlayed = true;
    }

    @Override
    public void attacked(){
        return;
    }
}
