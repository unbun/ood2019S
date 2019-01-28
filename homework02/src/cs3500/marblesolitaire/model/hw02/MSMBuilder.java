package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.posn.BoardPosn;
import cs3500.marblesolitaire.model.hw02.posn.NullPosn;
import cs3500.marblesolitaire.model.hw02.posn.Posn;
import cs3500.marblesolitaire.model.hw02.posn.PosnState;

public final class MSMBuilder {

  private int armThickness;
  private int sRow;
  private int sCol;

  private ArrayList<ArrayList<Posn>> board;


  /**
   * Defualt construct for the builder. Sets parameters for a 3-thick board with a center empty
   * space
   */
  public MSMBuilder() {
    this(3,3,3);
  }

  /**
   * Creates a builder with custom parameters
   * @param armThickness the arm thickness of the game
   * @param sRow the starting empty space's row
   * @param sCol the starting empty spaces's column
   */
  public MSMBuilder(int armThickness, int sRow, int sCol) {
    this.armThickness = armThickness;
    this.sRow = sRow;
    this.sCol = sCol;
  }


  /**
   * Set the armthickness for the game.
   * @param armThickness the armthickness
   * @return {@code this} for method chaining
   */
  public MSMBuilder armThickness(int armThickness) {
    return new MSMBuilder(armThickness, (3 * (armThickness - 1)) / 2, (3 * (armThickness - 1)) / 2);
  }

  /**
   * Sets the starting empty colum for the game.
   * @param startMtCol the starting empty column
   * @return {@code this} for method chaining
   */
  public MSMBuilder sCol(int startMtCol) {
    return new MSMBuilder(this.armThickness, this.sRow, startMtCol);
  }

  /**
   * Sets the starting empty row for the game
   * @param startMtRow the starting empty row
   * @return {@code this} for method chaining
   */
  public MSMBuilder sRow(int startMtRow) {
    return new MSMBuilder(this.armThickness, startMtRow, this.sCol);
  }


  /**
   * Builds and returns the specified {@link MarbleSolitaireModelImpl}
   * @return a new {@code MarbleSolitarieModelImpl}
   * @throws IllegalArgumentException if any of the specifications are invalid
   */
  public MarbleSolitaireModelImpl build() throws IllegalArgumentException{
    this.board = new ArrayList<>();

    if (armThickness % 2 != 1) {
      throw new IllegalArgumentException("Arm Thickness must be odd");
    }

    boolean squareRange = sRow >= realWidth() || sRow < 0 || sCol >= realWidth() || sCol < 0;

    //out of range
    if (squareRange) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position(%d,%d) %d", sRow, sCol, realWidth()));
    }

    for (int r = 0; r < realWidth(); r++) {
      ArrayList<Posn> nthRow = new ArrayList<>();
      int endOfArm = 2 * armThickness - 1;

      for (int c = 0; c < realWidth(); c++) {

        boolean isEmpty = r == sRow && c == sCol;
        PosnState ps = (isEmpty? PosnState.EMPTY : PosnState.FILLED);

        if (c >= armThickness - 1 && c < endOfArm) {
          nthRow.add(new BoardPosn(r, c, ps));
        } else if (r >= armThickness - 1 && r < endOfArm) {
          nthRow.add(new BoardPosn(r, c, ps));
        } else {
          if (isEmpty) { //isEmpty is null
            throw new IllegalArgumentException(
                    String.format("Invalid empty cell position(%d,%d)", sRow, sCol));
          }
          nthRow.add(new NullPosn(r, c));
        }
      }
      this.board.add(nthRow);
    }

//    return new MarbleSolitaireModelImpl();
    return new MarbleSolitaireModelImpl(armThickness, board);
  }


  /*********************************/
  /** for type check constructors **/

  public int getArmThickness() {
    return armThickness;
  }

  public ArrayList<ArrayList<Posn>> getBoard(){
    return this.board;
  }

  /** for type check constructors **/
  /*********************************/


  /**
   * The real width of the board. (the width of the whole square that the center square and arms
   * create).
   *
   * @return the width of the entire board (end of arm to end of arm.
   */
  private int realWidth() {
    return 3 * armThickness - 2;
  }
}
