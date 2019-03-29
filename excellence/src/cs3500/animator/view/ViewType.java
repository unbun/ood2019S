package cs3500.animator.view;

/**
 * Enumeration describing the different types of views.
 */
public enum ViewType {
  EDITOR("edit"), VISUAL("visual"), TEXT("text"), SVG("svg");

  String cliRep;

  ViewType(String cliRep) {
    this.cliRep = cliRep;
  }
}
