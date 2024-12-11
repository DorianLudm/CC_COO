package Modele;

public abstract class AnimalState {
    public abstract void play(MapObject[][] map, Animal animal);
    public abstract void attacked(Animal animal);
}
