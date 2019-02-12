package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.RowByColumnBoard;
import cs3500.marblesolitaire.model.posn.BoardPosn;
import cs3500.marblesolitaire.model.posn.NullPosn;
import cs3500.marblesolitaire.model.posn.Posn;
import cs3500.marblesolitaire.model.posn.PosnState;

public class EuropeanSolitaireModelImpl extends RowByColumnBoard {

  public EuropeanSolitaireModelImpl(){
    this(3,3,3);
  }

  public EuropeanSolitaireModelImpl(int size) {
    this(size, (3 * (size - 1)) / 2,
            (3 * (size - 1)) / 2);
  }

  public EuropeanSolitaireModelImpl(int sRow, int sCol){
    this(3,sRow, sCol);
  }

  public EuropeanSolitaireModelImpl(int size, int sRow, int sCol){
    this.size = size;
    this.board = new ArrayList<>();

    if (size % 2 != 1) {
      throw new IllegalArgumentException("Arm Thicknesses must be odd");
    }

    //out of range
    if (outOfRange(sRow, sCol)) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position(%d,%d)", sRow, sCol));
    }

    int boarder = size - 1 ;
    int fullRowCount = 0;

    for (int r = 0; r < realWidth(); r++) {
      ArrayList<Posn> nthRow = new ArrayList<>();

      for (int c = 0; c < realWidth(); c++) {

        boolean isEmpty = r == sRow && c == sCol;
        PosnState ps = (isEmpty ? PosnState.EMPTY : PosnState.FILLED);

        boolean isNull = (c < boarder || c > (realWidth() - boarder - 1));

        if(isNull){
          if(isEmpty){
            throw new IllegalArgumentException(
                    String.format("Invalid empty cell position(%d,%d)", sRow, sCol));
          }
          nthRow.add(new NullPosn(r,c));
        } else {
          nthRow.add(new BoardPosn(r,c,ps));
        }
      }

      if(boarder == 0) {
        fullRowCount++;
      }

      if(fullRowCount == 0) {
        boarder--;
      } else if (fullRowCount == size){
        boarder ++;
      }

      this.board.add(nthRow);
    }
  }
}
