HOW TO USE THE EDITVIEW:

1) Input a valid command line argument with Ðedit

2) If the window is too small, drag it to be as large as you would like

a. On Sharice's computer, the animation shows as super small, but on Genevieve's it is too large,
 so we went with somewhere in the middle.

3) If the animation is being cut off by the bottom panel, this may be because your computer
is not large enough to stretch the window,
consider using a smaller frame (and shape placement) canvas.

4) Refer to buttons list below:
5) if an error message pops up and seems unreasonable just exit out and continue with animation.


BUTTONS ON GUI:

A) save svg (extra credit)

a. Saves an svg text representation of the current model being displayed (i.e. if you load a
new file, this button will print that new file's svg)
to the file SavedSVGViewFile[insert a random number].txt

a2. The console will display the name of the file to which it was saved

       b. The current view keeps running, but the file will be saved.

c. It takes a second (the larger the file the longer)! Be patient, it will show up

B) save text (extra credit)

       a. Works the same as the above button, but outputs the text representation

C) load (extra credit)

a. If you want to load a new model from scratch from an input file (changing what you put for
 Ðin in the main method), write the name of the
file in the textbox following File Name:. If this file does not exist or is not on the same
project level, nothing will happen.

b. Replaces the current model with the model described in the input file completly

D) Start

       a. Starts the animation

E) Restart

a. Restarts the animation from tick 1

F) Rewind

       a. While this button is selected, the animation will play backwards

G) pause/resume

a. While this button is selected, the animationÕs timer will stop until the button is unselected

H) Loop

       a. While this loop is selected, the animation will restart every time the animation ends.

I) speed up

       a. This increases the speed of the animation, from within the controller.

J) slow down

       a. This decreases the speed of the animation, from within the controller.



K) update keyframes

- Click, add, modify, or remove in the KeyFrameAction box

       a. If you have add selected

       i. If any t-value, shape name, or fields of a keyframe is omit from being filled, a
        pop up message will tell
 you what fields are needed for this button to work


      ii. If a shape of that name exists, and that shape has a key frame at that given time,
      then it is removed from the model and a
new key frame with your desired values is created for that time (and the key frames are
re-tweened and passed back to the view)

     iii. If no shape exists nothing happens

      iv. If the shape exists but no key frame at that time exists, one is created at that time

       b. If you have remove selected

       i. If t-value or shape name, is omit, a pop up message will tell you what fields are
        needed for this button to work

ii. If a shape of that name exists, and that shape has a key frame at that given time, then
 it is removed from the model (and the key frames
are re-tweened and passed back to the view)

iii. If no shape exists or no key frame at that time exists, nothing happens

       c. If you have ÒmodifyÓ selected

       i. Modify key frame should modify how one frame looks

       ii. It has the same behavior as add, however.

L) drop Shape

       a.write the desired name of the shape you wanna drop in the Shape Name text box.
       b. If a shape in this model with that name exists, then it will be deleted and the
       animation will restart

       c. If no shape of that name exists, nothing will happen

M) add Shape

       a. If you want to add a shape, you can select either rectangle or ellipse from the
       selection box on the bottom pane,
and write the desired name in the Shape Name text box.

       b. If you input the name of a shape that already exists, you will receive a pop
        up message and nothing will happen.

       c. Otherwise, this shape will be added to the model, but the animation will not
        pause nor restart (until you add key frames)


NOTE: we chose controller class rather than interface

DESIGN CHOICES FOR EDITVIEW AND CONTROLLER:
A) Extending visual class
a. Since both EditView and VisualView share similar functionalities, we thought it would
 be a clever idea to use the JFrame and paintComponent
method that we had already created in VisualView.
B) Having the controller implement the actionListener interface.

a. Due to the fact that the View classes should not be able to directly alter the model
itself and are therefore only given access to a ReadOnly version of it.
Therefore by implementing the action Listener inteface in the controller, we give the view
an opportunity to indirectly modify the model and send a ReadOnly version of it to the view.
b.The controller splits into both Animating Views (VisualView and EditView) and the
 non-animating views (SVGView and TextView) when called.
c.The animating views have a read only model mainly because their jobs are to visually
display and not mutate all of the shapes and motions contained in the model.
d.The non-animating view on the other hand have an output field because their job is
 also to textually display the model and also not mutate the shapes and motions
contained in the model, which is why they too also only have access to a ReadOnly model.

/*
Customer Note: this is the only information we got on the Controller, and the phrase "splits into
both Animating Views and non-animating views" doesn't give any information as to how the the controller
handles animating views. The only clue we had is the method in the Provider's EditView "getSomething"
which seems to be where you can differentiate ActionEvents for the controller to work on the model.
But again, there is no example of the controller, and we asked for the controller interface multiple
times, multiple days before the deadline was closing in (we got no response to our request or to
any other questions we had). Basically, we can't really tell what it means to "start" from the
 view's POV. So we essentially had to rebuild the controller which was difficult to
do properly without any information or "customer support".

We don't mean to complain, but we feel that the complete lack of communication from the providers
put us at a very unfair disadvantage.
*/



EXCELLENCE:
To run our program, it works to put the –in, -out, -view, -speed arguments in any order.
The input file must be on the same level as the src and test folders (it cannot be within
one of those or outside).

If the command line is malformed, the user will get an IllegalArgumentException
Types of errors a user can get from command line arguments:
-	“Must input full commands”, if they input an odd number of arguments, or if the next word
after a command is another command (i.e. –view –in)
-	“File not found”, if they name a file that is not in the correct directory or does not exist
-	“Speed is not a number” or “Speed must be greater than 0”, if the user does not input a
correct speed
-	“Must include the input file and view”, if the user does not set the minimum requires
view and input file.

Types of errors from a malformed input file:
-	“Canvas not properly assigned” if the word canvas is not followed by four numbers
-	“Shape must have a name and type” if the name is not declared correctly
-	“Not a valid command” if the input file contains any lines that start with an unknown command
-	“Must initialize canvas”, if the input file does not initialize the canvas

The user will also encounter the errors from the model (i.e. if they try to set a shape as
having a negative width)

A) Design choices:
-	If the user inputs a speed for a text or svg view, which doesn’t matter, then nothing happens
 (value is just not stored).

a.If the view is visual, then main calls fillAllFrames, which goes through all key frames within
each shape in the model’s list of shapes and “tweens” each missing tick. Once the model is
created by building (specified below), it is turned into a ReadOnlyModel, which only has the
 capability of getting info from the model and not changing it.

b.If a shape is declared incorrectly, or with a repeated name, or an invalid motion Is attempted,
 the IllegalArgumentExceptions are caught, and nothing happens or changes. However if there are
  error from the command line, then the errors are thrown to the user.

B) Model:
We did not use the given Builder interface because both of model and view act as builders
 themselves.

a.In the main, after all the command line is read in, the model will be built in createModel,
 by calling initCanvas, addAMotion or declareAShape depending on what is being read in the
 input file. The model’s canvas is initialized as having all -1 values, but in main, we make
  sure that the canvas is initialized before passing it to the view.

b.The ReadOnlyModel interface only has getters, as said above, and all of the models extend
that functionality. AbstractModelImpl is an Abstract class for models that works with both
 ReadOnly models and regular AnimatorModelImpls. Each model has an AL of shapes,
 and the canvas dimensions.

c.AnimatorModelImpl extends the abstract class (read only methods too), as well as
extending a main AnimatorModel interface that allows a user to declareAShape, addAMotion,
 and initCanvas. We added removeAShape based on the feedback from the previous assignment.

d.Look at code JavaDoc to see specific choices within each model method.

C) Shape:
a.A shape has a shape name, a shape type, and a TreeMap of KeyFrames, being sorted by their
key value which is the key frame’s tick time.
b.Shape is a general class for all shapes, because the only difference between shapes is
their visual output (what they look like when animated). If you try to add a shape with
a shapeType that is not one of the two we have, throws an error.

D) AKeyFrame:
a. AKeyframe represents the state of a shape at one given point in time.
b.We adapted how we kept track of keyFrames to be more aligned with how the input is read in.
c.We read in the first “state” from one motion line (time and values), and then seperarely
read the second state. Starting with the first, if a keyFrame already exists at the same
value nothing happens. If nothing exists in a shape’s list of key frames for a given tick,
then add it.
d.Originally we had an error thrown for malformed input like trying to add a differing
keyframe at an existing tick, but changed it as a design choice so that the whole
program wouldn’t crash on one bad line.

E) Views
a.AnimatorView interface is implemented by all 3 view types. Each view needs to be able
to accept a model, and run the view. (Accept output is overridden in visual because it
is not needed).
b.For the two views that have a text output, there is an AbstractViewOutput class.
This class holds the method, get TextRepresentation, which is overridden in both extending
classes to out put the text view and SVG view.
In the abstract class, the runView consists of getting the respective textRepresentation,
 and either outputting it to System.out or writing it to a new file.
c.For the two views that have a visual output, run View runs the Java Swing Animation.
We override paint component:
-	We collect all the states of each shape from the model to be arranged by tick, so that
 we can easily iterate through the ticks instead of the shapes.