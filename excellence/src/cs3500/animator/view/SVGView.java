package cs3500.animator.view;



import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;

/**
 * The implementation of the SVG-based view. Creates a visual in .svg file format.
 */
public class SVGView implements IAnimationView {

  @Override
  public ViewType getViewType() {
    return ViewType.SVG;
  }

  @Override
  public AnimationPanel getAnimationPanel() {
    throw new IllegalArgumentException("There is not Panel here!");
  }


  @Override
  public String makeView(AnimationModel model) {

    StringBuilder stringBuilder = new StringBuilder();


    stringBuilder.append("<svg width=\"");
    stringBuilder.append(model.getWidth());
    stringBuilder.append("\" height=\"");
    stringBuilder.append(model.getHeight());
    stringBuilder.append("\" version=\"1.1\"\n");
    stringBuilder.append("xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (IShape s : model.getShapes()) {
      stringBuilder.append(s.printSVG());
      stringBuilder.append("\n");
    }

    stringBuilder.append("</svg>");

    return stringBuilder.toString();
  }

  @Override
  public void makeVisible() {
    // not applicable to this view type
  }
}