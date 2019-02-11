
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;
import testing.mocks.Interaction;
import testing.mocks.MockModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class TestMarbleSolitaireController {

  //***************** Helper Tests *****************//

  @Test
  public void endGame() {
    StringBuilder testInput = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    expected.append("Game quit!\n" +
            "State of game when quit:\n");

    StringReader input = new StringReader(testInput.toString());
    StringBuilder actual = new StringBuilder();

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, actual);
    controller.endGame(true);

    assertEquals(expected.toString(), actual.toString());
    actual.append("\n**************\n");
    expected.append("\n**************\n");

    controller.endGame(false);
    expected.append("Game over!\n");

    assertEquals(expected.toString(), actual.toString());
  }

  @Test
  public void transmission() {
    StringBuilder testInput = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    expected.append("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n");

    StringReader input = new StringReader(testInput.toString());
    StringBuilder actual = new StringBuilder();

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, actual);
    controller.setActiveModel(new MarbleSolitaireModelImpl());
    controller.stateTransmission();

    assertEquals(expected.toString(), actual.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void noModelTransmission() {
    StringReader input = new StringReader(new StringBuilder().toString());
    StringBuilder actual = new StringBuilder();

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, actual);
    // don't set the active model
    controller.stateTransmission();
  }

  //***************** Game Play *****************//

  @Test
  public void spaceDelimLowerQ() {
    try {
      runModelInteracts(new MockModel(9),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 2 3 4\n"),
              prints("(r=0,c=1) -> (r=2, c=3)\nScore: 1"),
              inputs("q\n"),
              prints("Game quit!\n" +
                      "State of game when quit:\n" +
                      "(r=0,c=1) -> (r=2, c=3)\n" +
                      "Score: 1"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void lineDelimUpperQ() {
    try {
      runModelInteracts(new MockModel(9),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1\n"),
              inputs("2\n"),
              inputs("3\n"),
              inputs("4\n"),
              prints("(r=0,c=1) -> (r=2, c=3)\nScore: 1"),
              inputs("Q\n"),
              prints("Game quit!\n" +
                      "State of game when quit:\n" +
                      "(r=0,c=1) -> (r=2, c=3)\n" +
                      "Score: 1"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void fullMockGame() {
    try {
      runModelInteracts(new MockModel(4),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 2 3 4\n"),
              prints("(r=0,c=1) -> (r=2, c=3)\nScore: 1"),
              inputs("3 4 5 6\n"),
              prints("(r=2,c=3) -> (r=4, c=5)\nScore: 2"),
              inputs("5 6 7 8\n"),
              prints("(r=4,c=5) -> (r=6, c=7)\nScore: 3"),
              inputs("7 8 9 10\n"),
              prints("Game over!\n" +
                      "(r=6,c=7) -> (r=8, c=9)\n" +
                      "Score: 4"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void fullMockGame2() {
    try {
      runModelInteracts(new MockModel(4),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 q\n"),
              prints("(r=0,c=1) -> (r=2, c=3)\nScore: 1"),
              prints("(r=4,c=5) -> (r=6, c=7)\nScore: 2"),
              prints("(r=8,c=9) -> (r=10, c=11)\nScore: 3"),
              prints("Game over!\n" +
                      "(r=12,c=13) -> (r=14, c=15)\n" +
                      "Score: 4"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void moreThan4PerLine() {
    try {
      runModelInteracts(new MockModel(3),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 2 3 4 5 6\n"),
              prints("(r=0,c=1) -> (r=2, c=3)\nScore: 1"),
              inputs("7 8 9 10 11 12\n"),
              prints("(r=4,c=5) -> (r=6, c=7)\n" +
                      "Score: 2"),
              prints("Game over!\n" +
                      "(r=8,c=9) -> (r=10, c=11)\n" +
                      "Score: 3"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void lessThan4PerLine() {
    try {
      runModelInteracts(new MockModel(1),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 2\n"),
              inputs("3\n"),
              inputs("4\n"),
              prints("Game over!\n" +
                      "(r=0,c=1) -> (r=2, c=3)\n" +
                      "Score: 1"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void nonPosIntInputs() {
    try {
      runModelInteracts(new MockModel(1),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("-1\n"),
              prints("Invalid Move. Play Again. Input '-1' must be an int greater than 1"),
              inputs("0\n"),
              prints("Invalid Move. Play Again. Input '0' must be an int greater than 1"),
              inputs("r\n"),
              prints("Invalid Move. Play Again. Input 'r' does not contain a valid string"),
              inputs("q\n"),
              prints("Game quit!\n" +
                      "State of game when quit:\n" +
                      "(r=0,c=0) -> (r=0, c=0)\n" +
                      "Score: 0"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void nonPosIntInputs2() {
    try {
      runModelInteracts(new MockModel(1),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("-1\n"),
              prints("Invalid Move. Play Again. Input '-1' must be an int greater than 1"),
              inputs("0 -3 -5 4 -3\n"),
              prints("Invalid Move. Play Again. Input '0' must be an int greater than 1"),
              prints("Invalid Move. Play Again. Input '-3' must be an int greater than 1"),
              prints("Invalid Move. Play Again. Input '-5' must be an int greater than 1"),
              prints("Invalid Move. Play Again. Input '-3' must be an int greater than 1"),
              inputs("q\n"),
              prints("Game quit!\n" +
                      "State of game when quit:\n" +
                      "(r=0,c=0) -> (r=0, c=0)\n" +
                      "Score: 0"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void negativeInputsWithValid() {
    try {
      runModelInteracts(new MockModel(3),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 2 3 4\n"),
              prints("(r=0,c=1) -> (r=2, c=3)\nScore: 1"),
              inputs("7 -6\n"),
              prints("Invalid Move. Play Again. Input '-6' must be an int greater than 1"),
              inputs("6 5 4\n"),
              prints("(r=6,c=5) -> (r=4, c=3)\nScore: 2"),
              inputs("q\n"),
              prints("Game quit!\n" +
                      "State of game when quit:\n" +
                      "(r=6,c=5) -> (r=4, c=3)\n" +
                      "Score: 2"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void negativeInputsWithValid2() {
    try {
      runModelInteracts(new MockModel(2),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 -2 3 -4 0 r 1/3 12 3 5 & -2 0.5 9 10 0 2\n"),
              prints("Invalid Move. Play Again. Input '-2' must be an int greater than 1"),
              prints("Invalid Move. Play Again. Input '-4' must be an int greater than 1"),
              prints("Invalid Move. Play Again. Input '0' must be an int greater than 1"),
              prints("Invalid Move. Play Again. Input 'r' does not contain a valid string"),
              prints("Invalid Move. Play Again. Input '1/3' does not contain a valid string"),
              prints("(r=0,c=2) -> (r=11, c=2)\nScore: 1"),
              prints("Invalid Move. Play Again. Input '&' does not contain a valid string"),
              prints("Invalid Move. Play Again. Input '-2' must be an int greater than 1"),
              prints("Invalid Move. Play Again. Input '0.5' does not contain a valid string"),
              prints("Invalid Move. Play Again. Input '0' must be an int greater than 1"),
              prints("Game over!\n" +
                      "(r=4,c=8) -> (r=9, c=1)\n" +
                      "Score: 2"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void negativeInputsWithValid3() {
    try {
      runModelInteracts(new MockModel(2),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 -2 3 -4 0 r 1/3 Q 12 3\n"),
              prints("Invalid Move. Play Again. Input '-2' must be an int greater than 1"),
              prints("Invalid Move. Play Again. Input '-4' must be an int greater than 1"),
              prints("Invalid Move. Play Again. Input '0' must be an int greater than 1"),
              prints("Invalid Move. Play Again. Input 'r' does not contain a valid string"),
              prints("Invalid Move. Play Again. Input '1/3' does not contain a valid string"),
              prints("Game quit!\n" +
                      "State of game when quit:\n" +
                      "(r=0,c=0) -> (r=0, c=0)\n" +
                      "Score: 0"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void quitWithoutFullInput() {
    try {
      runModelInteracts(new MockModel(3),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 2 3 4\n"),
              prints("(r=0,c=1) -> (r=2, c=3)\nScore: 1"),
              inputs("7 5 4\n"),
              inputs("q\n"),
              prints("Game quit!\n" +
                      "State of game when quit:\n" +
                      "(r=0,c=1) -> (r=2, c=3)\n" +
                      "Score: 1"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void quitWithoutFullInput2() {
    try {
      runModelInteracts(new MockModel(3),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 2 3 4\n"),
              prints("(r=0,c=1) -> (r=2, c=3)\nScore: 1"),
              inputs("7 5 -6\n"),
              prints("Invalid Move. Play Again. Input '-6' must be an int greater than 1"),
              inputs("q\n"),
              prints("Game quit!\n" +
                      "State of game when quit:\n" +
                      "(r=0,c=1) -> (r=2, c=3)\n" +
                      "Score: 1"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  //***************** Throwing Exceptions *****************//

  @Test(expected = IllegalArgumentException.class)
  public void badConstructor1() {
    MarbleSolitaireController contr = new MarbleSolitaireControllerImpl(null, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void badConstructor2() {
    MarbleSolitaireController contr =
            new MarbleSolitaireControllerImpl(new StringReader(""), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badConstructor3() {
    MarbleSolitaireController contr =
            new MarbleSolitaireControllerImpl(null, null);
  }

  @Test
  public void nullModel() {
    MarbleSolitaireController contr =
            new MarbleSolitaireControllerImpl(new StringReader(""), new StringBuilder());
    try {
      contr.playGame(null);
      fail("Controller should not run for null models");
    } catch (IllegalArgumentException e) {
      assertNotNull(e.getMessage());
    }
  }

  @Test
  public void readableRunsOut() {
    try {
      runModelInteracts(new MockModel(3),
              prints("(r=0,c=0) -> (r=0, c=0)\nScore: 0"),
              inputs("1 2 3 4\n"),
              prints("(r=0,c=1) -> (r=2, c=3)\nScore: 1"),
              inputs("7 5 4\n"));
    } catch (IllegalStateException se) {
      assertNotNull(se.getMessage());
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void badOutputStream() {
    StringBuilder testInput = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    testInput.append("1 2 3 4");
    expected.append("this should throw an error");

    StringReader input = new StringReader(testInput.toString());


    Appendable badStream = null;
    try {
      badStream = new FileWriter("not a file");
    } catch (IOException e) {
      fail(e.getMessage());
    }

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, badStream);

    try {
      controller.playGame(new MarbleSolitaireModelImpl());
      fail("playing the game (and trying to output) should fail");

    } catch (IllegalStateException se) {
      assertNotNull(se.getMessage());
    }
  }

  //***************** Mock/Interaction runners *****************//

  /**
   * Create an {@code Interaction} for outputting a set of given lines with a new line at the end.
   *
   * @param lines a set of lines to print
   * @return an {@code Interacttion} that will be able to append them to an {@code Appendable}
   */
  private static Interaction prints(String... lines) {
    return (in, out) -> {
      for (String line : lines) {
        out.append(line).append('\n');
      }
    };
  }

  /**
   * Create an {@code Interaction} for reading a given string.
   *
   * @param strIn a set of lines to read
   * @return an {@code Interacttion} that will be able to append them to a {@code Readable}
   */
  private static Interaction inputs(String strIn) {
    return (in, out) -> {
      in.append(strIn);
    };
  }

  private void runModelInteracts(MarbleSolitaireModel model, Interaction... interacts)
          throws IOException {

    StringBuilder testInput = new StringBuilder();
    StringBuilder expected = new StringBuilder();

    for (Interaction i : interacts) {
      i.apply(testInput, expected);
    }

    StringReader input = new StringReader(testInput.toString());
    StringBuilder actual = new StringBuilder();


    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, actual);
    controller.playGame(model);

    assertEquals(expected.toString(), actual.toString());
  }


  //********************* Running a full MS Model *********************//


  @Test
  public void winMSGame() {
    //Run a 31-move winning game and make sure the controller is working along the way
    try {
      runModelInteracts(new MarbleSolitaireModelImpl(),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 32"),
              inputs("4 2 4 4\n"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O _ _ O O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 31"),
              inputs("6 3 4 3\n"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O _ O O O O O\n" +
                      "O O _ O O O O\n" +
                      "    _ O O\n" +
                      "    O O O\n" +
                      "Score: 30"),
              inputs("5 1 5 3\n"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O _ O O O O O\n" +
                      "_ _ O O O O O\n" +
                      "    _ O O\n" +
                      "    O O O\n" +
                      "Score: 29"),
              inputs("5 4 5 2\n"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O _ O O O O O\n" +
                      "_ O _ _ O O O\n" +
                      "    _ O O\n" +
                      "    O O O\n" +
                      "Score: 28"),
              inputs("5 6 5 4\n"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O _ O O O O O\n" +
                      "_ O _ O _ _ O\n" +
                      "    _ O O\n" +
                      "    O O O\n" +
                      "Score: 27"),
              inputs("7 5 5 5\n"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O _ O O O O O\n" +
                      "_ O _ O O _ O\n" +
                      "    _ O _\n" +
                      "    O O _\n" +
                      "Score: 26"),
              inputs("4 5 6 5\n"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O _ O O _ O O\n" +
                      "_ O _ O _ _ O\n" +
                      "    _ O O\n" +
                      "    O O _\n" +
                      "Score: 25"),
              inputs("7 3 7 5\n"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O _ O O _ O O\n" +
                      "_ O _ O _ _ O\n" +
                      "    _ O O\n" +
                      "    _ _ O\n" +
                      "Score: 24"),
              inputs("7 5 5 5\n"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O _ O O _ O O\n" +
                      "_ O _ O O _ O\n" +
                      "    _ O _\n" +
                      "    _ _ _\n" +
                      "Score: 23"),
              inputs("3 3 5 3\n"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O _ O O O O\n" +
                      "O _ _ O _ O O\n" +
                      "_ O O O O _ O\n" +
                      "    _ O _\n" +
                      "    _ _ _\n" +
                      "Score: 22"),
              inputs("1 3 3 3\n"),
              prints("    _ O O\n" +
                      "    _ O O\n" +
                      "O O O O O O O\n" +
                      "O _ _ O _ O O\n" +
                      "_ O O O O _ O\n" +
                      "    _ O _\n" +
                      "    _ _ _\n" +
                      "Score: 21"),
              inputs("2 5 4 5\n"),
              prints("    _ O O\n" +
                      "    _ O _\n" +
                      "O O O O _ O O\n" +
                      "O _ _ O O O O\n" +
                      "_ O O O O _ O\n" +
                      "    _ O _\n" +
                      "    _ _ _\n" +
                      "Score: 20"),
              inputs("4 5 6 5\n"),
              prints("    _ O O\n" +
                      "    _ O _\n" +
                      "O O O O _ O O\n" +
                      "O _ _ O _ O O\n" +
                      "_ O O O _ _ O\n" +
                      "    _ O O\n" +
                      "    _ _ _\n" +
                      "Score: 19"),
              inputs("6 5 6 3\n"),
              prints("    _ O O\n" +
                      "    _ O _\n" +
                      "O O O O _ O O\n" +
                      "O _ _ O _ O O\n" +
                      "_ O O O _ _ O\n" +
                      "    O _ _\n" +
                      "    _ _ _\n" +
                      "Score: 18"),
              inputs("6 3 4 3\n"),
              prints("    _ O O\n" +
                      "    _ O _\n" +
                      "O O O O _ O O\n" +
                      "O _ O O _ O O\n" +
                      "_ O _ O _ _ O\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 17"),
              inputs("4 3 2 3\n"),
              prints("    _ O O\n" +
                      "    O O _\n" +
                      "O O _ O _ O O\n" +
                      "O _ _ O _ O O\n" +
                      "_ O _ O _ _ O\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 16"),
              inputs("3 1 5 1\n"),
              prints("    _ O O\n" +
                      "    O O _\n" +
                      "_ O _ O _ O O\n" +
                      "_ _ _ O _ O O\n" +
                      "O O _ O _ _ O\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 15"),
              inputs("5 1 5 3\n"),
              prints("    _ O O\n" +
                      "    O O _\n" +
                      "_ O _ O _ O O\n" +
                      "_ _ _ O _ O O\n" +
                      "_ _ O O _ _ O\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 14"),
              inputs("5 3 5 5\n"),
              prints("    _ O O\n" +
                      "    O O _\n" +
                      "_ O _ O _ O O\n" +
                      "_ _ _ O _ O O\n" +
                      "_ _ _ _ O _ O\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 13"),
              inputs("3 7 3 5\n"),
              prints("    _ O O\n" +
                      "    O O _\n" +
                      "_ O _ O O _ _\n" +
                      "_ _ _ O _ O O\n" +
                      "_ _ _ _ O _ O\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 12"),
              inputs("3 4 3 6\n"),
              prints("    _ O O\n" +
                      "    O O _\n" +
                      "_ O _ _ _ O _\n" +
                      "_ _ _ O _ O O\n" +
                      "_ _ _ _ O _ O\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 11"),
              inputs("5 7 3 7\n"),
              prints("    _ O O\n" +
                      "    O O _\n" +
                      "_ O _ _ _ O O\n" +
                      "_ _ _ O _ O _\n" +
                      "_ _ _ _ O _ _\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 10"),
              inputs("3 7 3 5\n"),
              prints("    _ O O\n" +
                      "    O O _\n" +
                      "_ O _ _ O _ _\n" +
                      "_ _ _ O _ O _\n" +
                      "_ _ _ _ O _ _\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 9"),
              inputs("1 5 1 3\n"),
              prints("    O _ _\n" +
                      "    O O _\n" +
                      "_ O _ _ O _ _\n" +
                      "_ _ _ O _ O _\n" +
                      "_ _ _ _ O _ _\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 8"),
              inputs("1 3 3 3\n"),
              prints("    _ _ _\n" +
                      "    _ O _\n" +
                      "_ O O _ O _ _\n" +
                      "_ _ _ O _ O _\n" +
                      "_ _ _ _ O _ _\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 7"),
              inputs("3 2 3 4\n"),
              prints("    _ _ _\n" +
                      "    _ O _\n" +
                      "_ _ _ O O _ _\n" +
                      "_ _ _ O _ O _\n" +
                      "_ _ _ _ O _ _\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 6"),
              inputs("3 4 3 6\n"),
              prints("    _ _ _\n" +
                      "    _ O _\n" +
                      "_ _ _ _ _ O _\n" +
                      "_ _ _ O _ O _\n" +
                      "_ _ _ _ O _ _\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 5"),
              inputs("3 6 5 6\n"),
              prints("    _ _ _\n" +
                      "    _ O _\n" +
                      "_ _ _ _ _ _ _\n" +
                      "_ _ _ O _ _ _\n" +
                      "_ _ _ _ O O _\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 4"),
              inputs("5 6 5 4\n"),
              prints("    _ _ _\n" +
                      "    _ O _\n" +
                      "_ _ _ _ _ _ _\n" +
                      "_ _ _ O _ _ _\n" +
                      "_ _ _ O _ _ _\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 3"),
              inputs("5 4 3 4\n"),
              prints("    _ _ _\n" +
                      "    _ O _\n" +
                      "_ _ _ O _ _ _\n" +
                      "_ _ _ _ _ _ _\n" +
                      "_ _ _ _ _ _ _\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 2"),
              inputs("2 4 4 4\n"),
              prints("Game over!\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "_ _ _ _ _ _ _\n" +
                      "_ _ _ O _ _ _\n" +
                      "_ _ _ _ _ _ _\n" +
                      "    _ _ _\n" +
                      "    _ _ _\n" +
                      "Score: 1"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void loseMSGame() {
    //Run a losing game in six moves and make sure the controller outputs everything correctly
    try {
      runModelInteracts(new MarbleSolitaireModelImpl(),
              inputs("2 4 4 4 5 4 3 4 4 2 4 4 4 5 4 3 4 7 4 5 7 4 5 4"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 32\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 31\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 30\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O _ _ O O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 29\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O _ O _ _ O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 28\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O _ O _ O _ _\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 27\n" +
                      "Game over!\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O _ O _ O _ _\n" +
                      "O O O O O O O\n" +
                      "    O _ O\n" +
                      "    O _ O\n" +
                      "Score: 26"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void quitMSGame1() {
    //Run a losing game in six moves and make sure the controller outputs everything correctly
    try {
      runModelInteracts(new MarbleSolitaireModelImpl(),
              inputs("2 4 4 4 5 4 3 4 4 2 4 4 q 4 5 4 3"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 32\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 31\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 30\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O _ _ O O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 29\n" +
                      "Game quit!\n" +
                      "State of game when quit:\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O _ _ O O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 29"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void quitMSGame2() {
    //Run a losing game in six moves and make sure the controller outputs everything correctly
    try {
      runModelInteracts(new MarbleSolitaireModelImpl(),
              inputs("2 4 4 4 5 4 3 4 4 2 4 4 4 5 Q 4 3"),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 32\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 31\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 30\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O _ _ O O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 29\n" +
                      "Game quit!\n" +
                      "State of game when quit:\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O _ _ O O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 29"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void modelInvalids1() {
    try {
      runModelInteracts(new MarbleSolitaireModelImpl(),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 32"),
              inputs("2 4 4 4\n"),
              prints("    O O O\n" +
                      "    O _ O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 31"),
              inputs("1 1 1 3\n"),
              prints("Invalid Move. Play Again. [null ] --([null ])--> [0, 2 ] contains" +
                      " positions with incorrect states.\n" +
                      "(note that the error messages treat " +
                      "the board as zero-indexed and inputs are one-indexed)"),
              inputs("5 4 3 4\n"),
              prints("    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 30"),
              inputs("q\n"),
              prints("Game quit!\n" +
                      "State of game when quit:\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 30"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }


  @Test
  public void modelInvalids2() {
    try {
      runModelInteracts(new MarbleSolitaireModelImpl(),
              prints("    O O O\n" +
                      "    O O O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 32"),
              inputs("2 4 4 4\n"),
              prints("    O O O\n" +
                      "    O _ O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "O O O O O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 31"),
              inputs("1 1 2 2\n"),
              prints("Invalid Move. Play Again. [null ] -> [null ] is not an " +
                      "orthogonal move of 2 places.\n(note that the error messages treat " +
                      "the board as zero-indexed and inputs are one-indexed)"),
              inputs("5 4 3 4\n"),
              prints("    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 30"),
              inputs("Q\n"),
              prints("Game quit!\n" +
                      "State of game when quit:\n" +
                      "    O O O\n" +
                      "    O _ O\n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O _ O O O\n" +
                      "    O O O\n" +
                      "    O O O\n" +
                      "Score: 30"));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }


}


