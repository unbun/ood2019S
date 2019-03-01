/**
 * This program represents a basic MVC architecture.
 *
 * The model maintains a single string that is input by the user using the GUI and echoed on the
 * GUI.
 *
 * The view is the GUI.
 *
 * The controller reacts whenever the user presses a button. It acts as a communicator between model
 * and view, and thus separates them.
 *
 * Advantages of this design:
 *
 * 1. Changing the GUI but not the functionality (model) is possible and clean. 2. Using a new
 * implementation of the model without changing view and controller is possible.
 *
 * Limitations of this design:
 *
 * Handling keyboard and mouse events is not covered yet. This must be done in a scalable manner,
 * because the key mappings (which keys do what) should be easily changeable, and should be
 * changeable by the controller and not mandate a rewrite of the view.
 */
public class MVCExampleBasicMVC {
  public static void main(String[] args) {
    IModel model = new Model(true);
    IView view = new JFrameView("Echo a string");
    Controller controller = new Controller(model, view);
  }
}
