package cs3500.marblesolitaire.commands;


import cs3500.marblesolitaire.MarbleSolitaire;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

public class EnglishGame implements Command{

    final private int size;
    final private int sRow;
    final private int sCol;

    public EnglishGame(int size){
        this(size, MarbleSolitaire.INVALID_INTEGER, MarbleSolitaire.INVALID_INTEGER);

    }

    public EnglishGame(int size, int sRow, int sCol){
        this.size = size;
        this.sRow = sRow;
        this.sCol = sCol;
    }

    @Override
    public MarbleSolitaireModel createModel() {
        if(sRow == MarbleSolitaire.INVALID_INTEGER && sCol == MarbleSolitaire.INVALID_INTEGER){
            return new MarbleSolitaireModelImpl(size);
        } else {
            return new MarbleSolitaireModelImpl(size, sRow, sCol);
        }
    }
}
