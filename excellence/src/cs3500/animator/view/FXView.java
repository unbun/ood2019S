package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;

public interface FXView<V> {

    void update(AnimationModel<V> model);

}
