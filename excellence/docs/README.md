
# Design

#### Controller/View Designs
We thought it would too clunky to have the keyboard control the edit view, and have updating JLabels tell the user what to do with
the keys. So everything is done with buttons and button handlers (handlers to keep everything organized, and so that the AnimationPanel
doesn't have to be an ActionListener and only update when an ActionEvent happens). The only key event that is needed in the 'e' for 
entering keyframe editing mode.

The Controller has parsing ability for CLI. The Controller also controls mouse and keyboard actions. Although, the Controller really
only changes how the View interacts with the Model and the Animation Panel, since changes to Model are done through ActionHandlers (which are in the controller package).

The ControllableView contains all of the necessary logic for the edit view, and uses custom ActionListener class We used custom ActionListener Objects,
rather than putting everything in one Controller class because it was simpler and easier to debug. Plus, every action can act
independently of the others, which means that if one bit of functionality isn't working or isn't supported by a certain view, 
that functionality is only contained in it's own ActionListener Object, and so it doesn't have to be added in that new view.

The ControllableView and VisualView use the AnimationPanel, which is the only class that needs a sense of time. The pane loops through
a model that is given to it's view and loops through the model's shapes. The shapes generate their own state and tell the Panel how to point
them at any given time.

The TextualView and VSGView simply loop through a given model's shapes, and the shapes loop through their Transforms. The shapes and transforms
generate the text files and output those Strings to the Views for formatting.

#### AnimationModel
The AnimationModel interface contains the methods necessary of keeping track of time, storing shapes, and outputing the states of those shapes at certain times

##### ShapeFXModel
This model implements the methods of AnimationModel in ways that will reduce redundant code when different models will need to be created. It also contains
an AnimationBuilder that can be used with the AnimationReader to read in animation files.

#### IShapes
IShapes are different kinds of shapes (Oval, Rectangle) that have a list of transforms. The shapes can generate their
own state at any given time by looping through their list of transforms and generating what they would look like due to those
transforms (tweening is taken into account here)

#### Transforms
Transforms contain an apply function that transform IShapes, and also have methods for getting svg and text strings to represent
them.

# Change Log

#### Assignment 7:
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

#### Assignment 6:
We had to do major changes to our model from the last assignment.
Firstly, we made it so that "transforming" a shape doesn't mutate it, but rather it copies the shape and then mutates it.
We did this by making Transforms operate around generating transform Function objects that take in and return live shapes.
This was a very big change, but we believed that it was safer and better design, because now Shapes always know what their
states are at any given time directly (because shapes own their own Transformations).





