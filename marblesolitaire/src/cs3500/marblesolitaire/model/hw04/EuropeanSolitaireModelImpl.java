package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

public class EuropeanSolitaireModelImpl implements MarbleSolitaireModel {
  private final int side;

  public EuropeanSolitaireModelImpl(){
    this(3,3,3);
  }

  public EuropeanSolitaireModelImpl(int side) {
    this(side, side, side);
  }

  public EuropeanSolitaireModelImpl(int sRow, int sCol){
    this(3,sRow, sCol);
  }

  public EuropeanSolitaireModelImpl(int side, int sRow, int sCol){
    this.side = side;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public String getGameState() {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }

  @Override
  public int[] getValidJumped(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    return new int[0];
  }
}
