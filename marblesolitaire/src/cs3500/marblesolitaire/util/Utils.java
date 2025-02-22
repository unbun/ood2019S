package cs3500.marblesolitaire.util;

public class Utils {

  /**
   * Version of the {@code Objects.requreNonNull()} that throws an Illegal Argument Object. Checks
   * that the specified object reference is not {@code null}.
   *
   * @param obj the object reference to check for nullity
   * @param <T> the type of the reference
   * @return {@code obj} if not {@code null}
   * @throws IllegalArgumentException if {@code obj} is {@code null}
   */
  public static <T> T requireNonNull(T obj) throws IllegalArgumentException {
    if (obj == null) {
      throw new IllegalArgumentException("Cannot have Null Argument");
    }
    return obj;
  }

  /**
   * Look at the given string to see if there is an Integer in it that is higher than the given
   * lower bound.
   *
   * @param toParse the string to look at
   * @param low the lower bound
   * @return the String if it has a valid integer
   * @throws IllegalArgumentException IllegalArgument if it doesn't have a valid String
   */
  public static String requireValidInteger(String toParse, int low)
      throws IllegalArgumentException {
    Integer i;
    try {
      i = Integer.parseInt(toParse);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          String.format("Input '%s' does not contain a valid string", toParse));
    }

    if (i < low) {
      throw new IllegalArgumentException(
          String.format("Input '%s' must be an int greater than %d", toParse, low));
    }

    return toParse;
  }

  /**
   * Get the sign of the given value (and a magnitude of 1). If zero is given, zero is returned.
   *
   * @param value the value to get the sign to
   * @return 1 or -1, with the result corresponding to the sign of the given value (if value == 0,
   *         returns 0)
   */
  public static int getSign(int value) {
    if (value != 0) {
      return value / Math.abs(value);
    }
    return 0;
  }

  /**
   * Concatenate 2 int (primitive) arrays.
   *
   * @param first the first array
   * @param second the second array to concatenate onto the end of the first array
   * @return the first array values followed by the second array values
   */
  public static int[] concat(int[] first, int[] second) {
    Utils.requireNonNull(first);
    Utils.requireNonNull(second);

    int[] both = new int[first.length + second.length];
    for (int i = 0; i < first.length; i++) {
      both[i] = first[i];
    }

    for (int j = 0; j < second.length; j++) {
      both[j + first.length] = second[j];
    }

    return both;
  }
}
