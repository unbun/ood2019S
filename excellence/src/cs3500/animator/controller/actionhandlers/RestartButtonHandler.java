package cs3500.animator.controller.actionhandlers;

import cs3500.animator.view.ControllableView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Action listener class that enables the restart animation functionality, triggered by a the
 * corresponding button's event.
 */
public class RestartButtonHandler implements ActionListener {

  ControllableView view;

  /**
   * Default constructor.
   *
   * @param view the hybrid view to be passed in
   */
  public RestartButtonHandler(ControllableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.view.getAnimationPanel().setTime(0);
  }
}
