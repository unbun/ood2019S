package cs3500.marblesolitaire.model.hw02;

/**
 * A BoardPosn represents a valid slot on the Solitare Board.
 */
public class BoardPosn extends AbstractPosn {

  /**
   * A BoardPosn represents a valid slot on the Solitare Board.
   *
   * @param r        the row on the board of this slot
   * @param c        the column on the board of this slot
   * @param occupied if this slot is occupied by a marble or not
   */
  public BoardPosn(int r, int c, boolean occupied) {
    super(r, c, occupied);
  }

  @Override
  public boolean checkJumpStates(Posn neighbor, Posn landing) {
    if (isOccupied()) {
      return neighbor.isOccupied() && !landing.isOccupied()
              && !neighbor.isNull() && !landing.isNull();
    } else {
      return false;
    }
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
        return false; // cannot jump in any direction except for the 4 orthogonal ones
    }
  }

  @Override
  public char getStateChar() {
    return (isOccupied() ? 'O' : '_');
  }

  @Override
  public String toString() {
    String format = (isOccupied() ? "[%d, %d ]" : "[%d, %d*]");
    return String.format(format, getRow(), getColumn());
  }
}
