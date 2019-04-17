package cs3500.animator.controller;

/**
 * Runs an Animation by parsing CLI instructions to produce different views of specific models. It
 * also starts the animation once those views are created.
 */
public interface AnimationController {

  /**
   * Gets the input file which user inputted in the main method.
   *
   * @return Inuput file which the user has input.
   */
  String getInputFile();

  /**
   * Creates an animation given user input on specific input file, output file, speed, and view
   * type.
   */
  void run();
}