/**
 * Do not modify this file. This file should compile correctly with your code! You DO NOT need to
 * submit this file.
 */
public class Hw02TypeChecks {

  /**
   * The contents of this method are meaningless. They are only here to ensure that your code
   * compiles properly.
   */
  public static void main(String[] args) {
    helper(new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl());
    helper(new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(2, 2));
    helper(new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(5));
    helper(new cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelImpl(3, 0, 4));
  }

  private static void helper(cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel model) {
    model.move(1, 3, 3, 3);
  }

}
