package cs3500.animator.controller;

/**
 * This interface represents multiple points of control for the Excellence Animation. It parses CLI
 * instructions to create views and models that a use specified. It also starts the animation once
 * those views are created.
 */
public interface AnimationController {

  /**
   * Gets the input file which user inputted in the main method.
   *
   * @return Inuput file which the user has input. Could be any of the input files in the project.
   */
  String getInputFilePath();

  /**
   * Allows for the creation of the animation given user input on specific input file to be run as
   * an animation, output file if selected as .txt or .svg, speed of the animation and view type.
   */
  void runAnimation();
}