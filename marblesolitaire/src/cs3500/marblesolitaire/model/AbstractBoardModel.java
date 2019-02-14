package cs3500.marblesolitaire.model;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.posn.BoardPosn;
import cs3500.marblesolitaire.model.posn.NullPosn;
import cs3500.marblesolitaire.model.posn.Posn;
import cs3500.marblesolitaire.model.posn.PosnState;
import cs3500.marblesolitaire.util.Utils;

public abstract class AbstractBoardModel implements MarbleSolitaireModel {

  protected ArrayList<ArrayList<Posn>> board;
  protected int size;

  /**
   * Creates a MS Model with an size set to the given value and the slot at the given place
   * empty to start.
   *
   * @param size the size of this MS model
   * @param sRow         the row of the starting empty slot.
   * @param sCol         the column of the startimg empty slot.
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

  protected ArrayList<ArrayList<Posn>> generateBoard(int size, int sRow, int sCol){
    ArrayList<ArrayList<Posn>> toBuild = new ArrayList<>();

    for (int r = 0; r < size; r++) {
      ArrayList<Posn> nthRow = new ArrayList<>();
      for (int c = 0; c < size; c++) {

        boolean isEmpty = r == sRow && c == sCol;
        PosnState ps = (isEmpty ? PosnState.EMPTY : PosnState.FILLED);

        if(!nullSlotCheck(r, c)){
          nthRow.add(new BoardPosn(r,c,ps));
        } else if(isEmpty){
          throw new IllegalArgumentException(
                  String.format("Invalid empty cell position(%d,%d)", sRow, sCol));
        } else{
          nthRow.add(new NullPosn(r,c));
        }
      }
      toBuild.add(nthRow);
    }

    return toBuild;
  }

  protected abstract boolean nullSlotCheck(int r, int c);

  /**
   * Get the Posn at the position at row = r, column = c.
   *
   * @param r the row to get from
   * @param c the column to get from
   * @return the Posn of this board at r, c
   * @throws IllegalArgumentException if r or c are out of the range of the board
   */
  protected Posn getFromBoard(int r, int c) throws IllegalArgumentException, IllegalStateException{
    if (outOfRange(r, c)) {
      throw new IllegalArgumentException(String.format("Can't get (%d, %d): out of range", r, c));
    }

    if(this.board.size() == 0){
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


  @Override
  /**
   * Move a single marble from a given position to another given position. A move is valid only if
   * the from and to positions are valid.
   *
   * A valid move satisfies 3 conditions:
   *  1. the to and from coordinates are in the range of the board.
   *  2. the movement is an orthogonal direction with a magnitude of 2 spaces in only 1 direction.
   *  3. states of slots are correct (you can't move into an occupied or Null slot. You can't move
   *  from an unoccupied or null spot).
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    //getFromBoard checks for cond #1
    Posn from = getFromBoard(fromRow, fromCol);
    Posn to = getFromBoard(toRow, toCol);

    //getValidJumped should check conds #2, #3
    int[] betweenCoords = Utils.requireNonNull(getValidJumped(fromRow, fromCol, toRow, toCol));

    Posn between = getFromBoard(betweenCoords[0], betweenCoords[1]);

    from.setState(PosnState.EMPTY);
    to.setState(PosnState.FILLED);
    between.setState(PosnState.EMPTY);
  }

  @Override
  public int[] getValidJumped(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {

    Posn origin = getFromBoard(fromRow, fromCol);
    Posn dest = getFromBoard(toRow, toCol);

    int diffs[] = validOrthoMoveBy2(origin, dest);
    Posn between = this.getFromBoard(origin.getRow() + (int) (diffs[0] / 2.0),
            origin.getColumn() + (int) (diffs[1] / 2.0));


    if (origin.checkJumpStates(between, dest)) { //&& origin.checkJumpDirection(diffs, realWidth() - 1);/
      int[] btwCoords = {between.getRow(), between.getColumn()};
      return btwCoords;
    } else {
      throw new IllegalArgumentException(String.format(
              "%s --(%s)--> %s contains positions with incorrect states",
              origin.toString(), between.toString(), dest.toString()));
    }
  }

  /**
   * Calculates the difference in the rows and columns between the origin and the dest {@code Posn}s
   * if moving from origin to the destination is a valid moves
   *
   * @param origin the posn where you are starting
   * @param dest   the posn where you are landing
   * @return an array of size = 2 where array[0] = rows moves and array[1] = column moves
   * @throws IllegalArgumentException if the movement between the orign and destination is not a an
   *                                  orthogonal move by 2
   */
  protected int[] validOrthoMoveBy2(Posn origin, Posn dest) throws IllegalArgumentException {
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
      throw new IllegalArgumentException(String.format("%s -> %s is not an orthogonal "
              + "move of 2 places", origin.toString(), dest.toString()));
    }

    return moves;
  }

  @Override
  public boolean isGameOver() {
    for (int originRow = 0; originRow < this.realWidth(); originRow++) {
      for (int originCol = 0; originCol < this.realWidth(); originCol++) {

        int destRows[] = {originRow - 2, originRow + 2, originRow, originRow};
        int destCols[] = {originCol, originCol, originCol - 2, originCol + 1};

        for (int i = 0; i < 4; i++) {
          try {
            getValidJumped(originRow, originCol, destRows[i], destCols[i]);
            return false;
          } catch (IllegalArgumentException e) {
            //do nothing
          }
        }

      }
    }
    return true;
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
