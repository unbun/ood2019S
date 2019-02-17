package cs3500.marblesolitaire;

import cs3500.marblesolitaire.commands.ModelCommand;
import cs3500.marblesolitaire.commands.EnglishGame;
import cs3500.marblesolitaire.commands.EuropeanGame;
import cs3500.marblesolitaire.commands.TriangleGame;
import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;

import java.io.InputStreamReader;
import java.util.*;

public final class MarbleSolitaire {

  public static final Integer INVALID_INTEGER = -1;

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
    Optional<List<Integer>> maybeHoleCoords;
    List<Integer> holeCoords;

    try {
      size = parseForSize(args, defaultSize.getOrDefault(mode, INVALID_INTEGER));
      maybeHoleCoords = parseForHole(args);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return;
    }

    Map<String, ModelCommand> knownCommands = new HashMap<>();

    if (!maybeHoleCoords.isPresent()) {
      //hole was not specfied
      knownCommands.put("english", new EnglishGame(size));
      knownCommands.put("european", new EuropeanGame(size));
      knownCommands.put("triangular", new TriangleGame(size));
    } else {
      //hole was specified
      holeCoords = maybeHoleCoords.get();
      knownCommands.put("english", new EnglishGame(size, holeCoords.get(0), holeCoords.get(1)));
      knownCommands.put("european", new EuropeanGame(size, holeCoords.get(0), holeCoords.get(1)));
      knownCommands.put("triangular", new TriangleGame(size, holeCoords.get(0), holeCoords.get(1)));
    }

    ModelCommand modelCmd = knownCommands.getOrDefault(mode, null);
    if (modelCmd == null) {
      System.out.println(String.format("Invalid argument: %s", mode));
      return;
    }

    MarbleSolitaireController ctrl = new MarbleSolitaireControllerImpl(new InputStreamReader(System.in), System.out);

    ctrl.playGame(modelCmd.createModel());

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

    //size was not specified
    return def;
  }

  private static Optional<List<Integer>> parseForHole(String[] fullArgs)
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
          return Optional.of(Arrays.asList(row, column));

        } catch (ArrayIndexOutOfBoundsException idxe) {
          throw new IllegalArgumentException("Invalid size argument");

        } catch (NumberFormatException nfe) {
          throw new IllegalArgumentException(String.format("Invalid string arguments: %s, %s",
                  rowStr, columnStr));

        }
      }
    }

    //no hole was specified
    return Optional.empty();
  }

}
