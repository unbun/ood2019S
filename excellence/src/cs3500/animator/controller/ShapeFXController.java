package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.util.Posn;
import cs3500.animator.view.AnimationPanel;
import cs3500.animator.view.ControllableView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.ViewType;
import cs3500.animator.view.VisualView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Implements the tasks of an {@code AnimationController}. Also acts as the MouseListener and
 * KeyListener of the {@code ControllableView} view (but can still run the other views as well).
 */
public class ShapeFXController implements AnimationController, MouseListener, KeyListener {

  protected final AnimationModel model;
  protected IView view;
  protected Appendable outputFile = System.out;
  protected String inputFile = "";
  protected int speed = 1;
  protected String viewType = "";
  protected boolean isDefaultOutput = true;

  /**
   * Default constructor for a controller.
   *
   * @param model the desired model to represent the animation
   * @param args arguments received in main method
   * @throws IllegalArgumentException if passed a null model
   */
  public ShapeFXController(AnimationModel model, String[] args)
      throws IllegalArgumentException {
    Objects.requireNonNull(model);
    this.model = model;
    this.parseInput(args);
    model.setSpeed(speed);
    this.createView(viewType);
    this.view.setModel(model);
    this.view.setListeners(this, this);
  }

  /**
   * Parses user inputs via the main method to establish view type, input file, output file, and
   * animation speed.
   *
   * @param args given arguments in the main method
   */
  protected void parseInput(String[] args) {
    for (int i = 0; i < args.length; i += 2) {
      try {
        if ((args[i + 1].length() > 0)) {
          switch (args[i]) {
            case "-in":
              if ((args[i + 1].endsWith(".txt")) || (args[i + 1].endsWith(".svg"))) {
                inputFile = args[i + 1];
              } else {
                throw new
                    IllegalArgumentException(args[i + 1].substring(args[i + 1].length() - 4)
                    + "input file must be a .txt or .svg file");
              }
              break;
            case "-out":
              if (args[i + 1].equals("out")) {
                outputFile = System.out;
              } else {
                try {
                  outputFile = new FileWriter(args[i + 1]);
                  isDefaultOutput = false;
                } catch (IOException e) {
                  throw new IllegalArgumentException("Error in creating output file, try again");
                }
              }
              break;
            case "-view":
              viewType = args[i + 1];
              break;
            case "-speed":
              try {
                speed = Integer.parseInt(args[i + 1]);
              } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Speed must be an integer!");
              }
              break;
            default:
              throw new IllegalArgumentException("The only valid inputs are -in, -out, -view, "
                  + "and -speed. Try again!");
          }
        }
      } catch (IndexOutOfBoundsException e) {
        throw new IllegalArgumentException("You must provide a value after" +
            " each input specifier.");
      }
    }
    if (this.inputFile.equals("")) {
      throw new IllegalArgumentException("Please specify" +
          " a pre-existing input file path.");
    }
  }

  /**
   * Setter for testing purposes, normally the view will be set by parsing args.
   *
   * @param view the view to be set to
   */
  public void setView(IView view) {
    this.view = view;
  }

  @Override
  public void run() {
    view.init();
    try {
      outputFile.append(view.updateView(model));
    } catch (IOException e) {
      throw new IllegalArgumentException("Error in creating view.");
    }
    if (!isDefaultOutput) {
      try {
        ((FileWriter) outputFile).flush();
        ((FileWriter) outputFile).close();
      } catch (IOException e) {
        throw new IllegalStateException("Cannot close file.");
      }
    }
  }

  /**
   * Creates the view of the animation based on user specified type; can be one of the following.
   * <ul>
   * <item>visual</item>
   * <item>edit</item>
   * <item>svg</item>
   * <item>text</item>
   * </ul>
   *
   * @param view the view to be made
   */
  protected void createView(String view) {
    switch (view.toLowerCase()) {
      case "visual":
        this.view = new VisualView();
        setViewPanelModel();
        break;
      case "edit":
        this.view = new ControllableView();
        setViewPanelModel();
        break;
      case "svg":
        this.view = new SVGView();
        break;
      case "text":
        this.view = new TextView();
        break;
      default:
        throw new IllegalArgumentException("Invalid view type!");
    }
  }

  protected void setViewPanelModel() throws UnsupportedClassVersionError {
    if (this.view == null) {
      return;
    }

    try {
      ((AnimationPanel) this.view.getAnimationPanel()).setModel(model);
    } catch (ClassCastException eCast) {
      throw new UnsupportedClassVersionError("Certain Views have JPanels that depend on models");
    }
  }


  @Override
  public String getInputFile() {
    return this.inputFile;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (this.view.getViewType() != ViewType.EDITOR || !(view instanceof ControllableView)) {
      return;
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (this.view.getViewType() != ViewType.EDITOR || !(view instanceof ControllableView)) {
      return;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (this.view.getViewType() != ViewType.EDITOR || !(view instanceof ControllableView)) {
      return;
    }
    if (e.getKeyChar() == ('q')) {
      System.exit(0);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (this.view.getViewType() != ViewType.EDITOR || !(view instanceof ControllableView)) {
      return;
    }
    ControllableView cView = (ControllableView) view;
    Posn mouseLoc = new Posn(e.getX(), e.getY());
    cView.loadClickedShape(mouseLoc);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (this.view.getViewType() != ViewType.EDITOR || !(view instanceof ControllableView)) {
      return;
    }
  }


  @Override
  public void mouseReleased(MouseEvent e) {
    if (this.view.getViewType() != ViewType.EDITOR || !(view instanceof ControllableView)) {
      return;
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    if (this.view.getViewType() != ViewType.EDITOR || !(view instanceof ControllableView)) {
      return;
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    if (this.view.getViewType() != ViewType.EDITOR || !(view instanceof ControllableView)) {
      return;
    }
  }
}


