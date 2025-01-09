package Modele;

public class TxtConverterForest extends TxtConverter {
    public TxtConverterForest(Factory factory) {
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
            case 'E':
                result[0] = facto.instanciateEmptySpace(x,y);
                result[1] = facto.instanciateAnimal(x,y);
                break;
            case 'A':
                result[0] = facto.instanciateTree(x,y);
                break;
            case 'B':
                result[0] = facto.instanciateDecoration(x,y);
                break;
            case 'G':
                result[0] = facto.instanciateFruit(x,y);
                break;
            case ' ':
                result[0] = facto.instanciateEmptySpace(x,y);
                break;
            case 'C':
                result[0] = facto.instanciateMushroom(x,y);
                break;
            case 'H':
                result[0] = facto.instanciateEmptySpace(x,y);
                result[1] = facto.instanciatePredator(x,y,0);
                break;
            case 'R':
                result[0] = facto.instanciateEmptySpace(x,y);
                result[1] = facto.instanciatePredator(x,y,1);
                break;
            case '*':
                result[0] = facto.instanciateRareRock(x,y);
                break;
        }
        return result;
    }
}
