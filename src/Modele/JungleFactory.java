package Modele;

public class JungleFactory implements Factory{
    public Animal instanciateAnimal(){
        return new Monkey();
    }

    public Tree instanciateTree(){
        return new CoconutTree();
    }

    public Decoration instanciateDecoration(){
        return new SmallRock();
    }

    public Fruit instanciateFruit(){
        return new Banana();
    }

    public EmptySpace instanciatEmptySpace(){
        return new JungleEmptySpace();
    }

    public Mushroom instanciateMushroom(){
        return new JungleMushroom();
    }
}
