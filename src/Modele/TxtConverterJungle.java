package Modele;

public class TxtConverterJungle extends TxtConverter {
    public TxtConverterJungle(Factory factory) {
        super(factory);
    }

    @Override
    public MapObject generate(char letter, int x, int y){
        MapObject result = null;
        switch(letter){
            case 'G':
                result = facto.instanciateTree(x,y);
                break;
            case 'B':
                result = facto.instanciateFruit(x,y);
        }
        return result;
    }
}
