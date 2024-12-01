package Modele;

public interface Factory {
    public Animal instanciateAnimal();
    public Tree instanciateTree();
    public Decoration instanciateDecoration();
    public Fruit instanciateFruit();
    public EmptySpace instanciatEmptySpace();
    public Mushroom instanciateMushroom();
}
