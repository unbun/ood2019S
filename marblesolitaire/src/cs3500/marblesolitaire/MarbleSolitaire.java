package cs3500.marblesolitaire;

import cs3500.marblesolitaire.commands.Command;
import cs3500.marblesolitaire.commands.EnglishGame;
import cs3500.marblesolitaire.commands.EuropeanGame;
import cs3500.marblesolitaire.commands.TriangleGame;
import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MarbleSolitaire {

  public static final Integer INVALID_INTEGER = -1;

  public static final List<Integer> DEFAULT_LIST = Arrays.asList(-1,-1);

  public static void main(String[] args) {
    Map<String, Integer> defaultSize = new HashMap<>();
    defaultSize.put("english", 3);
    defaultSize.put("triangular", 5);
    defaultSize.put("european", 3);


    if (args.length < 1) {
      System.out.println("No arguments given");
      return;
    }

    String mode = args[0];
    if (defaultSize.getOrDefault(mode, INVALID_INTEGER).equals(INVALID_INTEGER)) {
      System.out.println(String.format("Invalid argument: %s", mode));
      return;
    }

    if (mode == null || mode.equals("")) {
      System.out.println("Cannot use null as game mode");
      return;
    }

    Integer size;
    List<Integer> holeCoords;

    try {
      size = parseForSize(args, defaultSize.getOrDefault(mode, INVALID_INTEGER));
      holeCoords = parseForHole(args, DEFAULT_LIST);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return;
    }

    Map<String, Command> knownCommands = new HashMap<>();

    //hole was not specfied
    if (holeCoords.get(0) == DEFAULT_LIST.get(0) && holeCoords.get(1) == DEFAULT_LIST.get(1)) {
      System.out.println("Cannot use -1 for hole coordinates, using default hole positions\n");
      knownCommands.put("english", new EnglishGame(size));
      knownCommands.put("european", new EuropeanGame(size));
      knownCommands.put("triangle", new EuropeanGame(size));
    } else {
      knownCommands.put("english", new EnglishGame(size, holeCoords.get(0), holeCoords.get(1)));
      knownCommands.put("european", new EuropeanGame(size, holeCoords.get(0), holeCoords.get(1)));
      knownCommands.put("triangle", new TriangleGame(size, holeCoords.get(0), holeCoords.get(1)));
    }

    Command toRun = knownCommands.getOrDefault(mode, null);
    if (toRun == null) {
      System.out.println(String.format("Invalid argument: %s", mode));
      return;
    }

    MarbleSolitaireController ctrl = new MarbleSolitaireControllerImpl(new InputStreamReader(System.in), System.out);

    ctrl.playGame(toRun.createModel());

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

    //no hole was specified
    return def;
  }

}
