package cs3500.marblesolitaire.model.hw02.posn;

/**
 * Any Cartisian Location on a square Marble Board.
 */
public interface Posn {

  /**
   * Can a piece from this position jump to a landing position over it's neighbor, according to
   * their states of occupance? Assuming that the given neighbor is a true orthoganal neighbor and
   * the landing is 2 spaces away orthoganally on the board.
   *
   * @param neighbor a posn for a piece next to this one
   * @param landing  a posn for a piece 2 away from this (a valid landing spot)
   * @return can this jump take place according to the occupance/empty-related rules of a move?
   */
  boolean checkJumpStates(Posn neighbor, Posn landing);

  /**
   * Get the row this {@code Posn} is in.
   *
   * @return the row this {@code Posn} is in
   */
  int getRow();

  /**
   * Get the column this {@code Posn} is in.
   *
   * @return the column this {@code Posn} is in
   */
  int getColumn();

  /**
   * Get the state of {@code this} Posn.
   *
   * @return true if the slot is occupied, false if not
   */
  PosnState getState();

  /**
   * Change the state of occupance of this {@code Posn}.
   *
   * @param ps the state of occupance that this {@code Posn} should now have
   * @throws IllegalArgumentException if attempting to occupy/empty a Null position
   */
  void setState(PosnState ps) throws IllegalArgumentException;

  /**
   * Get the getState of this {@code Posn} represented as a char. '_' = empty/not occupied.'0' =
   * occupied.' '(space) = null.
   *
   * @return the charector to represent this position
   */
  char getStateChar();

  /**
   * Show this position in the following format(where r and c represent it's row and column): [r,c ]
   * if it is occupied [r,c*] if it is not occupied and [null] if it is null. This format is useful
   * for debugging
   */
  @Override
  String toString();
}
