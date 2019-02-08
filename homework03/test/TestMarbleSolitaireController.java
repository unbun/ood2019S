
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import interactions.Interaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestMarbleSolitaireController {

  @Test
  public void test4IntInputs(){
    try {
      runModelInteracts(new MockModel(9),
              inputs("1 2 3 4"\n),
              prints("1 2 3 4"),
              inputs("-2 3 4 5"),
              prints(""),
              inputs("3 -4 5 6"),
              prints(""),
              inputs("4 5 -6 7"),
              prints(""),
              inputs("5 6 7 -8"),
              prints(""),
              inputs("6 7 8 9"),
              prints("6 7 8 9"),
              inputs("7 8 9 10"),
              prints(""));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }




  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }

  static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in);
    };
  }



  void runModelInteracts(MarbleSolitaireModel model, Interaction... interacts) throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction i : interacts) {
      i.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(input, actualOutput);
    controller.playGame(model);

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

}
