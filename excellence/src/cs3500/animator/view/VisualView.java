package cs3500.animator.view;


import cs3500.animator.model.AnimationModel;
import cs3500.animator.util.Constants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * Implementation of a visual view of an animation.
 */
public class VisualView extends JFrame implements IView, ActionListener {

  private AnimationPanel animationPanel;

  /**
   * Constructs a visual view.
   */
  public VisualView() {
    super();
    this.animationPanel = new AnimationPanel();
    this.setTitle("Excellence in Animation");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    animationPanel.setPreferredSize(new Dimension(Constants.CANVAS_WIDTH,
        Constants.CANVAS_HEIGHT));
    this.add(animationPanel, BorderLayout.CENTER);

    JScrollPane scrollBar = new JScrollPane(this.animationPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    this.add(scrollBar);
    this.pack();
    this.animationPanel.setPlaying(true);
    this.animationPanel.setLooping(true);
  }

  @Override
  public void setListeners(MouseListener mouse, KeyListener keys) {
    // not applicable to this view type
    return;
  }

  @Override
  public void init() {
    this.setVisible(true);
  }

  public JPanel getAnimationPanel() {
    return this.animationPanel;
  }

  @Override
  public ViewType getViewType() {
    return ViewType.VISUAL;
  }

  @Override
  public String updateView(AnimationModel model) {
    Timer timer = new Timer(1000 / model.getTickRate(), this);
    timer.start();
    return "";
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    repaint();
  }

  @Override
  public void setModel(AnimationModel model) {
    return;
  }
}
