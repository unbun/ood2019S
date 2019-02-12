package cs3500.marblesolitaire.model.hw02.posn;

/**
 * A Spot on the Cartesian Square Board that is not a part of the center square or the arms. In
 * other words, the locations in the corners of a square board that are not slots. Having these
 * NullPosns allows for a 2D ArrayList to have indices that line up with the rows and columns of
 * every slot.
 */
public class NullPosn extends AbstractPosn {

  /**
   * A Spot on the Cartesian Square Board that is not a part of the center square or the arms. In
   * other words, the locations in the corners of a square board that are not slots.
   *
   * @param r the row on the board of this slot
   * @param c the column on the board of this slot
   */
  public NullPosn(int r, int c) {
    super(r, c, PosnState.NULL); // a NullPosn can never be occupied
  }

  @Override
  public boolean checkJumpStates(Posn neighbor, Posn landing) {
    return false;
  }

  @Override
  public void setState(PosnState ps) throws IllegalArgumentException {
    if (ps != PosnState.NULL) {
      throw new IllegalArgumentException("NullPosn cannot have another State");
    }
  }

  @Override
  public String toString() {
    return "[null ]";
  }
}
