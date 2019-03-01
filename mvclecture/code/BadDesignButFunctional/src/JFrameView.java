import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the view implementation. It directly converses with the model. This design suffices only
 * for very simple programs where there isn't much logic to handle for every user action.
 * <p>
 * In general this design is bad because the logic for handling user action is embedded into the
 * view. For example, you must write all the code that runs upon pressing a button in this class.
 * Most of that code will have nothing to do with the GUI per se, but sequence of operations. There
 * is no separation between how the UI looks and how the program works. This means that if the UI
 * must be changed without changing the functionality of the program, logic must also be replicated
 * as the old UI contains the operations as well!
 */

public class JFrameView extends JFrame implements IView, ActionListener {
  private JLabel display;
  private JButton echoButton, exitButton;
  private JTextField input;

  private IModel model;

  public JFrameView(String caption, IModel model) {
    super(caption);

    this.model = model;

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // this.setResizable(false);
    // this.setMinimumSize(new Dimension(300,300));


    this.setLayout(new FlowLayout());

    display = new JLabel("To be displayed");

    this.add(display);

    //the textfield
    input = new JTextField(10);
    this.add(input);

    //echobutton
    echoButton = new JButton("Echo");
    echoButton.setActionCommand("Echo Button");
    echoButton.addActionListener(this);
    this.add(echoButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    exitButton.addActionListener(this);
    this.add(exitButton);

    pack(); // set the size to be smaller than set so everything is fits exactly
    setVisible(true);

  }


  @Override
  public void setEchoOutput(String s) {
    display.setText(s);
  }

  @Override
  public String getInputString() {
    return input.getText();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      //read from the input textfield
      case "Echo Button":
        String text = getInputString();
        //send text to the model
        model.setString(text);

        //clear input textfield
        clearInputString();
        //finally echo the string in view
        text = model.getString();
        setEchoOutput(text);

        break;
      case "Exit Button":
        System.exit(0);
        break;
    }
  }
}
