package Modele;

public abstract class AnimalState {
    public abstract Integer[] play(MapObject[][] map, Animal animal);
    public abstract void attacked(MapObject[][] map, Animal animal);
}
