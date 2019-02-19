package cs3500.marblesolitaire;

import cs3500.marblesolitaire.commands.ModelCommand;
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
import java.util.Optional;


/**
 * Command line runner to run Marble Solitaire for any customizable boards.
 */
public final class MarbleSolitaire {

  public static final Integer INVALID_INTEGER = -1;


  /**
   * Main method to run the MarbleSolitaire through the command line.
   *
   * @param args arguments given from the command line
   */
  public static void main(String[] args) {

    Map<String, Integer> defaultSize = new HashMap<>();
    defaultSize.put("english", 3);
    defaultSize.put("triangular", 5);
    defaultSize.put("european", 3);

    if (args.length < 1) {
      throw new IllegalArgumentException("No arguments given");
    }

    String mode = args[0];
    if (defaultSize.getOrDefault(mode, INVALID_INTEGER).equals(INVALID_INTEGER)) {
      throw new IllegalArgumentException(String.format("Invalid argument: %s", mode));
    }

    if (mode == null || mode.equals("")) {
      throw new IllegalArgumentException("Cannot use null as game mode");
    }

    Integer size;
    Optional<List<Integer>> maybeHoleCoords;
    List<Integer> holeCoords;

    size = parseForSize(args, defaultSize.getOrDefault(mode, INVALID_INTEGER));
    maybeHoleCoords = parseForHole(args);

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
      throw new IllegalArgumentException(String.format("Invalid argument: %s", mode));
    }

    MarbleSolitaireController ctrl = new MarbleSolitaireControllerImpl(
        new InputStreamReader(System.in), System.out);

    ctrl.playGame(modelCmd.createModel());

  }


  /**
   * Parse the given arguments for a specified size.
   *
   * @param fullArgs the given arguments
   * @param def a default save for size to use if size wasn't specified
   * @return the specified size, or the default value if none were specified
   * @throws IllegalArgumentException if the specified size is not an integer or isn't there but is
   *                                  expected to be
   */
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
        } catch (NumberFormatException nfe) {
          throw new IllegalArgumentException("Invalid size argument: " + sizeStr);
        }
      }
    }

    //size was not specified
    return def;
  }


  /**
   * Parse the given arguments for a specified hole coordinate.
   *
   * @param fullArgs the given arguments
   * @return the specified hole coordinates (with {@code list.index(0)} being the row and {@code
   *         list.index(1)} being the column, or the default value if none were specified
   * @throws IllegalArgumentException if the specified hole coordinates aren't integers or aren't
   *                                  there but are expected to be
   */
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
