package Modele;

import java.util.List;
import java.util.ArrayList;

public class Map{

    private List<MapObject> mapNpcElements;
    private List<MapObject> mapFixedElements;

    /***/
    public Map(){}

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
