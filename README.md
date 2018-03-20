# slogo_team03 README
  

###  General information

Charlie Dracos, Belanie Nagiel, Andy Nguyen, Edward Zhuang began planning for this project on February 15th. This planning process continued until February 19th, at which point we began coding the initial implementation of the project. We completed the initial implementation on February 28th, and completed the final implementation on March 10th. To complete this project, we estimate that each person spent between 40 and 50 hours.

### Roles of project members
    
Belanie worked with the team on parsing commands, creating classes for the various commands to be used as nodes in the tree, and on creating the model class that helped connect the view to the back end. Edward and Andy worked primarily on developing all aspects of the front end of this project. In addition, they helped to set up connections between the front end and back end of the program. Andy worked extensively on dividing the Controller classes, and Edward worked on implementing features such as a front end help option and palette. Charles worked with information retrieved from Belanie’s parser and helped convert this to back end data. This was done through making a tree system, nodes for inputted commands, and setting up appropriate back end classes.

### Resources used
    
To begin setting up the structure of the program, Edward and Andy looked at a a section from  Head First Design Patterns by Bert Bates, Kathy Sierra, Eric Freeman, and Elisabeth Robson about ModelViewController. In addition, Edward consulted with his TA Ben Schwennesen to better understand how to connect the Model and View in the program’s structure. Charlie spoke with his friend Conrad Mitchell. 

These online resources were used:

[Spike Slogo Example](https://coursework.cs.duke.edu/CompSci308\_2018Spring/spike\_slogo)

[Reflection Example](https://coursework.cs.duke.edu/CompSci308\_2018Spring/example\_advanced)

[Regex Patterns](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html)

[Factory Pattern](https://www.tutorialspoint.com/design\_pattern/factory\_pattern.htm)

[Reflection](http://www.oracle.com/technetwork/articles/java/javareflection-1536171.html)

[More Reflection Examples](https://stackoverflow.com/questions/23680503/how-to-reduce-the-usage-of-if-else-by-reflection-can-i-get-the-code-example)
[Throwing Exceptions](https://stackoverflow.com/questions/15417254/class-forname-throws-classnotfoundexception)

[Splitting with Regex](https://stackoverflow.com/questions/12884573/split-string-by-all-spaces-except-those-in-brackets)

[More Exceptions](https://stackoverflow.com/questions/8423700/how-to-create-a-custom-exception-type-in-java)

[Checking Classes](https://stackoverflow.com/questions/4294844/check-if-an-object-belongs-to-a-class-in-java)

  
### Files used to start the project
    

To run the program, use the Main class, found in the Controller package.

  

###   Files used to test the project and errors you expect your program to handle without crashing
    
The parser should be able to handle errors with invalid command entries and errors with the commands being entered in the wrong language. These error messages are displayed in the command history and the program will continue to accept new commands.

###  Any data or resource files required by the project (including format of non-standard files)

**Regex.properties** : This was used to create custom regex patterns for the parser to distinguish which type of Node was going to be created for the tree.

**numChildren.properties** : Helped map each command to the number of children it required to help with creation of Nodes for the tree.

In resource.languages, we have ten properties files which mapped commands to its translations in several languages. In our propertiesFiles package, we have three properties files which are responsible for containing names of front end labels and buttons. There is a fourth properties file which maps on-screen command labels to strings of actual commands which are sent to the back end. There are also 17 images used in the program, which can be found within the src of our project.

  

###  Any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)
  

The program’s title is a reference to Supreme, a fashion brand which sports a similar logo to our image.

  

###   Any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements
  

Since there were many ways to implement the “tell” command, we decided to make a flexible command which would allow the user to make turtles with specific ID numbers and set active turtles. For example, if used at the start of the program, the command “tell \[ 2 3 \]” would create two turtles with IDs 2 and 3, and set them as active.

In setting up the Palette, we decided to use images instead of shapes to represent turtles. We also felt that seven options for both colors and images would be plenty for the user, though it would not be difficult to extend the size of these lists.

###   Any known bugs, crashes, or problems with the project's functionality
    
* If an on screen turtle reaches the corner of its visual container, it has the may accidentally be positioned outside of its drawer component on the next move command.
* For unlimited commands the unlimited command object does not have a return value on its first call
* When using a specific turtle's ID to move, it cannot do so when being a child node of the non-head node

###  Any extra features included in the project
    
When making design extensions after completing our basic implementation of Slogo, we decided to include several new features, including new front end components and additional commands. On the front end side of our program, we incorporated a visual palette which contained a set of images and colors with numeric values that could be referred to by new commands. One the back end, one feature we decided to implement was the inclusion of multiple turtles. Through the command “tell”, the user is able to make additional turtles. Another additional feature we decided to implement was a toroidal drawer screen; when a turtle reaches the edge of the screen it will wrap around to the other side of the screen.  

###   Impressions of the assignment to help improve it in the future
    
Overall, we found this to be a challenging yet fun assignment to work on. We learned a lot from this project, such as how to implement a Model View Controller structure and how to develop a robust parser System. One of the biggest challenges we had was creating a flexible controller system which would not bottleneck the program. Initially, we only had a single controller class which connected to all the front end components, but we soon realized this was an inefficient and messy solution. After heavy refactoring, we finally developed a flexible hierarchy which did not depend on one god class. If we were to redo this assignment, we would take more time to fully understand how to implement the Model View Controller pattern. This would prevent the accumulation of dependencies and bottlenecks within our code design. In addition, we would try to communicate a little more between front end and back end teams to avoid accidental communication mishaps.