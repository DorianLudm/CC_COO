package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Player extends MapObject{
    private static Player instance;

    private java.util.Map<String,Integer> stacksInventory;
    private java.util.Map<String,Animal> animalsInventory;

    private Player(int posX, int posY) {
        setBgColor("\u001B[47m");
        setFontColor("\u001B[35m");
        setRepresentation("@");
        this.posX = posX;
        this.posY = posY;

        this.stacksInventory = new HashMap<>();
        this.animalsInventory = new HashMap<>();
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
        if (item instanceof RareRock){
            type += ((RareRock) item).getRewindValue();
        }
        System.out.println(type);
        stacksInventory.put(type, stacksInventory.getOrDefault(type, 0) + 1);
    }

    public String removeItem(String item){
        if(stacksInventory.get(item) == null){return null;}

        if (stacksInventory.get(item) > 1) {
            stacksInventory.put(item, stacksInventory.get(item) - 1);
            return item;
        }
        else if (stacksInventory.get(item) == 1) {
            stacksInventory.remove(item);
            return item;
        }
        return null;
    }

    public void addAnimal(Animal animal){
        animalsInventory.put("" + animalsInventory.size(), animal);
    }

    public Animal removeAnimal(String index){
        return animalsInventory.remove(index);
    }

    public java.util.Map<String,Animal> getAnimals(){
        return animalsInventory;
    }

    @Override
    public String toString() {
        String s = "Player Inventory : ";
        for (String item : stacksInventory.keySet()) {
            s += item + ", " + stacksInventory.get(item) + " ; ";
        }
        s += "\n Animals : ";
        for (String ind : animalsInventory.keySet()) {
            s += ind + ":" + animalsInventory.get(ind).getClass().getSimpleName() + ", ";
        }
        return s;
    }
}
