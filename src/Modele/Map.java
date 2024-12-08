package Modele;

import java.util.List;
import java.util.ArrayList;

public class Map{

    private MapObject[][] map;
    private MapObject player;

    /***/
    public Map(){
        player = new Player();

    }

    public Map(int x, int y){
        map = new MapObject[x][y];
        player = new Player();

        MapObject tile = new ForestEmptySpace();
        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                map[i][j] = tile;
            }
        }

        map[2][2] = new Acorn();
        map[0][0] = player;

    }

    /***/
    public void loadMap(String biome){}

    /***/
    public void generateMap(String biome){}

    /***/
    private void NPCturn(){}

    /***/
    public void movePlayer(String direction){
        // NPCturn();
    }

    /***/
    public void playerFight(){}

    /***/
    public void playerInteract(){}

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                s += this.map[i][j].getRepresentation();
            }
            s += "\n";
        }
        return s;
    }
}
