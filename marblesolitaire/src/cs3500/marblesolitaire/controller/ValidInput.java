package cs3500.marblesolitaire.controller;

import java.util.Optional;

import cs3500.marblesolitaire.util.Utils;

/**
 * A class to represent and retrieve a valid input that the {@code MarbleSolitaireController} can
 * receive.
 */
public final class ValidInput {

  private final String input;
  private final InputType type;
  private final int userOffset;

  /**
   * A class to represent and retreive a valid input that the {@code MarbleSolitaireController} can
   * receive.
   *
   * @param input a String that may be a Valid Input
   * @param userOffset an offset that should be put on number inputs for user-facing
   * @throws IllegalArgumentException if the given input string cannot be a valid input
   */
  public ValidInput(String input, int userOffset) throws IllegalArgumentException {
    Utils.requireNonNull(input);
    this.userOffset = userOffset;

    if (input.equalsIgnoreCase("q")) {
      this.input = input;
      this.type = InputType.QUIT_STR;
    } else {
      this.input = Utils.requireValidInteger(input, userOffset);
      this.type = InputType.NUMBER;
    }

  }

  /**
   * Get this {@code ValidInput} as a number (if it is in fact a number).
   *
   * @return {@code this}'s input as a Number, or an empty {@code Optional} if {@code this} is not a
   *          number
   */
  public Optional<Integer> getNumberInput() {
    if (type == InputType.NUMBER) {
      return Optional.of(Integer.parseInt(this.input) - userOffset);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Get this {@code ValidInput} as a String (if it is in fact a String).
   *
   * @return {@code this}'s input as a String, or an empty {@code Optional} if {@code this} is not a
   *         String
   */
  public Optional<String> getStringInput() {
    if (type == InputType.QUIT_STR) {
      return Optional.of(this.input);
    } else {
      return Optional.empty();
    }
  }
}
