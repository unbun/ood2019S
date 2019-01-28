import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestMarbleSolitaireModel {

  private MarbleSolitaireModel defBoard = new MarbleSolitaireModelImpl();
  private MarbleSolitaireModel board3 = new MarbleSolitaireModelImpl(4, 6);
  private MarbleSolitaireModel board5 = new MarbleSolitaireModelImpl(5, 5, 9);
  private MarbleSolitaireModel board1 = new MarbleSolitaireModelImpl(1);

  @Test
  public void boardGeneration() {
    assertEquals("[0, 0*]", board1.toString());

    assertEquals("[null ][null ][0, 2 ][0, 3 ][0, 4 ][null ][null ]\n"
                    + "[null ][null ][1, 2 ][1, 3 ][1, 4 ][null ][null ]\n"
                    + "[2, 0 ][2, 1 ][2, 2 ][2, 3 ][2, 4 ][2, 5 ][2, 6 ]\n"
                    + "[3, 0 ][3, 1 ][3, 2 ][3, 3*][3, 4 ][3, 5 ][3, 6 ]\n"
                    + "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][4, 5 ][4, 6 ]\n"
                    + "[null ][null ][5, 2 ][5, 3 ][5, 4 ][null ][null ]\n"
                    + "[null ][null ][6, 2 ][6, 3 ][6, 4 ][null ][null ]",
            defBoard.toString());

    assertEquals("[null ][null ][0, 2 ][0, 3 ][0, 4 ][null ][null ]\n"
                    + "[null ][null ][1, 2 ][1, 3 ][1, 4 ][null ][null ]\n"
                    + "[2, 0 ][2, 1 ][2, 2 ][2, 3 ][2, 4 ][2, 5 ][2, 6 ]\n"
                    + "[3, 0 ][3, 1 ][3, 2 ][3, 3 ][3, 4 ][3, 5 ][3, 6 ]\n"
                    + "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][4, 5 ][4, 6*]\n"
                    + "[null ][null ][5, 2 ][5, 3 ][5, 4 ][null ][null ]\n"
                    + "[null ][null ][6, 2 ][6, 3 ][6, 4 ][null ][null ]",
            board3.toString());

    assertEquals(
            "[null ][null ][null ][null ][0, 4 ][0, 5 ][0, 6 ][0, 7 ][0, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][1, 4 ][1, 5 ][1, 6 ][1, 7 ][1, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][2, 4 ][2, 5 ][2, 6 ][2, 7 ][2, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][3, 4 ][3, 5 ][3, 6 ][3, 7 ][3, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][4, 5 ][4, 6 ][4, 7 ][4, 8 ]"
                    + "[4, 9 ][4, 10 ][4, 11 ][4, 12 ]\n"
                    + "[5, 0 ][5, 1 ][5, 2 ][5, 3 ][5, 4 ][5, 5 ][5, 6 ][5, 7 ][5, 8 ]"
                    + "[5, 9*][5, 10 ][5, 11 ][5, 12 ]\n"
                    + "[6, 0 ][6, 1 ][6, 2 ][6, 3 ][6, 4 ][6, 5 ][6, 6 ][6, 7 ][6, 8 ]"
                    + "[6, 9 ][6, 10 ][6, 11 ][6, 12 ]\n"
                    + "[7, 0 ][7, 1 ][7, 2 ][7, 3 ][7, 4 ][7, 5 ][7, 6 ][7, 7 ][7, 8 ]"
                    + "[7, 9 ][7, 10 ][7, 11 ][7, 12 ]\n"
                    + "[8, 0 ][8, 1 ][8, 2 ][8, 3 ][8, 4 ][8, 5 ][8, 6 ][8, 7 ][8, 8 ]"
                    + "[8, 9 ][8, 10 ][8, 11 ][8, 12 ]\n"
                    + "[null ][null ][null ][null ][9, 4 ][9, 5 ][9, 6 ][9, 7 ][9, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][10, 4 ][10, 5 ][10, 6 ][10, 7 ][10, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][11, 4 ][11, 5 ][11, 6 ][11, 7 ][11, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][12, 4 ][12, 5 ][12, 6 ][12, 7 ][12, 8 ]"
                    + "[null ][null ][null ][null ]",
            board5.toString());

    assertEquals("[null ][null ][null ][null ][0, 4 ][0, 5 ][0, 6 ][0, 7 ][0, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][1, 4 ][1, 5 ][1, 6 ][1, 7 ][1, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][2, 4 ][2, 5 ][2, 6 ][2, 7 ][2, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][3, 4 ][3, 5 ][3, 6 ][3, 7 ][3, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][4, 5 ][4, 6 ][4, 7 ][4, 8 ][4, 9 ]"
                    + "[4, 10 ][4, 11 ][4, 12 ]\n"
                    + "[5, 0 ][5, 1 ][5, 2 ][5, 3 ][5, 4 ][5, 5 ][5, 6 ][5, 7 ][5, 8 ][5, 9 ]"
                    + "[5, 10 ][5, 11 ][5, 12 ]\n"
                    + "[6, 0 ][6, 1 ][6, 2 ][6, 3 ][6, 4 ][6, 5 ][6, 6*][6, 7 ][6, 8 ][6, 9 ]"
                    + "[6, 10 ][6, 11 ][6, 12 ]\n"
                    + "[7, 0 ][7, 1 ][7, 2 ][7, 3 ][7, 4 ][7, 5 ][7, 6 ][7, 7 ][7, 8 ][7, 9 ]"
                    + "[7, 10 ][7, 11 ][7, 12 ]\n"
                    + "[8, 0 ][8, 1 ][8, 2 ][8, 3 ][8, 4 ][8, 5 ][8, 6 ][8, 7 ][8, 8 ][8, 9 ]"
                    + "[8, 10 ][8, 11 ][8, 12 ]\n"
                    + "[null ][null ][null ][null ][9, 4 ][9, 5 ][9, 6 ][9, 7 ][9, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][10, 4 ][10, 5 ][10, 6 ][10, 7 ][10, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][11, 4 ][11, 5 ][11, 6 ][11, 7 ][11, 8 ]"
                    + "[null ][null ][null ][null ]\n"
                    + "[null ][null ][null ][null ][12, 4 ][12, 5 ][12, 6 ][12, 7 ][12, 8 ]"
                    + "[null ][null ][null ][null ]",
            new MarbleSolitaireModelImpl(5).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void evenArmThick() {
    MarbleSolitaireModel b1 = new MarbleSolitaireModelImpl(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeArmThick() {
    MarbleSolitaireModel b1 = new MarbleSolitaireModelImpl(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmpty1() {
    MarbleSolitaireModel b1 = new MarbleSolitaireModelImpl(3, 1, 1); // a null posn
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmpty2() {
    MarbleSolitaireModel b1 = new MarbleSolitaireModelImpl(3, 7, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmpty3() {
    MarbleSolitaireModel b1 = new MarbleSolitaireModelImpl(2, 9);
  }

  @Test
  public void stateString() {
    assertEquals("_", board1.getGameState());

    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", defBoard.getGameState());

    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O _\n"
                    + "    O O O\n"
                    + "    O O O", board3.getGameState());

    assertEquals(
            "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O _ O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O", board5.getGameState());
  }

  @Test
  public void testMove1() {
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", defBoard.getGameState());
    //UP
    defBoard.move(3, 5, 3, 3);
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O _ _ O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", defBoard.getGameState());
    //DOWN
    defBoard.move(1, 4, 3, 4);
    assertEquals(
            "    O O O\n"
                    + "    O O _\n"
                    + "O O O O _ O O\n"
                    + "O O O O O _ O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", defBoard.getGameState());
    //LEFT
    defBoard.move(2, 6, 2, 4);
    assertEquals(
            "    O O O\n"
                    + "    O O _\n"
                    + "O O O O O _ _\n"
                    + "O O O O O _ O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", defBoard.getGameState());
    //RIGHT
    defBoard.move(2, 3, 2, 5);
    assertEquals(
            "    O O O\n"
                    + "    O O _\n"
                    + "O O O _ _ O _\n"
                    + "O O O O O _ O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", defBoard.getGameState());
    //Jump to edge/corner
    defBoard.move(4, 6, 2, 6);
    assertEquals(
            "    O O O\n"
                    + "    O O _\n"
                    + "O O O _ _ O O\n"
                    + "O O O O O _ _\n"
                    + "O O O O O O _\n"
                    + "    O O O\n"
                    + "    O O O", defBoard.getGameState());
  }

  @Test
  public void testMoveAndScoreOn5() {
    assertEquals(board5.getScore(), 104);
    board5.move(7, 9, 5, 9); //UP
    assertEquals(board5.getScore(), 103);
    board5.move(7, 7, 7, 9); // RIGHT
    assertEquals(board5.getScore(), 102);
    board5.move(7, 10, 7, 8); // LEFT
    assertEquals(board5.getScore(), 101);
    board5.move(4, 9, 6, 9); // DOWN
    assertEquals(board5.getScore(), 100);
  }

  /**
   * Invalid Movements.
   **/

  @Test
  public void moveToOccupied() {
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", defBoard.getGameState());

    try {
      defBoard.move(3, 0, 3, 2);
      fail("Invalid move did not throw an error");
    } catch (IllegalArgumentException e) {
      assertEquals(
              "    O O O\n"
                      + "    O O O\n"
                      + "O O O O O O O\n"
                      + "O O O _ O O O\n"
                      + "O O O O O O O\n"
                      + "    O O O\n"
                      + "    O O O", defBoard.getGameState());
    }
  }

  @Test
  public void multipleBadMoves1() {
    // O over _
    // 0 -> null
    // null -> anything
    // _ -> anything

    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O", defBoard.getGameState());

    int[] fRows = {3, 0, 0, 6, 3};
    int[] fColumns = {2, 3, 1, 0, 3};
    int[] tRows = {3, 0, 2, 6, 3};
    int[] tColumns = {4, 1, 1, 1, 2};
    runInvalidMoves(defBoard, fRows, fColumns, tRows, tColumns);
  }

  @Test
  public void multipleBadMoves2() {
    // O over _
    // 0 -> null
    // null -> anything
    // _ -> anything

    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O _\n"
                    + "    O O O\n"
                    + "    O O O", board3.getGameState());

    int[] fRows = {4, 2, 3};
    int[] fColumns = {6, 6, 6};
    int[] tRows = {4, 6, 5};
    int[] tColumns = {6, 1, 6};
    runInvalidMoves(board3, fRows, fColumns, tRows, tColumns);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveOffBoard1() {
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O _\n"
                    + "    O O O\n"
                    + "    O O O", board3.getGameState());
    board3.move(4, 5, 4, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveOffBoard2() {
    MarbleSolitaireModel boardC = new MarbleSolitaireModelImpl(3, 2, 0);
    boardC.move(2, 1, 2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveIntoNull() {
    assertEquals(
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O _\n"
                    + "    O O O\n"
                    + "    O O O", board3.getGameState());
    board3.move(3, 6, 5, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveMoreThanTwo() {
    defBoard.move(3, 2, 3, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveMoreThanTwo2() {
    board5.move(3, 7, 6, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveDiagonally() {
    defBoard.move(2, 2, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveOn1Board() {
    board1.move(0, 0, 0, 2);
  }

  /**
   * Game Play, Scoring, game over, etc.
   **/

  @Test
  public void testGameOver() {
    assertFalse(defBoard.isGameOver());
    assertTrue(board1.isGameOver());
  }

  @Test
  public void testScore() {
    assertEquals(0, board1.getScore());
    assertEquals(32, defBoard.getScore());
    defBoard.move(3, 5, 3, 3);
    assertEquals(31, defBoard.getScore());
    defBoard.move(5, 4, 3, 4);
    assertEquals(30, defBoard.getScore());
  }

  @Test
  public void winGame() {
    //http://www.chessandpoker.com/peg-solitaire-solution.html

    // @formatter:off
    int[] fRows = {3, 5, 4, 4, 4, 6, 3, 6, 6, 2, 0, 1, 3, 5,
                      5, 3, 2, 4, 4, 2, 2, 4, 2, 0, 0, 2, 2, 2, 4, 4, 1};
    int[] fColumns = {1, 2, 0, 3, 5, 4, 4, 2, 4, 2, 2, 4, 4, 4,
                      2, 2, 0, 0, 2, 6, 3, 6, 6, 4, 2, 1, 3, 5, 5, 3, 3};
    int[] tRows = {3, 3, 4, 4, 4, 4, 5, 6, 4, 4, 2, 3, 5, 5, 3,
                      1, 4, 4, 4, 2, 2, 2, 2, 0, 2, 2, 2, 4, 4, 2, 3};
    int[] tColumns = {3, 2, 2, 1, 3, 4, 4, 4, 4, 2, 2, 4, 4, 2,
                      2, 2, 0, 2, 4, 4, 5, 6, 4, 2, 2, 3, 5, 5, 3, 3, 3};
    // @formatter:on

    runValidMoves(defBoard, fRows, fColumns, tRows, tColumns);

    assertTrue(defBoard.isGameOver());
    assertEquals(1, defBoard.getScore());
  }


  @Test
  public void loseGame() {

    int[] fRows = {3, 5, 4, 4, 4, 6, 3, 6, 6, 2, 0, 1, 3, 5, 5, 3, 2, 4, 2, 0, 1, 4, 2, 4, 2};
    int[] fColumns = {1, 2, 0, 3, 5, 4, 4, 2, 4, 2, 2, 4, 4, 4, 2, 2, 6, 6, 4, 4, 2, 3, 0, 0, 2};
    int[] tRows = {3, 3, 4, 4, 4, 4, 5, 6, 4, 4, 2, 3, 5, 5, 3, 1, 2, 2, 2, 0, 1, 2, 4, 4, 2};
    int[] tColumns = {3, 2, 2, 1, 3, 4, 4, 4, 4, 2, 2, 4, 4, 2, 2, 2, 4, 6, 2, 2, 4, 3, 0, 2, 0};

    runValidMoves(defBoard, fRows, fColumns, tRows, tColumns);

    assertTrue(defBoard.isGameOver());
    assertEquals(7, defBoard.getScore());
  }


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
  private void runValidMoves(MarbleSolitaireModel msm,
                             int[] fRows, int[] fColumns,
                             int[] tRows, int[] tColumns) {
    int initScore = msm.getScore();

    if (fRows.length != fColumns.length
            && fColumns.length != tRows.length
            && tRows.length != tColumns.length) {
      fail("Invalid test: movement arrays must be the same length!");
    }

    for (int i = 0; i < fRows.length; i++) {
      assertEquals(initScore - i, defBoard.getScore());
      assertFalse(msm.isGameOver());
      msm.move(fRows[i], fColumns[i], tRows[i], tColumns[i]);
      assertEquals((initScore - i) - 1, defBoard.getScore());
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
  private void runInvalidMoves(MarbleSolitaireModel msm,
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
