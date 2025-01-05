package Modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Player extends MapObject{
    private static Player instance;

    private java.util.Map<String,Integer> stacksInventory;
    private List<Animal> animalsInventory;

    private Player(int posX, int posY) {
        setBgColor("\u001B[47m");
        setFontColor("\u001B[35m");
        setRepresentation("@");
        this.posX = posX;
        this.posY = posY;

        this.stacksInventory = new HashMap<>();
        this.animalsInventory = new ArrayList<>();
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
        animalsInventory.add(animal);
    }

    public void removeAnimal(){
        // si le nbr de tour restant sur le joueur est = 0 alors il descend
    }

    public List<Animal> getAnimals(){
        return animalsInventory;
    }

    @Override
    public String toString() {
        String s = "Player Inventory : ";
        for (String item : stacksInventory.keySet()) {
            s += item + ", " + stacksInventory.get(item) + " ; ";
        }
        s += "\n Animals : ";
        for (Animal animal : animalsInventory) {
            s += animal + ", "; 
        }
        return s;
    }
}
