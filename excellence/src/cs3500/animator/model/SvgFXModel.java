package cs3500.animator.model;

import cs3500.animator.shapes.LiveShape;
import cs3500.animator.transforms.Transform;
import cs3500.animator.util.Posn;

import java.util.List;

public final class SvgFXModel extends ShapeFXModel<String> {

    int loopback; // the duration of a loop (0 if no loop)

    public SvgFXModel(int rate) {
        super(rate);
        this.loopback = 0;
    }

    public SvgFXModel(int rate, int loopback) {
        super(rate);
        this.loopback = loopback;
    }

    public SvgFXModel(int rate, int loopback, LiveShape... shapes) {
        super(rate, shapes);
        this.loopback = loopback;
    }

    public SvgFXModel(int rate, int loopback, List<LiveShape> shapes) {
        super(rate, shapes, 300, 300, new Posn(0, 0));
        this.loopback = loopback;
    }

    @Override
    public String viewNow() {
        return null;
    }

    @Override
    public String viewAtTime(int time) {
        StringBuilder str = new StringBuilder();
        str.append(
                String.format(
                        "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\" version=\"1.1\">\n",
                        CANVAS_WIDTH, CANVAS_HEGHT));

        for (LiveShape s : shapes) {
            //TODO: implement shape svg text
            for (Transform t : s.getTransforms()) {
                //TODO: implement transform svg texts
            }
        }
        str.append("</svg>");
        return str.toString();
    }

    /**
     * @param duration in seconds
     * @return
     */
    public String getLoopElement(int duration) {

        if (loopback != 0) {
            return String.format("<rect xmlns=\"http://www.w3.org/2000/svg\">\n"
                    + "   <animate id=\"base\" begin=\"0;base.end\" dur=\"%.2fms\" attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
                    + "</rect>\n", duration);
        } else {
            return "";
        }

    }
}

/*

<svg xmlns="http://www.w3.org/2000/svg" width="700" height="500" version="1.1">

<!--A purple rectangle named P with lower left corner (200,200), width 50 and height 100 -->
<rect id="P" x="300" y="200" width="50" height="100" fill="rgb(128,0,128)" visibility="visible">
    <!-- starting at time=1s, move the rectangle horizontally from x=200 to x=300 in 4 seconds -->
    <!-- fill=freeze keeps it there after the animation ends -->
    <animate attributeType="xml" begin="1000ms" dur="4000ms" attributeName="x" from="200" to="300" fill="freeze"/>

    <!--add more animations here for this rectangle using animate tags -->
</rect>

<!--An orange ellipse named "E" with center at (500,100), x-radius 60 and y-radius 30 -->
<ellipse id="E" cx="500" cy="100" rx="60" ry="30" fill="rgb(255,128,0)" visibility="visible">
    <!-- starting at time=2s, move the ellipse's center from (500,100) to (600,400) in 5 seconds -->
    <!-- fill=remove, which is the default if you don't specify it, brings the shape back to its original attributes after
    this animation is over -->
    <animate attributeType="xml" begin="2000.0ms" dur="5000.0ms" attributeName="cx" from="500" to="600" fill="remove"/>
    <animate attributeType="xml" begin="2000.0ms" dur="5000.0ms" attributeName="cy" from="100" to="400" fill="remove"/>
    <!--add more animations here for this ellipse using animate tags -->
</ellipse>

</svg>
 */
