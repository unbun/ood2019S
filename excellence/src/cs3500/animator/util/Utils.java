package cs3500.animator.util;


/**
 * A Generaly utility class.
 */
public class Utils {

  /**
   * Check if the given number is negative.
   *
   * @param n the number to check
   * @return the number if it is not negative
   * @throws IllegalArgumentException thrown if the number is negative
   */
  public static <T> T requirePositive(T n) throws IllegalArgumentException {
    if (n instanceof Number) {
      Number nNum = (Number) n;
      if (nNum.doubleValue() <= 0) {
        throw new IllegalArgumentException("value cannot be negative.");
      }

      return n;
    }

    throw new IllegalArgumentException("value is not a number");
  }


  /**
   * Check if the given number is negative.
   *
   * @param n the number to check
   * @return the number if it is not negative
   * @throws IllegalArgumentException thrown if the number is negative
   */
  public static <T> T requireNonNegative(T n) throws IllegalArgumentException {
    return Utils.requireNonNegative(n, n.toString());
  }


  /**
   * Check if the given number is negative.
   *
   * @param n the number to check
   * @param name the name of the number for the error
   * @return the number if it is not negative
   * @throws IllegalArgumentException thrown if the number is negative
   */
  public static <T> T requireNonNegative(T n, String name) throws IllegalArgumentException {
    if (n instanceof Number) {
      Number nNum = (Number) n;
      if (nNum.doubleValue() < 0) {
        throw new IllegalArgumentException(name + " cannot be negative.");
      }

      return n;
    }

    throw new IllegalArgumentException(name + "is not a number");
  }

  /**
   * Map the given x value from a range of inMin - inMax to outMin - outMax.
   * @param x the value to map
   * @param inMin the input minimum
   * @param inMax the input maximum
   * @param outMin the output min
   * @param outMax the output max
   * @return the give value mapped to the output range values.
   */
  public static float map(float x, float inMin, float inMax, float outMin, float outMax) {
    if ((inMax - inMin) + outMin == 0) {
      throw new IllegalArgumentException("Cannot map these values");
    }
    return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
  }
}
