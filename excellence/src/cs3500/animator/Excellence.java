package cs3500.animator;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.PSupportedController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.LocalToProviderModel;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Class that contains main method which runs the animation.
 */
public class Excellence {

  /**
   * Runs an animation through command line.
   *
   * @param args parameters supplied by the user through the command line -view and -in are required
   *        -out represents the output file, set to syst.out if not specified - speed represents
   *        tick rate
   *        and is set to 1 if not specified -in represents an input file, must be a pre-existing
   *        file.
   *        -view represents the type of animation to be created, must be one of: text, visual,
   *        edit, or svg.
   */
  public static void main(String[] args) {
    AnimationReader reader = new AnimationReader();
    AnimationBuilder<AnimationModel> builder = new LocalToProviderModel.Builder();
    AnimationModel model = builder.build();
    AnimationController controller = new PSupportedController(model, args);
    String filePath = "";
    try {
      filePath = controller.getInputFile();
      Readable file = new FileReader(filePath);
      AnimationReader.parseFile(file, builder);
    } catch (IOException exception) {
      throw new IllegalArgumentException(
          "Error in parsing input file, try again.");
    }
    controller.run();
  }
}