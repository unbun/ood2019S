package cs3500.animator.transforms;


import cs3500.animator.shapes.LiveShape;
import cs3500.animator.util.Utils;

import java.util.Objects;

public abstract class ATransform implements Transform {

    private int startTime;
    private int endTime;
    private TransformType type;

    ATransform(TransformType type, int startTime, int endTime) throws IllegalArgumentException {

        if (startTime > endTime) {
            throw new IllegalArgumentException("Start time must be before endtime");
        }

        this.type = Objects.requireNonNull(type);
        this.startTime = Utils.requireNonNegative(startTime);
        this.endTime = Utils.requireNonNegative(endTime);
    }

    public LiveShape applyTimed(LiveShape ls, int time) {
        if (time >= startTime()) {
            return func().apply(ls);
        }
        return ls;
    }

    public String typeStr() {
        return type.descriptor();
    }

    public int startTime() {
        return startTime;
    }

    @Override
    public int endTime() {
        return endTime;
    }
}
