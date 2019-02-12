package cs3500.marblesolitaire.model.posn;

/**
 * Represents the state of a {@code Posn}/slot on the board.
 */
public enum PosnState {
  EMPTY('_'), FILLED('O'), NULL(' ');

  private char stateChar; // the state that represents this state

  /**
   * Creates PosnState enumeration.
   *
   * @param stateChar the state of this enum
   */
  PosnState(char stateChar) {
    this.stateChar = stateChar;
  }

  /**
   * Get the char reprsentation of this {@code PosnState}.
   *
   * @return the char representaiton of this {@code PosnState}.
   */
  public char toChar() {
    return this.stateChar;
  }
}
