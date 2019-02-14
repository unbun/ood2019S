import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestTriangleSolitaireModel extends AbstractTestMarbleSolitaireModel {

  private MarbleSolitaireModel board2 = new TriangleSolitaireModelImpl(2);
  private MarbleSolitaireModel board4 = new TriangleSolitaireModelImpl(4, 2, 1);
  private MarbleSolitaireModel defBoard = new TriangleSolitaireModelImpl();
  private MarbleSolitaireModel board5 = new TriangleSolitaireModelImpl(5);
  private MarbleSolitaireModel board6 = new TriangleSolitaireModelImpl(6, 3, 1);
  private MarbleSolitaireModel board7 = new TriangleSolitaireModelImpl(7, 4, 2);


  @Test
  public void testInitState() {
    assertEquals("[0, 0*][null ][null ][null ][null ]\n" +
            "[1, 0 ][1, 1 ][null ][null ][null ]\n" +
            "[2, 0 ][2, 1 ][2, 2 ][null ][null ]\n" +
            "[3, 0 ][3, 1 ][3, 2 ][3, 3 ][null ]\n" +
            "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ]", defBoard.toString());
    assertEquals(
            "    _\n" +
                    "   O O\n" +
                    "  O O O\n" +
                    " O O O O\n" +
                    "O O O O O", defBoard.getGameState());

    assertEquals("[0, 0*][null ]\n[1, 0 ][1, 1 ]", board2.toString());
    assertEquals(" _\n" +
            "O O", board2.getGameState());

    assertEquals("[0, 0 ][null ][null ][null ]\n" +
            "[1, 0 ][1, 1 ][null ][null ]\n" +
            "[2, 0 ][2, 1*][2, 2 ][null ]\n" +
            "[3, 0 ][3, 1 ][3, 2 ][3, 3 ]", board4.toString());
    assertEquals("   O\n" +
            "  O O\n" +
            " O _ O\n" +
            "O O O O", board4.getGameState());

    assertEquals("[0, 0*][null ][null ][null ][null ]\n" +
            "[1, 0 ][1, 1 ][null ][null ][null ]\n" +
            "[2, 0 ][2, 1 ][2, 2 ][null ][null ]\n" +
            "[3, 0 ][3, 1 ][3, 2 ][3, 3 ][null ]\n" +
            "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ]", board5.toString());
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", board5.getGameState());

    assertEquals("[0, 0 ][null ][null ][null ][null ][null ]\n" +
            "[1, 0 ][1, 1 ][null ][null ][null ][null ]\n" +
            "[2, 0 ][2, 1 ][2, 2 ][null ][null ][null ]\n" +
            "[3, 0 ][3, 1*][3, 2 ][3, 3 ][null ][null ]\n" +
            "[4, 0 ][4, 1 ][4, 2 ][4, 3 ][4, 4 ][null ]\n" +
            "[5, 0 ][5, 1 ][5, 2 ][5, 3 ][5, 4 ][5, 5 ]", board6.toString());
    assertEquals("     O\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O _ O O\n" +
            " O O O O O\n" +
            "O O O O O O", board6.getGameState());

    assertEquals("[0, 0 ][null ][null ][null ][null ][null ][null ]\n" +
            "[1, 0 ][1, 1 ][null ][null ][null ][null ][null ]\n" +
            "[2, 0 ][2, 1 ][2, 2 ][null ][null ][null ][null ]\n" +
            "[3, 0 ][3, 1 ][3, 2 ][3, 3 ][null ][null ][null ]\n" +
            "[4, 0 ][4, 1 ][4, 2*][4, 3 ][4, 4 ][null ][null ]\n" +
            "[5, 0 ][5, 1 ][5, 2 ][5, 3 ][5, 4 ][5, 5 ][null ]\n" +
            "[6, 0 ][6, 1 ][6, 2 ][6, 3 ][6, 4 ][6, 5 ][6, 6 ]", board7.toString());
    assertEquals(
            "      O\n" +
                    "     O O\n" +
                    "    O O O\n" +
                    "   O O O O\n" +
                    "  O O _ O O\n" +
                    " O O O O O O\n" +
                    "O O O O O O O", board7.getGameState());
  }


  @Test(expected = IllegalArgumentException.class)
  public void negativeArmThick() {
    new TriangleSolitaireModelImpl(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmpty1() {
    new TriangleSolitaireModelImpl(3, 1, 2); // a null posn
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmpty2() {
    new TriangleSolitaireModelImpl(3, 7, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidEmpty3() {
    new TriangleSolitaireModelImpl(2, 9);
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


}
