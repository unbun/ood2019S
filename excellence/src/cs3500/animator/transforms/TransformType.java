package cs3500.animator.transforms;

public enum TransformType {
  MOVE("motion"), TURN("motion"), FREEZE("motion"), RECOLOR("alter"), RESIZE("alter");

  private String descript;

  TransformType(String descript) {
    this.descript = descript;
  }

  String descriptor(){
    return this.descript;
  }

}
