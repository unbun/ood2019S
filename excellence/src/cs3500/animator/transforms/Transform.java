package cs3500.animator.transforms;

import cs3500.animator.shapes.LiveShape;

import java.util.function.Function;

public interface Transform {

    /**
     * Get the function that this transform does on live shapes
     */
    Function<LiveShape, LiveShape> func();

    /**
     * Get the given {@code LiveShape} in it's state at the given time, if {@code this} {@code
     * Transform} was to occur on it at a given time (according to the time period defined for {@code
     * this} {@code Transform})
     *
     * @param ls   a {@code LiveShape} to apply the given transform at
     * @param time the current time
     * @return the LiveShape (altered if the time contrainsts are active, un-altered otherwise
     */
    LiveShape applyTimed(LiveShape ls, int time);

    /**
     * String representation of the type of Transform this is (currently, there are 2 types: motion
     * and alter)
     *
     * @return a one-word String description of the transformation
     */
    String typeStr();

    /**
     * Get the end time/tick of this transformation
     *
     * @return start time/tick
     */
    int endTime();

    /**
     * Get the start time/tick of the transformation
     *
     * @return end time/tick
     */
    int startTime();

}
