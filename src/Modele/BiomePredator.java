package Modele;

import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public abstract class BiomePredator extends MapObject {
    protected int detectionRadius;
    protected int moveRadius;

    protected Map<Integer, List<MapTile>> scanForPrey(MapTile[][] map, BiomePredator predator) {
        return Modele.Map.scanArea(map, predator, predator.detectionRadius);
    }

    protected void randomMove(MapTile[][] map, BiomePredator predator){
        for(int i=0; i < this.moveRadius; i++){
            int x = predator.getPosX(); int y =  predator.getPosY();
            ArrayList<MapTile> moveSpaces = new ArrayList<>();
            for(MapTile obj: Modele.Map.getInstance().getSurroundings(x, y)){
                if(obj != null && obj.isReachable()){moveSpaces.add(obj);}
            }
            Random rd = new Random();
            int numberOfSpaces = moveSpaces.size();
            if (numberOfSpaces > 0){
                MapTile moveLocation = moveSpaces.get(rd.nextInt(numberOfSpaces));
                moveLocation.setForeground(predator);
                predator.setCoords(moveLocation.getPosX(), moveLocation.getPosY());
                map[x][y].setForeground(null);
            }
        }
    }

    protected List<MapTile> findClosestPreys(MapTile[][] map, BiomePredator predator, Class<?> preyType){
        return Modele.Map.findClosestEntityOfType(map, predator, predator.detectionRadius, preyType);
    }
}