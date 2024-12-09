package Modele;

public interface Factory {
    public Animal instanciateAnimal(int x, int y);
    public Tree instanciateTree(int x, int y);
    public Decoration instanciateDecoration(int x, int y);
    public Fruit instanciateFruit(int x, int y);
    public EmptySpace instanciatEmptySpace(int x, int y);
    public Mushroom instanciateMushroom(int x, int y);
}
