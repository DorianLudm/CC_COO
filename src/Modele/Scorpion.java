package Modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scorpion extends JunglePredator{
    boolean isWaitingOnRock = false;
    int timeToWaitOnRock = 0;
    int timeBeforeNextRock = 0;
    Scorpion(int x, int y){
        this.posX = x;
        this.posY = y;
        this.detectionRadius = 1;
        this.moveRadius = 1;
        setBgColor("\u001B[44m");
        setFontColor("\u001B[32m");
        setRepresentation("S");
    }

    @Override
    public void play(MapTile[][] map){
        System.out.println(timeBeforeNextRock);

        if (hasPlayed){
            return;
        }
        if(timeBeforeNextRock>0){
            timeBeforeNextRock--;
        }
        hasPlayed = true;
        if(isWaitingOnRock){
            timeToWaitOnRock--;
            if(timeToWaitOnRock == 0){
                isWaitingOnRock = false;
                setBgColor("\u001B[44m");
            }
        }
        java.util.Map<Integer, List<MapTile>> data = scanForPrey(map, this);
        if(data.containsKey(1)) {
            List<MapTile> tiles = data.get(1);
            for(MapTile tile : tiles){
                if(tile.getForeground() instanceof Monkey monkey){
                    if(monkey.predatorAttack(map, monkey, ForestTree.class)) {
                        tile.setForeground(null);
                    }
                    return;
                }
               if(tile.getBackground() instanceof SmallRock){
                   if(tile.getForeground()==null && timeBeforeNextRock == 0){
                       tile.setForeground(this);
                       map[posX][posY].setForeground(null);
                       this.setCoords(tile.getPosX(), tile.getPosY());
                       this.isWaitingOnRock = true;
                       this.timeToWaitOnRock = 5;
                       this.timeBeforeNextRock = 10;
                       setBgColor("\u001B[31m");
                   }
               }
            }
        }
        if(!isWaitingOnRock) {
            for (int i = 0; i < moveRadius; i++) {
                int x = this.posX;
                int y = this.posY;
                ArrayList<MapTile> moveSpaces = new ArrayList<>();
                for (MapTile obj : Modele.Map.getInstance().getSurroundings(x, y)) {
                    if (obj != null && obj.isReachable()) {
                        moveSpaces.add(obj);
                    }
                }

                Random rd = new Random();
                int numberOfSpaces = moveSpaces.size();
                if (numberOfSpaces > 0) {
                    MapTile moveLocation = moveSpaces.get(rd.nextInt(numberOfSpaces));
                    moveLocation.setForeground(this);
                    this.setCoords(moveLocation.getPosX(), moveLocation.getPosY());
                    map[x][y].setForeground(null);
                }
            }
        }
    }

    @Override
    public void attacked(){
        return;
    }
}
