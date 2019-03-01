import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements ActionListener, KeyListener {
  private IModel model;
  private IView view;

  public Controller(IModel m, IView v) {
    model = m;
    view = v;
    v.setListeners(this, this); // This controller can handle both kinds of events directly
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

        //set focus back to main frame so that keyboard events work
        view.resetFocus();

        break;
      case "Exit Button":
        System.exit(0);
        break;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    switch (e.getKeyChar()) {
      case 'd': //toggle color
        view.toggleColor();
        break;

    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_C: //caps
        String text = model.getString();
        text = text.toUpperCase();
        view.setEchoOutput(text);
        break;

    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_C: //caps
        String text = model.getString();
        view.setEchoOutput(text);
        break;
    }
  }
}