# Change Log


### Assignment 7:
We realized that it was very difficult to have a Shape mutate, but also keep track of it's own states at every tick and its transforms.
So we simplified the shapes and abandoned of the "LiveShape" concept. Now, on their own, Shapes don't really have much functionality.
But once they add Transforms to themselves, they can actually update their states. Transforms and Shapes are now more seperated than before.
We added getDescription() and getSVG() methods to the transforms that use a shape's current state 
and the fields of the Transforms for the TextView and SVGView to use. The transforms don't actually do anything to shapes anymore. They simply add themselves to the shape's 
list of transforms, and contain some methods to help for different views. The Shapes then use those lists of transforms to get their state at any given time.

This also allowed us to have model that was universal for all views. All the model has to do now is create/remove shapes and create transforms 
(and for this assignment, it also must add/remove keyframes). The model also contains some canvas-related information.

Now, the the shape's state at any time can be found simply by accessing the shape it self, and it won't mutate or copy itself unnecessarily.
The Text and SVG Views are then simplified to simply looping through each Shape at getting their states at specific times 
(for the Textual view, the times are the start and end times of each transform. For the SVG, the times are simply the min start time and 
the max end time, so if the transforms are sorted, it is easy to get the right times).
The Visual View has the same concept of looping through each shape and getting it's state. But the Visual view now uses an AnimationPanel with it's own timer (which was
lifted from the model to the view, since only 1 view really cares about time.). The timer takes into account the tickRate
set by the model, and ticks through an animation. The Visual View is simply a wrapper for that AnimationPanel to sit in and be 
organized and configured.

### Assignment 6:
We had to do major changes to our model from the last assignment.
Firstly, we made it so that "transforming" a shape doesn't mutate it, but rather it copies the shape and then mutates it.
We did this by making Transforms operate around generating transform Function objects that take in and return live shapes.
This was a very big change, but we believed that it was safer and better design, because now Shapes always know what their
states are at any given time directly (because shapes own their own Transformations).


# AnimationModel
The AnimationModel interface contains the methods necessary of keeping track of time, storing shapes, and outputing the states of those shapes at certain times

## ShapeFXModel
This model implements the methods of AnimationModel in ways that will reduce redundant code when different models will need to be created

#### PrintFXModel
This model outputs the state of the shapes as Strings formatted by the Text format

# LiveShapes
LiveShapes are different kinds of shapes (Oval, Rectangle, Triangle) that have stored transforms. The LiveShapes are mutable, but they aren't directly mutated by the Transformations. The LiveShapes are copied and then mutated.

# Transforms
Transforms contain Functions that transform LiveShapes, and also have time-based logic for applying those function objects. The applyTimed method returns a what a given LiveShape's state would be if it were to be (or have been) transformed by this transformation at any given time.

# Class Architecture (Depreciated Thursday Mar 28th, 6:00 am)

![alt text](classDiagram.png "Animation Classes")



