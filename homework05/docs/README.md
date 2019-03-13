# AnimationModel
The AnimationModel interface contains the methods necessary of keeping track of time, adding and removing transformations.

## ShapeFXModel
This model implements the methods of AnimationModel in ways that will reduce redunandt code when different models will need to be created

### PrintFXModel
This model updates a StringBuilder/Appendable String Stream based on the Transformation states.

# LiveShapes
LiveShapes are different kinds of shapes that can be mutated, copied, or reset to their original states. LiveShapes are Comparable (using a combination of their ID Strings, and the type of shape that they are). The reset is done with reflection on the LiveShape fields.

# Transforms<T>
Transforms change/transform a given Object (of type T) at a given time. The transformation is done in it's apply method, and the time at which that transformation will actually be completed is determined by the finished() and started() methods

## InstantShapeTransform implements Transforms<LiveShape>
InstantShapeTransforms wait until their start time, then mutate their LiveShapes, store these actions in Strings, and then they won't do anything after that first call to their apply function. This means that their strBefore and strAfter accurately show what the shapes were like before and after the transformation was complete. While these mutations are instant and only happen once at the starttime, the InstantShapeTransform's finished() methods still behave according to the endTime class field.


