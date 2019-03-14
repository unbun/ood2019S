package cs3500.animator.model;

import cs3500.animator.shapes.LiveShape;
import java.util.List;

public final class TextFXModel extends ShapeFXModel<String> {

  public TextFXModel(int rate) {
    super(rate);
  }

  public TextFXModel(int rate, LiveShape... shapes) {
    super(rate, shapes);
  }

  public TextFXModel(int rate, List<LiveShape> shapes) {
    super(rate, shapes);
  }

  @Override
  public String getView() {
    StringBuilder res = new StringBuilder();
    for (LiveShape s : currentShapes()) {
      res.append(s.buildTextDoc());
    }

    return res.toString();
  }
}
