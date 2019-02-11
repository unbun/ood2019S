package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.util.Utils;


/**
 * A Full Controller for the Marble Solitare Game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final Readable in;
  private final Appendable out;
  private MarbleSolitaireModel activeModel;

  /**
   * Create a Controller for the MS Game that can read and right form differenet streams.
   *
   * @param rd the Readable input stream
   * @param ap the Appendable output stream
   * @throws IllegalArgumentException if any of the streams are null
   */
  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    this.in = Utils.requireNonNull(rd);
    this.out = Utils.requireNonNull(ap);
    this.activeModel = null;
  }

  @Override
  public void playGame(MarbleSolitaireModel model)
          throws IllegalArgumentException, IllegalStateException {

    this.activeModel = Utils.requireNonNull(model);
    stateTransmission();

    Optional<String> endOfGame = gameLoop();
    endGame(endOfGame.isPresent());
    stateTransmission();
  }

  /**
   * Run the loop of retrieving and parsing inputs from {@code this} instance's. When it is done, it
   * may return information about how the game ended.
   *
   * @return And optional string that can contain information about how the game ended.
   */
  private Optional<String> gameLoop() throws IllegalStateException {

    Scanner scan = new Scanner(this.in);

    ArrayList<Integer> inputs = new ArrayList<>();
    Optional<String> possibleQuit = Optional.empty();
    Optional<Integer> possibleMove = Optional.empty();

    while (!activeModel.isGameOver() && !possibleQuit.isPresent()) {
      String userIn;

      try {
        userIn = scan.next();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("Readable stream ran out of inputs to read");
      }

      try {
        ValidInput vi = new ValidInput(userIn, 1);
        possibleMove = vi.getNumberInput();
        possibleQuit = vi.getStringInput();

      } catch (IllegalArgumentException e) {
        toOutStream(String.format("Invalid Move. Play Again. %s\n", e.getMessage()));
        possibleMove = Optional.empty();
        possibleQuit = Optional.empty();
      }

      if (possibleMove.isPresent()) {
        inputs.add(possibleMove.get());
      }

      if (inputs.size() == 4) { //Move with the model since you have 4 inputs
        try {
          activeModel.move(inputs.get(0),
                  inputs.get(1),
                  inputs.get(2),
                  inputs.get(3));

          if (!activeModel.isGameOver()) {
            stateTransmission();
          }

        } catch (IllegalArgumentException e) {
          toOutStream(String.format("Invalid Move. Play Again. %s." +
                  "\n(note that the error messages treat the board as zero-indexed and inputs " +
                  "are one-indexed)\n", e.getMessage()));
        }
        //remove the used inputs
        for (int i = 0; i < 4; i++) {
          inputs.remove(0);
        }
      }
    }
    return possibleQuit;
  }

  /**
   * Output the game state and score of {@code this}'s active {@code MarbleSolitaireModel} if the
   * given {@code MarbleSolitaireModel}'s game isn't over.
   *
   * @return what was outputted
   * @throws IllegalArgumentException if {code this}'s active model is null;
   */
  @Override
  public String stateTransmission() throws IllegalStateException {
    try {
      Utils.requireNonNull(this.activeModel);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("Cannot transmit the state of a null game");
    }
    String toSend = activeModel.getGameState() +
            "\nScore: " + activeModel.getScore() + "\n";
    toOutStream(toSend);
    return toSend;
  }

  /**
   * The void endGame sequence is what happens when the game is over. Any wrap that needs to be done
   * (i.e. outputs to the user) should happen here, explaining why the game ended.
   *
   * @param quit Did the user quit? (false would mean the game ended automatically).
   */
  @Override
  public void endGame(boolean quit) {

    if (quit) {
      toOutStream("Game quit!\n");
      toOutStream("State of game when quit:\n");
    } else {
      toOutStream("Game over!\n");
    }
  }

  /**
   * Transmit a given String to this controller's {@code Appendable}.
   *
   * @param toSend the String to transmit ("" to use default)
   * @throws IllegalStateException if {@code this}'s output stream can't append
   */
  private void toOutStream(String toSend) throws IllegalStateException {
    try {
      out.append(toSend);
    } catch (IOException ioe) {
      throw new IllegalStateException("Cannot use provided output stream.\n " + ioe.getMessage());
    }
  }

  /**
   * Setter for the active model of {@code this}.
   *
   * @param model the model to set to the active model
   * @throws IllegalArgumentException if the given model is nulla
   */
  @Override
  public void setActiveModel(MarbleSolitaireModel model) throws IllegalArgumentException {
    this.activeModel = Utils.requireNonNull(model);
  }
}


