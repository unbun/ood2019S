package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.AbstractBoardModel;

public class EuropeanSolitaireModelImpl extends AbstractBoardModel {

  public EuropeanSolitaireModelImpl(){
    this(3);
  }

  public EuropeanSolitaireModelImpl(int size) {
    this(size,(3 * (size - 1)) / 2, (3 * (size - 1)) / 2);
  }

  public EuropeanSolitaireModelImpl(int sRow, int sCol){
    this(3,sRow, sCol);
  }

  public EuropeanSolitaireModelImpl(int size, int sRow, int sCol){
    super(size, sRow, sCol, size % 2 == 0, "Side length must be odd");
  }

  @Override
  protected boolean nullSlotCheck(int r, int c) {
    boolean rowCheck = (r >= size - 1 && r < 2 * size - 1);
    int margin = Math.max((r - (size-1)) - (size - 1),
                            Math.max((size - 1) - r, 0));
    boolean columnCheck = !(c < margin || c > (realWidth() - margin - 1));

    return !(rowCheck || columnCheck);
  }
}
