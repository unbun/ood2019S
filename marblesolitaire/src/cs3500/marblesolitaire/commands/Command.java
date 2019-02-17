package cs3500.marblesolitaire.commands;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

public interface Command {

    MarbleSolitaireModel createModel();
}
