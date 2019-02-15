package cs3500.marblesolitaire;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MarbleSolitaire {

  private static final Integer INVALID_INTEGER = -1;
  private static final List<Integer> INVALID_LIST = Arrays.asList(-1,-1);

  public static void main(String[] args) {
    Map<String, Integer> defaultSize = new HashMap<>();
    defaultSize.put("english", 3);
    defaultSize.put("triangular", 5);
    defaultSize.put("european", 3);

    Map<String, List<Integer>> defaultHole = new HashMap<>();
    defaultHole.put("english", Arrays.asList(3, 3));
    defaultHole.put("triangular", Arrays.asList(0, 0));
    defaultHole.put("european", Arrays.asList(3, 3));

    if (args.length < 1) {
      System.out.println("No arguments given");
      return;
    }

    String mode = args[0];
    if (mode == null || mode.equals("")) {
      System.out.println("Cannot use null as game mode");
    }

    Integer size;
    List<Integer> holeCoords;

    try{
      size = parseForSize(args, defaultSize.getOrDefault(mode, INVALID_INTEGER));
      holeCoords = parseForHole(args, defaultHole.getOrDefault(mode, INVALID_LIST));
    } catch (IllegalArgumentException e){
      System.out.println(e.getMessage());
      return;
    }

    if(size == INVALID_INTEGER || holeCoords == INVALID_LIST){
      System.out.println(String.format("%s is not a valid mode.", mode));
      return;
    }

    System.out.println(String.format("I did it!\nmodel = '%s' size = %s hole = {%s, %s)", mode,
            size, holeCoords.get(0), holeCoords.get(1)));
  }


  private static int parseForSize(String[] fullArgs, Integer def) throws IllegalArgumentException {
    for (int i = 1; i < fullArgs.length; i++) {
      if (fullArgs[i].equalsIgnoreCase("-size")) {
        String sizeStr = "";
        try {
          sizeStr = fullArgs[i + 1];
          Integer size = Integer.parseInt(sizeStr);
          return size;

        } catch (ArrayIndexOutOfBoundsException idxe) {
          throw new IllegalArgumentException("Invalid size argument");
        } catch (NumberFormatException nfe){
          throw new IllegalArgumentException("Invalid size argument: " + sizeStr);
        }
      }
    }

    return def;
  }

  private static List<Integer> parseForHole(String[] fullArgs, List<Integer> def)
          throws IllegalArgumentException {

    for (int i = 1; i < fullArgs.length; i++) {
      if (fullArgs[i].equalsIgnoreCase("-hole")) {
        String rowStr = "";
        String columnStr = "";

        try {
          rowStr = fullArgs[i + 1];
          columnStr = fullArgs[i + 2];
          Integer row = Integer.parseInt(fullArgs[i + 1]);
          Integer column = Integer.parseInt(fullArgs[i + 2]);
          return Arrays.asList(row, column);

        } catch (ArrayIndexOutOfBoundsException idxe) {
          throw new IllegalArgumentException("Invalid size argument");

        } catch (NumberFormatException nfe) {
          throw new IllegalArgumentException(String.format("Invalid string arguments: %s, %s",
                  rowStr, columnStr));

        }
      }
    }

    return def;
  }

}
