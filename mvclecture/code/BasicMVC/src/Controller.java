import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
  private IModel model;
  private IView view;

  public Controller(IModel m, IView v) {
    model = m;
    view = v;
    view.setListener(this);
    view.display();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      //read from the input textfield
      case "Echo Button":
        String text = view.getInputString();
        //send text to the model
        model.setString(text);

        //clear input textfield
        view.clearInputString();
        //finally echo the string in view
        text = model.getString();
        view.setEchoOutput(text);

        break;
      case "Exit Button":
        System.exit(0);
        break;
      case "Invert Color":
        model.toggleColor();
        view.setInvertableButton(model.isWhite());
    }
  }
}
