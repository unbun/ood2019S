package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  Readable in;
  Appendable out;


  public MarbleSolitaireControllerImpl(Readable rd, Appendable ap) throws IllegalArgumentException {
    if(rd == null){
      throw new IllegalArgumentException("Controller's Input Stream cannot null");
    }

    if(ap == null){
      throw new IllegalArgumentException("Controller's Output Stream cannot null");
    }

    this.in = rd;
    this.out = ap;
  }

  @Override
  public void playGame(MarbleSolitaireModel model)
          throws IllegalArgumentException, IllegalStateException {

    if(!model.isGameOver()){
      Scanner scan = new Scanner(this.in);
      String userInput = scan.nextLine();
      //look at that input
      // if it's 'q', quit, if its a number, send it to move


    }



  }

  /**
   * Transmit a given String to this controller's {@code Appendable}
   * @param toSend    the String to transmit ("" to use default)
   * @param errorMsg  the error message to print if toSend cannot be sent
   * @throws IllegalStateException
   */
  private void out(String toSend, String errorMsg) throws IllegalStateException{
    try {
      out.append(toSend);
    } catch (IOException ioe){
      if(errorMsg.equals("")){
        throw new IllegalStateException(ioe.getMessage());
      } else {
        throw new IllegalStateException(errorMsg);
      }
    }
  }

  private String getTransission(MarbleSolitaireModel model){
    return this.getTransission(model, false);
  }

  private String getTransission(MarbleSolitaireModel model, boolean playerQuit){
    StringBuilder sb = new StringBuilder();
    if(playerQuit){
      sb.append("Game Quit!\n");
    }

    sb.append(String.format("%s\n", model.getGameState()));
    sb.append(String.format("Score: %d", model.getScore()));

    return sb.toString();
  }
}
