package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

public class TriangleSolitaireModelImpl implements MarbleSolitaireModel {
  final private int nRows;

  public TriangleSolitaireModelImpl(){
    this(5,5,5);
  }

  public TriangleSolitaireModelImpl(int nRows) {
    this(nRows, nRows, nRows);
  }

  public TriangleSolitaireModelImpl(int sRow, int sCol){
    this(3, sRow, sCol);
  }


  public TriangleSolitaireModelImpl(int nRows, int sRow, int sCol){
    this.nRows = nRows;
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
