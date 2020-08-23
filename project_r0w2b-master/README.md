# My Personal Project: A Travel Planner Application

## Created by: Antra Patel

My application is a travel planner. 
It allows you to create itineraries for trips you take around the world. 
It can be used by avid travellers to plan their trip in detail. 
This project was of interest to me because I myself am an avid traveller, having been to *25 countries by the age 18!* 
My family **loves** to travel together and more often than not we create itineraries to help us along the way. 
I wanted to create an application that could help us with this task, that would be **easy to use and visually appealing**.
I hope you find use in my project and let it help you when you're planning our trip! *Thanks!* 

## User Stories:
As a user, 
- I want to be able to create a new trip itinerary for a traveller
- I want to be able to add activities to the itinerary
- I want to be able to modify (delete items from) my itinerary
- I want to be able to view an entire trip itinerary
- I want to be able to check things off my itinerary when completed
- I want to be able to save my itinerary to file
- I want the application to load my previous itinerary data from file when the program starts

# Instructions for Grader
##**You run the program by running "Main" under ui!**
- You can generate the first required event by pressing "edit existing itinerary" on the main menu, then typing in an existing itinerary (try ones I have already saved: spain, france, moroco) hitting enter, then pressing the "add activity" button and follow the directions to add an activity to a specific day in the itinerary. (time is in military time formatted as an integer, ex. 2100) To see if it worked, navigate back to the main menu and press display with the itinerary you just edited and now you can see the new activity added to the itinerary. 

- You can generate the second required event by pressing to the "display existing itinerary" and typing in an existing itinerary (try ones I have already saved: spain, france, moroco) and pressing the enter button. this will display a pop up window of the itinerary with events added in the past. 

- You can trigger my audio component by pressing any button on the main menu, that will trigger a sound. And every "return to main menu" button on all the different frames will trigger a different sound. 

- You can save the state of my application by pressing the "save itineraries" button on the main menu. You can see the saved itineraries when you press "display existing itineraries" on the main menu and type in the name of an already created itinerary. You can create your own by following the steps of the "create new itinerary" button, and then save it using the button and quit the program and when you reopen in and display the new itinerary you created you should be able to see it in a pop up window. 

- You can reload the state of my application by pressing the load itineraries button on the main menu. The previous state of the program already loads every time you start the program though so you don't have to do this step every time. 

#Phase 4: Task 2

I chose to implement the first option of testing and designing a robust class. 
I did this in the Activity class in the model package. 
The constructor and the setTime() method throws the InvalidTimeException if the time entered is outside the range of a 24 hour military clock. 
In the ActivityTest class I test this exception thrown from both methods and test if the entered time is all correct, too high or too low.
The exception is caught in the AddActivityGUI class, which will display a popup message when caught, and otherwise proceed with adding an actvity to an itinerary. 
 
#Phase 4: Task 3

I extracted the play sound method, which had the same implementation in all the GUI classes, into an abstract class, which they all extend. 
I did this because it felt like an outlier method in the GUI classes as it has no associations with any of the other functionalities of the class, so removing it and placing it in a separate class, dedicated to playing sound, helped me achieve the single responsibility principle and increased cohesion among the GUI classes. 
This also reduced the coupling as because the code was the same in every class, if I needed to make a change to that method, I would have have originally had to go into each of the classes and changed the code, which would have been tedious and not very smart in case I forgot to update it somewhere. 
Now I can simply change it in one place: the PlaySoundGUI class.

Second I decided to implement the singleton pattern because I really only needed one instance of Traveller to be used in all the GUI classes. 
Being able to create other instances would mess up the whole point of the program, so I'm glad we learned that in time for me to refactor my code to include it. 
At first I was passing the one traveller created in the TravellerGUI class to all the other classes every time a new panel was needed, but now instead the private field in the classes calls Traveller.getInstance() instead in order to get the one instance. 
This is beneficial, because it allows me to make sure that the GUI classes are never called with a null traveller object, or one without all the previous saved information from other panels. 
In the singleton class, Traveller, I made the constructor private and added the getInstance() method as specified in lecture. 
This pattern allows me to have a global point of access to the object. 