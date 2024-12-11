package Modele;

public class JungleFactory implements Factory{
    public Animal instanciateAnimal(int x, int y){
        return new Monkey(x, y);
    }

    public Tree instanciateTree(int x, int y){
        return new CoconutTree(x, y);
    }

    public Decoration instanciateDecoration(int x, int y){
        return new SmallRock(x, y);
    }

    public Fruit instanciateFruit(int x, int y){
        return new Banana(x, y);
    }

    public EmptySpace instanciateEmptySpace(int x, int y){
        return new JungleEmptySpace(x, y);
    }

    public Mushroom instanciateMushroom(int x, int y){
        return new JungleMushroom(x, y);
    }
}
