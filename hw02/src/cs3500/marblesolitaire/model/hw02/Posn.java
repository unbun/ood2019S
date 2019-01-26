package cs3500.marblesolitaire.model.hw02;

/**
 * Any Cartisian Location on a square Marble Board.
 */
public interface Posn {

  /**
   * Is this position null (i.e. can it be slot?).
   *
   * @return Is this position null (i.e. can it be slot?).
   */
  boolean isNull();

  /**
   * Can a piece from this position jump to a landing position over it's neighbor, according to
   * their states of occupance? Assuming that the given neighbor is a true orthoganal neighbor and
   * the landing is a valid place to land.
   *
   * @param neighbor a posn for a piece next to this one
   * @param landing  a posn for a piece 2 away from this (a valid landing spot)
   * @return can this jump take place according to the occupance/empty-related rules of a move?
   */
  boolean checkJumpStates(Posn neighbor, Posn landing);

  /**
   * Can a piece from this position jump in the given direction without going out of range? The
   * range is defined as the give maxPosition as the max, and 0 as the min (inclusive).
   *
   * @param d           the {@code OrthogonalDir} that the piece is trying to jump in
   * @param maxPosition the maximum value any piece can be without being out of bounds (doesn't
   *                    check if it's jumping into null)
   * @return is this valid direction to jump into?
   */
  boolean checkJumpDirection(OrthogonalDir d, int maxPosition);

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
   * Is this Posn/Slot occupied by a piece?.
   *
   * @return true if the slot is occupied, false if not
   */
  boolean isOccupied();

  /**
   * Change the state of occupance of this {@code Posn}.
   *
   * @param b the state of occupance that this {@code Posn} should now have
   * @throws IllegalArgumentException if attempting to occupy a Null position
   */
  void setOccupied(boolean b) throws IllegalArgumentException;

  /**
   * Get the state of this {@code Posn} represented as a char. '_' -> empty/not occupied.'0' ->
   * occupied.' '(space) -> null.
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
