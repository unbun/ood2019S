import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TestEuropeanMarbleSolitaireModel extends AbstractTestMarbleSolitaireModel {

  private MarbleSolitaireModel board3Corner;

  public TestEuropeanMarbleSolitaireModel() {
    initData();
  }

  void initData(){
    super.initData(new EuropeanSolitaireModelImpl(), new EuropeanSolitaireModelImpl(4, 6),
            new EuropeanSolitaireModelImpl(5, 5, 9),new EuropeanSolitaireModelImpl(1),
            new EuropeanSolitaireModelImpl(3, 6));
    this.board3Corner = new EuropeanSolitaireModelImpl(5, 1);
  }


  @Test
  public void boardGeneration() {
    assertEquals("[0, 0*]", board1.toString());

    assertEquals("[null ][null ][0, 2 ][0, 3 ][0, 4 ][null ][null ]\n" +
                    "[null ][1, 1 ][1, 2 ][1, 3 ][1, 4 ][1, 5 ][null ]\n" +
                    "[2, 0 ][2, 1 ][2, 2 ][2, 3 ][2, 4 ][2, 5 ][2, 6 ]\n" +
                    "[3, 0 ][3, 1 ][3, 2 ][3, 3*][3, 4 ][3, 5 ][3, 6 ]\n" +
                    "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][4, 5 ][4, 6 ]\n" +
                    "[null ][5, 1 ][5, 2 ][5, 3 ][5, 4 ][5, 5 ][null ]\n" +
                    "[null ][null ][6, 2 ][6, 3 ][6, 4 ][null ][null ]",
            defBoard.toString());

    assertEquals("[null ][null ][0, 2 ][0, 3 ][0, 4 ][null ][null ]\n" +
                    "[null ][1, 1 ][1, 2 ][1, 3 ][1, 4 ][1, 5 ][null ]\n" +
                    "[2, 0 ][2, 1 ][2, 2 ][2, 3 ][2, 4 ][2, 5 ][2, 6 ]\n" +
                    "[3, 0 ][3, 1 ][3, 2 ][3, 3 ][3, 4 ][3, 5 ][3, 6 ]\n" +
                    "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][4, 5 ][4, 6*]\n" +
                    "[null ][5, 1 ][5, 2 ][5, 3 ][5, 4 ][5, 5 ][null ]\n" +
                    "[null ][null ][6, 2 ][6, 3 ][6, 4 ][null ][null ]",
            board3.toString());

    assertEquals("[null ][null ][0, 2 ][0, 3 ][0, 4 ][null ][null ]\n" +
                    "[null ][1, 1 ][1, 2 ][1, 3 ][1, 4 ][1, 5 ][null ]\n" +
                    "[2, 0 ][2, 1 ][2, 2 ][2, 3 ][2, 4 ][2, 5 ][2, 6 ]\n" +
                    "[3, 0 ][3, 1 ][3, 2 ][3, 3 ][3, 4 ][3, 5 ][3, 6 ]\n" +
                    "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][4, 5 ][4, 6 ]\n" +
                    "[null ][5, 1*][5, 2 ][5, 3 ][5, 4 ][5, 5 ][null ]\n" +
                    "[null ][null ][6, 2 ][6, 3 ][6, 4 ][null ][null ]",
            board3Corner.toString());

    assertEquals(
            "[null ][null ][null ][null ][0, 4 ][0, 5 ][0, 6 ][0, 7 ][0, 8 ]" +
                    "[null ][null ][null ][null ]\n" +
                    "[null ][null ][null ][1, 3 ][1, 4 ][1, 5 ][1, 6 ][1, 7 ][1, 8 ]" +
                    "[1, 9 ][null ][null ][null ]\n" +
                    "[null ][null ][2, 2 ][2, 3 ][2, 4 ][2, 5 ][2, 6 ][2, 7 ][2, 8 ]" +
                    "[2, 9 ][2, 10 ][null ][null ]\n" +
                    "[null ][3, 1 ][3, 2 ][3, 3 ][3, 4 ][3, 5 ][3, 6 ][3, 7 ][3, 8 ]" +
                    "[3, 9 ][3, 10 ][3, 11 ][null ]\n" +
                    "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][4, 5 ][4, 6 ][4, 7 ][4, 8 ]" +
                    "[4, 9 ][4, 10 ][4, 11 ][4, 12 ]\n" +
                    "[5, 0 ][5, 1 ][5, 2 ][5, 3 ][5, 4 ][5, 5 ][5, 6 ][5, 7 ][5, 8 ]" +
                    "[5, 9*][5, 10 ][5, 11 ][5, 12 ]\n" +
                    "[6, 0 ][6, 1 ][6, 2 ][6, 3 ][6, 4 ][6, 5 ][6, 6 ][6, 7 ][6, 8 ]" +
                    "[6, 9 ][6, 10 ][6, 11 ][6, 12 ]\n" +
                    "[7, 0 ][7, 1 ][7, 2 ][7, 3 ][7, 4 ][7, 5 ][7, 6 ][7, 7 ][7, 8 ]" +
                    "[7, 9 ][7, 10 ][7, 11 ][7, 12 ]\n" +
                    "[8, 0 ][8, 1 ][8, 2 ][8, 3 ][8, 4 ][8, 5 ][8, 6 ][8, 7 ][8, 8 ]" +
                    "[8, 9 ][8, 10 ][8, 11 ][8, 12 ]\n" +
                    "[null ][9, 1 ][9, 2 ][9, 3 ][9, 4 ][9, 5 ][9, 6 ][9, 7 ][9, 8 ]" +
                    "[9, 9 ][9, 10 ][9, 11 ][null ]\n" +
                    "[null ][null ][10, 2 ][10, 3 ][10, 4 ][10, 5 ][10, 6 ][10, 7 ]" +
                    "[10, 8 ][10, 9 ][10, 10 ][null ][null ]\n" +
                    "[null ][null ][null ][11, 3 ][11, 4 ][11, 5 ][11, 6 ][11, 7 ]" +
                    "[11, 8 ][11, 9 ][null ][null ][null ]\n" +
                    "[null ][null ][null ][null ][12, 4 ][12, 5 ][12, 6 ][12, 7 ]" +
                    "[12, 8 ][null ][null ][null ][null ]",
            board5.toString());

    assertEquals("[null ][null ][null ][null ][0, 4 ][0, 5 ][0, 6 ][0, 7 ][0, 8 ]" +
                    "[null ][null ][null ][null ]\n" +
                    "[null ][null ][null ][1, 3 ][1, 4 ][1, 5 ][1, 6 ][1, 7 ][1, 8 ][1, 9 ]" +
                    "[null ][null ][null ]\n" +
                    "[null ][null ][2, 2 ][2, 3 ][2, 4 ][2, 5 ][2, 6 ][2, 7 ][2, 8 ][2, 9 ]" +
                    "[2, 10 ][null ][null ]\n" +
                    "[null ][3, 1 ][3, 2 ][3, 3 ][3, 4 ][3, 5 ][3, 6 ][3, 7 ][3, 8 ][3, 9 ]" +
                    "[3, 10 ][3, 11 ][null ]\n" +
                    "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][4, 5 ][4, 6 ][4, 7 ][4, 8 ][4, 9 ]" +
                    "[4, 10 ][4, 11 ][4, 12 ]\n" +
                    "[5, 0 ][5, 1 ][5, 2 ][5, 3 ][5, 4 ][5, 5 ][5, 6 ][5, 7 ][5, 8 ][5, 9 ]" +
                    "[5, 10 ][5, 11 ][5, 12 ]\n" +
                    "[6, 0 ][6, 1 ][6, 2 ][6, 3 ][6, 4 ][6, 5 ][6, 6*][6, 7 ][6, 8 ][6, 9 ]" +
                    "[6, 10 ][6, 11 ][6, 12 ]\n" +
                    "[7, 0 ][7, 1 ][7, 2 ][7, 3 ][7, 4 ][7, 5 ][7, 6 ][7, 7 ][7, 8 ][7, 9 ]" +
                    "[7, 10 ][7, 11 ][7, 12 ]\n" +
                    "[8, 0 ][8, 1 ][8, 2 ][8, 3 ][8, 4 ][8, 5 ][8, 6 ][8, 7 ][8, 8 ][8, 9 ]" +
                    "[8, 10 ][8, 11 ][8, 12 ]\n" +
                    "[null ][9, 1 ][9, 2 ][9, 3 ][9, 4 ][9, 5 ][9, 6 ][9, 7 ][9, 8 ][9, 9 ]" +
                    "[9, 10 ][9, 11 ][null ]\n" +
                    "[null ][null ][10, 2 ][10, 3 ][10, 4 ][10, 5 ][10, 6 ][10, 7 ][10, 8 ]" +
                    "[10, 9 ][10, 10 ][null ][null ]\n" +
                    "[null ][null ][null ][11, 3 ][11, 4 ][11, 5 ][11, 6 ][11, 7 ][11, 8 ]" +
                    "[11, 9 ][null ][null ][null ]\n" +
                    "[null ][null ][null ][null ][12, 4 ][12, 5 ][12, 6 ][12, 7 ][12, 8 ]" +
                    "[null ][null ][null ][null ]",
            new EuropeanSolitaireModelImpl(5).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void evenArmThick() {
    new EuropeanSolitaireModelImpl(6);
  }



  @Test
  public void stateString() {
    assertEquals("_", board1.getGameState());

    assertEquals(
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O", defBoard.getGameState());

    assertEquals(
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O _\n" +
                    "  O O O O O\n" +
                    "    O O O", board3.getGameState());

    assertEquals(
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "  _ O O O O\n" +
                    "    O O O",
            board3Corner.getGameState());

    assertEquals(
            "        O O O O O\n" +
                    "      O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O _ O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "  O O O O O O O O O O O\n" +
                    "    O O O O O O O O O\n" +
                    "      O O O O O O O\n" +
                    "        O O O O O", board5.getGameState());
  }

  @Test
  public void getValidJump() {
    super.getValidJump();

    //LEFT to corner
    assertEquals(5, board3Corner.getValidJumped(5, 3, 5, 1).get()[0]);
    assertEquals(2, board3Corner.getValidJumped(5, 3, 5, 1).get()[1]);

    //DOWN to corner
    assertEquals(4, board3Corner.getValidJumped(3, 1, 5, 1).get()[0]);
    assertEquals(1, board3Corner.getValidJumped(3, 1, 5, 1).get()[1]);

    MarbleSolitaireModel board3OtherCorner = new EuropeanSolitaireModelImpl(1, 5);
    //RIGHT to corner
    assertEquals(1, board3OtherCorner.getValidJumped(1, 3, 1, 5).get()[0]);
    assertEquals(4, board3OtherCorner.getValidJumped(1, 3, 1, 5).get()[1]);

    //UP to corner
    assertEquals(2, board3OtherCorner.getValidJumped(3, 5, 1, 5).get()[0]);
    assertEquals(5, board3OtherCorner.getValidJumped(3, 5, 1, 5).get()[1]);
  }

  @Test
  public void getValidJumpBadState3() {
    super.getValidJumpBadState3(
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O _ _ O O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O");
  }


  /**
   * Game Play, Scoring, game over, etc.
   **/


  @Test
  public void testMove1() {
    assertEquals(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O", defBoard.getGameState());
    //UP
    defBoard.move(3, 5, 3, 3);
    assertEquals(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O _ _ O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O", defBoard.getGameState());
    //DOWN
    defBoard.move(1, 4, 3, 4);
    assertEquals(
            "    O O O\n"
                    + "  O O O _ O\n"
                    + "O O O O _ O O\n"
                    + "O O O O O _ O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O", defBoard.getGameState());
    //LEFT
    defBoard.move(2, 6, 2, 4);
    assertEquals(
            "    O O O\n"
                    + "  O O O _ O\n"
                    + "O O O O O _ _\n"
                    + "O O O O O _ O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O", defBoard.getGameState());
    //RIGHT
    defBoard.move(2, 3, 2, 5);
    assertEquals(
            "    O O O\n"
                    + "  O O O _ O\n"
                    + "O O O _ _ O _\n"
                    + "O O O O O _ O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O", defBoard.getGameState());
    //Jump to edge/corner
    defBoard.move(4, 6, 2, 6);
    assertEquals(
            "    O O O\n"
                    + "  O O O _ O\n"
                    + "O O O _ _ O O\n"
                    + "O O O O O _ _\n"
                    + "O O O O O O _\n"
                    + "  O O O O O\n"
                    + "    O O O", defBoard.getGameState());

    defBoard.move(5, 5, 3, 5);
    assertEquals(
            "    O O O\n"
                    + "  O O O _ O\n"
                    + "O O O _ _ O O\n"
                    + "O O O O O O _\n"
                    + "O O O O O _ _\n"
                    + "  O O O O _\n"
                    + "    O O O", defBoard.getGameState());

    defBoard.move(5, 3, 5, 5);
    assertEquals(
            "    O O O\n"
                    + "  O O O _ O\n"
                    + "O O O _ _ O O\n"
                    + "O O O O O O _\n"
                    + "O O O O O _ _\n"
                    + "  O O _ _ O\n"
                    + "    O O O", defBoard.getGameState());
  }

  @Test
  public void testMoveAndScoreOn5() {
    super.testMoveAndScoreOn5(128);
  }


  /**
   * Invalid Movements.
   **/

  @Test
  public void moveToOccupied() {
    super.moveToOccupied(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O");
  }

  @Test
  public void multipleBadMoves1() {
    super.multipleBadMoves(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "  O O O O O\n"
                    + "    O O O");
  }

  @Test
  public void multipleBadMoves2() {
    super.multipleBadMoves2(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O _\n"
                    + "  O O O O O\n"
                    + "    O O O");
  }


  @Test(expected = IllegalArgumentException.class)
  public void moveOffBoard1() {
    super.moveOffBoard1(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O _\n"
                    + "  O O O O O\n"
                    + "    O O O");
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveIntoNull() {
    super.moveIntoNull(
            "    O O O\n"
                    + "  O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O _\n"
                    + "  O O O O O\n"
                    + "    O O O");
  }

  @Test
  public void winGame() {

    MarbleSolitaireModel cornerStart = new EuropeanSolitaireModelImpl(0, 2);

    // @formatter:off
    int[] fRows = {2, 2, 1, 3, 3, 2, 5, 3, 5, 4, 5, 0, 2, 2, 5, 3, 1, 2, 0, 3, 3, 6, 3, 4,
            4, 6, 6, 4, 4, 4, 5, 3, 1, 2, 0};
    int[] fColumns = {2, 0, 4, 4, 2, 3, 3, 0, 1, 5, 5, 4, 1, 4, 2, 6, 1, 6, 3, 2, 4, 2, 2,
            0, 3, 4, 2, 1, 3, 6, 4, 4, 5, 3, 2};
    int[] tRows = {0, 2, 1, 1, 3, 2, 3, 3, 3, 4, 5, 2, 4, 4, 5, 3, 1, 2, 2, 5, 3, 4, 5, 4,
            4, 6, 4, 4, 4, 4, 3, 1, 1, 0, 0};
    int[] tColumns = {2, 2, 2, 4, 4, 1, 3, 2, 1, 3, 3, 4, 1, 4, 4, 4, 3, 4, 3, 2, 2, 2, 2,
            2, 1, 2, 2, 3, 5, 4, 4, 4, 3, 3, 4};
    // @formatter:on

    runValidMoves(cornerStart, fRows, fColumns, tRows, tColumns);

    assertTrue(cornerStart.isGameOver());
    assertEquals(1, cornerStart.getScore());
    assertEquals(
            "    _ _ O\n" +
            "  _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "  _ _ _ _ _\n" +
            "    _ _ _", cornerStart.getGameState());
  }

  @Test
  public void loseGame() {

    MarbleSolitaireModel cornerStart = new EuropeanSolitaireModelImpl(0, 2);

    // @formatter:off
    int[] fRows = {2, 2, 1, 3, 3, 2, 5, 3, 5, 4, 5, 0, 2, 2, 5, 3, 1, 2, 0, 3, 3, 6, 3, 5,
                    4, 5, 2, 3, 6, 0};
    int[] fColumns = {2, 0, 4, 4, 2, 3, 3, 0, 1, 5, 5, 4, 1, 4, 2, 6, 1, 6, 3, 2, 4, 2, 2,
                        4, 0, 2, 4, 2, 4, 2};
    int[] tRows = {0, 2, 1, 1, 3, 2, 3, 3, 3, 4, 5, 2, 4, 4, 5, 3, 1, 2, 2, 5, 3, 4, 5, 3,
                    4, 3, 2, 1, 6, 2};
    int[] tColumns = {2, 2, 2, 4, 4, 1, 3, 2, 1, 3, 3, 4, 1, 4, 4, 4, 3, 4, 3, 2, 2, 2, 2,
                        4, 2, 2, 2, 2, 2, 2};
    // @formatter:on

    runValidMoves(cornerStart, fRows, fColumns, tRows, tColumns);

    assertTrue(cornerStart.isGameOver());
    assertNotEquals(1, cornerStart.getScore());
  }

}
