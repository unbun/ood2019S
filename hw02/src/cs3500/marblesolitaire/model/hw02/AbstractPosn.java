package cs3500.marblesolitaire.model.hw02;

/**
 * An AbstractPosn class to repsent a cartesian Position on the Marble Solitaire board.
 */
public abstract class AbstractPosn implements Posn {
  private int r;
  private int c;
  private boolean occupied;

  /**
   * Any cartesian Position for a slot on the MarbleSolitairModel game(MSM).
   *
   * @param r        the row on the board of this slot (must be positive)
   * @param c        the column on the board of this slot (must be positive)
   * @param occupied if this slot is occupied by a marble or not
   */
  public AbstractPosn(int r, int c, boolean occupied) {
    if (r < 0 || c < 0) {
      throw new IllegalArgumentException("A Posn Cannot be negative for Marble Solitaire");
    }

    this.r = r;
    this.c = c;
    this.occupied = occupied;
  }

  @Override
  public boolean isNull() {
    return false;
  }

  @Override
  public int getRow() {
    return this.r;
  }

  @Override
  public int getColumn() {
    return this.c;
  }

  @Override
  public boolean isOccupied() {
    return this.occupied;
  }

  @Override
  public void setOccupied(boolean b) throws IllegalArgumentException {
    this.occupied = b;
  }
}
