package cs3500.marblesolitaire.commands;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

public interface ModelCommand {

  /**
   * Get the model that {@code this} command is customized to create.
   *
   * @return the model that the command will create.
   */
  MarbleSolitaireModel createModel();
}
