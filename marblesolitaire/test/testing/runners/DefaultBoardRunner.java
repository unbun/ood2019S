package testing.runners;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModelImpl;


/**
 * Runner for the default MSM board.
 */
public class DefaultBoardRunner {


  /**
   * Running the default board with System's in and out.
   *
   * @param args arguments ro run the main
   */
  public static void main(String[] args) {
    MarbleSolitaireModel defaultModel = new EuropeanSolitaireModelImpl();
    MarbleSolitaireController controller =
        new MarbleSolitaireControllerImpl(new InputStreamReader(System.in), System.out);
    controller.playGame(defaultModel);
  }

}
