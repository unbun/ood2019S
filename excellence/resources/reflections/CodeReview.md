# Assignment 8 Code Review
## Carolina Mack and Unnas Hussain

Overall, the code was well documented and their design choices were, for the most part, very sound and easy to understand. However, they gave no code of documentation about the controller, or any way to interact between the view and the model. They also had some inconsistencies between how the view and the model treated shapes and animations in general, so it was very difficult to implement that bridge.


### Design Critique
The interfaces that were provided to us were flexible. Their model interface provides the capability to initialize a canvas of any size and color, add to it a shape with a name and shape type of either rectangle or ellipse, remove a shape by referring to its name, and a method that updates shapes on each tick by Tweening between keyframe states. It kept it simple, but accomplished everything it needed to in order to produce a simple animation. This model provides a good basic foundation for an animation but also provides flexibility to add more enhancements to the animation without having to change the foundation. The view interfaces were also flexible. They provided functionality to set a model, change that model, set the tick rate, add ActionListeners, change view size, update desired output path, and get a text representation of the model. I think it would be very easy to add a new type of view to this interface, as well as expand the functionality of the interface with extra bells and whistles if desired.

One good aspect of their interface design is how they had 2 model interfaces, a ReadOnlyModel and an AnimatorModel. A ReadOnlyModel only has getters so that any implementing classes can access information but cannot change or mutate anything. An AnimatorModel, on the other hand, can have shapes and keyframes added and removed to it. Their view implementations included a ReadOnlyModel so that the model could not be changed from within the view. This provided them with the necessary functionality for updating animations, but also ensured that the model data was very well protected to ensure consistency and accuracy. 

 
### Implementation Critique
Their edit view and visual view produced a good animation. Their visual view displays animations inputted through text files, and their edit view adds onto this functionality by providing the user the ability to save the animation as svg file, save as text file, input a new animation file directly through the GUI, start, restart, rewind, pause, loop, speed up, slow down, add keyframe, delete keyframe, add shape, and delete shape. The functionality of these views is not at all lacking. Another thing we appreciated about their view implementations was the way they abstracted so much duplicate code between their visual and edit views. They had their visual view act as an abstract class that the edit view extended, so that they could use the same logic to create the view visually but then just add extra functionality to their edit view as needed. This was an extremely clever way of implementing the assignment, and we wish we had done it this way from the start, as our edit view and visual view had a little bit of unnecessary overlap. 

One aspect that could have been better for the implementation of the edit view was their differentiation of events. They chose to have 1 ActionListener that handles every action, which a perfectly valid approach. But Then the view is also responsible of differentiating those events by a string (via their "getSomething method). The way they implemented this lead to a lot of confusion, and could be the source of many errors, since "getSomething" can literally reutrn any Object. A user could severly abuse this, and a customer is left having to handle many cases that they may be unfit to handle.

### Documentation Critique
Their code was extremely well-documented and well-organized for the view and model. Their controller wasn't as well documented in the README, but there were context clues. Their class and interface organization was a little unorthodox, like for example how they had two view interfaces and two model interfaces which worked together to provide full functionality. So, if direct and precise documentation had not been provided, we would have had no idea how to use their interfaces. However, they documented the quirks of their code in great detail so we understood the exact purpose of each class and method extremely well without having to ask too many questions.  Furthermore, in addition to explaining what each method or class did, they justified why that method or class was necessary. One recommendation would be to provide explanation for why certain methods return what they do, because for some methods it was unclear why the return was necessary and what it would be used for.

They did not respond to any (but one) of our emails. Even a message saying "we're not sure we can give you that information" would have been better than silence.

### Design/Code Limitations
Overall, their code was fairly flexible and convenient to reuse their code. However, it did have some limitations in reusability. Some of their methods referred to concrete classes we didn't have access to, such as Shape and KeyFrame, which forced us to  create wrappers around these classes. One specific problem we ran into was with the fillAllFrames method in their AnimatorModel interface. This method is said to update the shapes of the model at each tick by Tweening between the states of keyframes. This made sense to us, however for some unknown reason it returned an ArrayList<Shape>. So, we weren’t sure if this method was actually mutating, or rather just returning a new list of shapes on each call.  We think it would have made the code more flexible if this method were changed to a void method, because it would still accomplish the same purpose of updating the model's shapes at each call, but it wouldn't have been dependent on another concrete class.

Their model updated motions with values for a shape at t0 and t1, but their views only collected one set of values for any given shape at a time, which meant there were difficult transitions from the view to the controller to the model.






