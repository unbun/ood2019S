package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;
import java.util.function.Function;

public interface Transform {

    Function<LiveShape, LiveShape> func(int time);

    boolean active(int time);

    String typeStr();

    int sortRank();
}
