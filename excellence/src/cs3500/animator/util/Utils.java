package cs3500.animator.util;


/**
 * A Generaly utility class.
 */
public class Utils {

  /**
   * Check if the given number is negative.
   *
   * @param n the number to check
   * @param name the name of the number for the error
   * @return the number if it is not negative
   * @throws IllegalArgumentException thrown if the number is negative
   */
  public static Number requireNonNegative(Number n, String name) throws IllegalArgumentException {
    if (n.doubleValue() < 0) {
      throw new IllegalArgumentException(name + " cannot be negative.");
    }

    return n;
  }

}
