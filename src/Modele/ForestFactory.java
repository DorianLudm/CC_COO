package Modele;

public class ForestFactory implements Factory{
    public Animal instanciateAnimal(int x, int y){
        return new Squirrel(x, y);
    }

    public Tree instanciateTree(int x, int y){
        return new ForestTree(x, y);
    }

    public Decoration instanciateDecoration(int x, int y){
        return new Bush(x, y);
    }

    public Fruit instanciateFruit(int x, int y){
        return new Acorn(x, y);
    }

    public EmptySpace instanciateEmptySpace(int x, int y){
        return new ForestEmptySpace(x, y);
    }

    public Mushroom instanciateMushroom(int x, int y){
        return new ForestMushroom(x, y);
    }
}
