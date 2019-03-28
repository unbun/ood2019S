import cs3500.animator.shapes.AShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;

import cs3500.animator.util.Posn;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;

/**
 * Test shapefx.
 */
public class ShapeTests {

  private AShape oval;
  private AShape rect;


  void initShapes() {
    oval = new Oval("O1",new Posn(1,2), Color.BLUE, 3,4,5,7);
    rect = new Rectangle("R1", new Posn(8,9), Color.RED, 10,11,12,13);
  }

  @Test
  public void testSVG(){
      initShapes();
    assertEquals("<ellipse id=\"O1\" cx=\"1.0\" cy=\"2.0\" rx=\"5.0\" ry=\"7.0\" fill=\"RGB(0,0,65025)\" visibility=\"visible\" >\n" +
            "</ellipse>\n", oval.printSVG());
    assertEquals("<rect id=\"R1\" x=\"8.0\" y=\"9.0\" width=\"12.0\" height=\"13.0\" fill=\"RGB(65025,0,0)\" visibility=\"visible\" >\n" +
            "</rect>\n", rect.printSVG());
  }

}
