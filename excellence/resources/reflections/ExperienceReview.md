# Assignment 8 Experience Review
## Carolina Mack and Unnas Hussain


### What do you wish you had done differently in your design/implementation? Did you learn any lessons from the code given to you, or the updates you had to make for your customers?
This assignment made us realize there are many things we wish we would have done differently in our implementation of our animator. In attempting to make another view implementation work with our code, we realized that our code was very heavily based on the idea of having inputs of "Shape States" at t0 and t1, and animating around those Shape States within Shape classes. But this was very different to people who dealt with animations in terms of KeyFrames. Also, we combined our time-keeping and our shape-drawing in one class, but probably should have split them up or included them as public features of our views. However, with the Adapter pattern, it was relatively easy to remedy those issues.

One lesson we took from the code provided to us was the way they used keyframes to increase efficiency of code, which was something we struggled with in the last assignment. They updated the shapes of the animation only at times when a keyframe was present for that shape, which would have been a better way to go (in our opinion, based on the fact that the user sees animations in terms of keyframs, not indivdual shape states). Thus, in order to implement their views we were forced to adapt the way our shapes were updated and painted, but ultimately this was for the best because this was a more efficient way of moving the animation forward than we had originally. 


### What was your experience like with your providers?
Our experience with our providers could have been better. They sent us their code upon our requesting it. However, we had a few questions about why certain methods returned certain things, as it seemed to us that they would've functioned better as void methods, and the purpose of the returns was not explained in the method documentation. However, they never answered our questions. Thus, we were left guessing at their intentions with a few of their methods, and had to implement entirely new features that didn't quite work as intended.
Because of the complete lack in communication, we could discuss renaming class to avoid class conflicts or to set correct generics.

<strong> Note about the Controller/Controllable Feauters </strong>
The is the only information we got on the Controller was in the README, where it states that was that "The controller splits into both Animating Views (VisualView and EditView) and the non-animating views (SVGView and TextView) when called."
This doesn't give any information as to how the the controller handles animating views. The only clue we had is the method in the Provider's EditView "getSomething()" which seems to be where you can differentiate ActionEvents for the controller to work on the model. But again, there is no example of the controller, and we asked for the controller interface multiple times, multiple days before the deadline was closing in (we got no response to our request or to any other questions we had). Basically, we can't really tell what it means to "start" from the view's POV. So we essentially had to rebuild the controller which was difficult to do properly without any information or "customer support".

We don't mean to complain, but we feel that the complete lack of communication from the providers
put us at a very unfair disadvantage.
