package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;

public class Snake extends JunglePredator{
    boolean isEating;
    int eatingDuration;
    Animal prey;
    Snake(int x, int y){
        this.posX = x;
        this.posY = y;
        this.detectionRadius = 1;
        this.moveRadius = 2;
        setBgColor("\u001B[43m");
        setFontColor("\u001B[30m");
        setRepresentation("S");

    }

    @Override
    public void play(MapTile[][] map){
        if (hasPlayed){
            return;
        }

        hasPlayed = true;

        if(isEating){ //Le serpent est en train de manger
            if(eatingDuration == 0){
                this.prey=null;
                return;
            }
            eatingDuration--;
            return;
        }
        Map<Integer, List<MapTile>> data = scanForPrey(map, this);
        if(data.containsKey(1)) {
            List<MapTile> tiles = data.get(1);
            for(MapTile tile : tiles){
                if(tile.getForeground() instanceof Animal monkey){
                    if(monkey.predatorAttack(map, monkey, ForestTree.class)) {
                        this.prey = monkey;
                        tile.setForeground(null);
                        isEating = true;
                        eatingDuration = 3;                 //temps necessaire à la digestion du serpent
                    }
                    return;
                }
            }
        }

    }

    @Override
    public void attacked(){
        return;
    }
}
