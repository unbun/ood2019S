package cs3500.animator.shapes;

/**
 * Enum for the different kinds of Shapes able to be represented in an animation.
 */
public enum ShapeClass {
  RECT("rectangle", "rect"), OVAL("oval", "ellipse");

  String text;
  String svg;

  ShapeClass(String text, String svg) {
    this.text = text;
    this.svg = svg;
  }

  String textName() {
    return text;
  }

  String svgName() {
    return svg;
  }
}
