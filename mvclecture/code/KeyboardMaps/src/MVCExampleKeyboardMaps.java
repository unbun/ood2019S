/**
 * Created by ashesh on 2/5/2016.
 */
public class MVCExampleKeyboardMaps {
  public static void main(String[] args) {
    IModel model = new Model();
    IView view = new JFrameView("Echo a string");
    Controller controller = new Controller(model, view);
  }
}
