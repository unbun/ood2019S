package cs3500.animation.utils;


public class Utils {

  public static Number requireNonNegative(Number n, String name){
    if (n.doubleValue() < 0){
      throw new IllegalArgumentException(name + " cannot be negative.");
    }

    return n;
  }



}
