import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.BoardPosn;
import cs3500.marblesolitaire.model.hw02.NullPosn;
import cs3500.marblesolitaire.model.hw02.Posn;

import cs3500.marblesolitaire.model.hw02.OrthogonalDir;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class TestPosn {

  @Test
  public void debugString() {
    Posn p1 = new BoardPosn(0, 0, true);
    Posn p2 = new BoardPosn(3, 4, false);
    Posn pN = new NullPosn(4, 5);

    assertEquals("[0, 0 ]", p1.toString());
    assertEquals("[3, 4*]", p2.toString());
    assertEquals("[null ]", pN.toString());
  }

  @Test
  public void stateChar() {
    Posn p1 = new BoardPosn(0, 0, true);
    Posn p2 = new BoardPosn(3, 4, false);
    Posn pN = new NullPosn(4, 5);

    assertEquals('O', p1.getStateChar());
    assertEquals('_', p2.getStateChar());
    assertEquals(' ', pN.getStateChar());
  }

  @Test
  public void nullMethod() {
    Posn p2 = new BoardPosn(3, 4, false);
    Posn pN = new NullPosn(4, 5);

    assertTrue(pN.isNull());
    assertFalse(p2.isNull());
  }

  @Test
  public void jumpableState() {
    Posn start = new BoardPosn(0, 0, true);
    Posn over = new BoardPosn(0, 1, true);
    Posn land = new BoardPosn(0, 2, false);

    assertTrue(start.checkJumpStates(over, land));
    land.setOccupied(true);
    assertFalse(start.checkJumpStates(over, land));
    start.setOccupied(false);
    assertTrue(land.checkJumpStates(over, start));
    start.setOccupied(true);

    //changing positions don't effect its state check
    Posn overNotReally = new BoardPosn(12, 12, true);
    Posn landNotReally = new BoardPosn(0, 7, false);
    assertTrue(start.checkJumpStates(overNotReally, landNotReally));

    Posn pN = new NullPosn(0, 0);
    assertFalse(pN.checkJumpStates(over, land));
    land.setOccupied(false);
    assertFalse(pN.checkJumpStates(over, land));
  }

  @Test
  public void jumpableDirection() {
    Posn p1 = new BoardPosn(0, 0, true);
    Posn p2 = new BoardPosn(3, 4, false);

    assertTrue(p1.checkJumpDirection(OrthogonalDir.DOWN, 5));
    assertFalse(p1.checkJumpDirection(OrthogonalDir.DOWN, 1));

    assertFalse(p1.checkJumpDirection(OrthogonalDir.UP, 5));
    assertTrue(p2.checkJumpDirection(OrthogonalDir.UP, 5));

    assertFalse(p1.checkJumpDirection(OrthogonalDir.LEFT, 100));
    assertTrue(p2.checkJumpDirection(OrthogonalDir.LEFT, 100));

    assertTrue(p1.checkJumpDirection(OrthogonalDir.RIGHT, 2));
    assertFalse(p2.checkJumpDirection(OrthogonalDir.RIGHT, 5));

    Posn pN = new NullPosn(3, 4);
    assertFalse(pN.checkJumpDirection(OrthogonalDir.UP, 5));
    assertFalse(pN.checkJumpDirection(OrthogonalDir.LEFT, 100));
    assertFalse(pN.checkJumpDirection(OrthogonalDir.RIGHT, 2));
    assertFalse(pN.checkJumpDirection(OrthogonalDir.DOWN, 500));
  }

  @Test
  public void gettersAndSetters() {
    Posn p1 = new BoardPosn(0, 0, true);
    Posn p2 = new BoardPosn(3, 4, false);
    Posn pN = new NullPosn(67, 89);

    assertEquals(p1.getColumn(), 0);
    assertEquals(p2.getRow(), 3);
    assertEquals(pN.getRow(), 67);

    assertEquals(false, pN.isOccupied());
    pN.setOccupied(false);
    assertEquals(false, pN.isOccupied());

    assertEquals(true, p1.isOccupied());
    p1.setOccupied(false);
    assertEquals(false, p1.isOccupied());

  }

  @Test(expected = IllegalArgumentException.class)
  public void occupyNull() {
    Posn nullp = new NullPosn(67, 89);
    nullp.setOccupied(true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidPosn1() {
    Posn negR = new BoardPosn(-4, 5, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidPosn2() {
    Posn negC = new NullPosn(4, -5);
  }
}
