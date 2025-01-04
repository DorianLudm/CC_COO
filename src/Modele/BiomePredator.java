package Modele;

import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public abstract class BiomePredator extends MapObject {
    protected int detectionRadius;
    protected int moveRadius;

    protected Map<Integer, List<MapTile>> scanForPrey(MapTile[][] map, BiomePredator predator) {
        Map<Integer, List<MapTile>> res = new HashMap<>();
        int predatorX = predator.getPosX();
        int predatorY = predator.getPosY();
        for (int i = (predatorX - predator.detectionRadius); i <= (predatorX + predator.detectionRadius); i++) {
            for (int j = (predatorY - predator.detectionRadius); j <= (predatorY + predator.detectionRadius); j++) {
                if (i >= 0 && j >= 0 && i < map.length && j < map[0].length) {
                    MapTile obj = map[i][j];
                    int deltaX = Math.abs(predatorX - obj.getPosX());
                    int deltaY = Math.abs(predatorY - obj.getPosY());
                    int distance = deltaX + deltaY;
                    if (res.containsKey(distance)) {
                        List<MapTile> list = res.get(distance);
                        list.add(obj);
                    } else {
                        List<MapTile> list = new ArrayList<>();
                        list.add(obj);
                        res.put(distance, list);
                    }
                }
            }
        }
        return res;
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
        Map<Integer, List<MapTile>> detection = this.scanForPrey(map, predator);
        List<MapTile> toEat = new ArrayList<>();
        for (Map.Entry<Integer, List<MapTile>> entry : detection.entrySet()){
            List<MapTile> tiles = entry.getValue();
            for(MapTile tile: tiles){
                if(preyType.isInstance(tile.getForeground()) && tile.getBackground() instanceof EmptySpace){
                    toEat.add(tile);
                }
            }
            if(toEat.size() > 0){break;}
        }
        return toEat;
    }
}