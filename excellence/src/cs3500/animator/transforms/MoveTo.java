package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.util.Posn;

import java.util.function.Function;

public class MoveTo extends ATransform {

    private Posn p;

    public MoveTo(int startTime, int endTime, Posn p) {
        super(TransformType.MOVE, startTime, endTime);
        this.p = p;
    }

    @Override
    public Function<LiveShape, LiveShape> func() {
        return ls -> ls.moveTo(p);
    }
}
