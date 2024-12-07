package Modele;

import java.util.List;
import java.util.ArrayList;

public class Map{

    private String[][] map;

    private List<MapObject> mapNpcElements;
    private List<MapObject> mapFixedElements;

    /***/
    public Map(){
        MapObject test = new Player();
        System.out.println(test.getRepresentation());
    }

    public Map(int x, int y){
        map = new String[x][y];

        MapObject tile = new ForestEmptySpace();
        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                map[i][j] = tile.getRepresentation();
            }
        }
    }

    /***/
    public void loadMap(String biome){}

    /***/
    public void generateMap(String biome){}

    /***/
    private void NPCturn(){}

    /***/
    public void movePlayer(String direction){
        // player move
        // NPCturn();
    }

    /***/
    public void playerFight(){}

    /***/
    public void playerInteract(){}

    @Override
    public String toString() {
        return "Map";
    }
}
