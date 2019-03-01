import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * The interface for our view class
 */
public interface IView
{
  /**
   * Set the label that is showing what the model stores.
   * @param s
   */
  void setEchoOutput(String s);

  /**
   * Get the string from the text field and return it
   * @return
   */
  String getInputString();

  /**
   * Clear the text field. Note that a more general "setInputString" would work for this
   * purpose but would be incorrect. This is because the text field is not set programmatically
   * in general but input by the user.
   */

  void clearInputString();

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard listener attached
   * to it, so that keyboard events will still flow through.
   */
  void resetFocus();

  /**
   * Toggle the color of the displayed text. This is an explicit view operation because
   * this is something that only the view can control
   */
  void toggleColor();

  void addFeatures(Features features);
}
