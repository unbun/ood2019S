import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class AbstractTestMarbleSolitaireModel {

  /**
   * Run a list of valid moves on the default board and assert that they are changing the score
   * properly. Each array must be the same length.
   *
   * @param msm      the MarbleSolitarieModel with the board to move on
   * @param fRows    ordered list of fromRow values for valid moves
   * @param fColumns ordered list of fromCol values for valid moves
   * @param tRows    ordered list of toRow values for valid moves
   * @param tColumns ordered list of toCol values for valid moves
   */
  protected void runValidMoves(MarbleSolitaireModel msm,
                             int[] fRows, int[] fColumns,
                             int[] tRows, int[] tColumns) {
    int initScore = msm.getScore();

    if (fRows.length != fColumns.length
            && fColumns.length != tRows.length
            && tRows.length != tColumns.length) {
      fail("Invalid test: movement arrays must be the same length!");
    }

    for (int i = 0; i < fRows.length; i++) {
      assertEquals(initScore - i, msm.getScore());
      assertFalse(msm.isGameOver());
      msm.move(fRows[i], fColumns[i], tRows[i], tColumns[i]);
      assertEquals((initScore - i) - 1, msm.getScore());
    }
  }

  /**
   * Run a list of valid moves on the default board and assert that they aren't changing the score
   * or getState. Each array must be the same length.
   *
   * @param msm      the MarbleSolitarieModel with the board to move on
   * @param fRows    ordered list of fromRow values for invalid moves
   * @param fColumns ordered list of fromCol values for invalid moves
   * @param tRows    ordered list of toRow values for invalid moves
   * @param tColumns ordered list of toCol values for invalid moves
   */
  protected void runInvalidMoves(MarbleSolitaireModel msm,
                               int[] fRows, int[] fColumns,
                               int[] tRows, int[] tColumns) {
    int initScore = msm.getScore();
    String initState = msm.getGameState();

    if (fRows.length != fColumns.length
            && fColumns.length != tRows.length
            && tRows.length != tColumns.length) {
      fail("Invalid test: movement arrays must be the same length!");
    }

    for (int i = 0; i < fRows.length; i++) {
      assertFalse(msm.isGameOver());
      try {
        msm.move(fRows[i], fColumns[i], tRows[i], tColumns[i]);
        fail(String.format("Invalid move #%d (%d, %d) -> (%d, %d) was not caught",
                i, fRows[i], fColumns[i], tRows[i], tColumns[i]));
      } catch (IllegalArgumentException e) {
        assertEquals(initState, msm.getGameState());
        assertEquals(initScore, msm.getScore());
      }
    }
  }
}
