package cs3500.marblesolitaire.model.hw02.posn;

/**
 * An AbstractPosn class to repsent a cartesian Position on the Marble Solitaire board.
 */
public abstract class AbstractPosn implements Posn {
  private int r;
  private int c;
  private PosnState state;

  /**
   * Any cartesian Position for a slot on the MarbleSolitairModel game(MSM).
   *
   * @param r         the row on the board of this slot (must be positive)
   * @param c         the column on the board of this slot (must be positive)
   * @param posnState the state of the slot
   */
  public AbstractPosn(int r, int c, PosnState posnState) {
    if (r < 0 || c < 0) {
      throw new IllegalArgumentException("A Posn Cannot be negative for Marble Solitaire");
    }

    this.r = r;
    this.c = c;
    this.state = posnState;
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
  public char getStateChar() {
    return getState().toChar();
  }

  @Override
  public PosnState getState() {
    return this.state;
  }

  @Override
  public void setState(PosnState ps) throws IllegalArgumentException {
    this.state = ps;
  }
}
