import org.junit.Test;

import cs3500.marblesolitaire.model.posn.BoardPosn;
import cs3500.marblesolitaire.model.posn.NullPosn;
import cs3500.marblesolitaire.model.posn.Posn;
import cs3500.marblesolitaire.model.posn.PosnState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestPosn {

  @Test
  public void debugString() {
    Posn p1 = new BoardPosn(0, 0, PosnState.FILLED);
    Posn p2 = new BoardPosn(3, 4, PosnState.EMPTY);
    Posn pN = new NullPosn(4, 5);

    assertEquals("[0, 0 ]", p1.toString());
    assertEquals("[3, 4*]", p2.toString());
    assertEquals("[null ]", pN.toString());
  }

  @Test
  public void stateChar() {
    Posn p1 = new BoardPosn(0, 0, PosnState.FILLED);
    Posn p2 = new BoardPosn(3, 4, PosnState.EMPTY);
    Posn pN = new NullPosn(4, 5);

    assertEquals('O', p1.getStateChar());
    assertEquals('_', p2.getStateChar());
    assertEquals(' ', pN.getStateChar());
  }

  @Test
  public void jumpableState() {
    Posn start = new BoardPosn(0, 0, PosnState.FILLED);
    Posn over = new BoardPosn(0, 1, PosnState.FILLED);
    Posn land = new BoardPosn(0, 2, PosnState.EMPTY);

    assertTrue(start.checkJumpStates(over, land));
    land.setState(PosnState.FILLED);
    assertFalse(start.checkJumpStates(over, land));
    start.setState(PosnState.EMPTY);
    assertTrue(land.checkJumpStates(over, start));
    start.setState(PosnState.FILLED);

    //changing positions don't effect its getState check
    Posn overNotReally = new BoardPosn(12, 12, PosnState.FILLED);
    Posn landNotReally = new BoardPosn(0, 7, PosnState.EMPTY);
    assertTrue(start.checkJumpStates(overNotReally, landNotReally));

    Posn pN = new NullPosn(0, 0);
    assertFalse(pN.checkJumpStates(over, land));
    land.setState(PosnState.EMPTY);
    assertFalse(pN.checkJumpStates(over, land));
  }

  @Test
  public void gettersAndSetters() {
    Posn p1 = new BoardPosn(0, 0, PosnState.FILLED);
    Posn p2 = new BoardPosn(3, 4, PosnState.EMPTY);
    Posn pN = new NullPosn(67, 89);

    assertEquals(p1.getColumn(), 0);
    assertEquals(p2.getRow(), 3);
    assertEquals(pN.getRow(), 67);

    assertEquals(PosnState.NULL, pN.getState());

    assertEquals(PosnState.FILLED, p1.getState());
    p1.setState(PosnState.EMPTY);
    assertEquals(PosnState.EMPTY, p1.getState());
    pN.setState(PosnState.NULL);
    assertEquals(PosnState.NULL, pN.getState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void occupyNull() {
    Posn nullp = new NullPosn(67, 89);
    nullp.setState(PosnState.FILLED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void emptyNull() {
    Posn nullp = new NullPosn(67, 89);
    nullp.setState(PosnState.EMPTY);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidPosn1() {
    Posn negR = new BoardPosn(-4, 5, PosnState.EMPTY);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidPosn2() {
    Posn negC = new NullPosn(4, -5);
  }
}
