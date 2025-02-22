import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestEnglishMarbleSolitaireModel extends AbstractTestMarbleSolitaireModel {

  public TestEnglishMarbleSolitaireModel() {
    initData();
  }

  void initData() {
    super.initData(new MarbleSolitaireModelImpl(), new MarbleSolitaireModelImpl(4, 6),
        new MarbleSolitaireModelImpl(5, 5, 9), new MarbleSolitaireModelImpl(1),
        new MarbleSolitaireModelImpl(3, 6));
  }


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
    new MarbleSolitaireModelImpl(6);
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
  public void getValidJumpBadState3() {
    assertNotNull(this.defBoard);
    super.getValidJumpBadState3("    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O _ _ O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O");
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
    assertNotNull(this.board5);
    super.testMoveAndScoreOn5(104);
  }

  /**
   * Invalid Movements.
   **/

  @Test
  public void moveToOccupied() {
    assertNotNull(this.defBoard);
    super.moveToOccupied(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O");
  }

  @Test
  public void multipleBadMoves1() {
    assertNotNull(this.defBoard);
    super.multipleBadMoves(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O");
  }

  @Test
  public void multipleBadMoves2() {
    assertNotNull(this.board3);
    super.multipleBadMoves2(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O _\n"
            + "    O O O\n"
            + "    O O O");
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveOffBoard1() {
    assertNotNull(this.board3);
    super.moveOffBoard1(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O _\n"
            + "    O O O\n"
            + "    O O O");
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveIntoNull() {
    assertNotNull(this.board3);
    super.moveIntoNull(
        "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O _\n"
            + "    O O O\n"
            + "    O O O");
  }

  /**
   * Game Play, Scoring, game over, etc.
   **/

  @Test
  public void winGame() {
    //http://www.chessandpoker.com/peg-solitaire-solution.html

    //@formatter:off
    int[] fRows = {3, 5, 4, 4, 4, 6, 3, 6, 6, 2, 0, 1, 3, 5,
                      5, 3, 2, 4, 4, 2, 2, 4, 2, 0, 0, 2, 2, 2, 4, 4, 1};
    int[] fColumns = {1, 2, 0, 3, 5, 4, 4, 2, 4, 2, 2, 4, 4, 4,
                      2, 2, 0, 0, 2, 6, 3, 6, 6, 4, 2, 1, 3, 5, 5, 3, 3};
    int[] tRows = {3, 3, 4, 4, 4, 4, 5, 6, 4, 4, 2, 3, 5, 5, 3,
                      1, 4, 4, 4, 2, 2, 2, 2, 0, 2, 2, 2, 4, 4, 2, 3};
    int[] tColumns = {3, 2, 2, 1, 3, 4, 4, 4, 4, 2, 2, 4, 4, 2,
                      2, 2, 0, 2, 4, 4, 5, 6, 4, 2, 2, 3, 5, 5, 3, 3, 3};
    //@formatter:on

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
    assertEquals("    O _ _\n" +
        "    _ _ O\n" +
        "O _ _ O _ _ O\n" +
        "_ _ _ _ _ O _\n" +
        "_ _ O _ _ _ _\n" +
        "    _ _ _\n" +
        "    _ _ _", defBoard.getGameState());
  }


}
