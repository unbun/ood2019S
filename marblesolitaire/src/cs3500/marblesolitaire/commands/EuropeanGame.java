package cs3500.marblesolitaire.commands;


import cs3500.marblesolitaire.MarbleSolitaire;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;

public class EuropeanGame implements ModelCommand {

    final private int size;
    final private int sRow;
    final private int sCol;

    public EuropeanGame(int size){
        this(size, MarbleSolitaire.INVALID_INTEGER, MarbleSolitaire.INVALID_INTEGER);

    }

    public EuropeanGame(int size, int sRow, int sCol){
        this.size = size;
        this.sRow = sRow;
        this.sCol = sCol;
    }

    @Override
    public MarbleSolitaireModel createModel() {
        if(sRow == MarbleSolitaire.INVALID_INTEGER && sCol == MarbleSolitaire.INVALID_INTEGER){
            return new EuropeanSolitaireModelImpl(size);
        } else {
            return new EuropeanSolitaireModelImpl(size, sRow, sCol);
        }
    }
}
