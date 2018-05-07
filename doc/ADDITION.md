
  
# Estimation: before looking at the old code:  
  
* How long do you think it will take you to complete this new feature?  

 I anticipate this feature will take around 2 hours to complete.
 * How many files will you need to add or update? Why?  

I will need to add another ScreenComponent, which would be the new front end display showing the new feature. To connect this to the back end, I will need to also create another Controller class, which will be instantiated within the Workspace class. In addition, I will also need to slightly modify the GUI so it can position the new ScreenComponent, and I will need to add some additional methods to the Model class so that I can retrieve that the images of all the turtles.  



# Review: 
 
  * How long did it take you to complete this new feature?  
  
This feature took me an hour or so to complete, which was surprisingly less than I had anticipated.   
  
  * How many files did you need to add or update? Why?  
 
To complete this feature, I added a new ImageChanger ScreenComponent and a new ImageChangerController for it. These classes were created from scratch, but I was able to follow the pattern of my other ScreenComponents and Controllers. I also created an Observable interface (ImageChangerObservable), which was very similar to the DrawerObservable interface.  

In addition to these new classes, I had to edit the Model, GUI, and Workspace. The GUI and Workspace modifications were very slight to allow for the addition of the new ScreenComponent and Controller, and the Model was a little more complex, as I had to add a couple of methods that would allow me to change the color of an individual Turtle. 

* Did you get it completely right on the first try?

I was close to getting it right on my first try, but unfortunately I had accidentally called a method creating the front end component before it I had initialized the JavaFX Components. After rearranging where I initialized my StackPane, the feature was successfully added. 

# Analysis: 
* Was it as good (or bad) as you remembered?

Honestly speaking, I felt as if this exercise showed that my project's design and documentation was solid and easily understandable. Despite having not touched this project for over a month, I was easily able to pick up from where I had left off thanks to the extensive Java documentation and clear Model-View-Controller design pattern that was implemented. 
* What could be improved?

I think that one thing that could be improved is the positioning of items onto the GUI. Currently, when a new ScreenComponent is created, the GUI is responsible for checking what type of ScreenComponent it is and positioning it onto its Pane. Instead, the ScreenComponent could position itself onto the Pane through a method that takes in the Pane of the GUI. This would make the GUI much more generalized, as it would not have to determine where to position everything.

* What would it have been like if you were not familiar with the code at all?

If I were not familiar with the code at all, I think that this assignment would still have been doable, but would have taken me significantly longer. While the ScreenComponents and Controllers are fairly straightforward, it would still take some time to understand how they were positioned onto the GUI, and how they were contained within the Workspace class. Furthermore, the back end is somewhat dense, as the Model class is responsible for a variety of back end components, such as the palette and the list of turtles. Had these responsibilities been isolated further in separate classes, it may have been easier for someone unfamiliar with the code to edit it.
