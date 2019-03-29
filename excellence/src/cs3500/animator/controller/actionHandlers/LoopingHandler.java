package cs3500.animator.controller.actionHandlers;

import cs3500.animator.view.ControllableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action listener class that implements the looping toggler mechanism by the corresponding check box.
 */
public class LoopingHandler implements ActionListener {
  ControllableView view;

  /**
   * Default constructor.
   *
   * @param view the hybrid view to be used
   */
  public LoopingHandler(ControllableView view) {
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(view.getLoopCheckBox().isSelected()){
      this.view.getAnimationPanel().setLooping(true);
      view.getLoopCheckBox().setText("Pause to toggle back");
    } else{
      if(!view.getPlayButtonTitle().equalsIgnoreCase("Press to pause")){
        this.view.getAnimationPanel().setLooping(false);
        view.getLoopCheckBox().setText("Toggle Looping");
      } else {
        view.getLoopCheckBox().setSelected(true);
      }
    }
  }
}
