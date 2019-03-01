package cs3500.marblesolitaire.model.hw04;

import java.util.Optional;

import cs3500.marblesolitaire.model.AbstractBoardModel;
import cs3500.marblesolitaire.util.posn.Posn;
import cs3500.marblesolitaire.util.Utils;


/**
 * A Triangle Board is the same as a square cartesian board, it's just that the y-axis is tilted.
 */
public class TriangleSolitaireModelImpl extends AbstractBoardModel {


  /**
   * Default constructor for the Triangle board's model.
   */
  public TriangleSolitaireModelImpl() {
    this(5, 0, 0);
  }

  /**
   * Constructor for the Triangle's board with a customized number of rows, and the hole at the
   * top.
   *
   * @param nRows the number of rows
   */
  public TriangleSolitaireModelImpl(int nRows) {
    this(nRows, 0, 0);
  }

  /**
   * Constructor for the Triangle's board with a customized hole but default number of the rows.
   */
  public TriangleSolitaireModelImpl(int sRow, int sCol) {
    this(5, sRow, sCol);
  }

  /**
   * Constructor for the Triangles board with a customized hole and number of rows.
   *
   * @param nRows the number of rows
   * @param sRow the row value for the hole
   * @param sCol the column value for the hole
   */
  public TriangleSolitaireModelImpl(int nRows, int sRow, int sCol) {
    super(nRows, sRow, sCol, (nRows < 0 || sRow < 0 || sCol < 0),
        String.format("Triangle Model can't be constructed with negative values (%d %d %d)",
            nRows, sRow, sCol));
  }

  @Override
  protected boolean nullSlotCheck(int r, int c) {
    return c > r;
  }

  @Override
  public String getGameState() {
    String[] regexed = super.getGameState().split("\n"); //regex the state into each line
    StringBuilder sb = new StringBuilder();

    //add margins to the beginning of each line to create a triangle shape
    int margin = realWidth() - 1;
    for (int rIdx = 0; rIdx < regexed.length; rIdx++) {

      //space out the margin of this row
      for (int i = 0; i < margin; i++) {
        sb.append(" ");
      }
      margin--;

      sb.append(regexed[rIdx]);

      if (rIdx != regexed.length - 1) {
        sb.append("\n");
      }
    }

    return sb.toString();
  }

  @Override
  protected Optional<int[]> validOrthoMoveBy2(Posn origin, Posn dest)
      throws IllegalArgumentException {
    Utils.requireNonNull(origin);
    Utils.requireNonNull(dest);

    Optional<int[]> maybeMoves = super.validOrthoMoveBy2(origin, dest);
    if (maybeMoves.isPresent()) { // move is a cartesian orthogonal direction
      return maybeMoves;
    } else { // move might be a diagonal movement

      int dRow = dest.getRow() - origin.getRow();
      int dCol = dest.getColumn() - origin.getColumn();
      int dRowSign = Utils.getSign(dRow);
      int dColSign = Utils.getSign(dCol);

      if (Math.abs(dRow) == 2 && Math.abs(dCol) == 2 && dRowSign == dColSign) {
        int[] moves = new int[2];
        moves[0] = dRow;
        moves[1] = dCol;
        return Optional.of(moves);
      } else {
        return Optional.empty();
      }

    }
  }


  /**
   * Get the rows that could be reached by jumping orthogonally by 2 from the given row in any valid
   * direction.<br>
   * <strong>THE ARRAY HAS VALUES FOR EACH DIRECTION THAT ARE SPECIFICALLY ORDERED IN THE FOLLOWING
   * WAY:</strong><br> {UP-LEFT, DOWN-RIGHT, LEFT, RIGHT, DOWN-LEFT, UP-RIGHT}.
   *
   * @param oRow the row that you are originating from
   * @return an array of rows that you can get in the <strong>THE ARRAY HAS VALUES FOR EACH
   *         DIRECTION THAT ARE SPECIFICALLY ORDERED IN THE FOLLOWING WAY:</strong><br>
   *         {UP-LEFT, DOWN-RIGHT, LEFT, RIGHT, DOWN-LEFT, UP-RIGHT}.
   */
  @Override
  protected int[] getOrthoJumpRows(int oRow) {
    int[] extra = {oRow + 2, oRow - 2};
    return Utils.concat(super.getOrthoJumpRows(oRow), extra);
  }

  /**
   * Get the columns that could be reached by jumping orthogonally by 2 from the given columns in
   * any valid direction.<br>
   * <strong>THE ARRAY HAS VALUES FOR EACH DIRECTION THAT ARE SPECIFICALLY ORDERED IN THE FOLLOWING
   * WAY:</strong><br> {UP-LEFT, DOWN-RIGHT, LEFT, RIGHT, DOWN-LEFT, UP-RIGHT}.
   *
   * @param oCol the columns that you are originating from
   * @return an array of columns that you can get in the <strong>THE ARRAY HAS VALUES FOR EACH
   *         DIRECTION THAT ARE SPECIFICALLY ORDERED IN THE FOLLOWING WAY:</strong><br>
   *         {UP-LEFT, DOWN-RIGHT, LEFT, RIGHT, DOWN-LEFT, UP-RIGHT}
   */
  @Override
  protected int[] getOrthoJumpColumns(int oCol) {
    int[] extra = {oCol + 2, oCol - 2};
    return Utils.concat(super.getOrthoJumpColumns(oCol), extra);
  }


  @Override
  protected int realWidth() {
    return this.size;
  }
}
