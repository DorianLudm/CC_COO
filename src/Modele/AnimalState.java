package Modele;

public abstract class AnimalState {
    public abstract void play(MapTile[][] map, Animal animal);
    public abstract void attacked(Animal animal);
}
