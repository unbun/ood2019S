package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.posn.BoardPosn;
import cs3500.marblesolitaire.model.hw02.posn.NullPosn;
import cs3500.marblesolitaire.model.hw02.posn.Posn;
import cs3500.marblesolitaire.model.hw02.posn.PosnState;

/**
 * Implementation of A MarbleSolitare Model (MSM) Game.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {

  private final int armThickness;
  private ArrayList<ArrayList<Posn>> board;

  /**
   * Creates a MSM with an armThickness of 3 and the center slot empty to start.
   */
  public MarbleSolitaireModelImpl() {
    this(3);
  }

  /**
   * Creates a MSM with an armThickness of 3 and the slot at the given place empty to start.
   *
   * @param sRow the row of the starting empty slot.
   * @param sCol the column of the startimg empty slot.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Creates a MSM with an armThickness set to the given value and the center slot empty to start.
   *
   * @param armThickness the armThickness of this MSM
   */
  public MarbleSolitaireModelImpl(int armThickness) {
    this(armThickness, (3 * (armThickness - 1)) / 2,
            (3 * (armThickness - 1)) / 2);
  }

  /**
   * Creates a MSM with an armThickness set to the given value and the slot at the given place empty
   * to start.
   *
   * @param armThickness the armThickness of this MSM
   * @param sRow         the row of the starting empty slot.
   * @param sCol         the column of the startimg empty slot.
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    this.armThickness = armThickness;
    this.board = new ArrayList<>();

    if (armThickness % 2 != 1) {
      throw new IllegalArgumentException("Arm Thicknesses must be odd");
    }

    //out of range
    if (outOfRange(sRow, sCol)) {
      throw new IllegalArgumentException(
              String.format("Invalid empty cell position(%d,%d)", sRow, sCol));
    }

    for (int r = 0; r < realWidth(); r++) {
      ArrayList<Posn> nthRow = new ArrayList<>();
      for (int c = 0; c < realWidth(); c++) {

        boolean isEmpty = r == sRow && c == sCol;
        PosnState ps = (isEmpty ? PosnState.EMPTY : PosnState.FILLED);
        int endOfArm = 2 * armThickness - 1;

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
  }

  /**
   * Checks if the given row and column are outside of the square area of the board.
   *
   * @param r the row to check
   * @param c the column to check
   * @return if the Posn represented by row=r column=c is on the board
   */
  private boolean outOfRange(int r, int c) {
    return r >= realWidth() || r < 0 || c >= realWidth() || c < 0;
  }

  /**
   * Get the Posn at the position at row = r, column = c.
   *
   * @param r the row to get from
   * @param c the column to get from
   * @return the Posn of this board at r, c
   * @throws IllegalArgumentException if r or c are out of the range of the board
   */
  private Posn getFromBoard(int r, int c) throws IllegalArgumentException {
    if (outOfRange(r, c)) {
      throw new IllegalArgumentException(String.format("Can't get (%d, %d): out of range", r, c));
    }
    return this.board.get(r).get(c);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    //1. in range of the board
    // this may be tested multiple times in this function, but it needs to be done to guarantee
    // that we can use getFromBoard() without an exception
    if (outOfRange(fromRow, fromCol) || outOfRange(toRow, toCol)) {
      throw new IllegalArgumentException(String.format("(%d, %d) -> (%d, %d) is out of range",
              fromRow, fromCol, toRow, toCol));
    }

    int dRow = toRow - fromRow;
    int dCol = toCol - fromCol;

    Posn from = getFromBoard(fromRow, fromCol);
    Posn to = getFromBoard(toRow, toCol);

    //2. orthogonal movement
    OrthogonalDir d;
    if (Math.abs(dRow) == 2 && Math.abs(dCol) == 0) {
      d = (dRow > 0 ? OrthogonalDir.DOWN : OrthogonalDir.UP);
    } else if (Math.abs(dCol) == 2 && Math.abs(dRow) == 0) {
      d = (dCol > 0 ? OrthogonalDir.RIGHT : OrthogonalDir.LEFT);
    } else {
      throw new IllegalArgumentException(String.format("%s -> %s is not a orthogonal "
              + "move of 2 places", from.toString(), to.toString()));
    }

    //3. states of slots are correct
    if (moveAvailable(d, from)) {
      from.setState(PosnState.EMPTY);
      to.setState(PosnState.FILLED);
      this.getFromBoard(fromRow + d.rowMove(), fromCol + d.columnMove()).setState(PosnState.EMPTY);
    } else {
      throw new IllegalArgumentException(String.format("%s -> %s is not in the "
              + "correct getState or is in an invalid direction", from.toString(), to.toString()));
    }
  }

  /**
   * Is it possible for a marble at the given origin to move in the given direction?.
   *
   * @param d      the {@code OrthogonalDir} that you are trying to move in.
   * @param origin the {@code Posn}/slot that you are trying to move from
   * @return can you move from the origin in the direction
   */
  private boolean moveAvailable(OrthogonalDir d, Posn origin) {
    int oRow = origin.getRow();
    int oColumn = origin.getColumn();

    if (outOfRange(oRow + d.rowMove(), oColumn + d.columnMove())
            || outOfRange(oRow + (2 * d.rowMove()), oColumn + (2 * d.columnMove()))) {
      return false;
    }

    Posn between = this.getFromBoard(oRow + d.rowMove(), oColumn + d.columnMove());
    Posn dest =
            this.getFromBoard(oRow + (2 * d.rowMove()), oColumn + (2 * d.columnMove()));

    boolean stateCheck = origin.checkJumpStates(between, dest);
    boolean directionCheck = origin.checkJumpDirection(d, this.realWidth() - 1);

    return directionCheck && stateCheck;
  }

  @Override
  public boolean isGameOver() {
    for (int r = 0; r < this.realWidth(); r++) {
      for (int c = 0; c < this.realWidth(); c++) {
        Posn origin = this.getFromBoard(r, c);

        boolean playable = moveAvailable(OrthogonalDir.UP, origin)
                || moveAvailable(OrthogonalDir.DOWN, origin)
                || moveAvailable(OrthogonalDir.LEFT, origin)
                || moveAvailable(OrthogonalDir.RIGHT, origin);

        if (playable) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public String getGameState() {
    StringBuilder sb = new StringBuilder();

    for (int r = 0; r < this.realWidth(); r++) {

      boolean startedRow = false; // started the non-NullPosns for a row

      for (int c = 0; c < this.realWidth(); c++) {
        Posn curr = getFromBoard(r, c);

        //end a row after the non-Nulls have been drawn, and a NullPosn is hit
        if (curr.getState() != PosnState.NULL) {
          startedRow = true;
        }
        if (startedRow && curr.getState() == PosnState.NULL) {
          break;
        }

        //put a space before the state char as long as it's not the first of a row
        if (c != 0) { //not at the beginning or null
          sb.append(" ");
        }
        sb.append(curr.getStateChar());
      }
      if (r != this.realWidth() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  //Positional Formulas

  @Override
  public int getScore() {
    int score = 0;
    for (ArrayList<Posn> row : this.board) {
      for (Posn p : row) {
        if (p.getState() == PosnState.FILLED) {
          score++;
        }
      }
    }
    return score;
  }

  /**
   * The real width of the board. (the width of the whole square that the center square and arms
   * create).
   *
   * @return the width of the entire board (end of arm to end of arm.
   */
  private int realWidth() {
    return 3 * armThickness - 2;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    int rowCount = 0;
    for (ArrayList<Posn> row : this.board) {
      rowCount++;
      for (Posn p : row) {
        sb.append(p.toString());
      }
      if (rowCount < this.realWidth()) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }
}
