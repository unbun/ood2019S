package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.MarbleSolitaire;
import cs3500.marblesolitaire.model.AbstractBoardModel;
import cs3500.marblesolitaire.model.posn.Posn;
import cs3500.marblesolitaire.model.posn.PosnState;
import cs3500.marblesolitaire.util.Utils;


/**
 * I noticed that a Triangle Board is the same as a square cartesian board, it's just that the
 * y-axis is tilted.
 */
public class TriangleSolitaireModelImpl extends AbstractBoardModel {

  public TriangleSolitaireModelImpl(){
    this(5,0,0);
  }

  public TriangleSolitaireModelImpl(int nRows) {
    this(nRows, 0, 0);
  }

  public TriangleSolitaireModelImpl(int sRow, int sCol){
    this(5, sRow, sCol);
  }


  public TriangleSolitaireModelImpl(int nRows, int sRow, int sCol){
    super(nRows, sRow, sCol, (nRows < 0 || sRow < 0 || sCol < 0),
            String.format( "Triangle Model can't be constructed with negative values (%d %d %d)",
                    nRows, sRow, sCol));
  }

  protected boolean nullSlotCheck(int r, int c){
    return c > r;
  }

  @Override
  public String getGameState() {
    String[] regexed = super.getGameState().split("\n"); //regex the state into each line
    StringBuilder sb = new StringBuilder();

    //add margins to the beginning of each line to create a triangle shape
    int margin = realWidth() - 1;
    for(int rIdx = 0; rIdx < regexed.length; rIdx++){

      //space out the margin of this row
      for(int i = 0; i < margin; i++){
        sb.append(" ");
      }
      margin--;

      sb.append(regexed[rIdx]);

      if(rIdx != regexed.length - 1){
        sb.append("\n");
      }
    }

    return sb.toString();
  }

  @Override
  public int[] getValidJumped(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    return super.getValidJumped(fromRow, fromCol, toRow, toCol);
  }

  @Override
  protected int[] validOrthoMoveBy2(Posn origin, Posn dest) throws IllegalArgumentException {
    Utils.requireNonNull(origin);
    Utils.requireNonNull(dest);

    int[] moves = new int[2];

    try {
      moves = super.validOrthoMoveBy2(origin, dest);
      return moves;
    } catch (IllegalArgumentException ae){

      int dRow = dest.getRow() - origin.getRow();
      int dCol = dest.getColumn() - origin.getColumn();
      int dRowSign = Utils.getSign(dRow);
      int dColSign = Utils.getSign(dCol);

      if (Math.abs(dRow) == 2 && Math.abs(dCol) == 2 && dRowSign == dColSign) {
        moves[0] = dRow;
        moves[1] = dCol;
        return moves;
      } else {
        throw new IllegalArgumentException(String.format("%s -> %s is not in a valid direction or "
                + "is not a move of 2 places", origin.toString(), dest.toString()));
      }
    }
  }

  @Override
  protected int realWidth() {
    return this.size;
  }
}
