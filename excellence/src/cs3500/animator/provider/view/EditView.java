package cs3500.animator.provider.view;

import cs3500.animator.provider.model.ReadOnlyModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 * An EditView will represent a View with a visual output that can be modified by the user using a
 * series of JButtons, JTextFields, etc. It will produce a digital representation of the this model
 * using the JFrame and JPanel class and Gooey.
 *
 * <p>By permission in the README, we had to play around with the font and Component Sizes. See
 * {@code EditView.FONT_FACTOR} and {@code EditView.COMPONENT_FACTOR}.
 */
public class EditView extends VisualView implements AnimatingView {

  private final double FONT_FACTOR = 0.5;
  private final double COMPONENT_FACTOR = 0.5;
  private JTextField whichShape;
  private JComboBox keyFrameAction;
  private JTextField shapeToBeChanged;
  private JTextField t;
  private JTextField x;
  private JTextField y;
  private JTextField width;
  private JTextField height;
  private JTextField r;
  private JTextField g;
  private JTextField b;
  private JTextField loadName;
  private JComboBox whichShapeType;
  private JToggleButton loopBack;
  private JToggleButton pauseResume;
  private JButton addShape;
  private JButton dropShape;
  private JButton changeKeyFrame;
  private JToggleButton rewind;
  private JButton restart;
  private JButton start;
  private JButton increaseSpeed;
  private JButton decreaseSpeed;
  private JButton saveText;
  private JButton saveSVG;
  private JButton load;

  //CONSTRUCTOR////////////////////////////////////////////////////////////////
  public EditView() {
    super();
  }

  //Type checks///////////////////////////////////////////////////////////////
  @Override
  public boolean isVisualView() {
    return false;
  }

  @Override
  public boolean isAnimatingView() {
    return true;
  }

  //COMPILED GETTERS///////////////////////////////////////////////////////////////
  @Override
  public Object getSomething(String s) {
    Object result;
    switch (s) {
      //Jbuttons
      case "start":
        result = this.start;
        break;
      case "restart":
        result = this.restart;
        break;
      case "dropShape":
        result = this.dropShape;
        break;
      case "addShape":
        result = this.addShape;
        break;
      case "changeKeyFrame":
        result = this.changeKeyFrame;
        break;
      //Toggle buttons
      case "pauseResume":
        result = this.pauseResume;
        break;
      case "rewind":
        result = this.rewind;
        break;
      case "loopBack":
        result = this.loopBack;
        break;
      //JTextField
      case "whichShape":
        result = this.whichShape;
        break;
      case "shapeToBeChanged":
        result = this.shapeToBeChanged;
        break;
      case "t":
        result = this.t;
        break;
      case "x":
        result = this.x;
        break;
      case "y":
        result = this.y;
        break;
      case "width":
        result = this.width;
        break;
      case "height":
        result = this.height;
        break;
      case "r":
        result = this.r;
        break;
      case "g":
        result = this.g;
        break;
      case "b":
        result = this.b;
        break;
      //JComboBox
      case "keyFrameAction":
        result = this.keyFrameAction;
        break;
      case "loadName":
        result = this.loadName;
        break;
      case "whichShapeType":
        result = this.whichShapeType;
        break;
      case "frame":
        result = this.frame;
        break;
      default:
        result = new JButton("Junk");
        break;
    }
    return result;
  }

  @Override
  public JButton getButton(String s) {
    JButton result;
    switch (s) {
      case "increaseSpeed":
        result = this.increaseSpeed;
        break;
      case "saveSVG":
        result = this.saveSVG;
        break;
      case "saveText":
        result = this.saveText;
        break;
      case "load":
        result = this.load;
        break;
      default:
        result = this.decreaseSpeed;
        break;
    }
    return result;
  }

  //INITVIEW///////////////////////////////////////////////////////////////
  @Override
  public void initView(ActionListener listener, int canWidth,
      int canHeight) {
    super.initView(listener, canWidth, canHeight);

    this.frame.setBackground(java.awt.Color.WHITE);
    this.frame.setSize(canWidth, canHeight + 150 + 300 + 250);

    //Panels//////////////////////////////////////////////////////////
    JPanel smallerPanel;
    JPanel bottomPanel;
    smallerPanel = new JPanel();
    smallerPanel.setBackground(new Color(204, 153, 255));
    smallerPanel.setPreferredSize(new Dimension(super.frame.getWidth(), 300));

    bottomPanel = new JPanel();
    bottomPanel.setBackground(new Color(166, 77, 255));
    bottomPanel.setPreferredSize(new Dimension(super.frame.getWidth() - 600, 250));

    //JTextFields//////////////////////////////////////////////////////////
    loadName = new JTextField(15);
    loadName.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    loadName.setPreferredSize(
        new Dimension((int) (15 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    loadName.addActionListener(listener);

    whichShape = new JTextField(15);
    whichShape.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    whichShape.setPreferredSize(
        new Dimension((int) (15 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    whichShape.addActionListener(listener);

    shapeToBeChanged = new JTextField("", 10);
    shapeToBeChanged.setPreferredSize(
        new Dimension((int) (6 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    shapeToBeChanged.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    shapeToBeChanged.addActionListener(listener);

    t = new JTextField("", 7);
    t.setPreferredSize(new Dimension((int) (6 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    t.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    t.addActionListener(listener);

    x = new JTextField("", 5);
    x.setPreferredSize(new Dimension((int) (6 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    x.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    x.addActionListener(listener);

    y = new JTextField("", 5);
    y.addActionListener(listener);
    y.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    y.setPreferredSize(new Dimension((int) (6 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));

    width = new JTextField("", 5);
    width.addActionListener(listener);
    width.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    width.setPreferredSize(
        new Dimension((int) (6 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));

    height = new JTextField("", 5);
    height.setPreferredSize(
        new Dimension((int) (6 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    height.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    height.addActionListener(listener);

    r = new JTextField("", 5);
    r.setPreferredSize(new Dimension((int) (15 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    r.setFont(new Font("", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    r.addActionListener(listener);

    g = new JTextField("", 5);
    g.setPreferredSize(new Dimension((int) (15 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    g.setFont(new Font("", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    g.addActionListener(listener);

    b = new JTextField("", 5);
    b.setPreferredSize(new Dimension((int) (15 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    b.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    b.addActionListener(listener);

    //JComboBoxes//////////////////////////////////////////////////////////
    keyFrameAction = new JComboBox();
    keyFrameAction.setPreferredSize(
        new Dimension((int) (150 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    keyFrameAction.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    keyFrameAction.addItem("add");
    keyFrameAction.addItem("remove");
    keyFrameAction.addItem("modify");
    keyFrameAction.addActionListener(listener);

    whichShapeType = new JComboBox();
    whichShapeType.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (50 * COMPONENT_FACTOR)));
    whichShapeType.addItem("rectangle");
    whichShapeType.addItem("ellipse");
    whichShapeType.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    whichShapeType.addActionListener(listener);

    //JToggleButtons//////////////////////////////////////////////////////////
    loopBack = new JToggleButton("loop");
    loopBack.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (125 * COMPONENT_FACTOR)));
    loopBack.addActionListener(listener);
    loopBack.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    loopBack.setBackground(new Color(255, 255, 0));
    loopBack.setBackground(new Color(150, 0, 255));

    pauseResume = new JToggleButton("pause/resume");
    pauseResume.setPreferredSize(
        new Dimension((int) (250 * COMPONENT_FACTOR), (int) (125 * COMPONENT_FACTOR)));
    pauseResume.addActionListener(listener);
    pauseResume.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    pauseResume.setBackground(new Color(255, 157, 0));
    pauseResume.setBackground(new Color(150, 100, 255));

    rewind = new JToggleButton("rewind");
    rewind.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (125 * COMPONENT_FACTOR)));
    rewind.addActionListener(listener);
    rewind.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    rewind.setBackground(new Color(75, 100, 255));

    //JButtons//////////////////////////////////////////////////////////
    saveSVG = new JButton("save svg");
    saveSVG.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (125 * COMPONENT_FACTOR)));
    saveSVG.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    saveSVG.addActionListener(listener);
    saveSVG.setBackground(new Color(255, 157, 0));

    saveText = new JButton("save text");
    saveText.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (125 * COMPONENT_FACTOR)));
    saveText.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    saveText.addActionListener(listener);
    saveText.setBackground(new Color(255, 255, 0));

    load = new JButton("load");
    load.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (125 * COMPONENT_FACTOR)));
    load.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    load.addActionListener(listener);
    load.setBackground(new Color(170, 255, 0));

    addShape = new JButton("add Shape");
    addShape.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (75 * COMPONENT_FACTOR)));
    addShape.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    addShape.addActionListener(listener);

    dropShape = new JButton("drop Shape");
    dropShape.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (75 * COMPONENT_FACTOR)));
    dropShape.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    dropShape.addActionListener(listener);

    changeKeyFrame = new JButton("update keyframes");
    changeKeyFrame.setPreferredSize(new Dimension(300, (int) (75 * COMPONENT_FACTOR)));
    changeKeyFrame.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    changeKeyFrame.addActionListener(listener);

    restart = new JButton("restart");
    restart.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (125 * COMPONENT_FACTOR)));
    restart.addActionListener(listener);
    restart.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    restart.setBackground(new Color(2, 157, 255));

    start = new JButton("start");
    start.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (125 * COMPONENT_FACTOR)));
    start.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    start.addActionListener(listener);
    start.setBackground(new Color(0, 200, 155));

    increaseSpeed = new JButton("speed up");
    increaseSpeed.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (125 * COMPONENT_FACTOR)));
    increaseSpeed.addActionListener(listener);
    increaseSpeed.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    increaseSpeed.setBackground(new Color(255, 157, 0));

    decreaseSpeed = new JButton("slow down");
    decreaseSpeed.setPreferredSize(
        new Dimension((int) (200 * COMPONENT_FACTOR), (int) (125 * COMPONENT_FACTOR)));
    decreaseSpeed.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    decreaseSpeed.addActionListener(listener);
    decreaseSpeed.setBackground(new Color(255, 255, 0));

    //JLabels//////////////////////////////////////////////////////////
    JLabel title;
    JLabel shapeToBeChangedLabel;
    JLabel tLabel;
    JLabel xLabel;
    JLabel yLabel;
    JLabel widthLabel;
    JLabel heightLabel;
    JLabel redLabel;
    JLabel greenLabel;
    JLabel blueLabel;
    JLabel shapeNameLabel;
    JLabel keyFrameActionLLabel;
    JLabel shapeTypeLabel;
    JLabel fileName;

    fileName = new JLabel("File Name:");
    fileName.setFont(new Font("Arial", Font.PLAIN, (int) (50 * COMPONENT_FACTOR)));
    fileName.setForeground(Color.WHITE);

    title = new JLabel(
        "Welcome to Sharice and Genevieve's Simple Animator (Adapted by Unnas and Carolina)!\n");
    title.setFont(new Font("Monotype Corsiva", Font.CENTER_BASELINE, 15));
    title.setForeground(Color.WHITE);

    shapeToBeChangedLabel = new JLabel("Shape Name:");
    shapeToBeChangedLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    shapeToBeChangedLabel.setForeground(Color.WHITE);

    tLabel = new JLabel("t:");
    tLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    tLabel.setForeground(Color.WHITE);

    xLabel = new JLabel("x:");
    xLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    xLabel.setForeground(Color.WHITE);

    yLabel = new JLabel("y:");
    yLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    yLabel.setForeground(Color.WHITE);

    widthLabel = new JLabel("width:");
    widthLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    widthLabel.setForeground(Color.WHITE);

    heightLabel = new JLabel("height:");
    heightLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    heightLabel.setForeground(Color.WHITE);

    redLabel = new JLabel("Color.RED:");
    redLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    redLabel.setForeground(Color.WHITE);

    greenLabel = new JLabel("Color.GREEN:");
    greenLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    greenLabel.setForeground(Color.WHITE);

    blueLabel = new JLabel("Color.BLUE:");
    blueLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    blueLabel.setForeground(Color.WHITE);

    shapeNameLabel = new JLabel("Shape Name:");
    shapeNameLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    shapeNameLabel.setForeground(Color.WHITE);

    keyFrameActionLLabel = new JLabel("KeyFrame Action:");
    keyFrameActionLLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    keyFrameActionLLabel.setForeground(Color.WHITE);

    shapeTypeLabel = new JLabel("Shape Type:");
    shapeTypeLabel.setFont(new Font("Arial", Font.PLAIN, (int) (30 * FONT_FACTOR)));
    shapeTypeLabel.setForeground(Color.WHITE);
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    smallerPanel.add(title);

    smallerPanel.add(saveSVG);
    smallerPanel.add(saveText);
    smallerPanel.add(fileName);
    smallerPanel.add(loadName);
    smallerPanel.add(load);
    smallerPanel.add(start);
    smallerPanel.add(restart);
    smallerPanel.add(rewind);
    smallerPanel.add(pauseResume);
    smallerPanel.add(loopBack);
    smallerPanel.add(increaseSpeed);
    smallerPanel.add(decreaseSpeed);

    //Add buttons to bottom panel
    bottomPanel.add(shapeToBeChangedLabel);
    bottomPanel.add(shapeToBeChanged);
    bottomPanel.add(tLabel);
    bottomPanel.add(t);
    bottomPanel.add(keyFrameActionLLabel);
    bottomPanel.add(keyFrameAction);
    bottomPanel.add(xLabel);
    bottomPanel.add(x);
    bottomPanel.add(yLabel);
    bottomPanel.add(y);
    bottomPanel.add(widthLabel);
    bottomPanel.add(width);
    bottomPanel.add(heightLabel);
    bottomPanel.add(height);
    bottomPanel.add(redLabel);
    bottomPanel.add(r);
    bottomPanel.add(greenLabel);
    bottomPanel.add(g);
    bottomPanel.add(blueLabel);
    bottomPanel.add(b);
    bottomPanel.add(changeKeyFrame);
    bottomPanel.add(shapeTypeLabel);
    bottomPanel.add(whichShapeType);
    bottomPanel.add(shapeNameLabel);
    bottomPanel.add(whichShape);
    bottomPanel.add(dropShape);
    bottomPanel.add(addShape);

    super.frame.add(smallerPanel, BorderLayout.NORTH);
    super.frame.add(bottomPanel, BorderLayout.SOUTH);
    super.frame.setSize(super.frame.getWidth(), super.frame.getHeight());
    super.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    super.frame.setVisible(true);
  }

  @Override
  public void callRepaint() {
    super.callRepaint();
  }

  @Override
  public ReadOnlyModel getModel() {
    return super.getModel();
  }

}