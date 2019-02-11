package cs3500.marblesolitaire.model.hw02;

/**
 * Represents a Orthogonal direction of movement for a Marble to move in.
 */
public enum OrthogonalDir {
  UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

  private int rSign;
  private int cSign;

  /**
   * Represents a Orthogonal direction of movement for a Marble to move in.
   *
   * @param rSign the sign of the change in row # for when a piece moves in this direction.
   *              (magnitude will be set to 1 if it not already).
   * @param cSign the sign of the change in column # for when a piece moves in this direction.
   *              (magnitude will be set to 1 if it not already).
   */
  OrthogonalDir(int rSign, int cSign) {

    //self-check: code will not compile w/ non-orthogonal directions
    if (rSign != 0 && cSign != 0) {
      throw new IllegalArgumentException("Marble Solitaire moves must only be in one direction");
    }

    this.rSign = rSign / Math.max(Math.abs(rSign), 1); //avoid div by 0 w/o changing behavior
    this.cSign = cSign / Math.max(Math.abs(cSign), 1); //avoid div by 0 w/o changing behavior
  }

  /**
   * Get the sign of the change in row # for when a piece moves in this direction.
   *
   * @return -1 or 1(the sign of the change in row # for when a piece moves in this direction)
   */
  public int rowMove() {
    return rSign;
  }

  /**
   * Get the sign of the change in column # for when a piece moves in this direction.
   *
   * @return -1 or 1(the sign of the change in column # for when a piece moves in this direction)
   */
  public int columnMove() {
    return cSign;
  }
}
