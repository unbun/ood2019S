package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.LocalToProviderModel;
import cs3500.animator.provider.view.SinglePanelEditView;
import cs3500.animator.util.Constants;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Objects;
import javax.swing.JPanel;

/**
 * An Adapter to get provided view to act as the view that can be controlled with the local
 * controller.
 */
public class ProviderToLocalEditView extends SinglePanelEditView implements IView {

  private ActionListener lstnr;
  private AnimationPanel animationPanel;

  public ProviderToLocalEditView(ActionListener lstnr) {
    this.lstnr = Objects.requireNonNull(lstnr);
    this.animationPanel = new AnimationPanel();
  }

  @Override
  public void setListeners(MouseListener mouse, KeyListener keys) {
    this.initView(lstnr, Constants.CANVAS_WEIGHT_PROVIDED, Constants.CANVAS_HEIGHT_PROVIDED);
  }

  @Override
  public ViewType getViewType() {
    if (super.isAnimatingView()) {
      return ViewType.PROVIDED_EDIT;
    }
    if (super.isVisualView()) {
      return ViewType.PROVIDED_VISUAL;
    }

    throw new UnsupportedOperationException("This Adpated View can only be EDITOR OR VISUAL");
  }

  @Override
  public JPanel getAnimationPanel() throws UnsupportedOperationException {
    return super.getCombinedPanel();
  }

  @Override
  public String updateView(AnimationModel model) {
    super.callRepaint();
    return "";
  }

  @Override
  public void init() {
    if (this.lstnr != null) {
      this.initView(lstnr, Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
    } else {
      throw new UnsupportedOperationException("Cannot listen if there is no listener available");
    }
  }

  @Override
  public void setModel(AnimationModel model) {
    if (model instanceof LocalToProviderModel) {
      super.model = (LocalToProviderModel) model;
    } else {
      throw new UnsupportedOperationException("Adapted Views must use Adapted Model");
    }
  }

  public void start() {
    animationPanel.setPlaying(true);
  }

  public void pause() {
    animationPanel.setPlaying(false);
  }

  public void setLooping(boolean loop) {
    animationPanel.setLooping(loop);
  }

}
