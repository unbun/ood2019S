package cs3500.animation.utils;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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


  /**
   * Copy the fields of the given object, and put them into a HashMap.
   * <p>
   * Do determine the fields to copy over, this method reflexively looks through the object's class.
   * But if the desired fields are in a superclass, then use the superLvl param to also collect
   * those fields. The method works by going up in extension only the amount that is specified by
   * the superLvl param.
   * </p>
   * <p>
   * Fields can also be ignored using the Collection parameter, but the names in that parameter must
   * match the field names exactly.
   *
   *
   * @param obj1 the object to get the specified fields from
   * @param superLvl the level to move up in super classes. However, the class level will stop
   *                 before it gets to the Object class.
   * @param ignore the name of the fields to ignore
   * @return a HashMap of the fields of the object as specified by the parameters
   * @throws IllegalArgumentException if the arguments are invalid
   * @throws IllegalAccessException if the fields of the object can't be accessed.
   */
  public static HashMap<String, Object> reflexiveCopy(Object obj1, int superLvl, String... ignore)
      throws IllegalArgumentException, IllegalAccessException {

    Class<? extends Object> c1 = obj1.getClass();
    List<Field> fields = new ArrayList<>();

    for (int i = -1; i < superLvl; i++) {
      fields.addAll(Arrays.asList(c1.getDeclaredFields()));

      if (c1.getSuperclass() == Object.class) {
        break;
      }
      c1 = c1.getSuperclass();
    }

    HashMap<String, Object> map = new HashMap<>();

    for(Field f : fields){
      String name = f.getName();
      if(!Arrays.asList(ignore).contains(name)){
        f.setAccessible(true);
        Object value = f.get(obj1);
        map.put(name, value);
      }
    }

    return map;
  }


}
