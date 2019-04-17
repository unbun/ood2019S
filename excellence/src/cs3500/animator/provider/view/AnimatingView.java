package cs3500.animator.provider.view;

import cs3500.animator.provider.model.ReadOnlyModel;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Interface providing methods for views that 1) have no text output, and 2) require event listeners
 * and use a timer to start and play animations. The two current views that our application supports
 * both have many components (hence the need for the abstracted getSomething method). Each
 * implementing view will store a ReadOnlyModel which will be updated by the controller. The view
 * SHOULD NOT be updating the model, only reading from it to find the state.
 */
public interface AnimatingView extends AnimatorView {

  /**
   * Set up animation screen and buttons for this type of AnimatingView. Each implementing class has
   * its own set up requirements (Further JavaDoc explains in each class). The listener from the
   * controller is passed in to be able to accept events from the view, because the controller will
   * be handling those events.
   *
   * @param listener listener from the controller
   */
  void initView(ActionListener listener, int canWidth, int canHeight);

  /**
   * Update the tick stored in this model. Although the controller also holds a tick, some
   * implementing views still need to access the CURRENT tick to know what to animate at a certain
   * time. This method is used to update this view's tick field in accordance with the controller.
   *
   * @param t new tick value
   */
  void setTick(int t);

  /**
   * Return a component (usually something being listened to) from this view, specified by the input
   * string s. This method is getting many things, so it returns an Object. Currently, only
   * VisualViews need only access to the frame from the controller. If the view is an EditView, it
   * overrides that method to include other items the EditView can grab (i.e. JButton, JComboBox).
   *
   * @param s name of item to return
   * @return item that s corresponds to in this view
   */
  Object getSomething(String s);

  /**
   * Method to allow this view to update it's model field to hold the current state of the model
   * after user interaction. Model is ReadOnlyModel so that it cannot be changed within the view.
   *
   * @param model current state of the controller's model
   */
  void updateModel(ReadOnlyModel model);

  /**
   * Allows us to get a button from EditView. This is mainly because the controller needs a way to
   * access the buttons displayed in editView.
   *
   * @param s The name of the button that you want to get
   * @return the button that is associated with that string
   */
  JButton getButton(String s);

  /**
   * Calls the built-in repaint() method. This method consistently calls the paintComponent()
   * method.
   */
  void callRepaint();

  /**
   * Gets the model of this view. The ReadOnly is a copy of the newly-edited model that can not be
   * modified by the View Classes.
   *
   * @return the Read-Only model of this view.
   */
  ReadOnlyModel getModel();
}
