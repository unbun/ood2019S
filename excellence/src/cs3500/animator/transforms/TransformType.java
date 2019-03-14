package cs3500.animator.transforms;


/**
 * Types of transforms that can be done for transforms.
 */
public enum TransformType {
  CREATE("create"), IDLE("freeze"), MOVE("motion"), SCALE("scale"), RECOLOR("color"), ROTATE(
      "rotate");

  String descriptor;

  /**
   * A type of action.
   *
   * @param descriptor the string description
   */
  TransformType(String descriptor) {
    this.descriptor = descriptor;
  }


  @Override
  public String toString() {
    return this.descriptor;
  }
}
