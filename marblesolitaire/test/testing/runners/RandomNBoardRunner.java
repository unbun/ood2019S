package testing.runners;

import java.io.InputStreamReader;
import java.util.Random;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl;


/**
 * Runner for a random MS board of a customizable size.
 */
public class RandomNBoardRunner {


  /**
   * Running the Randmo board with System's in and out.
   *
   * @param args arguments ro run the main
   */
  public static void main(String[] args) {

    MarbleSolitaireModel defaultModel;

    int n = 5;
    Random r = new Random();

    while (true) {

      int sRow = r.nextInt(3 * n - 2) + 1;
      int sCol = r.nextInt(3 * n - 2) + 1;

      try {
        defaultModel = new MarbleSolitaireModelImpl(n, sRow, sCol);
        System.out.flush();
        break;
      } catch (IllegalArgumentException e) {
        System.out.println("Can't use (" + sRow + ", " + sCol + ") as the starting empty slot");
      }
    }

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(new InputStreamReader(System.in), System.out);
    controller.playGame(defaultModel);
  }

}
