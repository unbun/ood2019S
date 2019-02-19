package cs3500.marblesolitaire.commands;


import cs3500.marblesolitaire.MarbleSolitaire;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;

public class EuropeanGame implements ModelCommand {

  private final int size;
  private final int sRow;
  private final int sCol;

  /**
   * Command to create an european model with the given size and a default hole.
   *
   * @param size the specified size
   */
  public EuropeanGame(int size) {
    this(size, MarbleSolitaire.INVALID_INTEGER, MarbleSolitaire.INVALID_INTEGER);

  }

  /**
   * Command to create an european model with a given size and the given hole.
   *
   * @param size the specified size
   * @param sRow the row coordinate of the specified hole
   * @param sCol the column coordinate of the specified hole
   */
  public EuropeanGame(int size, int sRow, int sCol) {
    this.size = size;
    this.sRow = sRow;
    this.sCol = sCol;
  }

  @Override
  public MarbleSolitaireModel createModel() {
    if (sRow == MarbleSolitaire.INVALID_INTEGER && sCol == MarbleSolitaire.INVALID_INTEGER) {
      return new EuropeanSolitaireModelImpl(size);
    } else {
      return new EuropeanSolitaireModelImpl(size, sRow, sCol);
    }
  }
}
