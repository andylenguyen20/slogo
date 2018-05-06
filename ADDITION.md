### Estimation

I think it will take me about 2 hours to implement this feature, because I will have to familiarize myself more with the front end in order to add the turtle images to the view. 

I will likely need to add 2 classes and then change 3 or 4 more. I will have to add a command node class for each of the new stamp commands and then I will have to update the model class as well as a couple front end classes in order to add the turtle images to the front end view. I will also have to add the new commands to the numChildren properties file.


### Review

It took me an hour and a half to implement this new feature.

I had to add two classes for the new commands and then update 3 other classes in order to implement the functionality. I edited the numChildren file to account for the new commands and then edited the Model and Turtle classes in order to create turtle images and add them to the list of things that the front end was observing. 

No I did not get it completely right on the first try. At first I was trying to use lambdas in the Stamp class in order to loop through the turtles, but this didn't work because I could not figure out how to get a return value from the lambda function. Because of this, I had to loop through the active turtles in the command node which was bad design but was the way in which i could add the stamps and also get the return value for the last turtle. Also, it took me a while to figure out where the observable list was being created but once I found the method in the Model class it was super easy to add to. 

### Analysis

This project was as overwhelming to work with as I remembered. My team did not involve me much with the design and made decisions without really consulting the rest of the group, so it was hard for me to figure out again where different things were happening. The project is very functional which is nice for adding updates, because I could add the updates without it breaking any other part of the project.

I think the design of the multiple turtles functionality could be improved and also the readability of the code could be improved. I had to loop through the active turtles in my command node classes, because I could not use the lambda fix that was used in some of the other classes in this situation. Also, I found it rather overwhelming to go through our code and figure out what needed to be changed, because some of the classes were really really long. However, I think the front end was very encapsulated which was nice. I thought I was going to have to go through a lot of front end code to set up the observable stuff for new turtle images, but I was able to make a change in only one method in the Model class.

I think this would have been pretty difficult if I wasn't familiar with the code at all. It took me a while to figure out where to insert everything that I needed to add even as someone who was familiar with the code. We could have done a better job making classes shorter and easier to read and we also could have implemented the multiple turtle functionality in a more flexible way.

