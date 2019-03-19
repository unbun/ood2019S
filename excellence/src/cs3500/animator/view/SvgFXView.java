package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public final class SvgFXView implements FXView<String> {

    FileWriter writer;

    public SvgFXView(FileWriter writer) {
        this.writer = Objects.requireNonNull(writer);
    }

    @Override
    public void update(AnimationModel<String> model) {
        try {
            writer.flush();
            writer.append(model.viewNow());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
