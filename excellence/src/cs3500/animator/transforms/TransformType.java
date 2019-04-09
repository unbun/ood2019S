package cs3500.animator.transforms;

/**
 * Represents a specific type of transformation.
 */
public enum TransformType {
  MOVE("motion"), RECOLOR("recolor"),
  RESIZE("scale");

  private String descript;

  TransformType(String descript) {
    this.descript = descript;
  }

  String descriptor() {
    return this.descript;
  }

}
