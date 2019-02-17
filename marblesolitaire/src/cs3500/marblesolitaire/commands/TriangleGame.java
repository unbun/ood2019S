package cs3500.marblesolitaire.commands;


import cs3500.marblesolitaire.MarbleSolitaire;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

public class TriangleGame implements Command{

    final private int size;
    final private int sRow;
    final private int sCol;

    public TriangleGame(int size){
        this(size, MarbleSolitaire.INVALID_INTEGER, MarbleSolitaire.INVALID_INTEGER);

    }

    public TriangleGame(int size, int sRow, int sCol){
        this.size = size;
        this.sRow = sRow;
        this.sCol = sCol;
    }

    @Override
    public MarbleSolitaireModel createModel() {
        if(sRow == MarbleSolitaire.INVALID_INTEGER && sCol == MarbleSolitaire.INVALID_INTEGER){
            return new TriangleSolitaireModelImpl(size);
        } else {
            return new TriangleSolitaireModelImpl(size, sRow, sCol);
        }
    }
}
