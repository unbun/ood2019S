package cs3500.marblesolitaire.model;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.util.posn.BoardPosn;
import cs3500.marblesolitaire.util.posn.NullPosn;
import cs3500.marblesolitaire.util.posn.Posn;
import cs3500.marblesolitaire.util.posn.PosnState;
import cs3500.marblesolitaire.util.Utils;

public abstract class AbstractBoardModel implements MarbleSolitaireModel {

  protected ArrayList<ArrayList<Posn>> board;
  protected int size;

  /**
   * Creates a MS Model with an size set to the given value and the slot at the given place empty to
   * start.
   *
   * @param size the size of this MS model
   * @param sRow the row of the starting empty slot.
   * @param sCol the column of the startimg empty slot.
   */
  public AbstractBoardModel(int size, int sRow, int sCol, boolean invalid, String errMsg) {
    this.size = size;
    this.board = new ArrayList<>();

    if (invalid) {
      throw new IllegalArgumentException(errMsg);
    }

    //out of range
    if (outOfRange(sRow, sCol)) {
      throw new IllegalArgumentException(
          String.format("Invalid empty cell position(%d,%d)", sRow, sCol));
    }
    this.board = generateBoard(realWidth(), sRow, sCol);

  }

  /**
   * Generate the ArrayList that represents a board with the given parameters.
   *
   * @param size the size of the board (number of rows and/or columns)
   * @param sRow the row that the hole is in
   * @param sCol the column that the hole is in
   * @return a size x size 2D array list of {@code Posn}s with an empty one at the specified coord
   */
  protected ArrayList<ArrayList<Posn>> generateBoard(int size, int sRow, int sCol) {
    ArrayList<ArrayList<Posn>> toBuild = new ArrayList<>();

    for (int r = 0; r < size; r++) {
      ArrayList<Posn> nthRow = new ArrayList<>();
      for (int c = 0; c < size; c++) {

        boolean isEmpty = r == sRow && c == sCol;
        PosnState ps = (isEmpty ? PosnState.EMPTY : PosnState.FILLED);

        if (!nullSlotCheck(r, c)) {
          nthRow.add(new BoardPosn(r, c, ps));
        } else if (isEmpty) {
          throw new IllegalArgumentException(
              String.format("Invalid empty cell position(%d,%d)", sRow, sCol));
        } else {
          nthRow.add(new NullPosn(r, c));
        }
      }
      toBuild.add(nthRow);
    }

    return toBuild;
  }

  /**
   * Should the Posn at the given coordinate be NULL?.
   *
   * @param r the row coord
   * @param c the column coord
   * @return if the given coord should have a Null posn or not
   */
  protected abstract boolean nullSlotCheck(int r, int c);

  /**
   * Get the Posn at the position at row = r, column = c.
   *
   * @param r the row to get from
   * @param c the column to get from
   * @return the Posn of this board at r, c
   * @throws IllegalArgumentException if r or c are out of the range of the board
   */
  protected Posn getFromBoard(int r, int c) throws IllegalArgumentException, IllegalStateException {
    if (outOfRange(r, c)) {
      throw new IllegalArgumentException(String.format("Can't get (%d, %d): out of range", r, c));
    }

    if (this.board.size() == 0) {
      System.out.println("\tDEBUG: " + r + ", " + c);
      throw new IllegalStateException("Board has not been created yet");
    }
    return this.board.get(r).get(c);
  }

  /**
   * Checks if the given row and column are outside of the square area of the board.
   *
   * @param r the row to check
   * @param c the column to check
   * @return if the Posn represented by row=r column=c is on the board
   */
  protected boolean outOfRange(int r, int c) {
    return r >= realWidth() || r < 0 || c >= realWidth() || c < 0;
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


  /**
   * Move a single marble from a given position to another given position. A move is valid only if
   * the from and to positions are valid. <br>
   *
   * <p>A valid move satisfies 3 conditions: 1. the to and from coordinates are in the range of the
   * board. 2. the movement is an orthogonal direction with a magnitude of 2 spaces in only 1
   * direction. 3. states of slots are correct (you can't move into an occupied or Null slot. You
   * can't move from an unoccupied or null spot).</p>
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow the row number of the position to be moved to (starts at 0)
   * @param toCol the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    //getFromBoard checks for cond #1
    Posn from = getFromBoard(fromRow, fromCol);
    Posn to = getFromBoard(toRow, toCol);

    //getValidJumped should check conds #2, #3
    Optional<int[]> maybeCoords = getValidJumped(fromRow, fromCol, toRow, toCol);
    if (!maybeCoords.isPresent()) {
      throw new IllegalArgumentException(String.format(
          "%s ----> %s involves positions with incorrect states or is not" +
              " an orthogonal move by 2 spaces",
          from.toString(), to.toString()));
    }

    int[] betweenCoords = maybeCoords.get();

    Posn between = getFromBoard(betweenCoords[0], betweenCoords[1]);

    from.setState(PosnState.EMPTY);
    to.setState(PosnState.FILLED);
    between.setState(PosnState.EMPTY);
  }

  /**
   * Examine the movement described by the parameters and determine which piece would be removed by
   * such a move (if it is valid) and return that piece's row and column coordinates. Using Optional
   * as the return type allows for use of the function without having to return null or throw a
   * {@code NullPointerException} on purpose for the sake of logical conditional design somewhere
   * else in the code. It also cuts down on the amount ot try-catches needed.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow the row number of the position to be moved to (starts at 0)
   * @param toCol the column number of the position to be moved to (starts at 0)
   * @return array[0] is the row number of the position of the removed piece (starts at 0) array[1]
   *         is the column number of the position of the removed piece (starts at 0).
   *         If the move is not possible, returns an empty Optional.
   * @throws IllegalArgumentException if the move is null
   */
  public Optional<int[]> getValidJumped(int fromRow, int fromCol, int toRow, int toCol) {

    if (outOfRange(fromRow, fromCol) || outOfRange(toRow, toCol)) {
      return Optional.empty();
    }

    Posn origin = getFromBoard(fromRow, fromCol);
    Posn dest = getFromBoard(toRow, toCol);

    Optional<int[]> maybeDiffs = validOrthoMoveBy2(origin, dest);
    if (!maybeDiffs.isPresent()) {
      return Optional.empty();
    }

    int[] diffs = maybeDiffs.get();
    Posn between = this.getFromBoard(origin.getRow() + (int) (diffs[0] / 2.0),
        origin.getColumn() + (int) (diffs[1] / 2.0));

    if (origin
        .checkJumpStates(between, dest)) { //&& origin.checkJumpDirection(diffs, realWidth() - 1);/
      int[] btwCoords = {between.getRow(), between.getColumn()};
      return Optional.of(btwCoords);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Calculates the difference in the rows and columns between the origin and the dest {@code Posn}s
   * if moving from origin to the destination is a valid moves.
   *
   * @param origin the posn where you are starting
   * @param dest the posn where you are landing
   * @return an array of size = 2 where array[0] = rows moves and array[1] = column moves
   * @throws IllegalArgumentException if the given {@code Posn}'s are null
   */
  protected Optional<int[]> validOrthoMoveBy2(Posn origin, Posn dest)
      throws IllegalArgumentException {
    Utils.requireNonNull(origin);
    Utils.requireNonNull(dest);

    int[] moves = new int[2];

    int dRow = dest.getRow() - origin.getRow();
    int dCol = dest.getColumn() - origin.getColumn();

    if (Math.abs(dRow) == 2 && Math.abs(dCol) == 0) {
      moves[0] = dRow;
    } else if (Math.abs(dCol) == 2 && Math.abs(dRow) == 0) {
      moves[1] = dCol;
    } else {
      return Optional.empty();
    }

    return Optional.of(moves);
  }

  @Override
  public boolean isGameOver() {
    for (int originRow = 0; originRow < this.realWidth(); originRow++) {
      for (int originCol = 0; originCol < this.realWidth(); originCol++) {

        int[] destRows = getOrthoJumpRows(originRow);
        int[] destCols = getOrthoJumpColumns(originCol);

        for (int i = 0; i < destRows.length; i++) {
          Optional<int[]> test = getValidJumped(originRow, originCol, destRows[i], destCols[i]);
          if (test.isPresent()) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Get the rows that could be reached by jumping orthogonally by 2 from the given row in any valid
   * direction.<br>
   * <strong>THE ARRAY HAS VALUES FOR EACH DIRECTION THAT ARE SPECIFICALLY ORDERED IN THE FOLLOWING
   * WAY:</strong><br> {UP, DOWN, LEFT, RIGHT}.
   *
   * @param oRow the row that you are originating from
   * @return an array of rows that you can get in the <strong>THE ARRAY HAS VALUES FOR EACH
   *          DIRECTION THAT ARE SPECIFICALLY ORDERED IN THE FOLLOWING WAY:</strong><br>
   *          {UP, DOWN, LEFT, RIGHT}
   */
  protected int[] getOrthoJumpRows(int oRow) {
    int[] rows = {oRow - 2, oRow + 2, oRow, oRow};
    return rows;
  }

  /**
   * Get the columns that could be reached by jumping orthogonally by 2 from the given columns in
   * any valid direction.<br>
   * <strong>THE ARRAY HAS VALUES FOR EACH DIRECTION THAT ARE SPECIFICALLY ORDERED IN THE FOLLOWING
   * WAY:</strong><br> {UP, DOWN, LEFT, RIGHT}.
   *
   * @param oCol the columns that you are originating from
   * @return an array of columns that you can get in the <strong>THE ARRAY HAS VALUES FOR EACH
   *         DIRECTION THAT ARE SPECIFICALLY ORDERED IN THE FOLLOWING WAY:</strong><br>
   *         {UP, DOWN, LEFT, RIGHT}
   */
  protected int[] getOrthoJumpColumns(int oCol) {
    int[] cols = {oCol, oCol, oCol - 2, oCol + 2};
    return cols;
  }


  /**
   * The real width of the board. (the width of the whole square that the center square and arms
   * create).
   *
   * @return the width of the entire board (end of arm to end of arm.
   */
  protected int realWidth() {
    return 3 * size - 2;
  }
}
