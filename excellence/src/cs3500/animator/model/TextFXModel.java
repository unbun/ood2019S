package cs3500.animator.model;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.transforms.Transform;
import java.util.List;
import java.util.function.Function;

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
  public String getView(){
    StringBuilder str = new StringBuilder();

    for(LiveShape s : shapes){
      str.append("shape " + s.getID() + "\n");
      LiveShape sState = s.copy();

      for(Transform t : sState.getTransforms()){
        str.append(t.typeStr() + " " + s.getName() + ": ");

        String before = String.format(sState.toString());
        str.append(before + "\t| ");

        sState = t.func(getTime()).apply(sState);

        String after = String.format(sState.toString());
        str.append(after + "\n");
      }

    }

    return str.toString();
  }
}
