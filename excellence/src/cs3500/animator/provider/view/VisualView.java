package cs3500.animator.provider.view;

import cs3500.animator.provider.model.ReadOnlyModel;
import cs3500.animator.shapes.IShape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * An VisualView will represent a View with a visual output. It will produce a digital
 * representation of the this model using the JFrame and JPanel class.
 */
public class VisualView extends JPanel implements AnimatingView {

  protected ReadOnlyModel model; //must init later
  JFrame frame;
  private int tick;


  //CONSTRUCTOR////////////////////////////////////////////////////////////////
  public VisualView() {
    this.frame = new JFrame();
    this.tick = 1;
  }

  //Type checks///////////////////////////////////////////////////////////////

  @Override
  public boolean isVisualView() {
    return true;
  }

  @Override
  public boolean isAnimatingView() {
    return true;
  }

  //METHODS THAT SHOULDN'T DO ANYTHING HERE////////////////////////////////////

  @Override
  public void setSpeed(int s) {
    //DOES NOTHING
  }

  @Override
  public void acceptOutput(String string) {
    //does nothing
  }

  @Override
  public String getTextRepresentation(ReadOnlyModel model) {
    return null;
  }

  @Override
  public String getOutput() {
    return null;
  }

  //METHODS/////////////////////////////////////////

  @Override
  public Object getSomething(String s) {
    Object result;
    if ("frame".equals(s)) {
      result = this.frame;
    } else {
      result = this.frame;
    }
    return result;
  }

  @Override
  public void updateModel(ReadOnlyModel model) {
    this.model = model;
  }

  @Override
  public JButton getButton(String s) {
    return null;
  }

  @Override
  public void callRepaint() {
    this.frame.repaint();
  }

  @Override
  public ReadOnlyModel getModel() {
    return this.model;
  }

  @Override
  public void setTick(int t) {
    this.tick = t;
  }

  //INITVIEW AND PAINTCOMPONENT//////////////////////////////////////////////

  @Override
  public void initView(ActionListener listener, int canWidth,
      int canHeight) {
    if (!frame.isVisible()) {
      frame.setSize(canWidth,
          canHeight);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLayout(new BorderLayout());
      frame.getContentPane().add(this, BorderLayout.CENTER);
      frame.requestFocus();
      frame.setVisible(true);
    }
  }


  @Override //Overriding the built in paintComponent method
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (IShape s : model.getShapes()) {
      if (s.getType().equals("oval")) {
        if (tick >= s.gett0()) {
          s = s.currState(tick);
          g.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(),
              s.getColor().getBlue()));
          g.fillOval((int) s.getPosn().getX(), (int) s.getPosn().getY(), (int)
              s.getWidth(), (int) s.getHeight());
        }
      }
      if (s.getType().equals("rectangle")) {
        if (tick >= s.gett0()) {
          s = s.currState(tick);
          g.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(),
              s.getColor().getBlue()));
          g.fillRect((int) s.getPosn().getX(), (int) s.getPosn().getY(),
              (int) s.getWidth(), (int) s.getHeight());
        }
      }
    }
  }
}






