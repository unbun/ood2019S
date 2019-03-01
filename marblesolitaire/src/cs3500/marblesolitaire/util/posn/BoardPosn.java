package cs3500.marblesolitaire.util.posn;

/**
 * A BoardPosn represents a valid slot on the Solitare Board.
 */
public class BoardPosn extends AbstractPosn {

  /**
   * A BoardPosn represents a valid slot on the Solitare Board.
   *
   * @param r         the row on the board of this slot
   * @param c         the column on the board of this slot
   * @param posnState the state of this slot
   */
  public BoardPosn(int r, int c, PosnState posnState) {
    super(r, c, posnState);
  }

  @Override
  public boolean checkJumpStates(Posn neighbor, Posn landing) {
    return getState() == PosnState.FILLED
            && neighbor.getState() == PosnState.FILLED
            && landing.getState() == PosnState.EMPTY;
  }

  @Override
  public String toString() {
    String format = (getState() == PosnState.FILLED ? "[%d, %d ]" : "[%d, %d*]");
    return String.format(format, getRow(), getColumn());
  }
}
