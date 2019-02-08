package cs3500.marblesolitaire.util;

import com.sun.deploy.util.StringUtils;

public class Utils {

  public static <T> T requireNonNull(T obj) throws IllegalArgumentException{
    if(obj == null){
      throw new IllegalArgumentException("Cannot have Null Argument");
    }
    return obj;
  }

  public static boolean containsPosNumberOnly(String str){
    boolean foundDelim = true;
    for(char c: str.toCharArray()){
      if(!Utils.isNumeric("" + c)){

      }


      if(c == ','){
        foundDelim = true;
        continue;
      } else {
        foundDelim = false;
        continue;
      }
    }

    return false;
  }

  public static boolean isNumeric(String strNum) {
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
      return false;
    }
    return true;
  }
}
