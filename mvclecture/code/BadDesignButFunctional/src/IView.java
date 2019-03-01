import java.awt.event.KeyListener;

/**
 * The interface for our view class
 */
public interface IView {
  /**
   * Set the label that is showing what the model stores.
   */
  void setEchoOutput(String s);

  /**
   * Get the string from the text field and return it
   */
  String getInputString();

  /**
   * Clear the text field. Note that a more general "setInputString" would work for this purpose but
   * would be incorrect. This is because the text field is not set programmatically in general but
   * input by the user.
   */
  void clearInputString();


}
