package cs3500.marblesolitaire.model.hw02.posn;

import cs3500.marblesolitaire.model.hw02.OrthogonalDir;

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
  public boolean checkJumpDirection(OrthogonalDir d, int maxPosition) {
    switch (d) {
      case UP:
        return this.getRow() > 1;
      case DOWN:
        return this.getRow() < maxPosition - 1;
      case LEFT:
        return this.getColumn() > 1;
      case RIGHT:
        return this.getColumn() < maxPosition - 1;
      default:
        return false; // required by style, cannot happen for enumerated OrthDir
    }
  }

  @Override
  public String toString() {
    String format = (getState() == PosnState.FILLED ? "[%d, %d ]" : "[%d, %d*]");
    return String.format(format, getRow(), getColumn());
  }
}
