package cs3500.animator.view;


import cs3500.animator.controller.handlers.AddKeyFrameHandler;
import cs3500.animator.controller.handlers.AddShapeHandler;
import cs3500.animator.controller.handlers.DeleteKeyFrameHandler;
import cs3500.animator.controller.handlers.DeleteShapeHandler;
import cs3500.animator.controller.handlers.LoopHandler;
import cs3500.animator.controller.handlers.PlayButtonHandler;
import cs3500.animator.controller.handlers.RestartButtonHandler;
import cs3500.animator.controller.handlers.SVGExportHandler;
import cs3500.animator.controller.handlers.SlowDownHandler;
import cs3500.animator.controller.handlers.SpeedUpHandler;
import cs3500.animator.controller.handlers.UpdateKeyFrameHandler;
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
 * Implementation of an edit view that allows users to control the animation with buttons.
 */
public class ControllableView extends JFrame implements IView, ActionListener {

  private AnimationPanel animationPanel;

  private AnimationModel model;


  private JCheckBox loopCheckBox;

  private JButton restartButton;
  private JButton playButton;
  private JButton speedUpButton;
  private JButton slowDownButton;
  private JButton editDeleteKeyFrameBtn;
  private JButton editAddKeyFrameBtn;
  private JButton editChangeKeyFrameBtn;
  private JButton exportSVG;
  private JButton editAddShapeBtn;
  private JButton editDeleteShapeBtn;

  private JTextField editAddTime0;
  private JTextField editAddShape;
  private JTextField editAddShapeName;
  private JTextField editAddColor;
  private JTextField editAddPosnSize;

  private String playButtonTitle;

  private JLabel currentSpeed;


  /**
   * Default constructor. Adds buttons and scroll bar to view. Sets panels onto this view.
   */
  public ControllableView() {
    super();
    this.animationPanel = new AnimationPanel();
    this.setTitle("Excellence in Animation!");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new GridLayout());
    animationPanel.setPreferredSize(new Dimension(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
    this.add(animationPanel);
    JScrollPane scrollBar = new JScrollPane(this.animationPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(scrollBar);
    this.playButtonTitle = "Press to play";
    this.playButton = new JButton(this.playButtonTitle);
    animationPanel.add(this.playButton, BorderLayout.NORTH);
    this.restartButton = new JButton("Press to restart");
    animationPanel.add(this.restartButton, BorderLayout.NORTH);
    this.loopCheckBox = new JCheckBox("Enable looping");
    animationPanel.add(this.loopCheckBox, BorderLayout.NORTH);
    this.speedUpButton = new JButton("Press to speed up");
    animationPanel.add(this.speedUpButton, BorderLayout.NORTH);
    this.slowDownButton = new JButton("Press to slow down");
    animationPanel.add(this.slowDownButton, BorderLayout.NORTH);
    this.currentSpeed = new JLabel("Rate: " + animationPanel.getRate() + "x");
    animationPanel.add(this.currentSpeed, BorderLayout.NORTH);
    JPanel editPanel = new JPanel();
    editPanel.setPreferredSize(new Dimension(50, animationPanel.getHeight()));
    editPanel.setMinimumSize(new Dimension(50, animationPanel.getHeight()));
    editPanel.setMaximumSize(new Dimension(50, animationPanel.getHeight()));
    editPanel.setLayout(new GridLayout(5, 1, 10, 10));
    this.add(editPanel, new GridLayout());
    editAddTime0 = new JTextField("start time");
    editAddTime0.setHorizontalAlignment(JTextField.CENTER);
    editPanel.add(editAddTime0);
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
    JTextArea mt1 = new JTextArea("Click a shape to load its values into the fields above");
    mt1.setLineWrap(true);
    mt1.setOpaque(false);
    mt1.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
    mt1.setAlignmentY(JTextArea.CENTER_ALIGNMENT);
    editPanel.add(mt1);
    editAddShapeBtn = new JButton("Add Shape Described");
    editPanel.add(editAddShapeBtn);
    editDeleteShapeBtn = new JButton("Delete Shape Described");
    editPanel.add(editDeleteShapeBtn);
    editChangeKeyFrameBtn = new JButton("Update KeyFrame @ start time");
    editPanel.add(editChangeKeyFrameBtn);
    editAddKeyFrameBtn = new JButton("Add KeyFrame @ start time");
    editPanel.add(editAddKeyFrameBtn);
    editDeleteKeyFrameBtn = new JButton("Delete KeyFrame @ start time");
    editPanel.add(editDeleteKeyFrameBtn);
    this.exportSVG = new JButton("Export animation as SVG");
    animationPanel.add(this.exportSVG, BorderLayout.NORTH);
    this.setVisible(true);
    this.pack();
  }

  @Override
  public void setListeners(MouseListener mouse, KeyListener keys) {
    this.addKeyListener(keys);
    this.addMouseListener(mouse);
    animationPanel.addMouseListener(mouse);
    this.playButton.addActionListener(new PlayButtonHandler(this));
    this.restartButton.addActionListener(new RestartButtonHandler(this));
    this.loopCheckBox.addActionListener(new LoopHandler(this));
    this.slowDownButton.addActionListener(new SlowDownHandler(this));
    this.speedUpButton.addActionListener(new SpeedUpHandler(this));
    this.editDeleteShapeBtn.addActionListener(new DeleteShapeHandler(this, this.model));
    this.editAddShapeBtn.addActionListener(new AddShapeHandler(this));
    this.editDeleteKeyFrameBtn.addActionListener(new DeleteKeyFrameHandler(this));
    this.editAddKeyFrameBtn.addActionListener(new AddKeyFrameHandler(this));
    this.editChangeKeyFrameBtn.addActionListener(new UpdateKeyFrameHandler(this));
    this.exportSVG.addActionListener(new SVGExportHandler(this, this.model));
  }

  /**
   * If the given posn is a mouse click, update the text fields with the fields of the shape clicked
   * on.
   *
   * @param mouseLoc the Posn that the mouse clicked.
   */
  public void loadClickedShape(Posn mouseLoc) {
    for (IShape s : this.model.getShapes()) {
      IShape currS = s.currState(animationPanel.getTime());
      Posn topLeft = new Posn(currS.getPosn().x - (currS.getWidth() / 2),
          currS.getPosn().y - (currS.getHeight() / 2));
      Posn botRight = new Posn(currS.getPosn().x + (currS.getWidth() / 2),
          currS.getPosn().y + (currS.getHeight() / 2));
      boolean inX = mouseLoc.getX() >= topLeft.getX() && mouseLoc.getX() <= botRight.getX();
      boolean inY = mouseLoc.getY() >= topLeft.getY() && mouseLoc.getY() <= botRight.getY();
      if (inX & inY) {
        editAddTime0.setText(String.format("%d", currS.gett0()));
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
   * array is as follows: name, t, x, y, w, h, r, g, b.
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
   * Get the shape described by the edit pane's fields. Returns null if there is no valid shape
   * described.
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
              Integer.parseInt(editAddTime0.getText()),
              Integer.parseInt(posnVals[2]), Integer.parseInt(posnVals[3]));
          return toAdd;
        case "rectangle":
          toAdd = new Rectangle(editAddShapeName.getText(),
              new Posn(Double.parseDouble(posnVals[0]), Double.parseDouble(posnVals[1])),
              new Color(Integer.parseInt(colorVals[0]), Integer.parseInt(colorVals[1]),
                  Integer.parseInt(colorVals[2])),
              Integer.parseInt(editAddTime0.getText()),
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

  /**
   * Initalizes a Pop-up dialog to warn the user of something.
   *
   * @param header the header of the Dialog Box
   * @param message the message of the Dialog Box
   */
  public void warningBox(String header, String message) {
    JOptionPane.showMessageDialog(this, message, header,
        JOptionPane.WARNING_MESSAGE);
  }


  @Override
  public String updateView(AnimationModel model) {
    this.model = model;
    Timer timer = new Timer(1000 / this.model.getTickRate(), this);
    timer.start();
    return "";
  }

  @Override
  public void init() {
    this.setVisible(true);
  }

  @Override
  /**
   * Class Invariant: this will always return an {@code AnimationPanel}
   */
  public JPanel getAnimationPanel() {
    return this.animationPanel;
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    repaint();
  }

  /**
   * Getter for the model this view is using.
   *
   * @return the model
   */
  public AnimationModel getModel() {
    return this.model;
  }

  public void setModel(AnimationModel model) {
    this.model = Objects.requireNonNull(model);
  }

  /**
   * Getter for play/pause JButton.
   */
  public JButton getPlayButton() {
    return this.playButton;
  }


  /**
   * Getter for export SVG JButton.
   */
  public JButton getExportSVG() {
    return this.exportSVG;
  }

  /**
   * Getter for restart Button.
   */
  public JButton getRestartButton() {
    return this.restartButton;
  }

  /**
   * Getter for looping checkbox.
   */
  public JCheckBox getLoopCheckBox() {
    return this.loopCheckBox;
  }

  /**
   * Getter for speed up JButton.
   */
  public JButton getSpeedUpButton() {
    return this.speedUpButton;
  }

  /**
   * Getter for slow down JButton.
   */
  public JButton getSlowDownButton() {
    return this.slowDownButton;
  }


  /**
   * Getter for the current speed label.
   */
  public JLabel getCurrentSpeedLabel() {
    return this.currentSpeed;
  }


  /**
   * Getter for the title of the play button.
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
   */
  public JButton getEditDeleteKeyFrameBtn() {
    return editDeleteKeyFrameBtn;
  }

  /**
   * Getter for the button to add a key frame.
   */
  public JButton getEditAddKeyFrameBtn() {
    return editAddKeyFrameBtn;
  }

  /**
   * Getter for the button to update a key frame.
   */
  public JButton getEditChangeKeyFrameBtn() {
    return editChangeKeyFrameBtn;
  }

  /**
   * Getter for the button to add a shape.
   */
  public JButton getEditAddShapeBtn() {
    return editAddShapeBtn;
  }

  @Override
  public ViewType getViewType() {
    return ViewType.EDITOR;
  }

  /**
   * Getter for the button to delete a shape.
   */
  public JButton getEditDeleteShapeBtn() {
    return editDeleteShapeBtn;
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
   * Setters for test purposes.
   */

  public void setEditAddPosnSize(JTextField editAddPosnSize) {
    this.editAddPosnSize = editAddPosnSize;
  }

  public void setEditAddColor(JTextField editAddColor) {
    this.editAddColor = editAddColor;
  }

  public void setEditAddShapeName(JTextField editAddShapeName) {
    this.editAddShapeName = editAddShapeName;
  }

  public void seteditAddTime0(JTextField editAddTime0) {
    this.editAddTime0 = editAddTime0;
  }

  public void setEditAddShape(JTextField shape) {
    this.editAddShape = shape;
  }

}
