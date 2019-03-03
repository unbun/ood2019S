package cs3500.animation.animations;


/**
 * Types of actions that can be done for animations.
 */
public enum ActionType {
  CREATE("shape"), IDLE("still"), MOVE("motion"), SCALE("scale"), RECOLOR("color"), ROTATE(
      "rotate");

  String descriptor;

  /**
   * A type of action.
   *
   * @param descriptor the string description
   */
  ActionType(String descriptor) {
    this.descriptor = descriptor;
  }

  /**
   * The description of the type.
   *
   * @return the String description
   */
  public String getDescriptor() {
    return this.descriptor;
  }
}
