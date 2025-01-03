package Modele;

import java.util.Map;
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
}