import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;


public class TestTriangleSolitaireModel {

  private MarbleSolitaireModel board2 = new TriangleSolitaireModelImpl(2);
  private MarbleSolitaireModel board4 = new TriangleSolitaireModelImpl(4,2,1);
  private MarbleSolitaireModel defBoard = new TriangleSolitaireModelImpl();
  private MarbleSolitaireModel board5 = new TriangleSolitaireModelImpl(5);
  private MarbleSolitaireModel board6 = new TriangleSolitaireModelImpl(6,3,1);


  @Test
  public void testInitState(){
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
  }



}
