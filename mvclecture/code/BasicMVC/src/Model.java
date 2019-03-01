public class Model implements IModel {
  private String input;
  private boolean isWhite;

  public Model(boolean isWhite) {
    input = "";
    isWhite = isWhite;
  }

  @Override
  public void setString(String i) {
    input = i;
  }

  @Override
  public String getString() {
    return input;
  }

  @Override
  public void toggleColor() {
    isWhite = !isWhite;
  }

  @Override
  public boolean isWhite() {
    return isWhite;
  }
}