import org.junit.Test;

import java.util.Optional;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class AbstractTestMarbleSolitaireModel {

  protected MarbleSolitaireModel defBoard;
  protected MarbleSolitaireModel board3;
  protected MarbleSolitaireModel board5;
  protected MarbleSolitaireModel board1;
  protected MarbleSolitaireModel board3b;


  void initData(MarbleSolitaireModel defBoard, MarbleSolitaireModel board3,
                    MarbleSolitaireModel board5, MarbleSolitaireModel board1,
                    MarbleSolitaireModel board3b) {
    this.defBoard = defBoard;
    this.board3 = board3;
    this.board5 = board5;
    this.board1 = board1;
    this.board3b = board3b;
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeArmThick() {
    new EuropeanSolitaireModelImpl(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmpty1() {
    new EuropeanSolitaireModelImpl(3, 0, 1); // a null posn
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmpty2() {
    new EuropeanSolitaireModelImpl(3, 7, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmpty3() {
    new EuropeanSolitaireModelImpl(2, 9);
  }


  @Test
  public void getValidJump(){
    //RIGHT
    assertEquals(3, defBoard.getValidJumped(3, 1, 3, 3).get()[0]);
    assertEquals(2, defBoard.getValidJumped(3, 1, 3, 3).get()[1]);
    //LEFT
    assertEquals(3, defBoard.getValidJumped(3, 5, 3, 3).get()[0]);
    assertEquals(4, defBoard.getValidJumped(3, 5, 3, 3).get()[1]);
    //DOWN
    assertEquals(2, defBoard.getValidJumped(1, 3, 3, 3).get()[0]);
    assertEquals(3, defBoard.getValidJumped(1, 3, 3, 3).get()[1]);
    //UP
    assertEquals(4, defBoard.getValidJumped(5, 3, 3, 3).get()[0]);
    assertEquals(3, defBoard.getValidJumped(5, 3, 3, 3).get()[1]);
  }

  @Test
  public void getValidJumpBadState1() {
    Optional<int[]> expected = board3b.getValidJumped(5, 6, 3, 6);
    assertFalse(expected.isPresent()); // origin is null
  }

  @Test
  public void getValidJumpBadState2() {
    Optional<int[]> expected = board3b.getValidJumped(3, 6, 3, 3);
    assertFalse(expected.isPresent()); // origin is empty
  }

  public void getValidJumpBadState3(String expectState) {
    //in order to make sure it's the empty b/w that's throwing the error, the move
    //has to create 2 empties right next to each other
    defBoard.move(3, 1, 3, 3);
    assertEquals(expectState, defBoard.getGameState());

    Optional<int[]> expected = defBoard.getValidJumped(3, 0, 3, 2);
    assertFalse(expected.isPresent()); //between was empty
  }

  @Test
  public void getValidJumpBadState4() {
    Optional<int[]> expected = defBoard.getValidJumped(0, 3, 0, 1);
    assertFalse(expected.isPresent()); //dest was null
  }

  @Test
  public void getValidJumpBadState5() {
    Optional<int[]> expected = defBoard.getValidJumped(0, 2, 0, 0);
    assertFalse(expected.isPresent()); // between is null
  }

  @Test
  public void getValidJumpBadState6() {
    Optional<int[]> expected = defBoard.getValidJumped(3, 0, 3, 2);
    assertFalse(expected.isPresent()); //dest was occupied
  }

  @Test
  public void testGameOver() {
    assertFalse(defBoard.isGameOver());
    assertTrue(board1.isGameOver());
  }

  public void testMoveAndScoreOn5(int initScore){
    assertEquals(board5.getScore(), initScore);
    board5.move(7, 9, 5, 9); //UP
    assertEquals(board5.getScore(), initScore - 1);
    board5.move(7, 7, 7, 9); // RIGHT
    assertEquals(board5.getScore(), initScore - 2);
    board5.move(7, 10, 7, 8); // LEFT
    assertEquals(board5.getScore(), initScore - 3);
    board5.move(4, 9, 6, 9); // DOWN
    assertEquals(board5.getScore(), initScore - 4);
  }

  public void moveToOccupied(String initState){
    assertEquals(initState, defBoard.getGameState());

    try {
      defBoard.move(3, 0, 3, 2);
      fail("Invalid move did not throw an error");
    } catch (IllegalArgumentException e) {
      assertEquals(initState, defBoard.getGameState());
    }
  }

  public void multipleBadMoves(String initState){
    // O over _
    // 0 -> null
    // null -> anything
    // _ -> anything

    assertEquals(initState, defBoard.getGameState());

    int[] fRows = {3, 0, 0, 6, 3};
    int[] fColumns = {2, 3, 1, 0, 3};
    int[] tRows = {3, 0, 2, 6, 3};
    int[] tColumns = {4, 1, 1, 1, 2};
    runInvalidMoves(defBoard, fRows, fColumns, tRows, tColumns);
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

  public void multipleBadMoves2(String initState){
    // O over _
    // 0 -> null
    // null -> anything
    // _ -> anything

    assertEquals(initState, board3.getGameState());

    int[] fRows = {4, 2, 3};
    int[] fColumns = {6, 6, 6};
    int[] tRows = {4, 6, 5};
    int[] tColumns = {6, 1, 6};

    runInvalidMoves(board3, fRows, fColumns, tRows, tColumns);
  }

  public void moveOffBoard1(String initState){
    assertEquals(initState, board3.getGameState());
    board3.move(4, 5, 4, 7);
  }

  public void moveIntoNull(String initState) {
    assertEquals(initState, board3.getGameState());
    board3.move(3, 6, 5, 6);
  }

  @Test
  public abstract void testMove1();

  @Test
  public abstract void boardGeneration();

  @Test
  public abstract void stateString();

  @Test
  public abstract void winGame();

  @Test
  public abstract void loseGame();

















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
