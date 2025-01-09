package Modele;

public class TxtConverterJungle extends TxtConverter {
    public TxtConverterJungle(Factory factory) {
        super(factory);
    }

    @Override
    public MapObject[] generate(char letter, int x, int y){
        MapObject[] result = new MapObject[2];
        switch(letter){
            case '@':
                result[0] = facto.instanciateEmptySpace(x,y);
                result[1] = Player.getInstance(x,y);
                break;
            case 'G':
                result[0] = facto.instanciateTree(x,y);
                break;
            case 'B':
                result[0] = facto.instanciateFruit(x,y);
                break;
            case 'M':
                result[0] = facto.instanciateEmptySpace(x,y);
                result[1] = facto.instanciateAnimal(x,y);
                break;
            case 'S':
                result[0] = facto.instanciateEmptySpace(x,y);
                result[1] = facto.instanciatePredator(x,y);
                break;
            case 'C':
                result[0] = facto.instanciateMushroom(x,y);
                break;
            case 'X':
                result[0] = facto.instanciateDecoration(x,y);
                break;
            case ' ':
                result[0] = facto.instanciateEmptySpace(x,y);
                break;
            case '*':
                result[0] = facto.instanciateRareRock(x,y);
                break;
        }
        return result;
    }
}
