package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.RowByColumnBoard;

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
    super(3, sRow, sCol);
  }

  /**
   * Creates a MS Model with an size set to the given value and the center slot empty to
   * start. The center is determined by the find int c = 3 * (size - 1) / 2, where c is the
   * value for sRow and sCol.
   *
   * @param size the size of this MS Model
   */
  public MarbleSolitaireModelImpl(int size) {
    super(size, (3 * (size - 1)) / 2,
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
    super(size, sRow, sCol);
  }


  @Override
  protected boolean nullSlotCheck(int r, int c) {
    boolean rowCheck = (r >= size- 1 && r < 2 * size - 1);
    boolean columnCheck = (c >= size - 1 && c < 2* size - 1);

    return columnCheck || rowCheck;
  }


}
