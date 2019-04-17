package cs3500.animator.provider.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


/**
 * An implementation of the EditView that can publicly provide a copy of the full panel being
 * viewed. This was created because the Controller/ActionListeners were not provided, so we had to
 * be able to use our own controllers and views adapted. We don't know how their Controllers handled
 * views or converted the shapes to viewable panels. But this seemed allowable given what we were
 * given.
 */
public class SinglePanelEditView extends EditView {

  /**
   * Get every panel in this view combined into one.
   *
   * @return one overall panel.
   */
  public JPanel getCombinedPanel() {
    JPanel combinedPanel = new JPanel();
    combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.Y_AXIS));
    combinedPanel.add(super.frame.getContentPane());
    return combinedPanel;

  }

}
