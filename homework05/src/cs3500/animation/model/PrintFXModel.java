package cs3500.animation.model;

import cs3500.animation.shapes.LiveShape;
import cs3500.animation.transforms.Transform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PrintFXModel extends ShapeFXModel {

  Appendable output;

  public PrintFXModel(int rate, Appendable output) {
    this(rate, new ArrayList<>(), output);
  }

  public PrintFXModel(int rate, List<LiveShape> shapes, Appendable output) {
    super(rate, shapes);
    this.output = output;
  }

  @Override
  public void onTick() {
    for (Transform t : transforms) {
      if (t.started(currTime - 1) && !t.started(currTime - 2)) { //starts on this next tick
        println(t.textualView());
      }
    }

    super.onTick();
  }

  private void println(String... strings) {
    try {
      for (String s : strings) {
        output.append(s);
        output.append("\n");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Output stream is invalid");
    }
  }
}
