package cs3500.animator.view;


import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * An implementation of an SVG-based view. Creates an animation in .svg file format.
 */
public class SVGView implements IView {

  @Override
  public ViewType getViewType() {
    return ViewType.SVG;
  }

  @Override
  public JPanel getAnimationPanel() {
    throw new UnsupportedOperationException("SVG does not use panels");
  }

  @Override
  public void setListeners(MouseListener mouse, KeyListener keys) {
    return;
  }

  @Override
  public String updateView(AnimationModel model) {

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
  public void init() {
    // do nothing
    // not applicable to this view type
  }

  @Override
  public void setModel(AnimationModel model) {
    return;
  }
}