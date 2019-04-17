package cs3500.animator.view;

/**
 * Enumeration describing the different types of views.
 */
public enum ViewType {
  EDITOR("edit"), VISUAL("visual"), TEXT("text"), SVG("svg"), PROVIDED_EDIT(
      "edit"), PROVIDED_VISUAL("visual");

  String type;

  ViewType(String type) {
    this.type = type;
  }


}
