/**
 * This is a complete program featuring a simple GUI. This program merely illustrates how it works.
 * It is designed rather badly. Read the comments in the view to see why.
 */
public class MVCExampleFunctionalButBadDesign {
  public static void main(String[] args) {
    IModel model = new Model();
    IView view = new JFrameView("Echo a string", model);
  }
}
