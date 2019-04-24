package cs3500.animator;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.ShapeFXController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.ShapeFXModel;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Class that contains main method and runs the animation.
 */
public class Excellence {

  /**
   * Entry point.
   *
   * @param args parameters, -iv and -if are required. -o is set to syst.out if not specified, tick
   *              set to 1 if not specified. -if is input file, it must be a pre-existing file:
   *              TextAnimationOutput.txt or SVGOutput.svg. -iv must be either text, visual or svg.
   *              -speed is tick.
   */
  public static void main(String[] args) {

    AnimationReader factoryRead;
    AnimationBuilder<AnimationModel> factoryBuild;

    factoryRead = new AnimationReader();
    factoryBuild = new ShapeFXModel.Builder();

    AnimationModel model = factoryBuild.build();

    AnimationController controller = new ShapeFXController(model, args);

    String path = "";
    try {
      path = controller.getInputFilePath();
      Readable file = new FileReader(path);
      factoryRead.parseFile(file, factoryBuild);
    } catch (IOException exception) {
      throw new IllegalArgumentException(
          "File path '" + path + "' does not exist, please specify a " +
              "pre-existing file");
    }

    controller.runAnimation();
  }
}