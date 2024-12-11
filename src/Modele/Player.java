package Modele;

import java.util.HashMap;


public class Player extends MapObject{
    private static Player instance;

    private java.util.Map<String,Integer> inventory;

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
        String type = item.getClass().getSimpleName();
        System.out.println(type);
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);
    }

    public String removeItem(String item){
        if(inventory.get(item) == null){return null;}

        if (inventory.get(item) > 1) {
            inventory.put(item, inventory.get(item) - 1);
            return item;
        }
        else if (inventory.get(item) == 1) {
            inventory.remove(item);
            return item;
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "Player Inventory : ";
        for (String item : inventory.keySet()) {
            s += item + ", " + inventory.get(item);
        }
        return s;
    }
}
