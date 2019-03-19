package cs3500.animator.model;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;

import java.util.ArrayList;
import java.util.List;

public final class TextFXModel extends ShapeFXModel<String> {

    private int duration;

    public TextFXModel(int rate, int duration) {
        super(rate);
        this.duration = duration;
    }

    public TextFXModel(int rate, LiveShape... shapes) {
        super(rate, shapes);
    }

    public TextFXModel(int rate, List<LiveShape> shapes) {
        super(rate, shapes, 300, 300, new Posn(0, 0));
    }

    public TextFXModel(int rate, Posn topLeftCorner, int cHeight, int cWidth) {
        super(rate, new ArrayList<>(), cHeight, cWidth, topLeftCorner);
    }

    @Override
    public String viewNow() {
        return viewAtTime(getTime());
    }

    @Override
    public String viewAtTime(int time) {
        StringBuilder str = new StringBuilder();
        str.append(String.format("canvas %s %s\n\n", CANVAS_HEGHT, CANVAS_WIDTH));

        for (LiveShape s : shapes) {
            str.append(String.format("shape %s\n", s.textTag()));
            LiveShape sState = s.copy();

            for (Transform t : sState.getTransforms()) {
                str.append(String.format("%s %s:\t ", t.typeStr(), s.getName()));

                String before = String.format("%d %s", t.startTime(), sState.textFields());
                str.append(String.format("%s\t| ", before));

                sState = t.applyTimed(sState, getFinalTime());

                String after = String.format("%d %s", t.endTime(), sState.textFields());
                str.append(String.format("%s\n", after));
            }
        }

        return str.toString();
    }

    private int getFinalTime() {
        int maxEnd = 0;
        for (LiveShape s : shapes) {
            for (Transform t : s.getTransforms()) {
                if (t.endTime() > maxEnd) {
                    maxEnd = t.endTime();
                }
            }
        }
        return maxEnd;
    }

}
