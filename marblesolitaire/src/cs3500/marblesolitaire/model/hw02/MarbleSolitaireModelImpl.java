package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.RowByColumnBoard;

import cs3500.marblesolitaire.model.posn.BoardPosn;
import cs3500.marblesolitaire.model.posn.NullPosn;
import cs3500.marblesolitaire.model.posn.Posn;
import cs3500.marblesolitaire.model.posn.PosnState;


/**
 * Implementation of A MarbleSolitare(MS) Model Game. Keeps track of the state of an MS Game as
 * different moves (consisting of from column, from row, to column, and to row values) are attempted
 * on a board of slots. Each move represents moving a game piece over another and into a previously
 * empty slot, which results in the jumped-over piece being removed.
 */
public class MarbleSolitaireModelImpl extends RowByColumnBoard {



  /**
   * Creates a MS Model with an size of 3 and the center slot empty to start.
   */
  public MarbleSolitaireModelImpl() {
    this(3);
  }

  /**
   * Creates a MS Model with an size of 3 and the slot at the given place empty to start.
   *
   * @param sRow the row of the starting empty slot.
   * @param sCol the column of the startimg empty slot.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Creates a MS Model with an size set to the given value and the center slot empty to
   * start. The center is determined by the find int c = 3 * (size - 1) / 2, where c is the
   * value for sRow and sCol.
   *
   * @param size the size of this MS Model
   */
  public MarbleSolitaireModelImpl(int size) {
    this(size, (3 * (size - 1)) / 2,
            (3 * (size - 1)) / 2);
  }

  /**
   * Creates a MS Model with an size set to the given value and the slot at the given place
   * empty to start.
   *
   * @param size the size of this MS model
   * @param sRow         the row of the starting empty slot.
   * @param sCol         the column of the startimg empty slot.
   */
  public MarbleSolitaireModelImpl(int size, int sRow, int sCol) {
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

    for (int r = 0; r < realWidth(); r++) {
      ArrayList<Posn> nthRow = new ArrayList<>();
      for (int c = 0; c < realWidth(); c++) {

        boolean isEmpty = r == sRow && c == sCol;
        PosnState ps = (isEmpty ? PosnState.EMPTY : PosnState.FILLED);
        int endOfArm = 2 * size - 1;

        if (c >= size - 1 && c < endOfArm) {
          nthRow.add(new BoardPosn(r, c, ps));
        } else if (r >= size - 1 && r < endOfArm) {
          nthRow.add(new BoardPosn(r, c, ps));
        } else {
          if (isEmpty) { //isEmpty is null
            throw new IllegalArgumentException(
                    String.format("Invalid empty cell position(%d,%d)", sRow, sCol));
          }
          nthRow.add(new NullPosn(r, c));
        }
      }
      this.board.add(nthRow);
    }
  }


}
