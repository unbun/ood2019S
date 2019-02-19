import cs3500.marblesolitaire.model.AbstractBoardModel;
import java.util.Optional;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class TestTriangleSolitaireModel extends AbstractTestMarbleSolitaireModel {

  private MarbleSolitaireModel board2;
  private AbstractBoardModel board4;
  private MarbleSolitaireModel board6;
  private AbstractBoardModel board7;

  public TestTriangleSolitaireModel() {
    initData();
  }

  void initData() {
    super.initData(new TriangleSolitaireModelImpl(), new TriangleSolitaireModelImpl(3, 2, 2),
        new TriangleSolitaireModelImpl(5, 4, 4), new TriangleSolitaireModelImpl(1),
        new TriangleSolitaireModelImpl(3, 3));
    this.board2 = new TriangleSolitaireModelImpl(2);
    this.board4 = new TriangleSolitaireModelImpl(4, 2, 1);
    this.board6 = new TriangleSolitaireModelImpl(6, 3, 1);
    this.board7 = new TriangleSolitaireModelImpl(7, 4, 2);
  }


  @Test
  public void boardGeneration() {
    assertEquals("[0, 0*][null ][null ][null ][null ]\n" +
        "[1, 0 ][1, 1 ][null ][null ][null ]\n" +
        "[2, 0 ][2, 1 ][2, 2 ][null ][null ]\n" +
        "[3, 0 ][3, 1 ][3, 2 ][3, 3 ][null ]\n" +
        "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ]", defBoard.toString());

    assertEquals("[0, 0*][null ]\n"
        + "[1, 0 ][1, 1 ]", board2.toString());

    assertEquals("[0, 0 ][null ][null ][null ]\n" +
        "[1, 0 ][1, 1 ][null ][null ]\n" +
        "[2, 0 ][2, 1*][2, 2 ][null ]\n" +
        "[3, 0 ][3, 1 ][3, 2 ][3, 3 ]", board4.toString());

    assertEquals("[0, 0 ][null ][null ][null ][null ]\n" +
        "[1, 0 ][1, 1 ][null ][null ][null ]\n" +
        "[2, 0 ][2, 1 ][2, 2 ][null ][null ]\n" +
        "[3, 0 ][3, 1 ][3, 2 ][3, 3 ][null ]\n" +
        "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4*]", board5.toString());

    assertEquals("[0, 0 ][null ][null ][null ][null ][null ]\n" +
        "[1, 0 ][1, 1 ][null ][null ][null ][null ]\n" +
        "[2, 0 ][2, 1 ][2, 2 ][null ][null ][null ]\n" +
        "[3, 0 ][3, 1*][3, 2 ][3, 3 ][null ][null ]\n" +
        "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][null ]\n" +
        "[5, 0 ][5, 1 ][5, 2 ][5, 3 ][5, 4 ][5, 5 ]", board6.toString());

    assertEquals("[0, 0 ][null ][null ][null ][null ][null ][null ]\n" +
        "[1, 0 ][1, 1 ][null ][null ][null ][null ][null ]\n" +
        "[2, 0 ][2, 1 ][2, 2 ][null ][null ][null ][null ]\n" +
        "[3, 0 ][3, 1 ][3, 2 ][3, 3 ][null ][null ][null ]\n" +
        "[4, 0 ][4, 1 ][4, 2*][4, 3 ][4, 4 ][null ][null ]\n" +
        "[5, 0 ][5, 1 ][5, 2 ][5, 3 ][5, 4 ][5, 5 ][null ]\n" +
        "[6, 0 ][6, 1 ][6, 2 ][6, 3 ][6, 4 ][6, 5 ][6, 6 ]", board7.toString());
  }


  @Override
  public void stateString() {
    assertEquals("_", board1.getGameState());

    assertEquals(" _\n" +
        "O O", board2.getGameState());

    assertEquals("  O\n"
        + " O O\n"
        + "O O _", board3.getGameState());

    assertEquals("    O\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O _\n"
        + "O O O O O", board3b.getGameState());

    assertEquals("   O\n" +
        "  O O\n" +
        " O _ O\n" +
        "O O O O", board4.getGameState());

    assertEquals(
        "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", defBoard.getGameState());

    assertEquals("    O\n" +
        "   O O\n" +
        "  O O O\n" +
        " O O O O\n" +
        "O O O O _", board5.getGameState());

    assertEquals("     O\n" +
        "    O O\n" +
        "   O O O\n" +
        "  O _ O O\n" +
        " O O O O O\n" +
        "O O O O O O", board6.getGameState());

    assertEquals(
        "      O\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O _ O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", board7.getGameState());
  }

  @Test
  public void getValidJump() {
    //RIGHT
    assertEquals(4, board7.getValidJumped(4, 4, 4, 2).get()[0]);
    assertEquals(3, board7.getValidJumped(4, 4, 4, 2).get()[1]);
    //LEFT
    assertEquals(4, board7.getValidJumped(4, 0, 4, 2).get()[0]);
    assertEquals(1, board7.getValidJumped(4, 0, 4, 2).get()[1]);
    //UP-LEFT
    assertEquals(3, board7.getValidJumped(2, 2, 4, 2).get()[0]);
    assertEquals(2, board7.getValidJumped(2, 2, 4, 2).get()[1]);
    //DOWN-LEFT
    assertEquals(5, board7.getValidJumped(6, 4, 4, 2).get()[0]);
    assertEquals(3, board7.getValidJumped(6, 4, 4, 2).get()[1]);
    //UP-RIGHT
    assertEquals(3, board7.getValidJumped(2, 0, 4, 2).get()[0]);
    assertEquals(1, board7.getValidJumped(2, 0, 4, 2).get()[1]);
    //DOWN-RIGHT
    assertEquals(5, board7.getValidJumped(6, 2, 4, 2).get()[0]);
    assertEquals(2, board7.getValidJumped(6, 2, 4, 2).get()[1]);
  }

  @Test
  public void winGame() {
    //https://www.joenord.com/puzzles/peggame/index.html

    int[] fRows = {2, 2, 0, 3, 4, 1, 3, 3, 4, 4, 2, 4, 4};
    int[] fColumns = {0, 2, 0, 0, 2, 0, 3, 0, 4, 1, 2, 3, 0};
    int[] tRows = {0, 2, 2, 1, 2, 3, 3, 3, 4, 4, 4, 4, 4};
    int[] tColumns = {0, 0, 2, 0, 0, 0, 1, 2, 2, 3, 2, 1, 2};

    runValidMoves(defBoard, fRows, fColumns, tRows, tColumns);

    assertTrue(defBoard.isGameOver());
    assertEquals(1, defBoard.getScore());
    assertEquals("    _\n" +
        "   _ _\n" +
        "  _ _ _\n" +
        " _ _ _ _\n" +
        "_ _ O _ _", defBoard.getGameState());
  }

  @Test
  public void loseGame() {

    int[] fRows = {2, 2, 0, 4, 3, 3, 2, 2, 4, 4};
    int[] fColumns = {0, 2, 0, 1, 0, 3, 2, 0, 3, 1};
    int[] tRows = {0, 2, 2, 2, 1, 3, 2, 0, 4, 2};
    int[] tColumns = {0, 0, 2, 1, 0, 1, 0, 0, 1, 1};

    runValidMoves(defBoard, fRows, fColumns, tRows, tColumns);

    assertTrue(defBoard.isGameOver());
    assertEquals(4, defBoard.getScore());
    assertEquals("    O\n" +
        "   _ _\n" +
        "  _ O _\n" +
        " _ _ _ _\n" +
        "O _ _ _ O", defBoard.getGameState());
  }

  @Test
  public void testMove1() {
    assertEquals("    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O", defBoard.getGameState());

    //UP-LEFT
    defBoard.move(2, 0, 0, 0);
    assertEquals("    O\n"
        + "   _ O\n"
        + "  _ O O\n"
        + " O O O O\n"
        + "O O O O O", defBoard.getGameState());

    //LEFT
    defBoard.move(2, 2, 2, 0);
    assertEquals("    O\n"
        + "   _ O\n"
        + "  O _ _\n"
        + " O O O O\n"
        + "O O O O O", defBoard.getGameState());

    //DOWN-RIGHT
    defBoard.move(0, 0, 2, 2);
    assertEquals("    _\n"
        + "   _ _\n"
        + "  O _ O\n"
        + " O O O O\n"
        + "O O O O O", defBoard.getGameState());

    //UP-RIGHT
    defBoard.move(3, 3, 1, 1);
    assertEquals("    _\n"
        + "   _ O\n"
        + "  O _ _\n"
        + " O O O _\n"
        + "O O O O O", defBoard.getGameState());

    //RIGHT
    defBoard.move(3, 1, 3, 3);
    assertEquals("    _\n"
        + "   _ O\n"
        + "  O _ _\n"
        + " O _ _ O\n"
        + "O O O O O", defBoard.getGameState());

    //DOWN-LEFT
    assertEquals("      O\n"
        + "     O O\n"
        + "    O O O\n"
        + "   O O O O\n"
        + "  O O _ O O\n"
        + " O O O O O O\n"
        + "O O O O O O O", board7.getGameState());
    board7.move(2, 2, 4, 2);
    assertEquals("      O\n"
        + "     O O\n"
        + "    O O _\n"
        + "   O O _ O\n"
        + "  O O O O O\n"
        + " O O O O O O\n"
        + "O O O O O O O", board7.getGameState());
  }


  @Test
  public void getValidJumpBadState1() {
    Optional<int[]> expected = board4.getValidJumped(2, 3, 2, 1);
    assertFalse(expected.isPresent()); // origin is null
  }

  @Test
  public void getValidJumpBadState2() {
    Optional<int[]> expected = board3b.getValidJumped(3, 3, 3, 1);
    assertFalse(expected.isPresent()); // origin is empty
  }

  @Test
  public void getValidJumpBadState3() {
    board7.move(4, 4, 4, 2);
    assertEquals("      O\n" +
        "     O O\n" +
        "    O O O\n" +
        "   O O O O\n" +
        "  O O O _ _\n" +
        " O O O O O O\n" +
        "O O O O O O O", board7.getGameState());
    Optional<int[]> expected = board7.getValidJumped(4, 2, 4, 4);
    assertFalse(expected.isPresent()); // between is empty
  }


  @Test
  public void getValidJumpBadState4() {
    Optional<int[]> expected = defBoard.getValidJumped(0, 2, 0, 0);
    assertFalse(expected.isPresent()); //dest was null
  }

  @Test
  public void getValidJumpBadState5() {
    Optional<int[]> expected = defBoard.getValidJumped(0, 2, 0, 0);
    assertFalse(expected.isPresent()); //between was null
  }

  @Test
  public void getValidJumpBadState6() {
    Optional<int[]> expected = board4.getValidJumped(4, 4, 2, 2);
    assertFalse(expected.isPresent()); // origin is null
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveDiagonally() {
    //for triangle boards, this will test moving up
    defBoard.move(2, 1, 0, 0);
  }

  @Test
  public void multipleBadMoves() {
    assertNotNull(defBoard);
    super.multipleBadMoves("    _\n" +
        "   O O\n" +
        "  O O O\n" +
        " O O O O\n" +
        "O O O O O");
  }

  @Test
  public void multipleBadMoves2() {
    assertNotNull(board3);
    super.multipleBadMoves2(
        "  O\n"
            + " O O\n"
            + "O O _");
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveOffBoard1() {
    assertEquals("  O\n"
        + " O O\n"
        + "O O _", board3.getGameState());
    board3.move(2, 1, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveIntoNull() {
    assertEquals("  O\n"
        + " O O\n"
        + "O O _", board3.getGameState());
    board3.move(1, 1, 1, 2);
  }

}
