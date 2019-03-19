package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;

public final class TextFXView implements FXView<String> {

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void update(AnimationModel<String> model) {
        TextFXView.clearScreen();
        System.out.append(model.viewNow());
    }
}
