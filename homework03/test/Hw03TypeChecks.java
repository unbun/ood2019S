/**
 * Do not modify this file. This file should compile correctly with your code!
 * You DO NOT need to submit this file.
 */
public class Hw03TypeChecks {

  /**
   * The contents of this method are meaningless.
   * They are only here to ensure that your code compiles properly.
   */
  public static void main(String[] args) {
    Readable rd = null;
    Appendable ap = null;
    helper(new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(),
           new cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl(rd, ap));
    helper(new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(3, 3),
           new cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl(rd, ap));
  }

  private static void helper(
           cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel model,
           cs3500.marblesolitaire.controller.MarbleSolitaireController controller) {
    controller.playGame(model);
  }

}
