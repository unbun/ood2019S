import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestEuropeanMarbleSolitaireModel {


  private MarbleSolitaireModel defBoard = new EuropeanSolitaireModelImpl();
  private MarbleSolitaireModel board3 = new EuropeanSolitaireModelImpl(4, 6);
  private MarbleSolitaireModel board3Corner = new EuropeanSolitaireModelImpl(5, 1);
  private MarbleSolitaireModel board5 = new EuropeanSolitaireModelImpl(5, 5, 9);
  private MarbleSolitaireModel board1 = new EuropeanSolitaireModelImpl(1);


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

}
