package cs3500.animator.provider.view;

import cs3500.animator.provider.model.ReadOnlyModel;

/**
 * Interface for an Animator View. The view acts as a builder itself, so it is initialized with
 * nothing, and the main method is called to add the fields to the View, such as model or output.
 * Each view must "run" in a certain way, which is specified through runView.
 */
public interface AnimatorView {

  /**
   * Add the output path to this AnimatorView. The VisualView does not need an output specified, so
   * this method will do nothing. Otherwise, the output field of this view will be updated, telling
   * the view where to output the results (if the type of view is an AbstractViewAppendable.
   *
   * @param string desired output path
   */
  void acceptOutput(String string);

  /**
   * Determine if an AnimatorView is an AnimatingView (which has no output and some different
   * behavior than the other views so far.
   *
   * @return true if this AnimatorView is a AnimatingView
   */
  boolean isAnimatingView();

  /**
   * Initialize the speed of an Animator View. This method only affects SVG view, because that is
   * the only view who needs access to a speed.
   *
   * @param s the desired speed
   */
  void setSpeed(int s);

  /**
   * Abstract method overriden in each view class that returns a text representation of the model in
   * whatever way each extending class describes. This text representation is output to the user
   * through runView.
   *
   * @return the text representation of a ReadOnlyModel
   */
  String getTextRepresentation(ReadOnlyModel model);

  /**
   * Getter that returns the output file for this view. This will either be the name of an output
   * file to be created, or "System.out", if the user wants the output to be to the console. This
   * method does nothing for AnimatingViews because they do not output a text output.
   *
   * @return the name of the output for this view
   */
  String getOutput();

  /**
   * Determine if an AnimatorView is an VisualView (which has no output and some different behavior
   * than the other views so far. Although EditViews extend VisualView, they are not considered to
   * simply be VisualViews (this method will return false).
   *
   * @return true if this AnimatorView is a VisualView
   */
  boolean isVisualView();

}
