package cs3500.animator.transforms;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.shapes.IShape;
import cs3500.animator.util.Posn;

/**
 * Moving a Shape to given location.
 */
public class MoveTo extends ATransform {
    private Posn dest;

    /**
     * Default constructor.
     *  @param startTime    the time at which the move begins
     * @param endTime      the time at which the move  ends
     * @param dest the dest position of the shape
     */
    public MoveTo(String name, int startTime, int endTime, Posn dest) {
        super(name, TransformType.MOVE, startTime, endTime);
        this.dest = dest;
    }

    @Override
    public void apply(IShape shape) throws IllegalArgumentException {
        List<Transform> opsListOnThisShape = new ArrayList<>();
        for (int i = 0; i < shape.getOperations().size(); i++) {
            if (shape.getOperations().get(i).getType()  == TransformType.MOVE) {
                opsListOnThisShape.add(shape.getOperations().get(i));
                if (this.startTime > shape.getOperations().get(i).getStartTime()
                        && this.startTime < shape.getOperations().get(i).getEndTime()) {
                    throw new IllegalArgumentException("Cannot call the same animation while in progress.");
                }
            }
        }

        // checks that operation happens after shape appears
        if (this.startTime < shape.getBirthTime()) {
            throw new IllegalArgumentException("Cannot animate shape before it appears.");
        }
        // checks that operation happens before shape disappears
        else if (this.startTime > shape.getDeathTime()) {
            throw new IllegalArgumentException("Cannot animate shape after it disappears.");
        }
        // adds shape to this operations list of shapes
        // adds this operation to the shape's list of operations
        else {
            this.opShapes.add(shape);
            shape.getOperations().add(this);
        }
    }

    @Override
    public String getDescription(AnimationModel model) throws IllegalArgumentException {
        if (this.opShapes.isEmpty()) {
            throw new IllegalArgumentException("This operation has not been used");
        } else {
            String result = getPreDescription(model.getTickRate());

            result += String.format("%d %.0f %.0f %.0f %.0f %d %d %d\n", (this.endTime / model.getTickRate()), this.dest.getX(), this.dest.getY(),
                    this.opShapes.get(0).getWidth(), this.opShapes.get(0).getHeight(),
                    this.opShapes.get(0).getColor().getRed(), this.opShapes.get(0).getColor().getGreen(), this.opShapes.get(0).getColor().getBlue());

            return result;
        }
    }

    @Override
    public String printSVG() {
        String result = "";

        result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
                + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\"" +
                " attributeName=\"" + this.opShapes.get(0).getSymbol() + "x\"" +
                " from=\"" + Integer.toString((int) this.opShapes.get(0).getPosn().getX()) +
                "\" to=\""
                + Integer.toString((int) this.dest.getX())
                + "\" fill=\"freeze\" />\n";
        result += "<animate attributeType=\"xml\" begin=\"" + Integer.toString(100 * this.startTime)
                + "ms\" dur=\"" + Integer.toString(100 * (this.endTime - this.startTime)) + "ms\" " +
                "attributeName=\"" + this.opShapes.get(0).getSymbol() + "y\"" +
                " from=\"" + Integer.toString((int) this.opShapes.get(0).getPosn().getY()) +
                "\" to=\""
                + Integer.toString((int) this.dest.getY())
                + "\" fill=\"freeze\" />\n";

        return result;
    }

    /**
     * Gets dest.
     *
     * @return dest.
     */
    public Posn getDest() {
        return this.dest;
    }


}