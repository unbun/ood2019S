package cs3500.animator.controller.handlers;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.ControllableView;
import cs3500.animator.view.SVGView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Creates an SVG file representing the animation to be exported when the corresponding button is
 * clicked.
 */
public class SVGExportHandler implements ActionListener {

  private ControllableView view;
  private AnimationModel model;

  /**
   * Constructs an SVGHandler.
   *
   * @param view the view passed into this handler
   * @param model the model passed into this handler
   * @throws IOException due to illegal creation of a file
   */
  public SVGExportHandler(ControllableView view, AnimationModel model) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Appendable ap;
    String svg = JOptionPane.showInputDialog(view, "Enter SVG file name.");
    try {
      ap = new FileWriter(svg);
      ap.append(new SVGView().updateView(model));
      ((FileWriter) ap).close();
      JOptionPane.showMessageDialog(view, svg + "exported successfully!");
    } catch (IOException exc) {
      throw new IllegalStateException("Export failed.");
    }
  }
}