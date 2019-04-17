package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * Represents the various types of views of animations supported.
 *
 * <p>Change Log: changed return type of getAnimationPanel to be less constraining.
 */
public interface IView {

  /**
   * Setter for the view's listeners. Only applicable to ControllableView.
   *
   * @param mouse the mouse listener
   * @param keys the keyboard listener
   */
  void setListeners(MouseListener mouse, KeyListener keys);


  /**
   * Returns the specific type of view, expressed as an enumeration.
   */
  ViewType getViewType();

  /**
   * Returns the animation panel of the visual views.
   *
   * @return a JPanel containing the animations of the view (don't need a pane with the buttons or
   *         any superflous things)
   * @throws UnsupportedOperationException if this method is called on a non-visual view.
   */
  JPanel getAnimationPanel() throws UnsupportedOperationException;


  /**
   * Refreshes the view with every tick.
   *
   * @param model the model of the animation to be drawn
   */
  String updateView(AnimationModel model);

  /**
   * Makes the view visible.
   */
  void init();

  /**
   * Setter for the view's model.
   *
   * @param model the model to use
   */
  void setModel(AnimationModel model);

}
