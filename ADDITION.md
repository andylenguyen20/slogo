* Estimation: before looking at the old code:
    * how long do you think it will take you to complete this new feature?
    
    I think it will take about an hour to complete this new feature. 
    * how many files will you need to add or update? Why?
    
    Two new command classes will need to be added, along with the
    appropriate information about them in the current properties file
    which tells the program how many children a given node has. Methods
    in Turtle and Model will need to be added as well, to add the turtle
    to the screen and keep track of 'stamped' turtles.
    
* Review: after completing the feature:
    * how long did it take you to complete this new feature?
    
    It took about 30 minutes to implement this new feature. 
    * how many files did you need to add or update? Why?
    
    I needed to add two extra classes, Stamp and ClearStamps. Updated the numChildren properties
    file to accept both of these commands with zero children each. Went into the model class
    and allowed for it to keep track of stampedTurtles separately by adding an arrayList. 
    
    The Model class added two methods stamp() and clearStamps() which takes the current turtles
    and makes copies, adds them to the stamps list, then notify the front end observer
    and clears the list of the stamped turtles and notifies the front end observer, respectively. 
    
    The getTurtleObservables() method in Model has to be changed as well to pass the stamped turtles
    to the front end as well. In the Turtle class I added a new constructor which creates a copy of
    a given turtle. 
    
    * did you get it completely right on the first try?
    
    I did not get it completely right on the first try, as I just was adding stamped turtles to a 
    list and then notifying the front end, which was not listening to these turtles. I had to update
    the method getTurtleObservables() such that the front end could now display these new turtles.
    
    Another thing that had to be changed was that the turtle copies did not immediately have the correct
    direction, which was then implemented in the constructor, as well as removing them by setting
    their opacity to zero, as the front end cannot rid turtles from the screen if a list of turtles
    that previous contained data became empty.

* Analysis: what do you feel this exercise reveals about your project's design and documentation?

    * was it as good (or bad) as you remembered?
    
    It was about the same as I remembered. Given that I worked with the back end, I understood well
    how to implement the new commands, which were easily added by two new classes and updating the
    properties class. The Model clearly contained information that is shown on the screen so I was
    able to directly modify turtles and the Turtle class allowed for easy access of information
    to make other Turtle copies. 
    
    The front end design was better than I remembered, as I understood better how the Observers work,
    which has the Model implement Observers such that the front end can get information from it, while
    before it seemed muddled in purely the contr oller package. While it took some time to figure out how
    to inform the front end how to show stamps, it was then easy to remove and add them. 
    * what could be improved?
    
    Every command stores a Model, which could potentially be better if were to find a way to implement
    static method calls is Model that a command can directly access, as for these two commands do not
    contain any of the actual logic of stamping or clearing, but instead notifies the model to do so,
    which means that any purely model-based commands will need method in the Model class. Another option
    could be to pass a lambda to the Model such that the command could know the logic and the Model can do 
    an abstract function on a constant amount of data. 
    
    * what would it have been like if you were not familiar with the code at all?    
    
    It probably would have been quite difficult to figure out how to add the commands at first, but if 
    he/she were to look at other classes and understand how commands are done, as well as understand
    our Model-View-Controller system he/she would eventually know the steps to be taken to add the
    commands, although learning how the front end is observed of purely GUI model-based actions might have taken
    a while, as the interaction between the parts is more complicated than how the individual parts work together 
    themselves.
   