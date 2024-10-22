package testing.mocks;

import cs3500.marblesolitaire.model.AbstractBoardModel;
import java.util.Optional;


/**
 * A Mock MSM Game model that simply accepts positive intergers to move, and scores you based on the
 * number of moves that were completed.
 */
public class MockModel extends AbstractBoardModel {

  private int myFromRow;
  private int myFromCol;
  private int myToRow;
  private int myToCol;

  private int moveCount;

  private final int maxMoves;

  /**
   * Create an instance of a MockModel.
   *
   * @param maxMoves the max amount of moves that will determine if the game is over.
   */
  public MockModel(int maxMoves) {
    super(0, 0, 0, false, "this is a mock model");
    this.maxMoves = maxMoves;

    this.myFromRow = 0;
    this.myFromCol = 0;
    this.myToRow = 0;
    this.myToCol = 0;
    this.moveCount = 0;
  }

  @Override
  protected boolean outOfRange(int r, int c) {
    return r < 0 || c < 0;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (fromRow < 0) {
      throw new IllegalArgumentException("Can't have negative fromRow");
    }
    if (fromCol < 0) {
      throw new IllegalArgumentException("Can't have negative fromCol");
    }
    if (toRow < 0) {
      throw new IllegalArgumentException("Can't have negative toRow");
    }
    if (toCol < 0) {
      throw new IllegalArgumentException("Can't have negative toCol");
    }

    this.myFromRow = fromRow;
    this.myFromCol = fromCol;
    this.myToRow = toRow;
    this.myToCol = toCol;
    this.moveCount++;
  }

  @Override
  protected boolean nullSlotCheck(int r, int c) {
    return false;
  }

  @Override
  public Optional<int[]> getValidJumped(int fromRow, int fromCol, int toRow, int toCol)
      throws IllegalArgumentException {
    int[] diffs = {toRow - fromRow, toCol - fromCol};
    return Optional.of(diffs);
  }

  @Override
  public boolean isGameOver() {
    return this.moveCount >= this.maxMoves;
  }

  @Override
  public String getGameState() {
    return String.format("(r=%d,c=%d) -> (r=%d, c=%d)",
        this.myFromRow, this.myFromCol, this.myToRow, this.myToCol);
  }

  @Override
  public int getScore() {
    return this.moveCount;
  }
}
