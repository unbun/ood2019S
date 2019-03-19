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
    public static <T> T requireNonNegative(T n) throws IllegalArgumentException {
        return Utils.requireNonNegative(n, n.toString());
    }


    /**
     * Check if the given number is negative.
     *
     * @param n    the number to check
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

}
