package cs3500.animator.controller;

/**
 * This interface represents the points of control as created in the animator model. Allows for
 * coordination between the view of the animation and the data controlled in the model. Controls
 * for user input through commands.
 */
public interface IController {
    /**
     * Gets the input file which user inputted in the main method.
     *
     * @return Inuput file which the user has input. Could be any of the input files in the project.
     */
    String getInputFilePath();

    /**
     * Allows for the creation of the animation given user input on specific input file to be run
     * as an animation, output file if selected as .txt or .svg, speed of the animation and view type.
     */
    void runAnimation();
}