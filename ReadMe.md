# To do app

This app lets you create ToDo list, prioritise, sort and filter the ToDo items

## Technologies used

Java 8  
Spring boot  
Spring ReST  
Gradle 3.2.1  
AngularJs 1.5.8  
Angular UI Grid  
TDD/BDD (Cucumber)  
Junit/Mockito  
Swagger  

## Development environment

Windows 7  
IntelliJ IDE  
Chrome browser  

## Steps to build the application

Prerequisite : Java 8 and gradle 3.2.1 installed  

1) Get the source code from git https://github.com/dineshbabu/ToDoListApp
2) Use intelliJ Java IDE and add the project to the IDE to view source code
3) Open a command line window and switch to folder where build.gradle file is present.
4) To build and run tests, use the command: gradle clean build
5) To run only integration test from the IDE, run the CukeTestRunner.java class
6) To run only unit test from the IDE, run the ToDoListControllerImplTest.java class

## Steps to run the application
1) To start the application, run the java main class : ToDoApplication.java . This should start the application on port 8888
2) Fire the URL http://localhost:8888/todoapp/createItem.html which will tale you to Create ToDo Item screen.
3) On creation of an item , you will be taken to list of ToDo items (http://localhost:8888/todoapp/ListItems.html)
4) On the ToDo Items list screen you have facility to create/update an item, filter the list by status and priority. 
5) On the ToDo Items list screen you have facility to sort items by name, status and priority. 
6) Max 10 items per page. After 10 items pagination kicks in  
7) To view all the ReST end points, use the Swagger URL, http://localhost:8888/todoapp/swagger-ui.html

Note: Since no database is used, once you restart the application you will lose all data.
