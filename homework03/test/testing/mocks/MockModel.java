package testing.mocks;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * A Mock MSM Game model that simply accepts positive intergers to move, and scores you based on the
 * number of moves that were completed.
 */
public class MockModel implements MarbleSolitaireModel {

  private int myFromRow;
  private int myFromCol;
  private int myToRow;
  private int myToCol;

  private int moveCount;

  private final int maxMoves;

  /**
   * Create an instance of a MockModel.
   * @param maxMoves the max amount of moves that will determine if the game is over.
   */
  public MockModel(int maxMoves) {
    this.maxMoves = maxMoves;

    this.myFromRow = 0;
    this.myFromCol = 0;
    this.myToRow = 0;
    this.myToCol = 0;
    this.moveCount = 0;
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
