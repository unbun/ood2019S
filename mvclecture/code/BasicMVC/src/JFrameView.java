import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameView extends JFrame implements IView {
  private JLabel display;
  private JButton echoButton, exitButton, invertColor;
  private JTextField input;

  public JFrameView(String caption) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.setResizable(false);
//		this.setMinimumSize(new Dimension(300,300));


    this.setLayout(new FlowLayout());

    display = new JLabel("To be displayed");
    //label = new JLabel(new ImageIcon("Jellyfish.JPG"));


    this.add(display);

    //the textfield
    input = new JTextField(10);
    this.add(input);

    //echobutton
    echoButton = new JButton("Echo");
    echoButton.setActionCommand("Echo Button");
    this.add(echoButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    //invert color button
    invertColor = new JButton("Invert Color");
    invertColor.setActionCommand("Invert Color");
    invertColor.setBackground(Color.WHITE);
    this.add(invertColor);
    pack();
  }

  @Override
  public void setInvertableButton(boolean isWhite) {
    if(isWhite){
      invertColor.setBackground(Color.WHITE);
    } else {
      invertColor.setBackground(Color.BLACK);
    }

  }

  @Override
  public void display() {
    setVisible(true);
  }


  @Override
  public void setListener(ActionListener listener) {
    echoButton.addActionListener(listener);
    exitButton.addActionListener(listener);
    invertColor.addActionListener(listener);
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
}
