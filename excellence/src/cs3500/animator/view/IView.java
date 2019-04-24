package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * This interface represents the various view supported by the animator.
 */
public interface IView {

  /**
   * Set the listeners to control this view with (if applicable).
   *
   * @param mouse the mouse listener
   * @param keys the keyboard listener
   */
  void setListeners(MouseListener mouse, KeyListener keys);


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

  /**
   * Setter for the view's model.
   *
   * @param model the model to use for this view.
   */
  void setModel(AnimationModel model);

}
