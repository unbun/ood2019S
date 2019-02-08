package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

public interface MarbleSolitaireController {
  /**
   * The void playGame(MarbleSolitaireModel model) method should play a game. It should “run”
   * the game in the following sequence until the game is over.
   * NOTE: Each transmission described below should end with a newline.
   *
   * 1. Transmit game state to the Appendable object exactly as the model provides it.
   * 2. Transmit "Score: N", replacing N with the actual score.
   * 3. If the game is ongoing, obtain the next user input from the  Readable object.
   *    A user input consists of the following four individual values
   *    (separated by spaces or newlines):
   *    - The row number of the position from where a marble is to be moved.
   *    - The column number of the position from where a marble is to be moved.
   *    - The row number of the position to where a marble is to be moved.
   *    - The column number of the position to where a marble is to be moved.
   *    - Note: To make the inputs more user-friendly, all row and columns numbers in the input
   *    begin from 1.
   *
   * The controller will parse these inputs and pass the information on to the model to make the move.
   * See below for more detail.
   *
   * If the game has been won is over, the method should transmit each of the following in order:
   * the message "Game over!", the final game state, and the message "Score: N" with N replaced
   * by the final score. The method should then end.
   * For example:
   *
   * Game over!
   *     _ _ _
   *     _ _ _
   * _ _ _ _ _ _ _
   * _ _ _ O _ _ _
   * _ _ _ _ _ _ _
   *     _ _ _
   *     _ _ _
   * Score: 1
   * Key points:
   *
   * Quitting: If at any point, the next value is either the letter 'q' or the letter 'Q',
   * the controller should transmit the following in order: the message "Game quit!", the message
   * "State of game when quit:", the current game state, and the message "Score: N" with N replaced
   * by the final score. The method should then end.
   *
   * For example:
   *
   * Game quit!
   * State of game when quit:
   *     O O O
   *     O _ O
   * O O O _ O O O
   * O O O O O O O
   * O O O O O O O
   *     O O O
   *     O O O
   * Score: 31
   * Bad inputs: If any individual value is unexpected (i.e. something other than 'q',
   * 'Q' or a positive number) it should ask the user to re-enter that value again. If the user
   * entered the from-row and from-column correctly but the to-row incorrectly, the controller
   * should continue attempting to read a value for to-row before moving on to read the value for
   * to-column. Once all four values are successfully read, if the move was invalid as signaled by
   * the model, the controller should transmit a message to the Appendable object saying  "Invalid
   * move. Play again. X" where X is any informative message about why the move was invalid
   * (all on one line), and resume waiting for valid input.
   *
   * Error handling: The playGame method should throw an IllegalArgumentException if a null model
   * is passed to it. If the Appendable object is unable to transmit output or the Readable object
   * is unable to provide inputs (for whatever reason), the playGame method should throw an
   * IllegalStateException to its caller. The playGame method must not throw any other exceptions,
   * not should it propagate any exceptions thrown by the model.
   * @param model
   * @throws IllegalArgumentException
   */
  void playGame(MarbleSolitaireModel model) throws IllegalArgumentException, IllegalStateException;
}
