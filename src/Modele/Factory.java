package Modele;

public interface Factory {
    public Animal instanciateAnimal(int x, int y);
    public Tree instanciateTree(int x, int y);
    public Decoration instanciateDecoration(int x, int y);
    public Fruit instanciateFruit(int x, int y);
    public EmptySpace instanciateEmptySpace(int x, int y);
    public Mushroom instanciateMushroom(int x, int y);
    public BiomePredator instanciatePredator(int x, int y);
    public BiomePredator instanciatePredator(int x, int y, int type);
}
