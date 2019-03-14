# AnimationModel
The AnimationModel interface contains the methods necessary of keeping track of time, storing shapes, and outputing the states of those shapes

## ShapeFXModel
This model implements the methods of AnimationModel in ways that will reduce redunandt code when different models will need to be created

#### PrintFXModel
This model outputs the state of the shapes as Strings formatted by the Text format

# LiveShapes
LiveShapes are different kinds of shapes (Oval, Rectangle, Triangle) that have stored transforms. The LiveShapes are mutable, but they aren't directly mutated by the Transformations. The LiveShapes are copied and then mutated.

# Transforms<T>
Transforms change/transform a given Object (of type T) at a given time. The transformation is done in it's apply method (which recieves a shape to mutate), and the time at which that transformation will actually be completed is determined by the finished() and started() methods. 

## InstantShapeTransform implements Transforms<LiveShape>
InstantShapeTransforms wait until their start time, then mutate a given LiveShapes,  and then they won't do anything to any shape after that first call to their apply function. This means that their strBefore and strAfter accurately show what the shapes were like before and after the transformation was complete. While these mutations are instant and only happen once at the starttime, the InstantShapeTransform's finished() methods still behave according to the endTime class field. The transformations store what the state of the Object was before and after the mutation. The InstantShapeTransforms are Create, MoveTo, TurnTo, ScaleBy, RecolorTo, Idle


