import cs3500.animator.shapes.LiveShape;
import cs3500.animator.shapes.Oval;
import cs3500.animator.shapes.Rectangle;
import cs3500.animator.shapes.Triangle;
import cs3500.animator.transforms.*;
import cs3500.animator.util.Posn;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;


/**
 * Test myShape transforms.
 */
public class TransformTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTimeInterval() {
        LiveShape o = new Oval(100, 50, 0, new Posn(200, 200),
                Color.RED, "R");
        Transform o1 = new MoveTo(50, 10,
                new Posn(300, 300));
    }

    @Test
    public void applyFuncs() {
        LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
                Color.RED, "R");

        Transform move = new MoveTo(10, 20,
                new Posn(300, 300));
        Transform resize = new Resize(10, 20,
                50, 51);
        Transform recolor = new Recolor(40, 60,
                Color.BLUE);
        Transform turn = new TurnTo(50, 60,
                60);
        Transform freeze = new Freeze(70, 80);

        LiveShape rmoved = new Rectangle(100, 50, 0, new Posn(300, 300),
                Color.RED, "R");

        assertEquals(r, move.applyTimed(r, 9));
        assertEquals(rmoved, move.applyTimed(r, 10));
        assertEquals(rmoved, move.applyTimed(r, 20));

        LiveShape rresized = new Rectangle(50, 51, 0, new Posn(200, 200),
                Color.RED, "R");

        assertEquals(r, resize.applyTimed(r, 0));
        assertEquals(rresized, resize.applyTimed(r, 15));
        assertEquals(rresized, resize.applyTimed(r, 100));

        LiveShape rcolored = new Rectangle(100, 50, 0, new Posn(200, 200),
                Color.BLUE, "R");

        assertEquals(r, recolor.applyTimed(r, 39));
        assertEquals(rcolored, recolor.applyTimed(r, 51));
        assertEquals(rcolored, recolor.applyTimed(r, 60));

        LiveShape rturned = new Rectangle(100, 50, 60, new Posn(200, 200),
                Color.RED, "R");

        assertEquals(r, turn.applyTimed(r, 40));
        assertEquals(rturned, turn.applyTimed(r, 51));
        assertEquals(rturned, turn.applyTimed(r, 60));

        assertEquals(r, freeze.applyTimed(r, 61));
        assertEquals(r, freeze.applyTimed(r, 71));
        assertEquals(r, freeze.applyTimed(r, 81));
    }

    @Test
    public void funcs() {
        LiveShape r = new Rectangle(100, 50, 0, new Posn(200, 200),
                Color.RED, "R");

        Transform move = new MoveTo(10, 20,
                new Posn(300, 300));
        Transform resize = new Resize(10, 20,
                50, 51);
        Transform recolor = new Recolor(40, 60,
                Color.BLUE);
        Transform turn = new TurnTo(50, 60,
                60);
        Transform freeze = new Freeze(70, 80);


        assertEquals(new Function<LiveShape, LiveShape>() {
            @Override
            public LiveShape apply(LiveShape liveShape) {
                return liveShape.moveTo(new Posn(300, 300));
            }
        }.apply(r), move.func().apply(r));

        assertEquals(new Function<LiveShape, LiveShape>() {
            @Override
            public LiveShape apply(LiveShape liveShape) {
                return liveShape.resize(50, 51);
            }
        }.apply(r), resize.func().apply(r));

        assertEquals(new Function<LiveShape, LiveShape>() {
            @Override
            public LiveShape apply(LiveShape liveShape) {
                return liveShape.recolor(0, 0, 255);
            }
        }.apply(r), recolor.func().apply(r));

        assertEquals(new Function<LiveShape, LiveShape>() {
            @Override
            public LiveShape apply(LiveShape liveShape) {
                return liveShape.turnTo(60);
            }
        }.apply(r), turn.func().apply(r));

        assertEquals(r, freeze.func().apply(r));
    }


    @Test
    public void iterateTest() {
        LiveShape t = new Triangle(100, 50, 0, new Posn(200, 200),
                Color.RED, "T");

        LiveShape t2 = new Triangle(50, 51, 60, 300, 300, Color.BLUE, "T");

        Transform move = new MoveTo(10, 20,
                new Posn(300, 300));
        Transform resize = new Resize(10, 20,
                50, 51);
        Transform recolor = new Recolor(40, 60,
                Color.BLUE);
        Transform turn = new TurnTo(50, 60,
                60);
        Transform freeze = new Freeze(70, 80);

        List<Transform> list = new ArrayList<>(Arrays.asList(move, resize, recolor, turn, freeze));

        LiveShape test = t.copy();

        for (Transform trnsfm : list) {
            test = trnsfm.func().apply(test);
        }

        assertEquals(t2, test);
    }

}