package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.util.Utils;

public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  Readable in;
  Appendable out;


  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    this.in = Utils.requireNonNull(rd);
    this.out = Utils.requireNonNull(ap);
  }

  @Override
  public void playGame(MarbleSolitaireModel model)
          throws IllegalArgumentException, IllegalStateException {

    Utils.requireNonNull(model);

    boolean playerQuit = false;

    //Game Loop
    while(!model.isGameOver()) {

      toOutStream(model.getGameState() + "\n");
      toOutStream("Score: " + model.getScore() + "\n");


      ArrayList<Integer> parsedInput = new ArrayList<>();

      //Input Loop
      while(true){
        Scanner scan = new Scanner(this.in);
        String userIn = scan.nextLine();
        ArrayList<String> newInputs = parseInputString(userIn);

        if (newInputs == null){ //need to get new more, these were invalid
          continue;
        } else if(newInputs.get(0).equals("q")){ //do a quit
          playerQuit = true;
          break;
        } else { // do a input update
          parsedInput = combine4Ints(parsedInput, newInputs);
        }

        if(parsedInput.size() == 4){
          break;
        }
      }

      if(playerQuit){
        break;
      }

      try{
        model.move(parsedInput.get(0), parsedInput.get(1), parsedInput.get(2), parsedInput.get(3));
      } catch (IllegalArgumentException e){
        toOutStream(String.format("Invalid Move. Play Again. %s", e.getMessage()));
      }
    }

    if(playerQuit){
      toOutStream("Game quit!\n");
      toOutStream("State of qame when quit:\n");
    } else{
      toOutStream("Game over!");
    }

    toOutStream(model.getGameState() + "\n");
    toOutStream("Score: " + model.getScore() + "\n");

  }

  /**
   * Return the given list of integers with the parsable integers in the String list.
   * If the result is more than 4 integers, then it's invalid, returns an empty array.
   * @param prevInts an Array of current Integers that have been inputted
   * @param newIntStrings an Array of Strings to parsable Integers and add to the other array
   * @return The parsable Integers added to the Integer array (or null if the resulting array is
   * too large).
   */
  private ArrayList<Integer> combine4Ints(ArrayList<Integer> prevInts,
                                          ArrayList<String> newIntStrings){

    if(prevInts.size() + newIntStrings.size() > 4){
      toOutStream(String.format("Invalid Move. Play Again. Too many Variables"));
      return new ArrayList<>();
    }

    for(String str: newIntStrings){
      prevInts.add(Integer.parseInt(str) - 1); //subtracting 1 for user-friendly inputs
    }

    return prevInts;
  }

  /**
   * Parse the input String, return a list of relevant valid inputs that the string contains.
   * If it contains invalid outputs, return null. If it contains a 'q' or 'Q' input, return just
   * the 'q'
   * @param in The line of input
   * @return If the input has 'q', an Array of 'q'. If the input is of integers, return the
   * integers, if the input is invalid, null.
   */
  private ArrayList<String> parseInputString(String in){
    ArrayList<String> parsed = new ArrayList<>();

    String[] tokens  = in.split("[ ]+");
    for(String t: tokens){
      try {
        Integer i = Integer.parseInt(t);
        parsed.add(t);
      } catch (NumberFormatException e) {
        if(!t.toLowerCase().equals("q")){
          String msg = String.format("'%s' is not valid", t);
          toOutStream(String.format("Invalid Move. Play Again. %s", msg));
          return null;
        } else {
          return new ArrayList<>(Arrays.asList("q"));
        }
      }
    }

    return parsed;
  }

  /**
   * Transmit a given String to this controller's {@code Appendable}
   * @param toSend    the String to transmit ("" to use default)
   * @throws IllegalStateException
   */
  private void toOutStream(String toSend) throws IllegalStateException{
    try {
      out.append(toSend);
    } catch (IOException ioe){
        throw new IllegalStateException("Cannot use provided output stream.\n " + ioe.getMessage());
    }
  }
}
