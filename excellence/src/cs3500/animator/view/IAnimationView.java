package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;

/**
 * This interface represents the various view supported by the animator.
 *      1. Textual view
 *      2. Visual Animation view
 *      3. SVG Animation view
 *      4. Interactive Animation view
 */
public interface IAnimationView {

  /**
   * Returns the specific type of view, expressed as an enumeration.
   *
   * @return the expected type of view, of the four visual types
   */
  ViewType getViewType();

  /**
   * Returns the animation panel of the visual views, such as Hybrid and visual view.
   *
   * @return the animation panel of the visual views.
   *
   * @throws IllegalArgumentException if this method is called on a Text View or an SVGView - one
   *                                  which does not have a panel.
   */
  AnimationPanel getAnimationPanel() throws IllegalArgumentException;


  /**
   * Refreshes the view with every tick.
   *
   * @param model the model of the animation to be drawn
   */
  String makeView(AnimationModel model);

  /**
   * Make the view visible. Called after the view is constructed.
   */
  void makeVisible();

}
