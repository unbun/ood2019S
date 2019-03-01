import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Controller {
  private IModel model;
  private IView view;

  public Controller(IModel m) {
    model = m;
  }

  public void setView(IView v) {
    view = v;
    //create and set the keyboard listener
    configureKeyBoardListener();
    configureButtonListener();
  }

  /**
   * Creates and sets a keyboard listener for the view
   * In effect it creates snippets of code as Runnable object, one for each time a key
   * is typed, pressed and released, only for those that the program needs.
   * <p>
   * In this example, we need to toggle color when user TYPES 'd', make the message
   * all caps when the user PRESSES 'c' and reverts to the original string when the
   * user RELEASES 'c'. Thus we create three snippets of code and put them in the appropriate map.
   * This example shows how to create these snippets of code using lambda functions, a new feature
   * in Java 8.
   * <p>
   * For more information on Java 8's syntax of lambda expressions, go here:
   * https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
   * <p>
   * The above tutorial has specific example for GUI listeners.
   * <p>
   * Last we create our KeyboardListener object, set all its maps and then give it to
   * the view.
   */
  private void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<Character, Runnable>();
    Map<Integer, Runnable> keyPresses = new HashMap<Integer, Runnable>();
    Map<Integer, Runnable> keyReleases = new HashMap<Integer, Runnable>();

    keyTypes.put('d', () -> view.toggleColor() //the contents of ToggleColor below
    );
    keyPresses.put(KeyEvent.VK_C, () -> { //the contents of MakeCaps below
              String text = model.getString();
              text = text.toUpperCase();
              view.setEchoOutput(text);
            }

    );
    keyReleases.put(KeyEvent.VK_C, () -> { //the contents of MakeOriginal below
              String text = model.getString();
              view.setEchoOutput(text);
            }

    );


    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    view.addKeyListener(kbd);

  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Echo Button", () -> {
      String text = view.getInputString();
      //send text to the model
      model.setString(text);

      //clear input textfield
      view.clearInputString();
      //finally echo the string in view
      text = model.getString();
      view.setEchoOutput(text);

      //set focus back to main frame so that keyboard events work
      view.resetFocus();

    });
    buttonClickedMap.put("Exit Button", () -> {
      System.exit(0);
    });

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }


}