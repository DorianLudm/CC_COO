package Modele;

import java.util.HashMap;


public class Player extends MapObject{
    private static Player instance;

    private java.util.Map<MapObject,Integer> inventory;

    private Player(int posX, int posY) {
        setBgColor("\u001B[47m");
        setFontColor("\u001B[35m");
        setRepresentation("@");
        this.posX = posX;
        this.posY = posY;

        this.inventory = new HashMap<>();
    }

    public static Player getInstance(int posX, int posY) {
        if (instance == null) {
            instance = new Player(posX, posY);
        }
        return instance;
    }

    public static Player getInstance(){
        return instance;
    }

    @Override
    public void attacked(){}

    public void addItem(MapObject item){
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
    }

    public void removeItem(MapObject item){
        if (inventory.containsKey(item))
            inventory.put(item, inventory.get(item) - 1);
        else
            inventory.remove(item);
    }

    @Override
    public String toString() {
        String s = "Player Inventory : ";
        for (MapObject item : inventory.keySet()) {
            s += item.toString() + ", " + inventory.get(item);
        }
        return s;
    }
}
