package Modele;

public class ForestFactory implements Factory{
    public Animal instanciateAnimal(){
        return new Squirrel();
    }

    public Tree instanciateTree(){
        return new ForestTree();
    }

    public Decoration instanciateDecoration(){
        return new Bush();
    }

    public Fruit instanciateFruit(){
        return new Acorn();
    }

    public EmptySpace instanciatEmptySpace(){
        return new ForestEmptySpace();
    }

    public Mushroom instanciateMushroom(){
        return new ForestMushroom();
    }
}
