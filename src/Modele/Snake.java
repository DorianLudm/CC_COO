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
                if(tile.getForeground() instanceof Monkey monkey){
                    if(monkey.predatorAttack(map, monkey, ForestTree.class)) {
                        monkey.isEaten = true;
                        this.prey = monkey;
                        tile.setForeground(null);
                        isEating = true;
                        eatingDuration = 3;                 //temps necessaire à la digestion du serpent
                    }
                    return;
                }
            }
        }
        for(int i=0; i < 2; i++){
            int x = this.posX; int y =  this.posY;
            ArrayList<MapTile> moveSpaces = new ArrayList<>();
            for(MapTile obj: Modele.Map.getInstance().getSurroundings(x, y)){
                if(obj != null && obj.isReachable()){moveSpaces.add(obj);}
            }

            Random rd = new Random();
            int numberOfSpaces = moveSpaces.size();
            if (numberOfSpaces > 0){
                MapTile moveLocation = moveSpaces.get(rd.nextInt(numberOfSpaces));
                moveLocation.setForeground(this);
                this.setCoords(moveLocation.getPosX(), moveLocation.getPosY());
                map[x][y].setForeground(null);
            }
        }
    }

    @Override
    public void attacked(){
        int x = this.posX; int y =  this.posY;
        ArrayList<MapTile> moveSpaces = new ArrayList<>();
        Monkey monkey = (Monkey) this.prey;
        monkey.isEaten = false;
        isEating = false;
        for(MapTile obj: Modele.Map.getInstance().getSurroundings(x, y)){
            if(obj != null && obj.isReachable()){
                obj.setForeground(this.prey);
                this.prey.setCoords(obj.getPosX(), obj.getPosY());
                this.prey = null;
            }
        }
    }
}
