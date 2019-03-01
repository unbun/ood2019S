package cs3500.animation.animations;

public enum ActionType {
  CREATE("shape"), IDLE("still"), MOVE("motion"), SCALE("scale"), RECOLOR("color"), ROTATE("rotate");

  String descriptor;

  ActionType(String descriptor) {
    this.descriptor = descriptor;
  }

  public String getDescriptor(){
    return this.descriptor;
  }
}
