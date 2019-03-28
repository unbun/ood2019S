package cs3500.animator.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.*;
import cs3500.animator.view.actionhandlers.*;

/**
 * This interface represents the points of control of the operations offered by the Easy Animator.
 * Allows the user to run animations through manual input.
 */
public class Controller implements IController {
  private final AnimationModel model;
  private IAnimationView view;
  private Appendable ap = System.out;
  private String inputFilePath = "";
  private int speed = 1;
  private String typeOfView = "";
  // isSytemOut is a flag used to detect whether or not the appendable needs to be flushed. If true,
  // the appendable does NOT need to be flushed.
  private boolean isSystemOut = true;
  private boolean canUseAppendable = true;

  /**
   * Default constructor for controller.
   *
   * @param model the desired model that handles the animation
   * @param args  arguments in main method
   * @throws IllegalArgumentException if passed a null model
   */
  public Controller(AnimationModel model, String[] args)
          throws IllegalArgumentException {
    Objects.requireNonNull(model);

    this.model = model;
    this.parseInput(args);
    model.setSpeed(speed);
    this.createView(typeOfView);

    if (this.view.getViewType().equals(ViewType.HYBRID)) {
      ((ControllableView) view)
              .getPlayButton().addActionListener(new PlayButtonHandler((ControllableView) this.view));
      ((ControllableView) view)
              .getRestartButton().addActionListener(
              new RestartButtonHandler((ControllableView) this.view));
      ((ControllableView) view)
              .getLoopCheckBox().addActionListener(new LoopingHandler((ControllableView) this.view));
      ((ControllableView) view)
              .getSlowDownButton().addActionListener(
              new SlowDownAnimationHandler((ControllableView) this.view));
      ((ControllableView) view)
              .getSpeedUpButton().addActionListener(
              new SpeedUpAnimationHandler((ControllableView) this.view));
      try {
        ((ControllableView) view).getExportSVG().addActionListener(
                new ExportSVGHandler((ControllableView) this.view, this.model, this.ap));
      } catch (IOException e) {
        throw new IllegalArgumentException("Unable to export SVG");
      }
    }
  }

  @Override
  public void runAnimation() {
    if (canUseAppendable) {
      view.makeVisible();
      try {
        ap.append(view.makeView(model));
      } catch (IOException e) {
        throw new IllegalArgumentException("Could not create View!");
      }
    } else {
      view.makeView(model);
      view.makeVisible();
    }

    if (!isSystemOut) {
      try {
        ((FileWriter) ap).flush();
        ((FileWriter) ap).close();
      } catch (IOException e) {
        throw new IllegalStateException("Cannot close, data has been lost");
      }
    }
  }

  /**
   * Creates the desired view based on user input; can be one of the following:.
   * - visual.
   * - interactive.
   * - svg.
   * - text.
   *
   * @param view the view to be made
   */
  private void createView(String view) {
    switch (view.toLowerCase()) {
      case "visual":
        this.view = new VisualView();
        this.view.getAnimationPanel().setModel(model);
        break;
      case "edit":
        this.view = new ControllableView();
        this.view.getAnimationPanel().setModel(model);
        break;
      case "svg":
        this.view = new SVGView();
        break;
      case "text":
        this.view = new TextualView();
        break;
      default:
        throw new IllegalArgumentException(typeOfView + " is not a valid view type!");
    }
  }

  /**
   * The purpose of this method is to parse the user input and establish the types of things the
   * user specified. Such as which view it is, what speed it is etc.
   *
   * @param args given arguments in the main method.
   */
  private void parseInput(String[] args) {

    if (args.length > 0) {

      for (int i = 0; i < args.length; i += 2) {

        try {
          if ((args[i + 1].length() > 0)) {
            switch (args[i]) {

              // name of animation file.
              case "-in":
                if ((args[i + 1].endsWith(".txt")) || (args[i + 1].endsWith(".svg"))) {
                  inputFilePath = args[i + 1];
                } else {
                  throw new
                          IllegalArgumentException(args[i + 1].substring(args[i + 1].length() - 4)
                          + " is not a valid output file type");
                }
                break;

              // type of view.
              case "-view":
                typeOfView = args[i + 1];
                break;

              // output location.
              case "-out":
                if (args[i + 1].equalsIgnoreCase("out")) {
                  ap = System.out;
                } else {
                  try {
                    ap = new FileWriter(args[i + 1]);
                    isSystemOut = false;
                  } catch (IOException e) {
                    throw new IllegalArgumentException("Error in output file creation");
                  }
                }
                break;

              //tick rate.
              case "-speed":
                try {
                  speed = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException e) {
                  throw new IllegalArgumentException(args[i + 1] + " is not a number");
                }
                if (speed < 1) {
                  throw new IllegalArgumentException("The tick rate cannot be zero");
                }
                break;

              default:
                throw new IllegalArgumentException("There was a bad parameter" +
                        " specifier, try again");
            }
          }
        } catch (IndexOutOfBoundsException e) {
          throw new IllegalArgumentException("You must have values after" +
                  " the parameter specifiers.");
        }
      }
    }

    if (this.inputFilePath.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("No input file path detected. Please specify" +
              " a pre-existing input file path.");
    }

  }

  @Override
  public String getInputFilePath() {
    return this.inputFilePath;
  }
}


