package cs3500.animator.view;


import cs3500.animator.controller.actionHandlers.AddKeyFrameHandler;
import cs3500.animator.controller.actionHandlers.AddShapeHandler;
import cs3500.animator.controller.actionHandlers.DeleteKeyFrameHandler;
import cs3500.animator.controller.actionHandlers.DeleteShapeHandler;
import cs3500.animator.controller.actionHandlers.ExportSVGHandler;
import cs3500.animator.controller.actionHandlers.LoopingHandler;
import cs3500.animator.controller.actionHandlers.PlayButtonHandler;
import cs3500.animator.controller.actionHandlers.RestartButtonHandler;
import cs3500.animator.controller.actionHandlers.SlowDownAnimationHandler;
import cs3500.animator.controller.actionHandlers.SpeedUpAnimationHandler;
import cs3500.animator.controller.actionHandlers.UpdateKeyFrameHandler;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.util.Constants;
import cs3500.animator.util.Posn;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.WindowConstants;


/**
 * The implementation of the edit view that allows users to control the animation with buttons and
 * the export of a svg file from a visual view for the animation.
 */
public class ControllableView extends JFrame implements IAnimationView, ActionListener {

  private AnimationPanel animationPanel;
  private Appendable ap;

  private AnimationModel model;
  private Timer timer;

  private JButton playButton;
  private String playButtonTitle;
  private JButton restartButton;
  private JCheckBox loopCheckBox;
  private JButton speedUpButton;
  private JButton slowDownButton;
  private JLabel currentSpeed;

  private JPanel editPanel;
  private JButton editDeleteKeyFrameBtn;
  private JButton editAddKeyFrameBtn;
  private JButton editChangeKeyFrameBtn;

  private JTextField editAddTime0;
  private JTextField editAddTimeF;
  private JTextField editAddShape;
  private JTextField editAddShapeName;
  private JTextField editAddColor;
  private JTextField editAddPosnSize;


  private JButton editAddShapeBtn;

  private JButton editDeleteShapeBtn;

  private JButton exportSVG;

  /**
   * Default constructor. Adds buttons and scroll bar to view. Sets panels onto this view, which
   * extends JFrame in Java Swing.
   */
  public ControllableView() {
    super();
    this.animationPanel = new AnimationPanel();
    this.setTitle("Excellence in Animation!");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // sets gridLayout
    this.setLayout(new GridLayout());

    // adds animation panel to this frame
    animationPanel.setPreferredSize(new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
    this.add(animationPanel);

    // adds scroll bars to panel
    JScrollPane scrollBar = new JScrollPane(this.animationPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(scrollBar);

    // play and pause button, adds to view
    this.playButtonTitle = "Press to play";
    this.playButton = new JButton(this.playButtonTitle);
    animationPanel.add(this.playButton, BorderLayout.NORTH);

    // restart button, adds to view
    this.restartButton = new JButton("Press to restart");
    animationPanel.add(this.restartButton, BorderLayout.NORTH);

    // loop checkbox
    this.loopCheckBox = new JCheckBox("Toggle looping");
    animationPanel.add(this.loopCheckBox, BorderLayout.NORTH);

    // export svg button
    this.exportSVG = new JButton("Click to export as SVG");
    animationPanel.add(this.exportSVG, BorderLayout.NORTH);

    // speed up button
    this.speedUpButton = new JButton("Press to speed up");
    animationPanel.add(this.speedUpButton, BorderLayout.NORTH);

    // slow down button
    this.slowDownButton = new JButton("Press to slow down");
    animationPanel.add(this.slowDownButton, BorderLayout.NORTH);

    this.currentSpeed = new JLabel("Rate: " + animationPanel.getRate() + "x");
    animationPanel.add(this.currentSpeed, BorderLayout.NORTH);

    //edit panel
    editPanel = new JPanel();
    editPanel.setPreferredSize(new Dimension(50, animationPanel.getHeight()));
    editPanel.setMinimumSize(new Dimension(50, animationPanel.getHeight()));
    editPanel.setMaximumSize(new Dimension(50, animationPanel.getHeight()));
    editPanel.setLayout(new GridLayout(5, 1, 10, 10));
    this.add(editPanel, new GridLayout());

    editAddTime0 = new JTextField("start time");
    editAddTime0.setHorizontalAlignment(JTextField.CENTER);
    editPanel.add(editAddTime0);

    editAddTimeF = new JTextField("end time");
    editAddTimeF.setHorizontalAlignment(JTextField.CENTER);
    editPanel.add(editAddTimeF);

    editAddShape = new JTextField("shape type");
    editAddShape.setHorizontalAlignment(JTextField.CENTER);
    editPanel.add(editAddShape);

    editAddShapeName = new JTextField("shape name");
    editAddShapeName.setHorizontalAlignment(JTextField.CENTER);
    editPanel.add(editAddShapeName);

    editAddPosnSize = new JTextField("\"x y w h\"");
    editAddPosnSize.setHorizontalAlignment(JTextField.CENTER);
    editPanel.add(editAddPosnSize);

    editAddColor = new JTextField("\"r g b\"");
    editAddColor.setHorizontalAlignment(JTextField.CENTER);
    editPanel.add(editAddColor);

    editAddShapeBtn = new JButton("Add Shape Described");
    editPanel.add(editAddShapeBtn);

    JTextArea mt1 = new JTextArea("Clicking a shape will load it's values into the fields above");
    mt1.setLineWrap(true);
    mt1.setOpaque(false);
    mt1.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
    mt1.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
    editPanel.add(mt1);

    editDeleteShapeBtn = new JButton("Delete Shape Described");
    editPanel.add(editDeleteShapeBtn);

    editChangeKeyFrameBtn = new JButton("Update KeyFrame @ start time");
    editPanel.add(editChangeKeyFrameBtn);

    editAddKeyFrameBtn = new JButton("Add KeyFrame @ start time");
    editPanel.add(editAddKeyFrameBtn);

    editDeleteKeyFrameBtn = new JButton("Delete KeyFrame @ start time");
    editPanel.add(editDeleteKeyFrameBtn);

    this.setVisible(true);

    this.pack();
  }

  @Override
  public void setListeners(MouseListener mouse, KeyListener keys) {
    this.addKeyListener(keys);
    this.addMouseListener(mouse);
    animationPanel.addMouseListener(mouse);
    this.getPlayButton().addActionListener(new PlayButtonHandler(this));
    this.getRestartButton().addActionListener(new RestartButtonHandler(this));
    this.getLoopCheckBox().addActionListener(new LoopingHandler(this));
    this.getSlowDownButton().addActionListener(new SlowDownAnimationHandler(this));
    this.getSpeedUpButton().addActionListener(new SpeedUpAnimationHandler(this));
    this.getEditDeleteShapeBtn().addActionListener(new DeleteShapeHandler(this, this.model));
    this.getEditAddShapeBtn().addActionListener(new AddShapeHandler(this));
    this.getEditDeleteKeyFrameBtn().addActionListener(new DeleteKeyFrameHandler(this));
    this.getEditAddKeyFrameBtn().addActionListener(new AddKeyFrameHandler(this));
    this.getEditChangeKeyFrameBtn().addActionListener(new UpdateKeyFrameHandler(this));

    try {
      this.getExportSVG().addActionListener(new ExportSVGHandler(this, this.model, this.ap));
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to export SVG");
    }
  }

  /**
   * Getter for the model this view is using.
   *
   * @return the model this view is using.
   */
  public AnimationModel getModel() {
    return this.model;
  }

  public void setModel(AnimationModel model) {
    this.model = Objects.requireNonNull(model);
  }

  /**
   * Getter for play/pause JButton.
   *
   * @return the playButton
   */
  public JButton getPlayButton() {
    return this.playButton;
  }

  /**
   * Getter for restart JButton.
   *
   * @return the restartButton
   */
  public JButton getRestartButton() {
    return this.restartButton;
  }

  /**
   * Getter for looping checkbox.
   *
   * @return the loopCheckBox
   */
  public JCheckBox getLoopCheckBox() {
    return this.loopCheckBox;
  }

  /**
   * Getter for speed up JButton.
   *
   * @return the speedUpButton
   */
  public JButton getSpeedUpButton() {
    return this.speedUpButton;
  }

  /**
   * Getter for slow down JButton.
   *
   * @return the slowDownButton
   */
  public JButton getSlowDownButton() {
    return this.slowDownButton;
  }


  /**
   * Getter for the current speed label
   *
   * @return the current speed label (for rate)
   */
  public JLabel getCurrentSpeedLabel() {
    return this.currentSpeed;
  }

  /**
   * Getter for export SVG JButton.
   *
   * @return the exportSVG button
   */
  public JButton getExportSVG() {
    return this.exportSVG;
  }

  /**
   * Getter for the title of the play button.
   *
   * @return the title of the play button
   */
  public String getPlayButtonTitle() {
    return this.playButtonTitle;
  }

  /**
   * Sets the title of the play button to the given string.
   *
   * @param s the new title of the button
   */
  public void setPlayButtonTitle(String s) {
    this.playButton.setText(s);
    this.playButtonTitle = s;
  }

  /**
   * Getter for button to delete a key frame.
   *
   * @return the delete key frame button
   */
  public JButton getEditDeleteKeyFrameBtn() {
    return editDeleteKeyFrameBtn;
  }

  /**
   * Getter for the button to add a key frame.
   *
   * @return the add key frame button
   */
  public JButton getEditAddKeyFrameBtn() {
    return editAddKeyFrameBtn;
  }

  /**
   * Getter for the button to update a key frame
   *
   * @return the update key frame button
   */
  public JButton getEditChangeKeyFrameBtn() {
    return editChangeKeyFrameBtn;
  }

  /**
   * Getter for the button to add a shape
   *
   * @return the add shape button
   */
  public JButton getEditAddShapeBtn() {
    return editAddShapeBtn;
  }

  /**
   * Getter for the button to delete a shape
   *
   * @return the delete shape button
   */
  public JButton getEditDeleteShapeBtn() {
    return editDeleteShapeBtn;
  }

  /**
   * If the given posn is a mouse click, update the text fields for editing if that mouse click was
   * a click on a shape.
   *
   * @param mouseLoc the Posn that the mouse clicked.
   */
  public void loadClickedShape(Posn mouseLoc) {
    for (IShape s : this.model.getShapes()) {
      IShape currS = s.getStateAt(animationPanel.getTime());
      Posn topLeft = new Posn(currS.getPosn().x - (currS.getWidth() / 2),
          currS.getPosn().y - (currS.getHeight() / 2));
      Posn botRight = new Posn(currS.getPosn().x + (currS.getWidth() / 2),
          currS.getPosn().y + (currS.getHeight() / 2));
      boolean inX = mouseLoc.getX() >= topLeft.getX() && mouseLoc.getX() <= botRight.getX();
      boolean inY = mouseLoc.getY() >= topLeft.getY() && mouseLoc.getY() <= botRight.getY();
      if (inX & inY) {
        editAddTime0.setText(String.format("%d", currS.getBirthTime()));
        editAddTimeF.setText(String.format("%d", currS.getDeathTime()));
        editAddShape.setText(currS.getType());
        editAddShapeName.setText(currS.getName());
        editAddPosnSize.setText(String
            .format("%f %f %f %f", currS.getPosn().x, currS.getPosn().y, currS.getWidth(),
                currS.getHeight()));
        editAddColor.setText(String
            .format("%d %d %d", currS.getColor().getRed(), currS.getColor().getGreen(),
                currS.getColor().getBlue()));
      }
    }
  }


  /**
   * Get the keyFrame described by the edit pane's fields in String array form. The order of the
   * array is as follows: name, t, x, y, w, h, r, g, b
   */
  public String[] getDescribedKeyFrame() {
    //String name, int t, int x, int y, int w, int h, int r, int g, int b

    try {
      String[] posnVals = editAddPosnSize.getText().split(" ");
      String[] colorVals = editAddColor.getText().split(" ");
      String[] result = {editAddShapeName.getText(), editAddTime0.getText(), posnVals[0],
          posnVals[1], posnVals[2],
          posnVals[3], colorVals[0], colorVals[1], colorVals[2]};
      return result;
    } catch (ArrayIndexOutOfBoundsException oobe) {
      return null;
    }

  }

  /**
   * Get the name of the shape the user wants to edit.
   *
   * @return a String of the name of the shape to edit.
   */
  public String getEditShapeName() {
    return editAddShapeName.getText();
  }


  /**
   * Get the shape described by the edit pane's fields. The format for the posn/width and color
   * fields are described in the init text values of the JTextField objects. Returns null if there
   * is no valid shape described
   */
  public IShape getDescribedShape() {
    String[] posnVals = editAddPosnSize.getText().split(" ");
    String[] colorVals = editAddColor.getText().split(" ");
    try {
      switch (editAddShape.getText()) {
        case "oval":
          IShape toAdd = new Oval(editAddShapeName.getText(),
              new Posn(Double.parseDouble(posnVals[0]), Double.parseDouble(posnVals[1])),
              new Color(Integer.parseInt(colorVals[0]), Integer.parseInt(colorVals[1]),
                  Integer.parseInt(colorVals[2])),
              Integer.parseInt(editAddTime0.getText()), Integer.parseInt(editAddTimeF.getText()),
              Integer.parseInt(posnVals[2]), Integer.parseInt(posnVals[3]));
          return toAdd;
        case "rectangle":
          toAdd = new Rectangle(editAddShapeName.getText(),
              new Posn(Double.parseDouble(posnVals[0]), Double.parseDouble(posnVals[1])),
              new Color(Integer.parseInt(colorVals[0]), Integer.parseInt(colorVals[1]),
                  Integer.parseInt(colorVals[2])),
              Integer.parseInt(editAddTime0.getText()), Integer.parseInt(editAddTimeF.getText()),
              Integer.parseInt(posnVals[2]), Integer.parseInt(posnVals[3]));
          return toAdd;
        default:
          return null;
      }
    } catch (ArrayIndexOutOfBoundsException idxe) {
      return null;
    } catch (NumberFormatException nfe) {
      return null;
    }
  }

  @Override
  public ViewType getViewType() {
    return ViewType.EDITOR;
  }

  @Override
  public String makeView(AnimationModel model) {
    this.model = model;
    this.timer = new Timer(1000 / this.model.getTickRate(), this);
    timer.start();
    return "";
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public AnimationPanel getAnimationPanel() {
    return this.animationPanel;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    repaint();
  }

  /**
   * Initalize a Pop-up dialog to warn the user of something.
   *
   * @param header the header of the Dialog Box
   * @param message the message of the Dialog Box
   */
  public void warnDialog(String header, String message) {
    JOptionPane.showMessageDialog(this, message, header, JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Initalize a Pop-up dialog to tell the user of an error.
   *
   * @param header the header of the Dialog Box
   * @param message the message of the Dialog Box
   */
  public void errorDialog(String header, String message) {
    JOptionPane.showMessageDialog(this, message, header, JOptionPane.ERROR_MESSAGE);
  }

}
